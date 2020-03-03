package com.company;

import java.math.BigDecimal;
import java.util.Scanner;

public class BankController {
    BankModel model;
    BankView view;
    Scanner input;
    Account account;
    public BankController(BankModel model, BankView view){
        this.model = model;
        this.view = view;
        input = new Scanner(System.in);
    }

    public void inputLoop() {
        // HERE FOR REFERENCE == NOT REQUIRED TO IMPLEMENT
        // tell view to display menu for loading new or old model => yes/no
        //view.displayLoadMenu():
        //int res = scanner.nextInt();
        // tell model to load new or old model using previous answer => model
        //model = model.loadBankModel(res);


        // tell view to display actions menu
        while (true) {


            view.displayMenu();
            int res = input.nextInt();
            input.nextLine();
            // based on response call model then view w/ info
            // great spot for switch/case (hint hint)
            int acctNum = 0;
            String name = null;
            BigDecimal deposit = BigDecimal.valueOf(0);
            BigDecimal balance = BigDecimal.valueOf(0);
            switch (res) {

                case 1:
                    System.out.println("Please enter a first name for the new account you wish to create: ");
                    name = input.nextLine();
                    account = model.addAcct(name);
                    view.displayAcct(account);
                    break;

                case 2:
                    // do not see this as being completed??? Am I missing something?
                    System.out.println("Enter the first name of the account you would like to display:");
                    name = input.nextLine();
                    account = model.findAccount(name) ;
                    if (account == null)
                        view.displayString("Account: " + name + " Not found!");
                    else
                        view.displayAcct(account);
                    // System.out.println(name);
                    break;

                case 3:
                    System.out.println("Enter the first name of the account you would like to delete: ");
                    name = input.nextLine();
                    account = model.deleteAccount(name);
                    System.out.println("The account: ");
                    view.displayAcct(account);
                    view.displayString( "was removed successfully");
                    break;
                case 4:
                    // do not see this as being completed??? Am I missing something?
                    System.out.println("Enter the first name of the account you would like to withdraw money from: ");
                    name = input.nextLine();
                    System.out.println("Enter in the amount of the withdrawl: ");
                    deposit = new BigDecimal(input.nextLine());
                    account = model.deposit(name, deposit.negate());
                    if (account == null)
                        view.displayString("You will be overdrawing or could not find account!");
                    break;
                case 5:
                    System.out.println("Enter the first name of the account you would like to deposit money into: ");
                    name = input.nextLine();
                    System.out.println("Enter in the amount of the deposit: ");
                    deposit = new BigDecimal(input.nextLine());
                    account = model.deposit(name, deposit);
                    if (account == null)
                        view.displayString("could not find account for : " + name);
                    break;
                case 6:
                    model.setIterator();
                    while ((account = model.getNextAccount()) != null) {
                        view.displayAcct(account);
                        view.displayString("------------------------");
                    }
                    break;
                case 7:
                    System.out.println("You are now exiting the Teller Menu.  Goodbye");
                    model.saveBankModel();
                    return;
            }
            // view.displayAcct(acctNum, name, balance);


        }

    }
}
