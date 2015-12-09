/**
 * This class models a staff.
 * 
 * @author kola <kola.erinoso@gmail.com>
 * @date 25/11/2015
 */

package kola.clubMembership.members;

import kola.clubMembership.main.Member;

public class Staff extends Member
{
    private int staffNum, assignedStudentNum;
    private double netPay;
    
    public Staff()
    {}
    
    /**
     * Handle initialization of some properties at instantiation
     * 
     * @param fullName
     * @param email
     * @param phoneNumber
     * @param dateOfBirth
     * @param gender
     * @param staffNum
     * @param assignedStudentNum
     * @param netPay 
     */
    public Staff(String fullName, String email, String phoneNumber, String dateOfBirth, Character gender, Integer staffNum, Integer assignedStudentNum, Double netPay)
    {
        super(fullName, email, phoneNumber, dateOfBirth, gender);
        this.staffNum = staffNum;
        this.assignedStudentNum = assignedStudentNum;
        this.netPay = netPay;
    }
    
    @Override
    public String toString()
    {
        return String.format("Staff: {Name: %s, Gender: %c, Number: %d}", this.getFullName(), this.getGender(), staffNum);
    }
    
    /**
     * Assign students to staff
     * 
     * @param assignedStudentNum
     */
    public void setAssignedStudentNum(int assignedStudentNum)
    {
        this.assignedStudentNum = assignedStudentNum;
    }
    
    /**
     * @return assignedStudentNum
     */
    public int getAssignedStudentNum()
    {
        return assignedStudentNum;
    }
    
    /**
     * @param staffNum 
     */
    public void setStaffNum(int staffNum)
    {
        this.staffNum = staffNum;
    }
    
    /**
     * @return staffNum
     */
    public int getStaffNum()
    {
        return staffNum;
    }
    
    /**
     * @param netPay 
     */
    public void setNetPay(double netPay)
    {
        this.netPay = netPay;
    }
    
    /**
     * @return netPay
     */
    public double getNetPay()
    {
        return netPay;
    }
}