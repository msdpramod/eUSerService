package Models;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter@Setter
public class Sessions {
    private String token;
    @ManyToOne
    private User user;
    private Date created_at;
    private Data expires_at;
    @Enumerated(value = EnumType.ORDINAL)
    private SessionStatus status;
}
