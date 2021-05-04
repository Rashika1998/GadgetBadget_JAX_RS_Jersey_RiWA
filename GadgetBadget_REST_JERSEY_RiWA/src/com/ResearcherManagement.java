package com;
import java.sql.*; 
public class ResearcherManagement 
{ 
private Connection connect() 
 { 
 Connection con = null; 
 try
 { 
	 Class.forName("com.mysql.jdbc.Driver"); 
	 con = 
	 DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbadget_rest_jersey", "root", "issa123"); 
 } 
 catch (Exception e) 
 { 
	 e.printStackTrace(); 
 } 
 	return con; 
 } 



public String readResearchers() 
 { 
	String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 { 
	 return "Error while connecting to the database for reading."; 
 } 
 // Prepare the html table to be displayed
 output = "<table border='1'><tr><th>Res Code</th><th>Res Name</th><th>Res email</th><th>Res Age</th><th>Res Address</th><th>Res Role</th>" + "<th>Joined Date</th><th>Update</th><th>Remove</th></tr>"; 
 String query = "select * from researcher_tab"; 
 Statement stmt = con.createStatement(); 
 ResultSet rs = stmt.executeQuery(query); 
 // iterate through the rows in the result set
 while (rs.next()) 
 { 
	 String resID = Integer.toString(rs.getInt("resID")); 
	 String resCode = rs.getString("resCode");
	 String resName = rs.getString("resName");
	 String resEmail = rs.getString("resEmail"); 
	 String resAge = rs.getString("resAge"); 
	 String resAddress = rs.getString("resAddress"); 
	 String resRole = rs.getString("resRole"); 
	 String joinDate = rs.getString("joinDate"); 
	 // Add into the html table
	 output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate' type='hidden' value='" + resID + "'>" + resCode + "</td>"; 
	 output += "<td>" + resName + "</td>"; 
	 output += "<td>" + resEmail + "</td>"; 
	 output += "<td>" + resAge + "</td>"; 
	 output += "<td>" + resAddress + "</td>"; 
	 output += "<td>" + resRole + "</td>"; 
	 output += "<td>" + joinDate + "</td>"; 
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>" + "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-itemid='" + resID + "'>" + "</td></tr>"; 
 
 } 
 con.close(); 
 // Complete the html table
 output += "</table>"; 
 } 
 catch (Exception e) 
 { 
	 output = "Error while reading the items."; 
	 System.err.println(e.getMessage()); 
 } 
 	return output; 
 } 


public String insertResearcher(String res_code, String res_name, String res_email, String res_age, String res_address, String res_role, String join_date) 
 { 
	String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 { 
	 return "Error while connecting to the database for inserting."; 
 } 
 // create a prepared statement
 		 String query = " insert into researcher_tab(`resID`,`resCode`,`resName`,`resEmail`,`resAge`,`resAddress`,`resRole`,`joinDate`)" + " values (?, ?, ?, ?, ?, ?, ?, ?)";
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, 0); 
		 preparedStmt.setString(2, res_code); 
		 preparedStmt.setString(3, res_name); 
		 preparedStmt.setString(4, res_email); 
		 preparedStmt.setString(5, res_age); 
		 preparedStmt.setString(6, res_address); 
		 preparedStmt.setString(7, res_role); 
		 preparedStmt.setString(8, join_date); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newItems = readResearchers(); 
		 output = "{\"status\":\"success\", \"data\": \"" + 
		 newItems + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "{\"status\":\"error\", \"data\":\"Error while inserting the researcher's details.\"}"; 
			 System.err.println(e.getMessage()); 
		 } 
		 	return output; 
		 } 



		public String updateResearcher(String res_id, String res_code, String res_name, String res_email, String res_age, String res_address, String res_role, String join_date) 
		 { 
			String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 { 
			 return "Error while connecting to the database for updating."; 
		 } 
		 // create a prepared statement
		 String query = "UPDATE researcher_tab SET resCode=?,resName=?,resEmail=?,resAge=?,resAddress=?,resRole=?,joinDate=? WHERE resID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setString(1, res_code); 
		 preparedStmt.setString(2, res_name); 
		 preparedStmt.setString(3, res_email); 
		 preparedStmt.setString(4, res_age); 
		 preparedStmt.setString(5, res_address); 
		 preparedStmt.setString(6, res_role); 
		 preparedStmt.setString(7, join_date); 
		 preparedStmt.setInt(8, Integer.parseInt(res_id));
		// execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newResearchers = readResearchers(); 
		 output = "{\"status\":\"success\", \"data\": \"" + newResearchers + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "{\"status\":\"error\", \"data\":\"Error while updating the researchers details.\"}"; 
			 System.err.println(e.getMessage()); 
		 } 
		 	return output; 
		 } 
		
		
		
		
		public String deleteResearcher(String resID) 
		 { 
			String output = ""; 
		 try
		 { 
			 Connection con = connect(); 
		 if (con == null) 
		 { 
			 return "Error while connecting to the database for deleting."; 
		 } 
		 // create a prepared statement
			 String query = "delete from researcher_tab where resID=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(resID)); 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 String readResearchers = readResearchers(); 
			 output = "{\"status\":\"success\", \"data\": \"" + readResearchers + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "{\"status\":\"error\", \"data\":\"Error while deleting the researcher.\"}"; 
			 System.err.println(e.getMessage()); 
		 } 
		 	return output; 
		 } 
		}