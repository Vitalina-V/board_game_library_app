package boardgame.library.dto.response;

import java.util.Date;
import lombok.Data;

@Data
public class ReviewDto {
    private Long id;
    private Long userId;
    private Long boardGameId;
    private String textReview;
    private int rate;
    private Date creationDate;
}
