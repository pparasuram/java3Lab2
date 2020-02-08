package cscc.edu;

import java.io.Serializable;
import java.text.NumberFormat;

public class BankAccount  implements Serializable {
    Integer accountNumber;
    String custName;
    double accountBalance;
    public double roundDoubleToTwoDigits(double amount) {
        return (Math.round(amount * 100.0) / 100.0);
    }
    public double getAccountBalance() {
        return (accountBalance);
        //
    }
    public  String formattedAmount(double amount) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return (formatter.format(amount));
    }
    public void setAccountBalance(double accountBalance) {
        this.accountBalance = this.roundDoubleToTwoDigits(accountBalance);
    }

    public BankAccount(Integer accountNumber, String custName, double accountBalance) {
        this.accountNumber = accountNumber;
        this.custName = custName;
        this.accountBalance = this.roundDoubleToTwoDigits(accountBalance);
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "accountNumber=" + accountNumber +
                ", custName='" + custName + '\'' +
                ", accountBalance=" + formattedAmount(accountBalance) +
                '}';
    }
}
