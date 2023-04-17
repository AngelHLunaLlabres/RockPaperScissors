package com.all.rpsg.game.service;

import java.util.List;
import java.util.function.BiPredicate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.all.rpsg.game.enums.MoveEnum;
import com.all.rpsg.game.enums.ResultEnum;
import com.all.rpsg.game.model.Game;
import com.all.rpsg.game.repository.GameRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class GameServiceImpl implements GameService {

    private GameRepository gamesRepository;

    public GameServiceImpl(GameRepository gamesRepository) {
        this.gamesRepository = gamesRepository;
    }

    Logger logger = LogManager.getLogger(com.all.rpsg.game.service.GameServiceImpl.class);

    @Override
    public List<Game> findLastFiveWinLostGames() {
        logger.debug("Finding last saved games");
        return (List<Game>) this.gamesRepository
                .findTop5ByResultInOrderByIdDesc(List.of(ResultEnum.LOSE, ResultEnum.WIN));
    }

    @Override
    public Game play(MoveEnum userMove) {
        logger.debug("Playing");
        MoveEnum computerMove = MoveEnum.getRandomMove();
        ResultEnum result = calculateGameResult(userMove, computerMove);

        Game game = new Game();
        game.setUserMove(userMove);
        game.setComputerMove(computerMove);
        game.setResult(result);

        logger.debug("Saving game. UserMove: {}, ComputerMove: {}, Result: {}", game.getUserMove(),
                game.getComputerMove(), game.getResult());
        return this.gamesRepository.save(game);
    }

    private ResultEnum calculateGameResult(MoveEnum userMove, MoveEnum computerMove) {
        if (userMove.getMove() == computerMove.getMove()) {
            return ResultEnum.TIE;
        } else if (userWinsComputer(userMove, computerMove)) {
            return ResultEnum.WIN;
        }
        return ResultEnum.LOSE;
    }

    private boolean userWinsComputer(MoveEnum userMove, MoveEnum computerMove) {
        BiPredicate<MoveEnum, MoveEnum> paperWinsRock = (user, computer) -> MoveEnum.PAPER.equals(user)
                && MoveEnum.ROCK.equals(computer);
        BiPredicate<MoveEnum, MoveEnum> rockWinsScissors = (user, computer) -> MoveEnum.ROCK.equals(user)
                && MoveEnum.SCISSORS.equals(computer);
        BiPredicate<MoveEnum, MoveEnum> scissorsWinsPaper = (user, computer) -> MoveEnum.SCISSORS.equals(user)
                && MoveEnum.PAPER.equals(computer);
        return paperWinsRock.or(rockWinsScissors).or(scissorsWinsPaper).test(userMove, computerMove);
    }

}