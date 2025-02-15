package ba.unsa.etf.zavrsni.server.util;

public enum PaymentStatus {
    SUFFICIENT_FUNDS ("SUFFICIENT_FUNDS"),
    PROBLEM ("PROBLEM"),
    CANCELED ("CANCELED"),
    PAID ("PAID"),
    INSUFFICIENT_FUNDS ("INSUFFICIENT_FUNDS"),
    INVALIDATED("INVALIDATED"),
    PENDING("PENDING"),
    INVALID_DATA("INVALID_DATA");

    private String status;

    PaymentStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
