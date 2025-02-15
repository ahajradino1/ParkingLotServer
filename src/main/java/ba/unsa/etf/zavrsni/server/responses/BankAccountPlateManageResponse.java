package ba.unsa.etf.zavrsni.server.responses;

public class BankAccountPlateManageResponse {
    private Boolean success;
    private String text;

    public BankAccountPlateManageResponse(Boolean success, String text) {
        this.success = success;
        this.text = text;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
