package ba.unsa.etf.zavrsni.server.requests;

import javax.validation.constraints.NotNull;

public class PasswordChangeRequest {
    @NotNull
    private String oldPassword;
    @NotNull
    private String newPassword;
    @NotNull
    private String answer;

    public PasswordChangeRequest(@NotNull String oldPassword, @NotNull String newPassword, @NotNull String answer) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.answer = answer;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
