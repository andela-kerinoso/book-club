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
    {
        this(null, null, null, null, null, null, null, null);
    }
    
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
    
    public int getAssignedStudentNum()
    {
        return assignedStudentNum;
    }
    
    public void setStaffNum(int staffNum)
    {
        this.staffNum = staffNum;
    }
    
    public int getStaffNum()
    {
        return staffNum;
    }
    
    public void setNetPay(double netPay)
    {
        this.netPay = netPay;
    }
    
    public double getNetPay()
    {
        return netPay;
    }
}