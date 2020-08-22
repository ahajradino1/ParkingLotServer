package ba.unsa.etf.zavrsni.server.requests;

public class RegistrationPlateRequest {
    private String registrationNumber;

    public RegistrationPlateRequest() {
    }

    public RegistrationPlateRequest(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }
}
