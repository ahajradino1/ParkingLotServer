package ba.unsa.etf.zavrsni.server.controllers;

import ba.unsa.etf.zavrsni.server.availability.UserIdentityAvailability;
import ba.unsa.etf.zavrsni.server.exceptions.ResourceNotFoundException;
import ba.unsa.etf.zavrsni.server.models.ApplicationUser;
import ba.unsa.etf.zavrsni.server.repositories.ApplicationUserRepository;
import ba.unsa.etf.zavrsni.server.responses.UserProfileResponse;
import ba.unsa.etf.zavrsni.server.responses.UserSummaryResponse;
import ba.unsa.etf.zavrsni.server.security.CurrentUser;
import ba.unsa.etf.zavrsni.server.security.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    private final ApplicationUserRepository applicationUserRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('ROLE_USER')")
    public UserSummaryResponse getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        return new UserSummaryResponse(currentUser.getId(), currentUser.getFirstName(), currentUser.getLastName(), currentUser.getUsername(), currentUser.getEmail());
    }

    @GetMapping("/user/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = !applicationUserRepository.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/user/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !applicationUserRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/users/{username}")
    public UserProfileResponse getUserProfile(@PathVariable(value = "username") String username) {
        ApplicationUser user = applicationUserRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException(username + " not found"));

        UserProfileResponse userProfile = new UserProfileResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getUsername(), user.getEmail(), user.getCreatedAt());

        return userProfile;
    }
}
