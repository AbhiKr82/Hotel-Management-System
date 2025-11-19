# Hotel Management System (Java + MySQL)

A simple, console-based Hotel Management System built using Java and MySQL.
This project allows hotel staff to book rooms, view reservations, retrieve room information, and checkout (delete) reservations using a clean menu-driven interface.

  # Features
1.Book a Room
    Enter guest details
    Shows already-booked rooms
    Saves reservation to database

2. View All Reservations
    Displays all reservation records in a formatted table
    Includes reservation ID, guest name, room number, contact no, and reservation date

3. Get Room Number by Reservation ID
    Input reservation ID
    System retrieves the associated room number

4. Checkout / Delete Reservation
    Validates if reservation exists
    Deletes the reservation from MySQL database

5. Exit System
  Exits with a loading-style animation

# Technologies Used
      Technology	Purpose
      Java (JDK)	Application logic
      MySQL	Data storage
      JDBC Driver	Java–MySQL connection
      Console / Scanner API	User input
# Database Schema
Table: Reservation

    Column	Type	Description
    reservationId	INT (PK, Auto Increment)	Unique ID
    guestName	VARCHAR	Guest full name
    roomNo	INT	Room number
    contactNo	VARCHAR	Guest phone
    reservationDate	TIMESTAMP	Auto generated


======================================================================================================


  

