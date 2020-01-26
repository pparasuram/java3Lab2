package cscc.edu;

import java.io.Serializable;

public class BankAccount  implements Serializable {
    Integer accountNumber;
    String custName;

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    double accountBalance;

    public BankAccount(Integer accountNumber, String custName, double accountBalance) {
        this.accountNumber = accountNumber;
        this.custName = custName;
        this.accountBalance = accountBalance;
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
                ", accountBalance=" + accountBalance +
                '}';
    }
}
