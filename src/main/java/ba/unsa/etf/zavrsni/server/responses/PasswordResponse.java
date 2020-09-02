package ba.unsa.etf.zavrsni.server.responses;

public class PasswordResponse {
    private Boolean success;
    private String password;

    public PasswordResponse(Boolean success, String password) {
        this.success = success;
        this.password = password;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
