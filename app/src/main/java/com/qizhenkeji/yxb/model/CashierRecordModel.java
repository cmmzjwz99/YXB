package com.qizhenkeji.yxb.model;

/**
 * Created by hc101 on 18/6/16.
 */

public class CashierRecordModel {
    //交易金额 手续费 到账金额 交易时间 交易类型 交易状态
    public int id;
    public String lines = "+300.00";
    public String fees = "-3.00";
    public String  reality_lines = "+295.00";
    public String time = "2018-06-15 12:23:34";
    public String type = "小额积分";
    public int status = 0;
}
