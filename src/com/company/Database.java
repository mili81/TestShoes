package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Scanner;


public class Database {

    static final String DB_URL = "jdbc:mysql://group1db.ckfcq92zr1jy.eu-west-2.rds.amazonaws.com/shoes";


    static Connection conn = null;
    static Statement stmt = null;

    public static void repeat() throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("How many rows You will enter?");
        int answer = input.nextInt();
        int count = 0;
        do {
            insertValues();
            count++;
        } while (count < answer);
    }

    public static void insertValues() throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Name:");
        String name = input.next();
        System.out.println("Size:");
        int size = input.nextInt();
        System.out.println("Colour:");
        String colour = input.next();
        System.out.println("Brand:");
        String brand = input.next();


        String sql = "INSERT INTO Shoes(name,size,colour,brand) VALUES (+" +
                "'" + name + "'," + size + ",'" + colour + "','" + brand + "')";
        stmt.execute(sql);
    }


    public static void main(String[] args) {
        //Scanner name = new Scanner(System.in);
        //System.out.println("Enter username:");
        //String username = name.next();
        //Scanner pass = new Scanner(System.in);
        //System.out.println("Enter password:");
        //String password = pass.next();

        class HidePasswordFromCommandLine extends Thread {
            boolean stopThread = false;
            boolean hideInput = false;
            boolean shortMomentGone = false;

            public void run() {
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                }
                shortMomentGone = true;
                while (!stopThread) {
                    if (hideInput) {
                        System.out.println("\b*");
                    }
                    try {
                        sleep(1);
                    } catch (InterruptedException e) {
                    }
                }
            }
            public void mains(String[]args){
                String username  = "";
                String password = "";
                HidePasswordFromCommandLine hideThread = new HidePasswordFromCommandLine();
                hideThread.start();
                BufferedReader in = new BufferedReader( new InputStreamReader(System.in));
                try {
                    System.out.println("Name:");
                    do {
                        username = in.readLine();
                    } while (hideThread.shortMomentGone == false);
                    //Now the hide thread should begin to overwrite any input  with "*"
                    hideThread.hideInput = true;
                    //Read the password
                    System.out.println("\nPassword:");
                    System.out.println("");
                    password = in.readLine();
                    hideThread.stopThread = true;
                }
                catch(Exception e){
                }
                System.out.println("\b\b");

            }
        }




        try {

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, username, password);
            System.out.println("Creating statement...");

            //Create statement object
            stmt = conn.createStatement();

            repeat();

        } catch (SQLException sqlException) {
            System.out.println("Error:" + sqlException.getMessage());

        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Got error and connection closed." + ex.getMessage());
            }
        }


    }
}
 //class HidePasswordFromCommandLine extends Thread {
    //boolean stopThread = false;
    //boolean hideInput = false;
    //boolean shortMomentGone = false;

    //public void run() {
        //try {
            //sleep(500);
        //} catch (InterruptedException e) {
        //}
        //shortMomentGone = true;
        //while (!stopThread) {
           // if (hideInput) {
               // System.out.println("\b*");
            //}
            //try {
             //   sleep(1);
           // } catch (InterruptedException e) {
          //  }
       // }
    //}
    //public static void mains(String[]args){
       // String name  = "";
       // String password = "";
        //HidePasswordFromCommandLine hideThread = new HidePasswordFromCommandLine();
        //hideThread.start();
       // BufferedReader in = new BufferedReader( new InputStreamReader(System.in));
        //try {
       //     System.out.println("Name:");
        //    do {
         //       name = in.readLine();
         //   } while (hideThread.shortMomentGone == false);
         //   //Now the hide thread should begin to overwrite any input  with "*"
         //   hideThread.hideInput = true;
            //Read the password
        //    System.out.println("\nPassword:");
         //   System.out.println("");
        //    password = in.readLine();
       //     hideThread.stopThread = true;
       // }
       // catch(Exception e){
       //     }
      //  System.out.println("\b\b");
      //
      //  }
  //  }

