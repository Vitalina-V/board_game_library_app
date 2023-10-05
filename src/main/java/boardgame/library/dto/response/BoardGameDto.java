package boardgame.library.dto.response;

import lombok.Data;

@Data
public class BoardGameDto {
    private Long id;
    private String title;
    private String category;
    private int duration;
    private int minPlayers;
    private int maxPlayers;
    private String description;
    private String coverImage;

}
