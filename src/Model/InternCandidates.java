/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Dell Latitude 7490
 */
public class InternCandidates extends Candidates{
    private String Major;
    private int Semester;
    private String UniversityName;

    public InternCandidates(String CandidateID, String FirstName, String LastName, Date BirthDate, String Phone, String Email, String Major, int Semester, String UniversityName) {
        super(CandidateID, FirstName, LastName, BirthDate, Phone, Email);
        this.Major = Major;
        this.Semester = Semester;
        this.UniversityName = UniversityName;
    }

    public String getMajor() {
        return Major;
    }

    public void setMajor(String Major) {
        this.Major = Major;
    }

    public int getSemester() {
        return Semester;
    }

    public void setSemester(int Semester) {
        this.Semester = Semester;
    }

    public String getUniversityName() {
        return UniversityName;
    }

    public void setUniversityName(String UniversityName) {
        this.UniversityName = UniversityName;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDob = sdf.format(super.getBirthDate());
        String formattedString = String.format("%-10s%-10s%-20s%-15s%-30s%-30s%-20s%-20s%-20s",
                super.getCandidateID(), super.getFirstName(), super.getLastName(), formattedDob, super.getPhone(), super.getEmail(), Major, Semester, UniversityName);
        return formattedString;
    }
    
}
