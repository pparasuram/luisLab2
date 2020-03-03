package com.company;

public class BankView {
    public BankView() {
    }

    public void displayMenu() {
        System.out.println("Hello.  Please enter a choice from the list below");
        System.out.println("==================");
        System.out.println("1. Add New Account");
        System.out.println("2. Display account");
        System.out.println("3. Delete An Account");
        System.out.println("4. Withdraw from an Account");
        System.out.println("5. Deposit to An account");
        System.out.println("6. List all accounts");
        System.out.println("7. Exit Teller Menu");
        System.out.println("==================");
    }

    public void displayAcct(Account account) {
        System.out.println("==================");
        System.out.println("Name: " + account.getName());
        System.out.println("Balance: " + account.getBalance());
        System.out.println("AccountNum: " + account.getAcctNum());
        System.out.println("==================");
    }
    public void displayString(String s) {
        System.out.println(s);
    }
    public void displayStringNoLineBreak(String s) {
        System.out.print(s);
    }
}
