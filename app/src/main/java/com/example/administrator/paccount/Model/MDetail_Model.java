package com.example.administrator.paccount.Model;

import java.util.List;

/**
 * Created by Administrator on 2018/3/21.
 */

public class MDetail_Model {
    /**
     * list : [{"id":1,"userid":1,"addmoney":0,"submoney":120,"paykind":"食品酒水","remark":"吃饭","paydate":"2018-03-21 16:58:53"},{"id":2,"userid":1,"addmoney":0,"submoney":12,"paykind":"行车交通","remark":"打车","paydate":"2018-03-21 17:20:52"}]
     * listsize : 2
     * alladd : 0.0
     * allsub : 132.0
     * state : 1
     * msg : OK
     */

    private int listsize;
    private double alladd;
    private double allsub;
    private int state;
    private String msg;
    private List<ListBean> list;

    public int getListsize() {
        return listsize;
    }

    public void setListsize(int listsize) {
        this.listsize = listsize;
    }

    public double getAlladd() {
        return alladd;
    }

    public void setAlladd(double alladd) {
        this.alladd = alladd;
    }

    public double getAllsub() {
        return allsub;
    }

    public void setAllsub(double allsub) {
        this.allsub = allsub;
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

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 1
         * userid : 1
         * addmoney : 0.0
         * submoney : 120.0
         * paykind : 食品酒水
         * remark : 吃饭
         * paydate : 2018-03-21 16:58:53
         */

        private int id;
        private int userid;
        private double addmoney;
        private double submoney;
        private String paykind;
        private String remark;
        private String paydate;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public double getAddmoney() {
            return addmoney;
        }

        public void setAddmoney(double addmoney) {
            this.addmoney = addmoney;
        }

        public double getSubmoney() {
            return submoney;
        }

        public void setSubmoney(double submoney) {
            this.submoney = submoney;
        }

        public String getPaykind() {
            return paykind;
        }

        public void setPaykind(String paykind) {
            this.paykind = paykind;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getPaydate() {
            return paydate;
        }

        public void setPaydate(String paydate) {
            this.paydate = paydate;
        }
    }
}
