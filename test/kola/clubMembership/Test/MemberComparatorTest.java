/**
 * This tests all public methods of class MemberComparator.
 * 
 * @author kola <kola.erinoso@gmail.com>
 * @date 07/12/2015
 */
package kola.clubMembership.Test;

import org.junit.Test;
import static org.junit.Assert.*;
import kola.clubMembership.main.Member;
import kola.clubMembership.members.Staff;
import kola.clubMembership.members.Student;
import kola.clubMembership.MemberComparator;

public class MemberComparatorTest {
    /**
     * Test of compare method, of class MemberComparator.
     * @throws java.lang.InterruptedException
     */
    @Test
    public void testCompare() throws InterruptedException {
        Member student1 = new Student("Kola", "kola@gmail.com", "09123456789", "04-05-1990", 'M', 12, 20);
        Thread.sleep(1);
        Member staff1 = new Staff("Kamiye", "abbasakande@gmail.com", "09123456789", "06-01-1967", 'M', 40, 12, 100.45);
        Member staff2 = new Staff("Verem", "abbasakande@gmail.com", "09123456789", "06-01-1967", 'M', 40, 12, 100.45);
        Thread.sleep(1);
        Member student2 = new Student("Yemisi", "danvery@gmail.com", "09123456789", "04-05-1991", 'M', 5, 4);
        
        MemberComparator memberCoparator = new MemberComparator();
        
        assertEquals(-1, memberCoparator.compare(student1, student2));
        assertEquals(1, memberCoparator.compare(student2, staff1));
        assertEquals(0, memberCoparator.compare(staff1, staff2));
    }
}
