package com.all.rpsg.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.all.rpsg.game.enums.MoveEnum;
import com.all.rpsg.game.enums.ResultEnum;
import com.all.rpsg.game.model.Game;
import com.all.rpsg.game.repository.GameRepository;
import com.all.rpsg.game.service.GameServiceImpl;

@ExtendWith(MockitoExtension.class)
public class GameTest {

    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private GameServiceImpl gameService;

    @Test
    public void findAllShouldReturnAllGames() {

        List<Game> list = new ArrayList<>();
        list.add(mock(Game.class));

        when(gameRepository.findTop5ByResultInOrderByIdDesc(List.of(ResultEnum.LOSE, ResultEnum.WIN))).thenReturn(list);

        List<Game> games = gameService.findLastFiveWinLostGames();

        assertNotNull(games);
        assertEquals(1, games.size());
    }

    @Test
    public void playGameShouldBeSaved() {

        ArgumentCaptor<Game> game = ArgumentCaptor.forClass(Game.class);

        gameService.play(MoveEnum.PAPER);

        verify(gameRepository).save(game.capture());

    }

}