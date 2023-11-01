/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author Dell Latitude 7490
 */
public class FresherCandidates extends Candidates{
    private Date Graduation_date;
    private String Graduation_rank;
    private String Education;

    public FresherCandidates(String CandidateID, String FirstName, String LastName, Date BirthDate, String Phone, String Email, Date Graduation_date, String Graduation_rank, String Education) {
        super(CandidateID, FirstName, LastName, BirthDate, Phone, Email);
        this.Graduation_date = Graduation_date;
        this.Graduation_rank = Graduation_rank;
        this.Education = Education;
    }

    public Date getGraduation_date() {
        return Graduation_date;
    }

    public void setGraduation_date(Date Graduation_date) {
        this.Graduation_date = Graduation_date;
    }

    public String getGraduation_rank() {
        return Graduation_rank;
    }

    public void setGraduation_rank(String Graduation_rank) {
        this.Graduation_rank = Graduation_rank;
    }

    public String getEducation() {
        return Education;
    }

    public void setEducation(String Education) {
        this.Education = Education;
    }
    
    
    
}
