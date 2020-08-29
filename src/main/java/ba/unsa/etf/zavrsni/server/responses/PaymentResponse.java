package ba.unsa.etf.zavrsni.server.responses;

import ba.unsa.etf.zavrsni.server.util.PaymentStatus;

public class PaymentResponse {
    private PaymentStatus paymentStatus;
    private String message;

    public PaymentResponse() {
    }

    public PaymentResponse(PaymentStatus paymentStatus, String message) {
        this.paymentStatus = paymentStatus;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
