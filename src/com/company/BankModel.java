package com.company;

import java.io.*;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;


public class BankModel implements Serializable {
    HashMap<Integer, Account> data;
    Integer nextAcctId;
    Iterator iterator;
    public Iterator getIterator() {
        return iterator;
    }

    public void setIterator() {
        this.iterator = data.entrySet().iterator();
    }
    public BankModel() {
        data = new HashMap<Integer, Account>();
        nextAcctId = 1;
        loadBankModel();
    }

    public HashMap<Integer, Account> getData() {
        return data;
    }

    public void loadBankModel() {
        try {
            FileInputStream fis = new FileInputStream("BankInfo.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            this.data = (HashMap<Integer, Account>) ois.readObject();
            this.nextAcctId = Collections.max(this.data.keySet());
            ois.close();
            fis.close();
        } catch (FileNotFoundException e) {
            System.out.println("Input File not found: " + e.getMessage());
        } catch (IOException ioe) {
            System.out.println("IO Exception: " + ioe.getMessage());
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
        }
    }

    public void saveBankModel() {


        try {
            FileOutputStream fos =
                    new FileOutputStream("BankInfo.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this.data);
            oos.close();
            fos.close();
            System.out.printf("Serialized HashMap data is saved in hashmap.ser");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


    public String getAcct(Integer id) {
        Account a = data.get(id);
        return a.name;
        //return data.get(id).name;
    }

    public Account addAcct(String name) {
        Integer acctNum = this.nextAcctId;
        Account a = new Account();
        try {
            a.name = name;
            a.acctNum = acctNum;
            data.put(acctNum, a);
            this.nextAcctId++;
        } catch (Exception e) {
            return null;
        }
        return a;
    }

    public Account deposit(String name, BigDecimal deposit) {
        // once they have passed in a name we need to find the acct number.
        Account account = findAccount(name);
        // remember deposit could be negative
        // Account acct = data.get(acctNum);
        if (account == null)
                return null;
        BigDecimal balance = account.getBalance();
        if (deposit.compareTo(BigDecimal.valueOf(0)) < 0 && (deposit.abs().compareTo(balance) > 0))
            return null;
        balance = balance.add(deposit);
        account.setBalance(balance);
        data.put(account.acctNum, account);
        return account;
    }

    public Account findAccount(String name) {
        Account account = null;
        int acctNum = 0;
        for (HashMap.Entry<Integer, Account> entry : data.entrySet()) {
            account = entry.getValue();
            acctNum = entry.getKey();

            if (name.equals(account.getName())) {
                if (account.acctNum != acctNum) {
                    System.out.println("somehow key is not matching account number, debug program a bit more");
                    return null;
                }
                return account;
            }
        }
        // Print error message saying acct not found.  May have to change everything that shows int for
        return null;
    }

    public Account deleteAccount (String name) {
      Account account = this.findAccount(name);
      data.remove(account.getAcctNum());
      return (account);
    }


    public Account getNextAccount() {

        if(iterator.hasNext()){
            HashMap.Entry mapElement  = (HashMap.Entry)iterator.next();
            return (Account) mapElement.getValue();
        }
        return null;
    }
}
