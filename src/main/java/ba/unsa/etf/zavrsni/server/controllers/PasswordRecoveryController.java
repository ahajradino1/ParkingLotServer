package ba.unsa.etf.zavrsni.server.controllers;

import ba.unsa.etf.zavrsni.server.exceptions.ResourceNotFoundException;
import ba.unsa.etf.zavrsni.server.models.ApplicationUser;
import ba.unsa.etf.zavrsni.server.models.Question;
import ba.unsa.etf.zavrsni.server.requests.PasswordRecoveryRequest;
import ba.unsa.etf.zavrsni.server.requests.RecoverRequest;
import ba.unsa.etf.zavrsni.server.responses.PasswordResponse;
import ba.unsa.etf.zavrsni.server.service.ApplicationUserService;
import ba.unsa.etf.zavrsni.server.util.PasswordGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/recover")
public class PasswordRecoveryController {
    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserService applicationUserService;

    public PasswordRecoveryController(PasswordEncoder passwordEncoder, ApplicationUserService applicationUserService) {
        this.passwordEncoder = passwordEncoder;
        this.applicationUserService = applicationUserService;
    }

    @PostMapping("/securityquestion")
    public Question getSecurityQuestion(@Valid @RequestBody RecoverRequest recoverRequest) {
        ApplicationUser user = applicationUserService.getUserByUsernameOrEmail(recoverRequest.getUsernameOrEmail());
        if(user==null)
            throw new ResourceNotFoundException("User with credentials " + recoverRequest.getUsernameOrEmail() + " does not exist");
        return user.getAnswer().getQuestion();
    }

    @PostMapping("/newPassword")
    public PasswordResponse getNewPassword(@Valid @RequestBody PasswordRecoveryRequest passwordRecoveryRequest) {
        ApplicationUser applicationUser = applicationUserService.getUserByUsernameOrEmail(passwordRecoveryRequest.getUsernameOrEmail());
        if(applicationUser == null)
            throw new ResourceNotFoundException("User with credentials " + passwordRecoveryRequest.getUsernameOrEmail() + " does not exist.");
        if(passwordRecoveryRequest.getAnswer().equals(applicationUser.getAnswer().getText())) {
            PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder().useDigits(true).useLower(true).useUpper(true).build();
            String password = passwordGenerator.generate(8);
            applicationUser.setPassword(passwordEncoder.encode(password));
            applicationUserService.save(applicationUser);
            return new PasswordResponse(true, password);
        }
        return new PasswordResponse(false, "");
    }
}
