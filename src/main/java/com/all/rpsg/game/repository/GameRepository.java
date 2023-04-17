package com.all.rpsg.game.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.all.rpsg.game.enums.ResultEnum;
import com.all.rpsg.game.model.Game;

public interface GameRepository extends CrudRepository<Game, Long> {
    List<Game> findTop5ByResultInOrderByIdDesc(List<ResultEnum> results);
}