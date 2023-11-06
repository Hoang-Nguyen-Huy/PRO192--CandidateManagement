/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.*;

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
    String usefulID = "";
    int usefulType;
    public static String fileExp = "src/data/Exp.txt";
    public static String fileFresher = "src/data/Fresher.txt";
    public static String fileIntern = "src/data/Intern.txt";
    Scanner sc = new Scanner(System.in);
    Validation valid = new Validation();

    public int enterChoice() {
        String inp;
        do {
            System.out.print("Enter choices: ");
            inp = sc.nextLine();
        } while(!valid.checkInt(inp));
        return Integer.parseInt(inp);
    }
    public String enterID() {
        String inp;
        do {
            System.out.print("Enter id: ");
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

    // candidates management
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
        usefulType = Integer.parseInt(choice);
        // hỏi xong-----------------------------

        // dẫn đến file mà người dùng muốn tìm
        String fileName = "";
        if (usefulType == 1) {
            fileName = fileExp;
        } else if (usefulType == 2) {
            fileName = fileFresher;
        } else if (usefulType == 3) {
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
        usefulID = enterID();
        Candidates result = null;
        for (Candidates c : check) {
            if(c.getCandidateID().equals(usefulID)) {
                result = c;
                found = true;
            }
        }
        if (!found) {
            System.out.println("This: " + usefulID + " is not found!!!");
        }
        System.out.println(result.toString());
        //find xong-------------------
    }

    public void findByName() {
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

        //bắt đầu findByName trong check
        ArrayList<Candidates> result = new ArrayList<Candidates>();
        boolean found = false;
        System.out.print("Enter name that you want to find (name = first name + last name): ");
        String name = enterName();
        for (Candidates c : check) {
            String temp = c.getFirstName() + " " + c.getLastName();
            if(temp.equals(name)) {
                found = true;
                result.add(c);
            }
        }
        if (!found) {
            System.out.println("No name is found");
            return;
        }
        for (Candidates c : result) {
            System.out.println(c.toString());
        }
    }
    
    public void update() {
        // Bắt người dùng tìm người theo Id để cập nhật thông tin
        findByID();
        //---

        // Hỏi người dùng muốn cập nhật thông tin nào
        System.out.println("What information do you want to update? [1].Name | [2].Phone | [3].Email?");
        String choice = "";
        do {
            System.out.print("Enter a number: ");
            choice = sc.nextLine();
        } while (!valid.checkInt(choice) && Integer.parseInt(choice) <= 3 && Integer.parseInt(choice) >= 1);
        int type = Integer.parseInt(choice);
        //xong-------------------------------------


        //kiếm tra xem người dùng muốn thay đổi thông tin nào và bắt đầu cho update
        String fileName = "";
        if (usefulType == 1) {
            fileName = fileExp;
        } else if (usefulType == 2) {
            fileName = fileFresher;
        } else if (usefulType == 3) {
            fileName = fileIntern;
        }
        ArrayList<Candidates> check = FileHandler.readFromFile(fileName);

        for (Candidates c : check) {
            if (c.getCandidateID().equals(usefulID)) {
                if (type == 1) {
                    System.out.print("New first name. ");
                    String newFirst = enterName();
                    c.setFirstName(newFirst);

                    System.out.print("New last name. ");
                    String newLast = enterName();
                    c.setLastName(newLast);
                } else if (type == 2) {
                    System.out.print("New phone. ");
                    String newPhone = enterPhone();
                    c.setPhone(newPhone);
                } else if (type == 3) {
                    System.out.print("New email. ");
                    String newEmail = enterEmail();
                    c.setEmail(newEmail);
                }
                break;
            }
        }
        // xong ----------------------------------


        //Ghi lại danh sách vào file
        FileHandler.updateToFile(check, fileName);
        System.out.println("Update successfully!!!!!");
    }
    
    public void delete() {
        //show ra danh sách và cho người dùng chọn ID nhân viên muốn xóa
        show();
        //xong


        // bắt đầu cho phép nguời dùng nhập ID
        String fileName = "";
        if (usefulType == 1) {
            fileName = fileExp;
        } else if (usefulType == 2) {
            fileName = fileFresher;
        } else if (usefulType == 3) {
            fileName = fileIntern;
        }
        System.out.print("ID that you want to delete. ");
        String id = enterID();
        ArrayList<Candidates> check = FileHandler.readFromFile(fileName);

        for (Candidates c : check) {
            if (c.getCandidateID().equals(id)) {
                check.remove(c);
                break;
            }
        }
        //cập nhật lại vào file
        FileHandler.updateToFile(check, fileName);
        // xong-----------------------------

        System.out.println("Delete successfully");

    }
    
    public void show() {
        // hỏi người dùng muốn tìm loại ứng viên nào
        System.out.println("Which candidates do you want to show: [1].Experience | [2].Fresher | [3].Intern?");
        String choice = "";
        do {
            System.out.print("Enter a number: ");
            choice = sc.nextLine();
        } while(!valid.checkInt(choice));
        usefulType = Integer.parseInt(choice);
        // hỏi xong-----------------------------

        // dẫn đến file mà người dùng muốn show
        String fileName = "";
        if (usefulType == 1) {
            fileName = fileExp;
        } else if (usefulType == 2) {
            fileName = fileFresher;
        } else if (usefulType == 3) {
            fileName = fileIntern;
        }
        ArrayList<Candidates> check = FileHandler.readFromFile(fileName);
        if(check.isEmpty()) {
            System.out.println("Empty file");
            return;
        }
        //xong----------------------------

        //bắt đấu show
        for(Candidates c : check) {
            System.out.println(c.toString());
        }
    }
    //---------------------


    // account management
    public void showAllAccount(String fileName) {
        System.out.println("Username | Password");
        ArrayList<User> check = FileHandler.read(fileName);
        for (User user : check) {
            System.out.println(user.toString());
        }
    }
    //-------------------

}
