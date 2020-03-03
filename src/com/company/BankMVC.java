package com.company;

public class BankMVC {

    public static void main(String[] args) {
        // BankModel model = BankModel.loadBankModel();
        BankModel model = new BankModel();
        BankView view = new BankView();
        BankController controller = new BankController(model,view);
        controller.inputLoop();
    }
}
