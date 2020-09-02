package ba.unsa.etf.zavrsni.server.controllers;


import ba.unsa.etf.zavrsni.server.models.ApplicationUser;
import ba.unsa.etf.zavrsni.server.models.Question;
import ba.unsa.etf.zavrsni.server.requests.PasswordChangeRequest;
import ba.unsa.etf.zavrsni.server.responses.ApiResponse;
import ba.unsa.etf.zavrsni.server.security.CurrentUser;
import ba.unsa.etf.zavrsni.server.security.UserPrincipal;
import ba.unsa.etf.zavrsni.server.service.ApplicationUserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/change")
public class PasswordChangeController {
    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserService applicationUserService;
    private final AuthenticationManager authenticationManager;

    public PasswordChangeController(PasswordEncoder passwordEncoder, ApplicationUserService applicationUserService, AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.applicationUserService = applicationUserService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/securityquestion")
    public Question getSecurityQuestions(@CurrentUser UserPrincipal currentUser) {
        ApplicationUser applicationUser = applicationUserService.find(currentUser.getId());
        return applicationUser.getAnswer().getQuestion();
    }

    @PostMapping("/newPassword")
    public ApiResponse getNewPassword(@Valid @RequestBody PasswordChangeRequest passwordChangeRequest, @CurrentUser UserPrincipal currentUser) {
        ApplicationUser applicationUser = applicationUserService.find(currentUser.getId());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(currentUser.getUsername(), passwordChangeRequest.getOldPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        if(passwordChangeRequest.getAnswer().equals(applicationUser.getAnswer().getText())) {
            applicationUser.setPassword(passwordEncoder.encode(passwordChangeRequest.getNewPassword()));
            applicationUserService.save(applicationUser);
            return new ApiResponse(true, "Password changed successfully!");
        }
        return new ApiResponse(false, "Password change failed! Wrong answer!");
    }
}
