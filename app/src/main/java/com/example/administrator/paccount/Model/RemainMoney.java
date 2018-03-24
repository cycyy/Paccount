package com.example.administrator.paccount.Model;

/**
 * Created by Administrator on 2018/3/21.
 */

public class RemainMoney {
    /**
     * money : 0.0
     * state : 1
     * msg : OK
     */

    private double money;
    private int state;
    private String msg;

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
