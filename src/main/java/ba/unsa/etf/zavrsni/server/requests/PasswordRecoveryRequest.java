package ba.unsa.etf.zavrsni.server.requests;

import javax.validation.constraints.NotBlank;

public class PasswordRecoveryRequest {
    @NotBlank
    private String usernameOrEmail;

    @NotBlank
    private String answer;

    public PasswordRecoveryRequest(@NotBlank String usernameOrEmail, @NotBlank String answer) {
        this.usernameOrEmail = usernameOrEmail;
        this.answer = answer;
    }

    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
