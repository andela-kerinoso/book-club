package kola.clubMembership.Test;

import org.junit.Test;
import java.util.PriorityQueue;
import kola.clubMembership.Borrow;
import kola.clubMembership.members.*;
import kola.clubMembership.main.Member;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class BorrowTest {
    /**
     * Test of queueForBook method, of class Borrow.
     * 
     * @throws java.lang.InterruptedException
     */
    @Test
    public void testQueueForBook() throws InterruptedException
    {       
        Member member4 = new Student("Kola", "kola@gmail.com", "09123456789", "04-05-1990", 'M', 12, 20);
        Thread.sleep(1);
        Member member3 = new Staff("Kamiye", "abbasakande@gmail.com", "09123456789", "06-01-1967", 'M', 40, 12, 100.45);
        Thread.sleep(1);
        Member member2 = new Student("Yemisi", "danvery@gmail.com", "09123456789", "04-05-1991", 'M', 5, 4);
        Thread.sleep(1);
        Member member1 = new Staff("Verem", "abbasakande@gmail.com", "09123456789", "06-01-1967", 'M', 40, 12, 100.45);
        
        Member[] members = new Member[]{member1, member2, member3, member4};
        PriorityQueue<Member> borrowers = Borrow.queueForBook(members);
        
        assertEquals("Hello", member3, borrowers.poll());
        assertEquals(member1, borrowers.poll());
        assertEquals(member4, borrowers.poll());
        assertEquals(member2, borrowers.poll());
        assertTrue(borrowers.isEmpty());
    }
    
}
