/** This is my internship 1st Project**/
/*Online Reservation System */

import java.util.*;
import java.sql.*;
import java.sql.Date;
class Login
{
    private String username;
    private String password;
    public String getPassword() {
        return password;
    }
    public String getUsername() {
        return username;
    }
    Login()
    {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the User Name :");
        this.username=sc.nextLine();
        System.out.print("-:Enter Password:");
        this.password=sc.nextLine();
        System.out.println();
    }
}
class PNR_Record
{
    private int pnrNumber;
    private String passengerName;
    private String trainNumber;
    private String trainName;
    private String classType;
    private Date journeyDate;
    private String from;
    private String to;
    PNR_Record()
    {
        Scanner sc=new Scanner(System.in);
        pnrNumber=new Random().nextInt(9999) + 1000;
        System.out.println("Enter Passerger name:");
        passengerName=sc.nextLine();
        System.out.println("Enter train Name:");
        trainName=sc.nextLine();
        System.out.println("Enter train numbetr:");
        trainNumber=sc.nextLine();
        System.out.println("Enter train class");
        classType=sc.nextLine();
        System.out.println("Enter date of journey [Date shound be in: YYYY-MM-DD ] :");
        String temp=sc.nextLine();
        journeyDate=Date.valueOf(temp);
        System.out.println("Enter Journey Starion:");
        from=sc.nextLine();
        System.out.println("Enter Destination Station:");
        to=sc.nextLine();   
    }
    public String getClassType() {
        return classType;
    }
    public String getFrom() {
        return from;
    }
    public Date getJourneyDate() {
        return journeyDate;
    }
    public String getPassengerName() {
        return passengerName;
    }
    public int getPnrNumber() {
        return pnrNumber;
    }
    public String getTo() {
        return to;
    }
    public String getTrainNumber() {
        return trainNumber;
    }
    public String getTrainName() {
        return trainName;
    }
}


public class Reservation {
    public static void main(String[] args) {
        Login login=new Login();
    
        String username=login.getUsername();
        String password=login.getPassword();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Booking_Info",username,password);  
            System.out.println("User has connected Syccefully");
            while (true) {
                String InsertQuery = "INSERT INTO reservation( pnr_number,Name,Train_name,Train_class,Train_number,Journey_date,Source_Station,Destination_Station) VALUES (?,?,?,?,?,?,?,?)";
                String DeleteQuery = "DELETE FROM reservation WHERE pnr_number = ?";
                String ShowQuery = "SELECT * FROM reservation";

                System.out.println("----Enter yuor choice---- ");
                System.out.println("1. Insert Record.\n");
                System.out.println("2. Delete Record.\n");
                System.out.println("3. Show All Records.\n");
                System.out.println("4. Exit.\n");
                int choice=new Scanner(System.in).nextInt();
                switch (choice) {
                    case 1:
                     synchronized(login)
                        {
                        PNR_Record user_Record=new PNR_Record();
                        int pnr=user_Record.getPnrNumber();
                        String name=user_Record.getPassengerName();
                        String train_name=user_Record.getTrainName();
                        String cls=user_Record.getClassType();
                        String t_num=user_Record.getTrainNumber();
                        Date date=user_Record.getJourneyDate();
                        String to=user_Record.getTo();
                        String from=user_Record.getFrom();
                        try {
                            PreparedStatement preparedStatement=con.prepareStatement(InsertQuery);
                            preparedStatement.setInt(1, pnr);
                            preparedStatement.setString(2, name);
                            preparedStatement.setString(3, train_name);
                            preparedStatement.setString(4, cls);
                            preparedStatement.setString(5, t_num);
                            preparedStatement.setDate(6, date);
                            preparedStatement.setString(7, to);
                            preparedStatement.setString(8, from);
                            int rowsAffected = preparedStatement.executeUpdate();
                            if (rowsAffected > 0) {
                                System.out.println("Record added successfully.");
                            }

                            else {
                                System.out.println("No records were added.");
                            }

                        } catch (SQLException e) {
                            System.out.println("SQL Exception:"+e.getMessage());
                        }
                      }
                        break;
                    case 2:
                      synchronized(login)
                      {
                        System.out.println("Enter the PNR number to delete the record : ");
                        int pnrNumber = new Scanner(System.in).nextInt();
                        try {
                            PreparedStatement preparedStatement=con.prepareStatement(DeleteQuery);
                            preparedStatement.setInt(1, pnrNumber);
                            int rowsAffected = preparedStatement.executeUpdate();

                            if (rowsAffected > 0) {
                                System.out.println("Record deleted successfully.");
                            }

                            else {
                                System.out.println("No records were deleted.");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                       }
                    case 3:
                      synchronized(login)
                        {
                            try {
                            System.out.println("------Table------");
                            PreparedStatement preparedStatement = con.prepareStatement(ShowQuery);
                            ResultSet rs = preparedStatement.executeQuery();
                            while(rs.next())
                            {
                                  System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4)+"  "+rs.getString(5)+"  "+rs.getString(6)+"  "+rs.getString(7)+"  "+rs.getString(8));
                            }
             
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                       } 
                    case 4:
                        System.exit(0);
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
           System.out.println("Enterd wrong Password ");
           e.printStackTrace();
        }
    }
}
