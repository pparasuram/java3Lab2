package cscc.edu;

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
            switch (getValidInput()){
                case 6:
                    done = true;
                    bankView.displayExitScreen();
                    break;
                case 5:
                    findFirst(students);
                    break;
                case 4:
                    fillStudents(students);
                    break;
                case 3:
                    listAllStudents();
                    break;
                case 2:
                    findStudent(students);
                    break;
                case 1:
                    addStudent(students);
                    break;
                default:
                    System.out.println("Please enter a number between 1 and 4!");
                    break;
            }
        }
    }
    public Integer getValidInput() {
        boolean done = false;
        while (!done) {
            if (!input.hasNextInt()){
                String word = input.next();
                System.out.println(word + " is not a number");
            } else {
                Integer i = input.nextInt();
                input.nextLine();
                if (i > BankView.MenuMaxNumber || i <= 0) {
                    System.out.println(" Number must be between 1 and "+BankView.MenuMaxNumber);
                } else {
                    done = true;
                    return i;
                }
            }
        }
        return 0;
    }
}
