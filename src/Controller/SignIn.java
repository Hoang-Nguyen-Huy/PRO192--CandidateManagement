package Controller;

import Model.User;

public class SignIn {
    public static String fileSignIn = "src/data/SignIn.txt";
    public static String fileSignUp = "src/data/SignUp.txt";
    public static String fileGranting = "src/data/Granting.txt";


    public boolean admin(String username, String password) {
        if (username.equals("admin") && password.equals("adminpassword")) {
            return true;
        }
        return false;
    }
}
