package com.company;

import java.io.Serializable;
import java.math.BigDecimal;

public class Account implements Serializable {
    String name;
    Integer acctNum;
    BigDecimal balance = new BigDecimal("0");


    public BigDecimal getBalance() {
        return balance;
    }
    public void setBalance(BigDecimal balance){
        this.balance=balance;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAcctNum() {
        return acctNum;
    }

    public void setAcctNum(Integer acctNum) {
        this.acctNum = acctNum;
    }
}

