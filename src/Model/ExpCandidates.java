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
public class ExpCandidates extends Candidates{
    private int ExpInYear;
    private String ProSkill;

    public ExpCandidates(String CandidateID, String FirstName, String LastName, Date BirthDate, String Phone, String Email, int ExpInYear, String ProSkill) {
        super(CandidateID, FirstName, LastName, BirthDate, Phone, Email);
        this.ExpInYear = ExpInYear;
        this.ProSkill = ProSkill;
    }

    public int getExpInYear() {
        return ExpInYear;
    }

    public void setExpInYear(int ExpInYear) {
        this.ExpInYear = ExpInYear;
    }

    public String getProSkill() {
        return ProSkill;
    }

    public void setProSkill(String ProSkill) {
        this.ProSkill = ProSkill;
    }
    
    
    
}
