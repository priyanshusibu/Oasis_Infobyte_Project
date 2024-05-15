import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.time.LocalTime;

class Bank {
    /*I have deposite 1000 ruprres as a initial deposit */
    private static int balance=1000;//innitial deposite 
    private String id="system";
    private String password="system@123";
    private String bankName="PUNJAB NATONAL BANK";
    private String transaction_history="Innitial Amount you have "+balance+" ruprrs .\n";
    public static void setBalance(int amount) {
        Bank.balance = Bank.balance+amount;
    }
    public static int getBalance() {
        return balance;
    }
    public String getId() {
        return id;
    }
    public String getPassword() {
        return password;
    }
    public String getbankName()
    {
        return bankName;
    }
    public void setTransaction_history(String transaction_history) {

        this.transaction_history = this.transaction_history+transaction_history;
    }
    public String getTransaction_history() {
        return transaction_history;
    }

}
public class ATM extends Bank{
     Bank bank=new Bank();
     public String id=bank.getId();
     public String password=bank.getPassword();
     Date currentDate = new Date();
     LocalTime currentTime = LocalTime.now();
     String date=String.valueOf(currentDate);
     String time=String.valueOf(currentTime);

     public void deposite(int amount)
     {
       setBalance(amount); 
       String details="Amount added "+amount+" on date "+date+" and time at "+time+"\n";
       setTransaction_history(details);
     }

     public int  withdraw(int amount)
     {
      int temp_balance= getBalance();
      temp_balance=temp_balance-amount;
      if (temp_balance<0) {
        System.out.println("Insufficient balance to withdraw");
        return 1;
      }
        setBalance(temp_balance);
        String details="Amont withdraw "+amount+" rupees , on date "+date+" and time at "+time+"\n";
        setTransaction_history(details);
        return 0;
     }

     public int trasfer(int amount,long ac_no)
     {
        int temp_balance= getBalance();
        temp_balance=temp_balance-amount;
        if (temp_balance<0) {
            System.out.println("Insufficient balance to transfer : ");
            return 1;
          }
          setBalance(temp_balance);
          String details="Amount transfer "+amount+" rupees , on date "+date+" and time at "+time+" to the account number "+ac_no+" .\n";
          setTransaction_history(details);
          return 0;
        
     }

}
 class  Test {
   static int agn;
    public static void main(String[] args) throws Exception{
        ATM atm=new ATM();
        Scanner sc=new Scanner(System.in);
        System.out.println("\t\tWELCOME TO THE  "+atm.getbankName()+" ATM");
        System.out.println("Enter your id:");
        String id=sc.nextLine();
        System.out.println("Enter your password: ");
        String pass=sc.nextLine();
        if (atm.id.equals(id) && atm.password.equals(pass)) {
            System.out.println("---LOGIN SUCCESSFULLY--");
            do {
                System.out.println("Enter \n 1 for deposite \n 2 for withdraw \n 3 for transfer \n 4 for check transaction history \n 5 for chech balsnce \n 6 for  QUIT your choice:");
                int choice=sc.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Enter the money you want to deposite : ");
                        int amount=sc.nextInt();
                        atm.deposite(amount);
                        System.out.println("Deposite successfully");
                        break;
                    case 2:
                        System.out.println("Enter the money you want to withwraw :");
                        int money=sc.nextInt();
                        if (atm.withdraw(money)==0) {
                            System.out.println("Withdraw Syccessfull");
                        } else
                        {
                            System.out.println("Enter 1 for see total balance 0 for exit  :");
                            int a=sc.nextInt();
                            if (a==1) 
                                System.out.println("Your balance is :"+atm.getBalance());
                            else
                                System.exit(0);
                        }
                        break;
                    case 3:
                        System.out.println("Enter the amount to transfer :");
                        int amt=sc.nextInt();
                        System.out.println("Enter the account number which u want to transfer :");
                        long ac_no=sc.nextLong();
                        if (atm.trasfer(amt,ac_no)==0) {
                            System.out.println("Trasfer Syccessfully");
                        }else
                        {
                            System.out.println("Insufficient balance to transfer in your account");
                        }
                        break;
                    case 4:
                        System.out.println("---ATM HISTORY---");
                        System.out.println(atm.getTransaction_history());
                        break;
                    case 5:
                        System.out.println("Avaliable balance in your account is :"+atm.getBalance());
                        break;
                    case 6:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid entry entered...");
                        break;
                }
            System.out.println("Press 1 for work again O for go:");
            agn=sc.nextInt();
        } while (agn==1);
        }else
        {
            System.out.println("INVALID LOGIN CRIATRIA");
        }

    }
}