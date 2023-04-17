package com.all.rpsg.game.enums;

public enum ResultEnum {

    TIE(0), WIN(1), LOSE(2);

    private int result;

    ResultEnum(int result) {
        this.result = result;
    }

    public int getResult() {
        return result;
    }

}
