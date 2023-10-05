package boardgame.library.dto.response;

import lombok.Data;

@Data
public class CartItemDto {
    private Long id;
    private Long boardGameId;
    private String boardGameTitle;
}
