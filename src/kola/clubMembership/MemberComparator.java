/**
 *
 * @author kola
 */
package kola.clubMembership;

import java.util.Comparator;
import kola.clubMembership.main.Member;
import kola.clubMembership.members.Staff;

public class MemberComparator implements Comparator<Member>{
    @Override
    public int compare(Member member1, Member member2)
    {
        if (member1.getClass().getName().equals(member2.getClass().getName())) {
            long diff = member1.getDateOfRegistration().getTime() - member2.getDateOfRegistration().getTime();
            
            if (diff != 0) {
                return diff > 0 ? 1 : -1;
            }
            
            return 0;
        }
        
        if (member1 instanceof Staff) {
            return -1;
        }
        
        return 1;
    }
}
