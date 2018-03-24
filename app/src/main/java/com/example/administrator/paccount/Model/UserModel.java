package com.example.administrator.paccount.Model;

/**
 * Created by Administrator on 2018/3/20.
 */

public class UserModel {

    /**
     * state : 1
     * msg : OK
     * id : 2
     */

    private int state;
    private String msg;
    private int id;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
