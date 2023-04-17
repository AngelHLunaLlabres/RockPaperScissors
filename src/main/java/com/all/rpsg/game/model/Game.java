package com.all.rpsg.game.model;

import java.time.LocalDateTime;

import com.all.rpsg.game.enums.MoveEnum;
import com.all.rpsg.game.enums.ResultEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "game_result")
public class Game {

    @PrePersist
    public void prePersist() {
        this.playDateTime = LocalDateTime.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "userMove", nullable = false)
    private MoveEnum userMove;

    @Column(name = "computerMove", nullable = false)
    private MoveEnum computerMove;

    @Column(name = "result", nullable = false)
    private ResultEnum result;

    @Column(name = "playDateTime", nullable = false)
    private LocalDateTime playDateTime;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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