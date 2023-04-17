package com.all.rpsg.game.enums;

import java.util.Random;

public enum MoveEnum {

    ROCK(0), PAPER(1), SCISSORS(2);

    private int move;

    MoveEnum(int move) {
        this.move = move;
    }

    public int getMove() {
        return move;
    }

    public static MoveEnum getRandomMove() {
        MoveEnum[] moves = MoveEnum.values();
        Random random = new Random();
        return moves[random.nextInt(moves.length)];
    }

}
