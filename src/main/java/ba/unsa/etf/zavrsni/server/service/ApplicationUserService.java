package ba.unsa.etf.zavrsni.server.service;

import ba.unsa.etf.zavrsni.server.exceptions.ResourceNotFoundException;
import ba.unsa.etf.zavrsni.server.models.ApplicationUser;
import ba.unsa.etf.zavrsni.server.repositories.ApplicationUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationUserService {
    private final ApplicationUserRepository applicationUserRepository;

    public ApplicationUserService(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    public ApplicationUser find(Long userId) {
        return applicationUserRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
    }

    public List<ApplicationUser> getAll() {
        return applicationUserRepository.findAll();
    }

    public ApplicationUser save(ApplicationUser applicationUser) {
        return applicationUserRepository.save(applicationUser);
    }

    public ApplicationUser getUserByUsernameOrEmail(String usernameOrEmail) {
        ApplicationUser user;
        if(!applicationUserRepository.existsByUsername(usernameOrEmail)) {
            if(!applicationUserRepository.existsByEmail(usernameOrEmail))
                return null;
            else
                user = applicationUserRepository.findByEmail(usernameOrEmail).get();
        } else
            user = applicationUserRepository.findByUsername(usernameOrEmail).get();
        return user;
    }
}
