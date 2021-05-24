import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        User justin = new User("Justin");
        User aries = new User("Aries");
        User pig = new User("Pig");

        justin.addFriend(aries);
        justin.addFriend(pig);
        aries.addFriend(pig);
        

        Transaction t1 = new Transaction("lunch", justin, LocalDate.now(), 21.00, new User[]{justin,aries}, new Double[]{10.5,10.5});
        justin.addPayerTran(t1);
        Transaction t2 = new Transaction("dinner", justin, LocalDate.now(), 41.00, new User[]{justin,aries}, new Double[]{29.5,11.5});
        justin.addPayerTran(t2);
        Transaction t3 = new Transaction("toy", pig, LocalDate.now(), 105.90, new User[]{pig,aries}, new Double[]{105.0,0.9});
        pig.addPayerTran(t3);
        
        justin.printAllPayerTran();
        pig.printAllPayerTran();
        aries.printDebtorTranByFriend(justin);
        aries.printDebtorTranByFriend(pig);
    }
}