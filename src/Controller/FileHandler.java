package Controller;

import Model.*;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FileHandler {

    // quản lý candidates
    public static ArrayList<Candidates> readFromFile (String fileName) {
        ArrayList<Candidates> candidates = new ArrayList<>();
            if (fileName.equals(Management.fileExp)) {
                try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                    String line;
                    while((line = reader.readLine()) != null) {
                        String []data = line.split("\\s+");
                        String id = data[0];
                        String first = data[1];
                        String last = data[2];
                        Date dob = new SimpleDateFormat("dd/MM/yyyy").parse(data[3]);
                        String phone = data[4];
                        String email = data[5];
                        int expYear = Integer.parseInt(data[6].trim());
                        String proSkill = data[7];
                        Candidates candidates1 = new ExpCandidates(id, first, last, dob, phone, email, expYear, proSkill);
                        candidates.add(candidates1);
                    }
                } catch (IOException | ParseException e) {
                    e.printStackTrace();
                }
            } else if (fileName.equals(Management.fileFresher)) {
                try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                    String line;
                    while((line = reader.readLine()) != null) {
                        String []data = line.split("\\s+");
                        String id = data[0];
                        String first = data[1];
                        String last = data[2];
                        Date dob = new SimpleDateFormat("dd/MM/yyyy").parse(data[3]);
                        String phone = data[4];
                        String email = data[5];
                        Date graduation_date = new SimpleDateFormat("dd/MM/yyyy").parse(data[6]);
                        String rank = data[7];
                        String education = data[8];
                        Candidates candidates1 = new FresherCandidates(id, first, last, dob, phone, email, graduation_date, rank, education);
                        candidates.add(candidates1);
                    }
                } catch (IOException | ParseException e) {
                    e.printStackTrace();
                }
            } else if (fileName.equals(Management.fileIntern)) {
                try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                    String line;
                    while((line = reader.readLine()) != null) {
                        String []data = line.split("\\s+");
                        String id = data[0];
                        String first = data[1];
                        String last = data[2];
                        Date dob = new SimpleDateFormat("dd/MM/yyyy").parse(data[3]);
                        String phone = data[4];
                        String email = data[5];
                        String major = data[6];
                        int semester = Integer.parseInt(data[7].trim());
                        String university = data[8];
                        Candidates candidates1 = new InternCandidates(id, first, last, dob, phone, email, major, semester, university);
                        candidates.add(candidates1);
                    }
                } catch (IOException | ParseException e) {
                    e.printStackTrace();
                }
            }
            return candidates;
    }
    public static void writeToFile(ArrayList<Candidates> candidates, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            for (Candidates c : candidates) {
                writer.write(c.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void updateToFile(ArrayList<Candidates> candidates, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Candidates c : candidates) {
                writer.write(c.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static boolean duplicate(String inp, String fileName) {
        ArrayList<Candidates> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean isEmpty = true;
            while((line = reader.readLine()) != null) {
                isEmpty = false;
                String[] data = line.split("\\s+");
                String id = data[0];
                String first = data[1];
                String last = data[2];
                Date dob = new SimpleDateFormat("dd/MM/yyyy").parse(data[3]);
                String phone = data[4];
                String email = data[5];
                Candidates candidates = new Candidates(id, first, last, dob, phone, email);
                list.add(candidates);
            }
            if (isEmpty) {
                return true;
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        for (Candidates c : list) {
            if (c.getPhone().equals(inp) || c.getEmail().equals(inp)) {
                return false;
            }
        }
        return true;
    }
    // xong -----------


    // quản lý tài khoản người dùng
    public static ArrayList<User> read (String fileName) {
        ArrayList<User> result = new ArrayList<User>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while((line = reader.readLine()) != null) {
                String []data = line.split("\\s+");
                String username = data[0];
                String password = data[1];
                User user = new User(username, password);
                result.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static void signin(ArrayList<User> list, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            for (User user : list) {
                writer.write(user.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateFileGrating(ArrayList<User> list, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (User user : list) {
                writer.write(user.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void signup(User user, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(user.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static boolean checkDuplicate(String inp, String fileName) {
        ArrayList<User> list = new ArrayList<User>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean isEmpty = true;
            while((line = reader.readLine()) != null) {
                isEmpty = false;
                String[] data = line.split("\\s+");
                String username = data[0];
                String password = data[1];
                User u = new User(username, password);
                list.add(u);
            }
            if (isEmpty) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (User user1 : list) {
            if (user1.getUsername().equals(inp)) {
                return false;
            }
        }
        return true;
    }
    public static boolean checkSignIn(User user, String fileName) {
        ArrayList<User> list = new ArrayList<User>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean isEmpty = true;
            while((line = reader.readLine()) != null) {
                isEmpty = false;
                String[] data = line.split("\\s+");
                String username = data[0];
                String password = data[1];
                User user1 = new User(username, password);
                list.add(user1);
            }
            if (isEmpty) {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (User user1 : list) {
            if (user1.getUsername().equals(user.getUsername()) && user1.getPassword().equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }
    // xong------------
}
