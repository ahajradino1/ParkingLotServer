package ba.unsa.etf.zavrsni.server.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.OptBoolean;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class ActiveTicketRequest {
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="Europe/Sarajevo")
    private Date currentDate;

    public ActiveTicketRequest() {
    }

    public ActiveTicketRequest(Date currentDate) {
        this.currentDate = currentDate;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }
}
