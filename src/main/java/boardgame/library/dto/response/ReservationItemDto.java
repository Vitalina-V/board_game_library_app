package boardgame.library.dto.response;

import lombok.Data;

@Data
public class ReservationItemDto {
    private Long id;
    private Long boardGameId;
    private String gameTitle;
}
