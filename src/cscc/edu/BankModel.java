package cscc.edu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class BankModel {
    public static final String MY_OBJECTS_TXT = "myObjects.txt";

    HashMap<Integer,BankAccount> bankAccounts;
    Integer nextAcctId;

    public BankModel() {
        this.bankAccounts = new HashMap<Integer,BankAccount>();;
        this.nextAcctId = 1;
    }

    // public static BankModel loadBankModel() {
    public void loadBankModel() {
        // load serialized model
        // -or-
        // return new model
        // return new BankModel();
        readAndSerializeBankModel();
        // get size and set nextAcctId to be ready to add new accounts
        nextAcctId = bankAccounts.size() + 1;
    }
    public String getAcctName (Integer id) {
        BankAccount a = bankAccounts.get(id);
        return  a.getCustName();
        //return bankAccounts.get(id).name;
    }
    public boolean addAcct (Integer id, String name){
        return false;
    }



    public void serializeAndWriteBankModel() {
        try {
            FileOutputStream fos =
                    new FileOutputStream(new File(MY_OBJECTS_TXT));
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(bankAccounts);
            oos.close();
            fos.close();
            System.out.printf("Serialized HashMap data is saved in: " + MY_OBJECTS_TXT);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        }
    }

    public void readAndSerializeBankModel() {
        File f = new File(MY_OBJECTS_TXT);
        if (f.isFile() && f.canRead()) {
            try {
                FileInputStream fis = new FileInputStream(f);
                ObjectInputStream ois = new ObjectInputStream(fis);
                this.bankAccounts = (HashMap) ois.readObject();
                ois.close();
                fis.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            } catch (IOException e) {
                System.out.println("Error initializing stream");
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                System.out.println("Error Class Not Found Exception while reading from file");
                e.printStackTrace();
            }
        }  // end of if
    }  // end of readAndSerializeBankModel() {
}
