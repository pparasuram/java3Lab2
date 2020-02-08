package cscc.edu;

public class BankView {
    public BankView (){
    }
    public static Integer MenuMaxNumber = 9;
    public void displayAcct(BankAccount bankAccount){
        System.out.println("===========================================");
        System.out.println("Name: " + bankAccount.getCustName());
        System.out.println("ID: " + bankAccount.getAccountNumber());
        System.out.println("Balance: " + bankAccount.formattedAmount(bankAccount.getAccountBalance()));
        System.out.println("===========================================");
    }

    public void displayMenu(){
        System.out.println("!!!!!!!! Acme Bank Welcome Screen !!!!!!!!");
        System.out.println("Please enter a choice from the below");
        System.out.println("===========================================");
        System.out.println("1. Add account");
        System.out.println("2. Display account");
        System.out.println("3. Delete account");
        System.out.println("4. Withdraw from account");
        System.out.println("5. Deposit into account");
        System.out.println("6. Find account by Name (regex) (Administrators)");
        System.out.println("7. Display all accounts (Administrators)");
        System.out.println("8. Write accounts to Disk");
        System.out.println("9. Exit Program");
        System.out.println("===========================================");
    }
    public void displayExitScreen() {
        System.out.println("!!! Bye bye from Acme Bank !!!");
    }
    public void displayGetCustName(){
        System.out.println("Enter Customer Name as First Name, Last Name: ");
    }
    public void displayGetAcctDeposit(){
        System.out.println("Enter the amount you would like to Deposit just numbers: ");
    }
    public void displayGetAcctWithdraw(){
        System.out.println("Enter the amount you would like to Withdraw just numbers: ");
    }
    public void displayGetAccountNumer(){
        System.out.println("Enter the Account Number or -1 to go back to Main Menu: ");
    }
    public void displayAccountNotFound(String str){
        System.out.println("Account: " + str + " not Found!");
    }
    public void displayDashes() {
        System.out.println("--------------Next Account-----------------");
    }

    void displaySuccess(Integer acctNumber, BankAccount bankAccount, BankController bankController) {
        System.out.println("Successfully updated Account: " + bankAccount + " with amount: " +
                // (bankController.bankModel.bankAccounts.get(acctNumber).getAccountBalance()));
                bankAccount.formattedAmount(bankController.bankModel.bankAccounts.get(acctNumber).getAccountBalance()));
    }

    void displayMessage(String s) {
        System.out.println(s);
    }

    void displayBalanceLow(Double balance) {
        System.out.println("Sorry, you only have $"+(Math.round(balance * 100.0) / 100.0)+ " in your account going to Menu!");
    }
}
