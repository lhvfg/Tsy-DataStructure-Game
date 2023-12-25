package com.tsy.game.domain;

import lombok.Data;

@Data
public class Result {
    private String status;
    private String userName;
    private Integer succeedNum;

    public Result(String status) {
        this.status = status;
    }

    public Result(Integer succeedNum) {
        this.succeedNum = succeedNum;
    }

    public Result(String status, Integer succeedNum) {
        this.status = status;
        this.succeedNum = succeedNum;
    }

    public Result(String status, String userName, Integer succeedNum) {
        this.status = status;
        this.userName = userName;
        this.succeedNum = succeedNum;
    }

    public Result() {
    }
}

