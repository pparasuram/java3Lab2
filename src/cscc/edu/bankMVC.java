package cscc.edu;

public class bankMVC {

    public static void main(String[] args) {
        // write your code here
        // create account
        // close account
        // display account
        // update info
        BankModel model = new BankModel();
        model.loadBankModel();
        BankView view = new BankView();
        BankController controller = new BankController(model,view);

        controller.inputLoop();
    }
}
