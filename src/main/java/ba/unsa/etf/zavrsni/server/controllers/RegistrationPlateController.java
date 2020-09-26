package ba.unsa.etf.zavrsni.server.controllers;

import ba.unsa.etf.zavrsni.server.exceptions.ResourceNotFoundException;
import ba.unsa.etf.zavrsni.server.models.*;
import ba.unsa.etf.zavrsni.server.requests.RegistrationPlateRequest;
import ba.unsa.etf.zavrsni.server.responses.BankAccountPlateManageResponse;
import ba.unsa.etf.zavrsni.server.security.CurrentUser;
import ba.unsa.etf.zavrsni.server.security.UserPrincipal;
import ba.unsa.etf.zavrsni.server.service.RegistrationPlateService;
import ba.unsa.etf.zavrsni.server.service.RegistrationPlateUserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/plates")
public class RegistrationPlateController {
    private final RegistrationPlateService registrationPlateService;
    private final RegistrationPlateUserService registrationPlateUserService;

    public RegistrationPlateController(RegistrationPlateService registrationPlateService, RegistrationPlateUserService registrationPlateUserService) {
        this.registrationPlateService = registrationPlateService;
        this.registrationPlateUserService = registrationPlateUserService;
    }

    @GetMapping("/all")
    public List<RegistrationPlate> getAllPlates(@CurrentUser UserPrincipal currentUser) {
        return registrationPlateUserService.findRegistrationTables(currentUser.getId());
    }

    @PostMapping("/add")
    public BankAccountPlateManageResponse addRegistrationPlate(@Valid @RequestBody RegistrationPlateRequest registrationPlateRequest, @CurrentUser UserPrincipal currentUser) {
        List<RegistrationPlate> registrationPlates = registrationPlateService.findByRegistrationNumber(registrationPlateRequest.getRegistrationNumber());

        if(!registrationPlates.isEmpty() && !registrationPlateUserService.findAllByRegistrationPlate_Number(registrationPlates.get(0).getRegistrationNumber()).isEmpty())
            throw new ResourceNotFoundException("Registration plates are already added!");

        RegistrationPlateUser registrationPlateUser = new RegistrationPlateUser();
        ApplicationUser user = new ApplicationUser();
        user.setId(currentUser.getId());
        registrationPlateUser.setApplicationUser(user);
        RegistrationPlate addedPlate = new RegistrationPlate(registrationPlateRequest.getRegistrationNumber());
        registrationPlateUser.setRegistrationPlate(addedPlate);
        registrationPlateService.save(addedPlate);
        registrationPlateUserService.save(registrationPlateUser);

        return new BankAccountPlateManageResponse(true, "Succefully added registration plates.");
    }

    @DeleteMapping("/delete/{platesId}")
    public BankAccountPlateManageResponse deleteRegistrationTable(@PathVariable Long platesId, @CurrentUser UserPrincipal currentUser) {
        if(!registrationPlateUserService.existsByIdAndUserId(platesId, currentUser.getId())) {
            return new BankAccountPlateManageResponse(false, "Registration plates don't exist!");
        }
        registrationPlateUserService.delete(platesId);
        registrationPlateService.delete(platesId);
        return new BankAccountPlateManageResponse(true, "Successful deletion!");
    }
}
