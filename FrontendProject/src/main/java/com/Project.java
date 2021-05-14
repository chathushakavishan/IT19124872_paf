package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Project {
	
	//DB connection
	private Connection connect() {
		
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/projectdb1?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

			public String readProject() {
				
				
				//System.out.println("read");
				String output = "";
				

				try {
					Connection con = connect();
					if (con == null) {
						return "Error while connecting to the database for reading.";
					}

					// Prepare the html table to be displayed
					output = "<table border='1'><tr><th>Name</th>" + "<th>Category</th><th>Budget</th>"
							+ "<th>Description</th>" + "<th>Update</th><th>Remove</th></tr>";

					String query = "select * from project";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);

					while (rs.next()) {

						String ProjectID = Integer.toString(rs.getInt("ProjectID"));
						String Name = rs.getString("Name");
						String Category = rs.getString("Category");
						String Budget = rs.getString("Budget");
						String Description =rs.getString("Description");

						// Add into the html table

						
						output += "<td>" + Name + "</td>";		
						output += "<td>" + Budget + "</td>";
						output += "<td>" + Category + "</td>";
						output += "<td>" + Description + "</td>";

						// buttons
						output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
								+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-ProjectID='"
								+ ProjectID + "'>" + "</td></tr>";

					}

					con.close();

					// Complete the html table
					
					output += "</table>";
					
				} catch (Exception e) {
					output = "Error while reading the Appointment.";
					System.err.println(e.getMessage());
				}
				
				

				return output;
				
				
			}

			
			public String insertProject(String Name, String Category, String Budget,String Description) {
				
				
				String output = "";
				
				System.out.println("insert " + Name + Category + Budget + Description);

				try {
					Connection con = connect();

					if (con == null) {
						return "Error while connecting to the database";
					}

					// create a prepared statement
					String query = " insert into project (`Name`,`Category`,`Budget`,`Description`)"
							+ " values (?, ?, ?, ?)";

					PreparedStatement preparedStmt = con.prepareStatement(query);

					// binding values
					preparedStmt.setString(1, Name);
					preparedStmt.setString(2, Category);
					preparedStmt.setString(3, Budget);
					preparedStmt.setString(4, Description);

					// execute the statement
					preparedStmt.execute();
					con.close();

					// Create JSON Object to show successful msg.
					String newProject = readProject();
					output = "{\"status\":\"success\", \"data\": \"" + newProject + "\"}";
				} catch (Exception e) {
					// Create JSON Object to show Error msg.
					output = "{\"status\":\"error\", \"data\": \"Error while Inserting Project.\"}";
					System.err.println(e.getMessage());
				}

				return output;
			}

			// Update updateCustomer
			public String updateProject(String Name, String Category, String Budget,String Description, int ProjectID) {
				
				
				String output = "";

				
				
				try {
					Connection con = connect();

					if (con == null) {
						return "Error while connecting to the database for updating.";
					}

					// create a prepared statement
					String query = "update project SET Name=?,Category=?,Budget=?,Description=? WHERE ProjectID=?";

					PreparedStatement preparedStmt = con.prepareStatement(query);

					// binding values
					preparedStmt.setString(1, Name);
					preparedStmt.setString(2, Category);
					preparedStmt.setString(3, Budget);
					preparedStmt.setString(4, Description);
					preparedStmt.setInt(5, ProjectID);

					// execute prepared statement
					preparedStmt.execute();
					con.close();

					// create JSON object to show successful msg
					String newProject = readProject();
					output = "{\"status\":\"success\", \"data\": \"" + newProject + "\"}";
				} catch (Exception e) {
					output = "{\"status\":\"error\", \"data\": \"Error while Updating Project Details.\"}";
					System.err.println(e.getMessage());
				}

				return output;
			}

			
			
			public String deleteProject(String ProjectID) {
				
				
				String output = "";
				
				

				try {
					Connection con = connect();

					if (con == null) {
						return "Error while connecting to the database for deleting.";
					}

					// create a prepared statement
					String query = "delete from project where ProjectID=?";

					PreparedStatement preparedStmt = con.prepareStatement(query);

					
					preparedStmt.setInt(1, Integer.parseInt(ProjectID));
					// execute the statement
					preparedStmt.execute();
					con.close();

					
					String newProject = readProject();
					output = "{\"status\":\"success\", \"data\": \"" + newProject + "\"}";
					
				} catch (Exception e) {
					
					
					output = "{\"status\":\"error\", \"data\": \"Error while Deleting Project.\"}";
					System.err.println(e.getMessage());

				}

				return output;
				
				
			}
		}

		
