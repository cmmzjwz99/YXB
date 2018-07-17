package com.qizhenkeji.yxb.model;

/**
 * Created by hc101 on 18/6/16.
 */

public class PaymentDetailModel extends BaseModel {

    public int id;
    public String time;
    public String type;
    public String lines;
    public String fees;

    public PaymentDetailModel(int id, String type, String time, String lines, String fees) {
        this.id = id;
        this.time = time;
        this.type = type;
        this.lines = lines;
        this.fees = fees;
    }
}
