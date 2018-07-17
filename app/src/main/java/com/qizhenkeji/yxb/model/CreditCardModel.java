package com.qizhenkeji.yxb.model;

/**
 * Created by hc101 on 18/6/16.
 */

public class CreditCardModel extends BaseModel {
    public int id;
    public String img_url;
    public String bank_name;
    public String name;
    public String bank_no;

    public CreditCardModel(int id, String bank_name, String name, String bank_no) {
        this.id = id;
        this.bank_name = bank_name;
        this.name = name;
        this.bank_no = bank_no;
    }
}
