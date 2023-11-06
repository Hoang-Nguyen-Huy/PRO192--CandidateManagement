/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.util.Scanner;

/**
 *
 * @author Dell Latitude 7490
 */
public class MenuManagement {
    public void MenuLogin() {
        System.out.println("----------Candidate Management System----------");
        System.out.println("[1]. Sign In.");
        System.out.println("[2]. Sign Up.");
        System.out.println("[0]. Exit.");
    }
    
    public void MenuManager() {
        System.out.println("----------Candidate Management System----------");
        System.out.println("[1]. Add candidates");
        System.out.println("[2]. Find candidates");
        System.out.println("[3]. Update candidates");
        System.out.println("[4]. Delete candidates");
        System.out.println("[5]. Show all candidates");
        System.out.println("[0]. Log out");
    }

    public void MenuFind() {
        System.out.println("----------Candidate Management System----------");
        System.out.println("[1]. Find by ID");
        System.out.println("[2]. Find by Name");
        System.out.println("[0]. Back to main menu");
    }

    public void AdminMenuManager() {
        System.out.println("----------Candidate Management System----------");
        System.out.println("[1]. Add candidates");
        System.out.println("[2]. Find candidates");
        System.out.println("[3]. Update candidates");
        System.out.println("[4]. Delete candidates");
        System.out.println("[5]. Show all candidates");
        System.out.println("-----------------------------------------------");
        System.out.println("[6]. Granting sign in");
        System.out.println("[7]. Show all sign up user");
        System.out.println("[8]. Show all sign in user");
        System.out.println("[9]. Show all granting list");
        System.out.println("[0]. Log out");
    }
}
