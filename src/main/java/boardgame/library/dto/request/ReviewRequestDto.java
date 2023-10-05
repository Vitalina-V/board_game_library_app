package boardgame.library.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ReviewRequestDto {
    @NotNull
    private Long boardGameId;
    @Size(min = 4, max = 150)
    private String textReview;
    @Min(1)
    @Max(5)
    private int rate;
}
