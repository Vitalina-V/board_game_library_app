package boardgame.library.dto.response;

import java.util.Date;
import java.util.Set;
import lombok.Data;

@Data
public class ReservationDto {
    private Long id;
    private Long userId;
    private Set<ReservationItemDto> reservationItems;
    private Date reservationDateTime;
}
