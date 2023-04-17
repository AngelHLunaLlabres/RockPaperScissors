package com.all.rpsg.game.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.all.rpsg.game.dto.GameDto;
import com.all.rpsg.game.enums.MoveEnum;
import com.all.rpsg.game.model.Game;
import com.all.rpsg.game.service.GameService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Game", description = "API of the Game")
@RequestMapping(value = "/game")
@RestController
@CrossOrigin(origins = "*")
public class GameController {

    private GameService gameService;

    private DozerBeanMapper mapper;

    public GameController(GameService gameService, DozerBeanMapper mapper) {
        this.gameService = gameService;
        this.mapper = mapper;
    }

    /**
     * Method to recover las Win or Lost games
     *
     * @return {@link List} de {@link GameDto}
     */
    @Operation(summary = "Find", description = "Method that returns a list of last Win o Lost games")
    @RequestMapping(path = "/findLastFiveWinLostGames", method = RequestMethod.GET)
    public List<GameDto> findLastWinLostGames() {
        List<Game> categories = this.gameService.findLastFiveWinLostGames();
        return categories.stream().map(game -> mapper.map(game, GameDto.class)).collect(Collectors.toList());
    }

    /**
     * Method to play the game
     *
     * @param userMove {@link MoveEnum}
     *
     * @return {@link GameDto}
     */
    @Operation(summary = "Play", description = "Method that calculates the play result and saves it")
    @RequestMapping(path = "/{userMove}", method = RequestMethod.PUT)
    public GameDto save(@PathVariable(name = "userMove", required = true) MoveEnum userMove) {
        return mapper.map(this.gameService.play(userMove), GameDto.class);
    }

}