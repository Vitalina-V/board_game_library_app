package boardgame.library.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BoardGameRequestDto {
    @NotNull
    private String title;
    @NotNull
    private String category;
    @NotNull
    private int duration;
    @Min(1)
    private int minPlayers;
    private int maxPlayers;
    @Size(min = 10)
    private String description;
    private String coverImage;
}
