package com.kazuha.controller.utils;

import lombok.Data;

@Data
public class ResultsForFront {
    private boolean flag;
    private Object data;
    private String msg;

    public ResultsForFront(boolean flag) {
        this.flag = flag;
    }

    public ResultsForFront(boolean flag, Object data) {
        this.flag = flag;
        this.data = data;
    }

    public ResultsForFront(boolean flag, String msg) {
        this.flag = flag;
        this.msg = msg;
    }

    public ResultsForFront(String msg) {
        this.msg = msg;
    }
}
