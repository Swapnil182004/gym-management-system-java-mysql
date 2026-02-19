/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package project1;

import java.sql.*;

import java.util.Scanner;
/**
 *
 * @author SWAPNIL
 */
public class Project1 {
   
   static final String DB_URL = "jdbc:mysql://localhost:3306/project1";
   static final String USER = "root";
   static final String PASS = "";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            conn = DriverManager.getConnection(DB_URL, USER,PASS);
            stmt = conn.createStatement();
            int password=0, user_password=0;
            String username="", user_username="";
            Scanner sc = new Scanner(System.in);

            System.out.println("1 for SignUp");
            System.out.println("2 for Login");
            System.out.print("Enter your choice: ");
            int ch = sc.nextInt();
            
            while(true){
                switch(ch){
                //-----------------signup-----------------
                case 1:
                    System.out.print("Create a username: ");
                    username = sc.next();
                    System.out.print("Enter a Password: ");
                    password = sc.nextInt();
                    
                    String insertQuery = "INSERT INTO users(username, password) VALUES(? , ?)";
                    PreparedStatement ps = conn.prepareStatement(insertQuery);
                    ps.setString(1, username);
                    ps.setInt(2, password);
                    
                    ps.executeUpdate();
                    System.out.println("Signup Successful!");
                    
                    break;

                case 2:
                    System.out.print("Enter the username: ");
                    user_username = sc.next();
                    System.out.print("Password: ");
                    user_password = sc.nextInt();

                    String loginQuery = "SELECT * FROM users WHERE username=? AND password=?";
                    PreparedStatement ps2 = conn.prepareStatement(loginQuery);
                    ps2.setString(1, user_username);
                    ps2.setInt(2, user_password);

                    ResultSet rs = ps2.executeQuery();

                    if(rs.next()){
                        System.out.println("Login Successful!");
                        
                        
                        
                        int choice = 0;
                        while(choice != 7){
                            showMenu();
                            System.out.print("Enter your choice: ");
                            choice = sc.nextInt();
                            sc.nextLine();
                            switch(choice){
                            case 1:
                                System.out.print("Enter the member name: ");
                                String name = sc.nextLine();

                                System.out.print("Enter age: ");
                                int age = sc.nextInt();
                                sc.nextLine();  // clear buffer

                                System.out.print("Enter gender (Male/Female): ");
                                String gender = sc.nextLine();

                                System.out.print("Enter phone number: ");
                                String phone = sc.nextLine();

                                System.out.print("Enter date of admission (YYYY-MM-DD): ");
                                String date = sc.nextLine();

                                String insertMemberQuery =
                                    "INSERT INTO members (name, age, gender, phone, date_of_admission) VALUES (?, ?, ?, ?, ?)";

                                PreparedStatement psMember = conn.prepareStatement(insertMemberQuery);

                                psMember.setString(1, name);
                                psMember.setInt(2, age);
                                psMember.setString(3, gender);
                                psMember.setString(4, phone);
                                psMember.setString(5, date);

                                psMember.executeUpdate();

                                System.out.println("Member added successfully...");
                                break;
                                
                           case 2:

                                String showQuery = "SELECT * FROM members";
                                PreparedStatement ps3 = conn.prepareStatement(showQuery);

                                ResultSet rs1 = ps3.executeQuery();

                                System.out.println("\n--------------- MEMBER LIST ---------------");

                                while (rs1.next()) {

                                    System.out.println(
                                        "ID: " + rs1.getInt("id") +
                                        " | Name: " + rs1.getString("name") +
                                        " | Age: " + rs1.getInt("age") +
                                        " | Gender: " + rs1.getString("gender") +
                                        " | Phone: " + rs1.getString("phone") +
                                        " | Admission Date: " + rs1.getDate("date_of_admission")
                                    );
                                }

                                break;
                                
                           case 3:
                                System.out.print("Enter Member ID to update: ");
                                int updateId = sc.nextInt();
                                sc.nextLine();   // clear buffer

                                System.out.println("What do you want to update?");
                                System.out.println("1. Name");
                                System.out.println("2. Age");
                                System.out.println("3. Gender");
                                System.out.println("4. Phone");
                                System.out.println("5. Date of Admission");

                                System.out.print("Enter your choice: ");
                                int updateChoice = sc.nextInt();
                                sc.nextLine();   // clear buffer

                                String updateQuery = "";

                                switch(updateChoice) {

                                    case 1:
                                        System.out.print("Enter new name: ");
                                        String newName = sc.nextLine();

                                        updateQuery = "UPDATE members SET name=? WHERE id=?";
                                        PreparedStatement psUpdate1 = conn.prepareStatement(updateQuery);
                                        psUpdate1.setString(1, newName);
                                        psUpdate1.setInt(2, updateId);

                                        psUpdate1.executeUpdate();
                                        System.out.println("Name updated successfully!");
                                        break;

                                    case 2:
                                        System.out.print("Enter new age: ");
                                        int newAge = sc.nextInt();

                                        updateQuery = "UPDATE members SET age=? WHERE id=?";
                                        PreparedStatement psUpdate2 = conn.prepareStatement(updateQuery);
                                        psUpdate2.setInt(1, newAge);
                                        psUpdate2.setInt(2, updateId);

                                        psUpdate2.executeUpdate();
                                        System.out.println("Age updated successfully!");
                                        break;

                                    case 3:
                                        System.out.print("Enter new gender: ");
                                        String newGender = sc.nextLine();

                                        updateQuery = "UPDATE members SET gender=? WHERE id=?";
                                        PreparedStatement psUpdate3 = conn.prepareStatement(updateQuery);
                                        psUpdate3.setString(1, newGender);
                                        psUpdate3.setInt(2, updateId);

                                        psUpdate3.executeUpdate();
                                        System.out.println("Gender updated successfully!");
                                        break;

                                    case 4:
                                        System.out.print("Enter new phone number: ");
                                        String newPhone = sc.nextLine();

                                        updateQuery = "UPDATE members SET phone=? WHERE id=?";
                                        PreparedStatement psUpdate4 = conn.prepareStatement(updateQuery);
                                        psUpdate4.setString(1, newPhone);
                                        psUpdate4.setInt(2, updateId);

                                        psUpdate4.executeUpdate();
                                        System.out.println("Phone updated successfully!");
                                        break;

                                    case 5:
                                        System.out.print("Enter new date (YYYY-MM-DD): ");
                                        String newDate = sc.nextLine();

                                        updateQuery = "UPDATE members SET date_of_admission=? WHERE id=?";
                                        PreparedStatement psUpdate5 = conn.prepareStatement(updateQuery);
                                        psUpdate5.setString(1, newDate);
                                        psUpdate5.setInt(2, updateId);

                                        psUpdate5.executeUpdate();
                                        System.out.println("Date updated successfully!");
                                        break;

                                    default:
                                        System.out.println("Invalid choice!");
                                }

                                break;
                                
                           case 5:
                                System.out.print("Enter Member ID: ");
                                int memberId = sc.nextInt();
                                sc.nextLine();

                                System.out.print("Enter amount: ");
                                double amount = sc.nextDouble();
                                sc.nextLine();
                                
                                String feeQuery = "INSERT INTO fees(member_id, amount, payment_date, status) VALUES(?, ?, CURDATE(), ?)";
                                PreparedStatement ps5 = conn.prepareStatement(feeQuery);
                                
                                ps5.setInt(1, memberId);
                                ps5.setDouble(2, amount);
                                ps5.setString(3, "paid");
                                
                                ps5.executeUpdate();
                                
                                System.out.println("Fee payment recorded successfully !");
                               
                               break;
                           
                           case 6:
                               String showPayQuery = "SELECT * FROM fees";
                               
                               PreparedStatement ps6 = conn.prepareStatement(showPayQuery);
                               ResultSet rs3 = ps6.executeQuery();

                                System.out.println("\n--------------- Payment LIST ---------------");

                                while (rs3.next()) {

                                    System.out.println(
                                        "Payment ID: " + rs3.getInt("fee_id") +
                                        " | Member ID: " + rs3.getInt("member_id") +
                                        " | Amount: " + rs3.getInt("amount") +
                                        " | Payment Date: " + rs3.getDate("payment_date") +
                                        " | Status: " + rs3.getString("status")
                                    );
                                }

                                break;

                           case 7:
                               System.out.println("Logging out...");
                               System.exit(0);
                               break;
                               

                            default:
                                System.out.println("Invalid choice.");
                        }
                        }
                        
                    } else {
                        System.out.println("Invalid Credentials!");
                    }

                    break;



            }
            }
            
         stmt.close();
         conn.close();

        }//end try
        catch(SQLException se){
      se.printStackTrace();
   }catch(Exception e){
      e.printStackTrace();
   }finally{
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }// nothing we can do
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try

    }//end of main
    
    
    
    //functions:
    static void showMenu(){
        System.out.println("Press 1 for Add Member");
        System.out.println("Press 2 for View Members");
        System.out.println("Press 3 for Update Member");
        System.out.println("Press 4 for Delete Member");
        System.out.println("Press 5 for Fee Payment");
        System.out.println("Press 6 for View Payment List");
        System.out.println("Press 7 for Logout");
    }
    
}
