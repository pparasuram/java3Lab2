package cscc.edu;

public class BankView {
    public BankView (){
    }
    public static Integer MenuMaxNumber = 7;
    public void displayAcct(BankAccount bankAccount){
        System.out.println("==================");
        System.out.println("Name: " + bankAccount.getCustName());
        System.out.println("ID: " + bankAccount.getAccountNumber());
        System.out.println("Balance: " + bankAccount.getAccountBalance());
        System.out.println("==================");
    }

    public void displayMenu(){
        System.out.println("!!! Acme Bank Welcome Screen !!!");
        System.out.println("Please enter a choice from the below");
        System.out.println("==================");
        System.out.println("1. Add account");
        System.out.println("2. Display account");
        System.out.println("3. Delete account");
        System.out.println("4. Withdraw from account");
        System.out.println("5. Deposit into account");
        System.out.println("6. Withdraw from account");
        System.out.println("7. Exit Program");
        System.out.println("==================");
    }
    public void displayExitScreen() {
        System.out.println("!!! Bye bye from Acme Bank !!!");
    }
    public void displayGetCustName(){
        System.out.println("Enter Customer Name as First Name, Last Name: ");
    }
    public void displayGetAcctDeposit(){
        System.out.println("Enter the amount you would like to Deposit: ");
    }
    public void displayGetAcctWithdraw(){
        System.out.println("Enter the amount you would like to Withdraw: ");
    }

}
