package ba.unsa.etf.zavrsni.server.requests;

import javax.validation.constraints.NotBlank;

public class RecoverRequest {
    @NotBlank
    private String usernameOrEmail;

    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }
}
