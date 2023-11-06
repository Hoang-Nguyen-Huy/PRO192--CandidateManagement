package Controller;

import Model.User;

import java.util.ArrayList;
import java.util.Scanner;

public class SignUp {

    Scanner sc = new Scanner(System.in);
    Validation valid = new Validation();

    public String enterUserName() {
        String inp;
        do {
            System.out.print("Enter username: ");
            inp = sc.nextLine();
        } while(!valid.checkUserName(inp));
        return inp;
    }

    public String enterPassword() {
        String inp;
        System.out.print("Enter password: ");
        inp = sc.nextLine();
        return inp;
    }

    public void signUp() {
        String username = enterUserName();
        String password = enterPassword();
        User user = new User(username, password);
        FileHandler.signup(user, SignIn.fileSignUp);
        FileHandler.signup(user, SignIn.fileGranting);
        System.out.println("User registered successfully!");
    }
}
