package com.quatela;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;

import com.opencsv.CSVReader;

public class Main {

	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbTest?autoReconnect=true&useSSL=false", "root", "P@ssG0!");
			
			stmt = con.createStatement();
			
			rs = stmt.executeQuery("Select * from tbEmployee");
			
			while(rs.next()) {
				System.out.println(rs.getString(2) + " " + rs.getDouble(3) + " " + rs.getDouble(4) +
						" " + rs.getDouble(5));
			}
			
			CSVReader reader = new CSVReader(new FileReader("/home/rquatela/Desktop/test1.csv"));
			String[] nextLine;
			ps = con.prepareStatement("insert into tbEmployee (empName, empRate, empRegHours, empOTHours) " +
			"VALUES (?, ?, ?, ?)");
			while((nextLine = reader.readNext()) != null) {
				ps.setString(1, nextLine[0]);
				ps.setDouble(2, Double.parseDouble(nextLine[1]));
				ps.setDouble(3, Double.parseDouble(nextLine[2]));
				ps.setDouble(4, Double.parseDouble(nextLine[3]));
				ps.executeUpdate();
			}
			
			
			while(rs.next()) {
				System.out.println(rs.getString(2) + " " + rs.getDouble(3) + " " + rs.getDouble(4) +
						" " + rs.getDouble(5));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(rs != null) {
				try {
					con.close();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void read() {
		try {
			CSVReader reader = new CSVReader(new FileReader("/home/rquatela/Desktop/test1.csv"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
