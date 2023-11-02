/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Candidates;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Dell Latitude 7490
 */
public class Management {
    public static String fileName = "src/data/Managering.txt";
    Scanner sc = new Scanner(System.in);
    Validation valid = new Validation();
    public String enterName() {
        String name;
        do {
            System.out.print("Enter : ");
            name = sc.nextLine();
        } while (!valid.checkName(name));

        return name;
    }
    public String enterPhone() {
        String phone;
        do {
            System.out.print("Enter phone: ");
            phone = sc.nextLine();
        } while (!valid.checkPhone(phone));
        return phone;
    }
    public String enterEmail() {
        String email;
        do{
            System.out.print("Enter email: ");
            email = sc.nextLine();
        } while(!valid.checkEmail(email));
        return email;
    }


    public void add() {
        System.out.print("First name.");
        String first = enterName();
        System.out.print("Last name. ");
        String last = enterName();
        Date DOB = null;
        String dob = "";
        boolean isEnterDob = true;
        while(isEnterDob) {
            do {
                System.out.print("Enter dob (dd/MM/yyyy): ");
                dob = sc.nextLine();
            } while(!valid.checkDate(dob));
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                DOB = sdf.parse(dob);
                isEnterDob = false;
            } catch (Exception e) {
                isEnterDob = true;
            }
        }
        String phone = enterPhone();
        String email = enterEmail();

        //lấy dữ liệu từ file để kiểm tra trong file đã có bao nhiêu Candidates
        ArrayList<Candidates> check = FileHandler.readFromFile(fileName);

        // nếu file trống thì Candidate đầu tiên được nhập vào file sẽ có id là C001
        String candidateID;
        if (check.isEmpty()) {
            candidateID = "C001";
        } else {
            int lastID = Integer.parseInt(check.get(check.size() - 1).getCandidateID().substring(1));
            candidateID = String.format("C%03d", lastID + 1);
        }

        ArrayList<Candidates> list = new ArrayList<Candidates>();
        Candidates newCandidate = new Candidates(candidateID, first, last, DOB, phone, email);
        list.add(newCandidate);

        // ghi thông tin Candidate vào file
        FileHandler.writeToFile(list, fileName);

        System.out.println("Added successfully!!!");
    }
    
    public void find(int id) {
        
    }
    
    public void update(int id) {
        
    }
    
    public void delete(int id) {
        
    }
    
    public void show() {
        
    }


}
