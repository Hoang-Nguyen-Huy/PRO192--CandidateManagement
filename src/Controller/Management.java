/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Candidates;
import Model.ExpCandidates;
import Model.FresherCandidates;
import Model.InternCandidates;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Dell Latitude 7490
 */
public class Management {
    public static String fileExp = "src/data/Exp.txt";
    public static String fileFresher = "src/data/Fresher.txt";
    public static String fileIntern = "src/data/Intern.txt";
    Scanner sc = new Scanner(System.in);
    Validation valid = new Validation();

    public String enterID() {
        String inp;
        do {
            System.out.print("Enter id that you want to find: ");
            inp = sc.nextLine();
        } while(!valid.checkID(inp));
        return inp;
    }
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
    public int enterType() {
        String inp;
        do {
            System.out.print("Enter candidate type(1.Experience, 2.Fresher, 3.Intern): ");
            inp = sc.nextLine();
        } while(!valid.checkType(inp));
        return Integer.parseInt(inp);
    }
    public int enterExpYear() {
        String inp;
        do {
            System.out.print("Enter experience year: ");
            inp = sc.nextLine();
        } while (!valid.checkInt(inp));
        return Integer.parseInt(inp);
    }
    public String enterSkill() {
        String inp;
        System.out.print("Enter your best skill: ");
        inp = sc.nextLine();
        return inp;
    }
    public String enterRank() {
        String inp;
        do {
            System.out.println("Which one is your Rank: [Excellent] || [Good] || [Bad]");
            inp = sc.nextLine();
        } while(!valid.checkRank(inp));
        return inp;
    }
    public String enterEducation() {
        String inp;
        do {
            System.out.print("Enter your education: ");
            inp = sc.nextLine();
        } while(!valid.checkName(inp));
        return inp;
    }
    public String enterMajor() {
        String inp;
        do {
            System.out.print("Enter your major: ");
            inp = sc.nextLine();
        } while(!valid.checkName(inp));
        return inp;
    }
    public int enterSemester() {
        String inp;
        do {
            System.out.print("Enter your present semester (a number): ");
            inp = sc.nextLine();
        } while(!valid.checkInt(inp));
        return Integer.parseInt(inp);
    }
    public String enterUniversityName () {
        String inp;
        do {
            System.out.print("Enter your University: ");
            inp = sc.nextLine();
        } while(!valid.checkName(inp));
        return inp;
    }

    public void add() {
        //các thông tin có thể có thêm
        Candidates candidates = null;

        int expYear = 0;
        String proSkill = "";
        //.. ExpCandidates

        Date graduationDate = null;
        String graduationRank = "";
        String education = "";
        //..Fresher

        String major = "";
        int semester = 0;
        String UniversityName = "";
        //..Intern

        //----------------------

        // nhập thông tin Candidate
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

        int type = enterType();
        if (type == 1) {
            expYear = enterExpYear();
            proSkill = enterSkill();
        } else if (type == 2) {
            String gD = "";
            boolean isEnterGraduationDate = true;
            while(isEnterGraduationDate) {
                do {
                    System.out.print("Enter graduation date (dd/MM/yyyy): ");
                    gD = sc.nextLine();
                } while(!valid.checkDate(gD));
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    graduationDate = sdf.parse(gD);
                    isEnterGraduationDate = false;
                } catch (Exception e) {
                    isEnterGraduationDate = true;
                }
            }
            graduationRank = enterRank();
            education = enterEducation();
        } else if (type == 3) {
            major = enterMajor();
            semester = enterSemester();
            UniversityName = enterUniversityName();
        }
        // nhập xong thông tin


        //lấy dữ liệu từ file để kiểm tra trong file đã có bao nhiêu Candidates
        String fileName = "";
        if (type == 1) {
            fileName = fileExp;
        } else if (type == 2) {
            fileName = fileFresher;
        } else if (type == 3) {
            fileName = fileIntern;
        }

        ArrayList<Candidates> check = FileHandler.readFromFile(fileName);

        // nếu file trống thì Candidate đầu tiên được nhập vào file sẽ có id là C001
        String candidateID;
        if (check.isEmpty()) {
            candidateID = "C001";
        } else {
            int lastID = Integer.parseInt(check.get(check.size() - 1).getCandidateID().substring(1));
            candidateID = String.format("C%03d", lastID + 1);
        }

        if (type == 1) {
            candidates = new ExpCandidates(candidateID, first, last, DOB, phone, email, expYear, proSkill);
        } else if (type == 2) {
            candidates = new FresherCandidates(candidateID, first, last, DOB, phone, email, graduationDate, graduationRank, education);
        } else if (type == 3) {
            candidates = new InternCandidates(candidateID, first, last, DOB, phone, email, major, semester, UniversityName);
        }

        ArrayList<Candidates> list = new ArrayList<Candidates>();
        list.add(candidates);

        // ghi thông tin Candidate vào file
        FileHandler.writeToFile(list, fileName);

        System.out.println("Added successfully!!!");
    }
    
    public void findByID() {
        // hỏi người dùng muốn tìm loại ứng viên nào
        System.out.println("Which candidates do you want to find: [1].Experience | [2].Fresher | [3].Intern?");
        String choice = "";
        do {
            System.out.print("Enter a number: ");
            choice = sc.nextLine();
        } while(!valid.checkInt(choice));
        int type = Integer.parseInt(choice);
        // hỏi xong-----------------------------

        // dẫn đến file mà người dùng muốn tìm
        String fileName = "";
        if (type == 1) {
            fileName = fileExp;
        } else if (type == 2) {
            fileName = fileFresher;
        } else if (type == 3) {
            fileName = fileIntern;
        }
        ArrayList<Candidates> check = FileHandler.readFromFile(fileName);
        if(check.isEmpty()) {
            System.out.println("Empty file");
            return;
        }
        // dẫn xong----------------------------

        //bắt đầu findByID trong check
        boolean found = false;
        String id = enterID();
        Candidates result = null;
        for (Candidates c : check) {
            if(c.getCandidateID().equals(id)) {
                result = c;
                found = true;
            }
        }
        if (!found) {
            System.out.println("This: " + id + " is not found!!!");
        }
        System.out.println(result.toString());
        //find xong-------------------
    }

    public void findByName(String name) {

    }
    
    public void update(int id) {
        
    }
    
    public void delete(int id) {
        
    }
    
    public void show() {
        
    }


}
