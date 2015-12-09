/**
 * This class handles the ordering of a group of borrowers in a queue according to priority.
 * 
 * @author kola <kola.erinoso@gmail.com>
 * @date 25/11/2015
 */

package kola.clubMembership.administration;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.PriorityQueue;
import kola.clubMembership.main.Member;
import kola.clubMembership.members.MemberComparator;

public class Borrow {
    /**
     * Handle the ordering of a group of borrowers in a queue
     * 
     * @param members Group of borrowers
     * @return borrowers Queue of borrowers ordered according to priority
     */
    public static PriorityQueue<Member> queueForBook(Member[] members)
    {
        PriorityQueue<Member> borrowers = new PriorityQueue<>(new MemberComparator());
        borrowers.addAll(new ArrayList<>(Arrays.asList(members)));
        
        return borrowers;
    }
}