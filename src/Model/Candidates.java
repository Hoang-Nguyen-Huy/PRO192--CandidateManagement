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
public class Candidates {
    private String CandidateID;
    private String FirstName;
    private String LastName;
    private Date BirthDate;
    private String Phone;
    private String Email;

    public Candidates(String CandidateID, String FirstName, String LastName, Date BirthDate, String Phone, String Email) {
        this.CandidateID = CandidateID;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.BirthDate = BirthDate;
        this.Phone = Phone;
        this.Email = Email;
    }

    public String getCandidateID() {
        return CandidateID;
    }

    public void setCandidateID(String CandidateID) {
        this.CandidateID = CandidateID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public Date getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(Date BirthDate) {
        this.BirthDate = BirthDate;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    
    
}
