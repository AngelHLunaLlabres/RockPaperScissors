package com.all.rpsg.game.dto;

import com.all.rpsg.game.enums.MoveEnum;
import com.all.rpsg.game.enums.ResultEnum;

public class GameDto {

    private MoveEnum userMove;
    private MoveEnum computerMove;
    private ResultEnum result;

    public MoveEnum getUserMove() {
        return userMove;
    }

    public void setUserMove(MoveEnum userMove) {
        this.userMove = userMove;
    }

    public MoveEnum getComputerMove() {
        return computerMove;
    }

    public void setComputerMove(MoveEnum computerMove) {
        this.computerMove = computerMove;
    }

    public ResultEnum getResult() {
        return result;
    }

    public void setResult(ResultEnum result) {
        this.result = result;
    }

}
