/**
 * This is the base class for all types of membership of the club.
 * 
 * @author kola <kola.erinoso@gmail.com>
 * @date 23/11/2015
 */

package kola.clubMembership.main;

import java.util.Date;

public class Member {

    private String fullName, email, phoneNumber, dateOfBirth;
    private char gender;
    private Date dateOfRegistration;
    
    /**
     * Handle initialization of some properties at instantiation
     * 
     * @param fullName
     * @param email
     * @param phoneNumber
     * @param dateOfBirth
     * @param gender 
     */
    public Member(String fullName, String email, String phoneNumber, String dateOfBirth, Character gender)
    {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.dateOfRegistration = new Date();
    }
    
    /**
     * @param fullName 
     */
    public void setFUllName(String fullName)
    {
        this.fullName = fullName;
    }
    
    /**
     * @return fullName
     */
    public String getFullName()
    {
        return fullName;
    }
    
    /**
     * @param email 
     */
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    /**
     * @return email
     */
    public String getEmail()
    {
        return email;
    }
    
    /**
     * @param phoneNumber 
     */
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
    
    /**
     * @return phoneNumber
     */
    public String getPhoneNumber()
    {
        return phoneNumber;
    }
    
    /**
     * @param gender 
     */
    public void setGender(char gender)
    {
        this.gender = gender;
    }
    
    /**
     * @return gender
     */
    public char getGender()
    {
        return gender;
    }
    
    /**
     * @return dateOfRegistration
     */
    public Date getDateOfRegistration()
    {
        return dateOfRegistration;
    }
}