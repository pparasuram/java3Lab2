package cscc.edu;

import java.util.Collections;
import java.util.Scanner;

public class BankController {
    BankModel bankModel;
    BankView bankView;
    private static Scanner input = new Scanner(System.in);
    public BankController(BankModel bankModel, BankView bankView) {
        this.bankModel = bankModel;
        this.bankView = bankView;
        this.bankModel.loadBankModel();
    }

    public void inputLoop() {
        boolean done = false;
        while (! done) {
            bankView.displayMenu();
            switch (getValidMenuInput()){
                case 9:
                    done = true;
                    bankView.displayExitScreen();
                    break;
                case 8:
                    writeAccountsToFile();
                    break;
                case 7:
                    bankModel.bankAccounts.forEach((k, v) -> {
                                bankView.displayDashes();
                                bankView.displayAcct(v);
                            }
                    );
                    break;
                case 6:
                    findAccountByName();
                    break;
                case 5:
                    depositIntoAccount();
                    break;
                case 4:
                    withdrawFromAccount();
                    break;
                case 3:
                    deleteAccount();
                    break;
                case 2:
                    displayAccount();
                    break;
                case 1:
                    addAccount();
                    break;
                default:
                    bankView.displayMessage("Please enter a number between 1 and " + BankView.MenuMaxNumber);
                    break;
            }
        }
    }

    private void writeAccountsToFile() {
        bankModel.serializeAndWriteBankModel();
        bankView.displayMessage("Successfully written accounts to File!");
    }

    private void addAccount() {
        // get input from customer
        boolean done = false;
        double amount;
        String name;
        while (!done) {
            bankView.displayGetCustName();
            name = getString();
            if (name == null)
                // message would have been displayed in the getString()
                continue;
            try {
                bankView.displayGetAcctDeposit();
                amount = getValidAmountInput();
                Integer acctNumber = bankModel.getNextAcctId();
                bankModel.setNextAcctId(acctNumber + 1);
                BankAccount newBankAccount = new BankAccount(acctNumber, name, amount);
                bankModel.bankAccounts.put(acctNumber, newBankAccount);
                done = true;
                bankView.displayMessage("Successfully Added account "+ acctNumber);
            } catch (Exception e) {
                bankView.displayMessage("Exception Error occurred while adding: " + e);
            }
        }

    }
    private void displayAccount() {
        Integer acctNumber;
        Double deposit;
        bankView.displayGetAccountNumer();
        acctNumber = getValidAccountInput();
        BankAccount bankAccount = null;
        if (acctNumber == -1) // user is unsure, go back to main menu
            return;
        // now we have valid account number
        try {
            bankAccount = bankModel.bankAccounts.get(acctNumber);
        }
        catch (Exception e) {
            bankView.displayMessage("Could not retrieve account: " + acctNumber + " may have been deleted!");
        }
        bankView.displayAcct(bankAccount);
    }

    private void deleteAccount() {
        Integer acctNumber;
        Double deposit;
        bankView.displayGetAccountNumer();
        acctNumber = getValidAccountInput();
        if (acctNumber == -1) // user is unsure, go back to main menu
            return;
        // we have account number here we need to delete it
        try {
            bankModel.bankAccounts.remove(acctNumber);
            bankView.displayMessage("Successfully Deleted Account: "+ acctNumber);
        }
        catch (Exception e) {
            bankView.displayMessage("Exception Error occurred while adding: " + e);
        }
    }

    private void withdrawFromAccount() {
        depositOrWithdrawFromAccount("Withdraw");
        /*
        Integer acctNumber;
        Double deposit;
        bankView.displayGetAccountNumer();
        acctNumber = getValidAccountInput();
        if (acctNumber == -1) // user is unsure, go back to main menu
            return;
        // now we have valid account number
        // now get amount to be deposited
        bankView.displayGetAcctWithdraw();
        deposit = getValidAmountInput();
        // now make deposit
        BankAccount bankAccount = bankModel.bankAccounts.get(acctNumber);
        bankAccount.setAccountBalance(bankAccount.getAccountBalance()- deposit);
        bankModel.bankAccounts.put(acctNumber, bankAccount);
        bankView.displaySuccess(acctNumber, bankAccount, this);
         */
    }

