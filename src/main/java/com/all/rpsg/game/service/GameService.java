package com.all.rpsg.game.service;

import java.util.List;

import com.all.rpsg.game.enums.MoveEnum;
import com.all.rpsg.game.model.Game;;

public interface GameService {

    Game play(MoveEnum userMove);

    List<Game> findLastFiveWinLostGames();

}