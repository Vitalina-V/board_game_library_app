package boardgame.library.controller;

import boardgame.library.dto.request.BoardGameRequestDto;
import boardgame.library.dto.response.BoardGameDto;
import boardgame.library.service.BoardGameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Board game management",
        description = "Endpoints for managing board games")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/boardgames")
public class BoardGameController {
    private final BoardGameService boardGameService;

    @GetMapping
    @Operation(summary = "Get all board gamed",
            description = "Get list of all available board games")
    public List<BoardGameDto> findAll(Pageable pageable) {
        return boardGameService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find board game by id", description = "Find board game by id")
    @PreAuthorize("hasAuthority('ADMIN')")
    public BoardGameDto findById(@PathVariable Long id) {
        return boardGameService.findById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new board game", description = "Create a new board game")
    @PreAuthorize("hasAuthority('ADMIN')")
    public BoardGameDto save(@RequestBody @Valid BoardGameRequestDto requestDto) {
        return boardGameService.save(requestDto);
    }

    @GetMapping("/by-title")
    @Operation(summary = "Find a board game by title", description = "Find a board game by title")
    public List<BoardGameDto> getAllByTitle(@RequestParam String title) {
        return boardGameService.findAllByTitleContainsIgnoreCase(title);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a board game", description = "Delete a board game by id")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void delete(@PathVariable Long id) {
        boardGameService.deleteById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a board game", description = "Update a board game by id")
    @PreAuthorize("hasAuthority('ADMIN')")
    public BoardGameDto update(@PathVariable Long id,
                               @RequestBody @Valid BoardGameRequestDto requestDto) {
        return boardGameService.update(id, requestDto);
    }
}
