package Controller;

import Model.Candidates;
import Model.ExpCandidates;
import Model.FresherCandidates;
import Model.InternCandidates;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FileHandler {

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
}