    private void depositIntoAccount() {
        depositOrWithdrawFromAccount("Deposit");
    }

    private void depositOrWithdrawFromAccount(String action) {
        Integer acctNumber;
        Double deposit, balance;
        bankView.displayGetAccountNumer();
        acctNumber = getValidAccountInput();
        if (acctNumber == -1) // user is unsure, go back to main menu
            return;
        // now we have valid account number
        // now get amount to be deposited
        if (action.equals("Deposit"))
            bankView.displayGetAcctDeposit();
        else
            // withdraw message displayed
            bankView.displayGetAcctWithdraw();
        deposit = getValidAmountInput();
        // now make deposit
        BankAccount bankAccount = bankModel.bankAccounts.get(acctNumber);
        balance = bankAccount.getAccountBalance();
        if (action.equals("Deposit"))
            bankAccount.setAccountBalance(balance+deposit);
        else { // withdraw
            if (balance < deposit) {
                bankView.displayBalanceLow(balance);
                return;
            } else
                bankAccount.setAccountBalance(balance-deposit);
        }
        bankModel.bankAccounts.put(acctNumber, bankAccount);
        bankView.displaySuccess(acctNumber, bankAccount, this);
    }

    private Double getValidAmountInput() {
        // job is to get valid dollar amount or 0 if user changed their mind
        // we have a valid account number by now
        boolean done = false;
        double deposit = 0;
        while (!done) {
            if (!input.hasNextDouble()){
                String word = input.next();
                bankView.displayMessage(word + " is not a number");
            } else {
                deposit = input.nextDouble();
                input.nextLine();
                deposit = Math.round(deposit * 100.0) / 100.0;
                if ( deposit <= 0) {
                    bankView.displayMessage("Amount must be greater than 0.0");
                } else {
                    done = true;
                    return deposit;
                }
            }
        }
        return 0.0;
    }

    private void findAccountByName() {
        // display prompt for user to enter account information
        // search for user using regex in bankModel.bankAccounts hashMap
        // display all users matching regex
        bankView.displayGetCustName();
        String str = input.nextLine();
        // now search for str in values
        bankModel.bankAccounts.forEach((k, v) -> {
                    if (v.getCustName().matches(str)) {
                        bankView.displayDashes();
                        bankView.displayAcct(v);
                    }
                }
        );
    }
    public Integer getValidMenuInput() {
        boolean done = false;
        while (!done) {
            if (!input.hasNextInt()){
                String word = input.next();
                bankView.displayMessage(word + " is not a number");
            } else {
                Integer i = input.nextInt();
                input.nextLine();
                if (i > BankView.MenuMaxNumber || i <= 0) {
                    bankView.displayMessage(" Number must be between 1 and "+ BankView.MenuMaxNumber);
                } else {
                    done = true;
                    return i;
                }
            }
        }
        return 0;
    }
    public Integer getValidAccountInput() {
        boolean done = false;
        Integer maxKey = Collections.max(bankModel.bankAccounts.keySet());
        while (!done) {
            if (!input.hasNextInt()){
                String word = input.next();
                bankView.displayMessage(word + " is not a number");
            } else {
                Integer i = input.nextInt();
                input.nextLine();
                if (i == -1)
                    return i;
                if (i > maxKey || i <= 0) {
                    bankView.displayMessage(" Number must be between 1 and "+ maxKey);
                } else
                    if (bankModel.bankAccounts.containsKey(i)){
                        done = true;
                        return i;
                    } else
                    {
                        bankView.displayMessage("Account " + i + " does not exist! Going back to Main Menu!");
                        done = true;
                        return -1;

                    }
            }
        }
        return 0;
    }
    public String getString() {
        String name;
        name = input.nextLine();
        if ((name.equals(""))
                || (name == null)
                || !(name.matches("^[a-z ,A-Z']*$"))) {
            bankView.displayMessage("Name can only contain alphabets or the apostrophe(')");
            return null;
        }
        return name;
    }

}
