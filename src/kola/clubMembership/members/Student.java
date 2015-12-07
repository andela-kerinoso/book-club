/**
 * This class models a student.
 * 
 * @author kola <kola.erinoso@gmail.com>
 * @date 25/11/2015
 */

package kola.clubMembership.members;

import kola.clubMembership.main.Member;

public class Student extends Member
{
    private int studentNum, studentClass;
    
    public Student()
    {
        this(null, null, null, null, null, null, null);
    }
    
    public Student(String fullName, String email, String phoneNumber, String dateOfBirth, Character gender, Integer studentNum, Integer studentClass)
    {
        super(fullName, email, phoneNumber, dateOfBirth, gender);
        this.studentNum = studentNum;
        this.studentClass = studentClass;
    }
    
    @Override
    public String toString()
    {
        return String.format("Student: {Name: %s, Gender: %c, Number: %d, Class: %d}", this.getFullName(), this.getGender(), studentNum, studentClass);
    }
    
    public void setStudentNum(int studentNum)
    {
        this.studentNum = studentNum;
    }
    
    public int getStudentNum()
    {
        return studentNum;
    }
    
    public void setStudentClass(int studentClass)
    {
        this.studentClass = studentClass;
    }
    
    public double getStudentClass()
    {
        return studentClass;
    }
}
