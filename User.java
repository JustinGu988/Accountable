import java.util.*;

public class User {
    String userName;
    ArrayList<User> friends;
    ArrayList<Transaction> payerTran;   //u paid the bill
    ArrayList<Transaction> debtorTran;  //others paid the bill


    public User (String userName){
        this.userName = userName;
        this.friends = new ArrayList<User>();
        this.payerTran = new ArrayList<Transaction>();
        this.debtorTran = new ArrayList<Transaction>();
    }

    
    public String getUserName() {return userName;}
    public void setUserName(String userName) {this.userName = userName;}

    public ArrayList<User> getFriends() {return friends;}
    public void setFriends(ArrayList<User> friends) {this.friends = friends;}

    public ArrayList<Transaction> getPayerTran() {return payerTran;}
    public void setPayerTran(ArrayList<Transaction> payerTran) {this.payerTran = payerTran;}

    public ArrayList<Transaction> getDebtorTran() {return debtorTran;}
    public void setDebtorTran(ArrayList<Transaction> debtorTran) {this.debtorTran = debtorTran;}



    ///////////////////////////////////
    //Friends
    ///////////////////////////////////
    public void addFriend(User f){
        //avoid duplication
        if(this.friends.contains(f)){
            System.out.println("Friend exists");
            return;
        }

        ArrayList<User> temp = this.getFriends();
        temp.add(f);
        this.setFriends(temp);
    }

    public void removeFriend(User f){
        if(this.friends.contains(f)){
            ArrayList<User> temp = this.getFriends();
            temp.remove(f);
            this.setFriends(temp);
        } else {
            System.out.println("Friend does not exist");
        }
    }

    public void printAllFriends(){
        System.out.println("All friends:");
        for(User curr : this.getFriends()) System.out.println(curr.getUserName());
        System.out.println();
    }





    ///////////////////////////////////
    //Transaction
    ///////////////////////////////////
    public void addPayerTran(Transaction pt){
        ArrayList<Transaction> temp = this.getPayerTran();
        temp.add(pt);
        this.setPayerTran(temp);

        //Add debtor tran to other debtors
        User[] allParticipent = pt.getBillParticipent();
        for(User p : allParticipent){
            //Avoid adding myself as a debtor
            if(this == p) continue;
            else p.addDebtorTran(pt);
        }
    }

    public void addDebtorTran(Transaction dt){
        ArrayList<Transaction> temp = this.getDebtorTran();
        temp.add(dt);
        this.setDebtorTran(temp);
    }

    public void printAllPayerTran(){
        if(this.getPayerTran() == null || this.getPayerTran().size() == 0){
            System.out.println("No Payer Transactions for " + this.getUserName());
            return;
        }
        System.out.println(this.getUserName() + " as a payer:");
        for(Transaction curr : this.getPayerTran()) curr.printOneTransaction();
        System.out.println();
    }

    public void printAllDebtorTran(){
        if(this.getDebtorTran() == null || this.getDebtorTran().size() == 0){
            System.out.println("No Debtor Transactions for " + this.getUserName());
            return;
        }
        System.out.println(this.getUserName() + " as a debtor:");
        for(Transaction curr : this.getDebtorTran()) {
            System.out.print("Paid by: " + curr.getPayer().getUserName() + " ");
            curr.printOneTransaction();
        }
        System.out.println();
    }
    
}