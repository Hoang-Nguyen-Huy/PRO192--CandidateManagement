/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.User;
import View.MenuManagement;

import java.io.PrintWriter;

/**
 *
 * @author Dell Latitude 7490
 */
public class RunManagement {
    SignUp su = new SignUp();
    SignIn si = new SignIn();
    MenuManagement ui = new MenuManagement();
    Management mn = new Management();
    public void UserRun () {
        boolean isRunning = true;
        while(isRunning) {
            ui.MenuManager();
            int choices;
            switch (choices = mn.enterChoice()) {
                case 1:
                    mn.add();
                    break;
                case 2:
                    finding();
                    break;
                case 3:
                    mn.update();
                    break;
                case 4:
                    mn.delete();
                    break;
                case 5:
                    mn.show();
                    break;
                case 0:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Please enter choices from 0-5!!!!!");
                    break;
            }
        }
    }
    public void finding() {
        boolean isFinding = true;
        while(isFinding) {
            ui.MenuFind();
            int choices;
            switch (choices = mn.enterChoice()) {
                case 1:
                    mn.findByID();
                    break;
                case 2:
                    mn.findByName();
                    break;
                case 0:
                    isFinding = false;
                    break;
                default:
                    System.out.println("Please enter choices from 0-2");
                    break;
            }
        }
    }

    public void RunningProgram() {
        boolean isRunningProgram = true;
        while(isRunningProgram) {
            ui.MenuLogin();
            int choices;
            switch (choices = mn.enterChoice()) {
                case 1:
                    String username = su.enterUserName();
                    String password = su.enterPassword();
                    User user = new User(username, password);
                    if (si.admin(username, password)) {
                        AdminMenu();
                    } else if (FileHandler.checkSignIn(user, SignIn.fileSignIn)) {
                        UserRun();
                    } else {
                        System.out.println("May be you have not registered!!!");
                    }
                    break;
                case 2:
                    su.signUp();
                    break;
                case 0:
                    isRunningProgram = false;
                    break;
                default:
                    System.out.println("Please enter choices from 0-2!!!");
            }
        }
    }

    public void AdminMenu() {
        boolean isAdmin = true;
        while(isAdmin) {
            ui.AdminMenuManager();
            int choices;
            switch (choices = mn.enterChoice()) {
                case 1:
                    mn.add();
                    break;
                case 2:
                    finding();
                    break;
                case 3:
                    mn.update();
                    break;
                case 4:
                    mn.delete();
                    break;
                case 5:
                    mn.show();
                    break;
                case 6:

                    break;
                case 7:
                    mn.showAllAccount(SignIn.fileSignUp);
                    break;
                case 8:
                    mn.showAllAccount(SignIn.fileSignIn);
                    break;
                case 9:
                    mn.showAllAccount(SignIn.fileGranting);
                    break;
                case 0:
                    isAdmin = false;
                    break;
                default:
                    System.out.println("Please enter choices from 0-9!!!!");
                    break;
            }
        }
    }
}
