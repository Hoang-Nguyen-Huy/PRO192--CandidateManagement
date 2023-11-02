package Controller;

import Model.Candidates;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FileHandler {

    public static ArrayList<Candidates> readFromFile (String fileName) {
            ArrayList<Candidates> candidates = new ArrayList<>();

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
                    Candidates candidates1 = new Candidates(id, first, last, dob, phone, email);
                    candidates.add(candidates1);
                }
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
            return  candidates;
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
            while((line = reader.readLine()) != null) {
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
