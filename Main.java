import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        User justin = new User("Justin");
        User aries = new User("Aries");
        User pig = new User("Pig");

        justin.addFriend(aries);
        justin.addFriend(pig);
        justin.printAllFriends();


        justin.printAllPayerTran();
        Transaction t1 = new Transaction("午饭", justin, LocalDate.now(), 21.00, new User[]{justin,aries}, new Double[]{10.5,10.5});
        Transaction t2 = new Transaction("晚饭", justin, LocalDate.now(), 41.00, new User[]{justin,aries}, new Double[]{30.5,10.5});
        justin.addPayerTran(t1);
        justin.addPayerTran(t2);
        justin.printAllPayerTran();
        aries.printAllDebtorTran();
    }
}