import java.util.*;
public class Guss_Number {
    public static int returnPoints(int num)
    {
        int score=0;
        if (num>=80) {
            score=5;
        }else if (num>=60) {
            score=4;
        }else if (num>=40) {
            score=3;
        }else if (num>=20) {
            score=2;
        }else if (num>=0) {
            score=1;
        }
      return score;
    }
  public static void main(String[] args) {

        int min = 10;
        int max = 100;
    
        Random random = new Random();
        int num,choice=0;
        int guss = random.nextInt(max - min + 1) + min;
        int round=1;
        System.out.println("--You have only 10 rounds for guessing the number--");
       do{
           int nguss=0;
        do {
            System.out.println("Enter your guess:");
            num=new Scanner(System.in).nextInt();
            if (guss>num) {
                System.out.println("Too low ");
            }else if(num>guss)
            {
                System.out.println("Too high");
            }
            else
            {
                System.out.println("Congrats you guess the number.");
            }
            nguss=nguss+10;
            if (nguss==100) {
                break;
            }
            } while (num!=guss);
            if (num==guss) {
             System.out.println("---GAME COMPLETE---");
             System.out.println("You Guess the number in "+nguss/10+" steps in round :"+round);
             System.out.println("Your score is "+nguss+" out of 100");
             System.out.println("You got "+Guss_Number.returnPoints(nguss)+" points.");
             break;
            }else
            {
                System.out.println("10 chance has been completed");
                System.out.println("Want more round then pesss 1 and 0 for quit");
                choice =new Scanner(System.in).nextInt();
                if (choice==0) System.exit(0);
            }
            ++round;
        }while(choice==1);
    }
    }
