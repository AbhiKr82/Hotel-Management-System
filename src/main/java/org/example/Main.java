package org.example;

import java.sql.*;
import java.util.Scanner;

class Main {
    private static final String url = "jdbc:mysql://localhost:3306/Hotel_DB";
    private static final String userName = "root";
    private  static final String password="1234567890";

    public static void main(String[] args) throws Exception {


        // Connecting with database;
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection= DriverManager.getConnection(url,userName,password);



        while (true){
            System.out.println();
            System.out.println("HOTEL MANAGEMENT SYSTEM");
            Scanner sc= new Scanner(System.in);

            System.out.println("1. Book a Room ");
            System.out.println("2. View Reservations");
            System.out.println("3. Get Room No");
            System.out.println("4. Checkout Room");
            System.out.println("0 Exit !!");
            System.out.println();

            int choice= sc.nextInt();

            switch (choice){
                case 1:reserveRoom(connection,sc);
                    break;
                case 2:viewReservation(connection);
                    break;
                case 3:getRoomNo(connection,sc);
                    break;
                case 4:deleteReservation(connection,sc);
                    break;
                case 0:
                    exist();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }


        }





    }

    private static void roomBooked(Connection connection){
        try{
            String sql = "SELECT roomNo FROM Reservation";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql) ;
            System.out.print("Room Booked [ ");
            while (resultSet.next()){
                int roomNumber = resultSet.getInt("roomNo");
                System.out.print(roomNumber+" ");
            }
            System.out.print("]");
            System.out.println();
            System.out.println("================================================================");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    private static  void reserveRoom(Connection connection, Scanner scanner){

        roomBooked(connection);


        System.out.print("Enter First name: ");
        String first = scanner.next();
        scanner.nextLine();
        System.out.print("Enter Last name: ");
        String second = scanner.next();
        String guestName=first+" "+second;
        scanner.nextLine();
        System.out.print("Enter room number: ");
        int roomNumber = scanner.nextInt();
        System.out.print("Enter contact number: ");
        String contactNumber = scanner.next();

        String sql = "INSERT INTO Reservation (guestName, roomNo, contactNo) " +
                "VALUES ('" + guestName + "', " + roomNumber + ", '" + contactNumber + "')";

        try{

            Statement statement = connection.createStatement();
            int affectedRoows = statement.executeUpdate(sql);

            if(affectedRoows>0){
                System.out.println("Reservation successful!");
            }
            else {
                System.out.println("Reservation failed.");
            }

            System.out.println("================================================================");

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private static  void viewReservation(Connection connection) throws Exception {

        try{
            String sql = "SELECT reservationId, guestName, roomNo, contactNo, reservationDate FROM Reservation";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            System.out.println("Current Reservations:");
            System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");
            System.out.println("| Reservation ID | Guest           | Room Number   | Contact Number      | Reservation Date        |");
            System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");

            while (resultSet.next()) {
                int reservationId = resultSet.getInt("reservationId");
                String guestName = resultSet.getString("guestName");
                int roomNumber = resultSet.getInt("roomNo");
                String contactNumber = resultSet.getString("contactNo");
                String reservationDate = resultSet.getTimestamp("reservationDate").toString();

                // Format and display the reservation data in a table-like format
                System.out.printf("| %-14d | %-15s | %-13d | %-20s | %-19s   |\n",
                        reservationId, guestName, roomNumber, contactNumber, reservationDate);
            }

            System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");
            System.out.println();
            System.out.println("================================================================");


        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }

    private   static void getRoomNo(Connection connection,Scanner scanner) throws Exception{
        System.out.print("Enter reservation ID: ");
        int reservationId = scanner.nextInt();

        try{
            String sql = "SELECT roomNo FROM Reservation WHERE reservationId = " + reservationId;

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql) ;

            if (resultSet.next()) {
                int roomNumber = resultSet.getInt("roomNo");
                System.out.println("Room number for Reservation ID " + reservationId +" is "+roomNumber);
            } else {
                System.out.println("Reservation not found for the given ID and guest name.");
            }

            System.out.println("================================================================");


        }
        catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }



    private static void deleteReservation(Connection connection, Scanner scanner) throws Exception{
        try {
            System.out.print("Enter reservation ID to delete: ");
            int reservationId = scanner.nextInt();

            if (!reservationExists(connection, reservationId)) {
                System.out.println("Reservation not found for the given ID.");
                return;
            }

            String sql = "DELETE FROM Reservation WHERE reservationId = " + reservationId;

           Statement statement = connection.createStatement();
           int affectedRows = statement.executeUpdate(sql);

           if (affectedRows > 0) {
               System.out.println("Reservation deleted successfully!");
           } else {
               System.out.println("Reservation deletion failed.");
           }

            System.out.println("================================================================");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }




    private static boolean reservationExists(Connection connection, int reservationId) throws Exception {

            String sql = "SELECT reservationId FROM Reservation WHERE reservationId = " + reservationId;

            try{
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql) ;

                return resultSet.next();

            }catch (Exception e){
                System.out.println(e.getMessage());
                return false;
            }

    }

    private  static void exist() throws InterruptedException {
        System.out.print("Exiting System");
        for(int i=0;i<5;i++){
            System.out.print(".");
            Thread.sleep(500);

        }
        System.out.println();
        System.out.println("ThankYou !!!");
    }
}