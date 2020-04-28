package ba.unsa.etf.zavrsni.server.controllers;

import ba.unsa.etf.zavrsni.server.exceptions.AppException;
import ba.unsa.etf.zavrsni.server.models.ApplicationUser;
import ba.unsa.etf.zavrsni.server.models.Question;
import ba.unsa.etf.zavrsni.server.models.auth.Role;
import ba.unsa.etf.zavrsni.server.models.auth.RoleName;
import ba.unsa.etf.zavrsni.server.repositories.ApplicationUserRepository;
import ba.unsa.etf.zavrsni.server.repositories.RoleRepository;
import ba.unsa.etf.zavrsni.server.requests.LoginRequest;
import ba.unsa.etf.zavrsni.server.requests.SignUpRequest;
import ba.unsa.etf.zavrsni.server.responses.ApiResponse;
import ba.unsa.etf.zavrsni.server.responses.JwtAuthenticationResponse;
import ba.unsa.etf.zavrsni.server.security.JwtTokenProvider;
import ba.unsa.etf.zavrsni.server.service.AnswerService;
import ba.unsa.etf.zavrsni.server.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final ApplicationUserRepository applicationUserRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;
    private final QuestionService questionService;
    private final AnswerService answerService;

    public AuthController(AuthenticationManager authenticationManager, ApplicationUserRepository applicationUserRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtTokenProvider tokenProvider, QuestionService questionService, AnswerService answerService) {
        this.authenticationManager = authenticationManager;
        this.applicationUserRepository = applicationUserRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.questionService = questionService;
        this.answerService = answerService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup/{questionId}")
    public ResponseEntity<?> registerUser(@PathVariable Long questionId, @Valid @RequestBody SignUpRequest signUpRequest) {
        if(applicationUserRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(new ApiResponse(false, "Username is already taken!"), HttpStatus.BAD_REQUEST);
        }

        if(applicationUserRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(new ApiResponse(false, "Email address is already taken!"), HttpStatus.BAD_REQUEST);
        }

        if(signUpRequest.getAnswer() == null || signUpRequest.getAnswer().getText().isEmpty()) {
            return new ResponseEntity<>(new ApiResponse(false, "Answer text must not be empty!"), HttpStatus.BAD_REQUEST);
        }

        ApplicationUser user = new ApplicationUser(signUpRequest.getFirstName(), signUpRequest.getLastName(), signUpRequest.getEmail(), signUpRequest.getUsername(), signUpRequest.getPassword(), signUpRequest.getAnswer());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(() -> new AppException("User Role not set."));
        user.setRoles(Collections.singleton(userRole));

        if(!questionService.existsById(questionId)) {
            return new ResponseEntity<>(new ApiResponse(false, "Question with given id does not exist!"), HttpStatus.BAD_REQUEST);
        }

        Question question = questionService.findById(questionId).get();
        user.getAnswer().setQuestion(question);
        answerService.save(user.getAnswer());
        ApplicationUser result = applicationUserRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{username}").buildAndExpand(result.getUsername()).toUri();
        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully!"));

    }
}
