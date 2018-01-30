/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ble.Functionalities;

/**
 *
 * @author Max
 */
import java.util.HashMap;
import java.util.regex.Pattern;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/compilerdb";
	
	
	static final String USER = "root";
	static final String PASS = "";
	
	public static void test(String line){
		
		Connection conn = null;
		Statement stmt;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");	
			
			conn = DriverManager.getConnection(DB_URL,USER, PASS);
			
			System.out.println("Database Connected...");
			//stmt = conn.createStatement();
			
			//String sql = "CREATE "
		}catch(Exception e){
			System.out.println("Error! Connection failed!");
			System.out.println(e);
		}
		
		String oneArg = "let\\s+\\w+\\s+=\\s+" +
				"(table|insertRow)" +
				"\\(\\s*\\w+\\s*\\)";
		
		String twoArg = "let\\s+\\w+\\s+=\\s+" +
				"(retrieveRows|updateRows)" +
				"\\(\\s*\\w+\\s*,\\s*\\w+\\s*\\)";
		
		String threeArg = "let\\s+\\w+\\s+=\\s+" +
				"(deleteRows)" +
				"\\(\\s*\\w+\\s*,\\s*\\w+\\s*,\\s*\\w+\\s*\\)";
		
		String anyArg = "let\\s+\\w+\\s+=\\s+" +
				"(table)" +
				"\\(\\s*\\w+\\s*"
				+ "(,\\s*\\w+\\s*)+"
				+ "\\)";
		
		
                /*String line = "let vals = deleteRows (patrick, (firstName = 'RB' && contact='012345'))";
		//String line = "Let vals = retrieveRows (patrick, (firstName = 'RB' && contact='012345'))";
		//String line = "insertRow(data)";
		//String line = "Let rows = retrieveRows(data, rand_var)";
		//String line = "Let updateRows = deleteRows(data,rb, rand_var)";
		
		String line2 = "insertRow(data)";*/
		String name2 = line.substring(line.indexOf('i'), line.indexOf('w')+1).trim();
		//System.out.println("name2: " + name2);
		String name = line.substring(line.indexOf('=') + 1, line.indexOf('(')).trim();
		String varName = line.substring(line.indexOf('t') + 1, line.indexOf('=')).trim();
		String tableName1 = line.substring(line.indexOf('(') + 1, line.indexOf(')')).trim();
		String tableName;
		
		//String secondArg = "";
		String retriveTable = tableName1.substring(tableName1.indexOf(tableName1.charAt(0)), tableName1.indexOf(',')).trim();
		String secondArg = tableName1.substring(tableName1.indexOf('(') + 1).trim();
		secondArg = secondArg.concat(";");
	
		System.out.println("Name: " + name);
		System.out.println("Second Arg: " + tableName1);
		System.out.println("varName: " + retriveTable);
		
		String[] tableColumns = tableName1.split(", "); //createTable
		System.out.println("tableColumns: " + tableColumns[0]);  //createTable
 		tableName = tableName1.replaceAll(", ", " VARCHAR(255), ");   //createTable
		tableName = tableName.concat(" VARCHAR(255)");   //createTable
		//String tableColumns = line.substring(line.indexOf('(') + 1, line.indexOf(')')).trim();
		
		//System.out.println("tableColumns: "  + tableColumns);
		
		System.out.println("tableName:" + tableName);
		HashMap<String, String> mapValues = new HashMap<String,String>();
		/*for (int i = 0; i < tableColumns.length; i++) {
			Scanner scan = new Scanner(System.in);
			System.out.println(tableColumns[i] + ": " );
			String answer = scan.nextLine();
			mapValues.put(tableColumns[i], answer);
		}*/
		
	
		//System.out.println("varname: " + varName);
		//System.out.print(line + ": ");
		//Keywords...
		
		//System.out.println(mapValues.get("firstname"));
		if(varName.equals("Let") 
				|| varName.equals("table")
				|| varName.equals("retrieveRows")
				|| varName.equals("deleteRows")
				|| varName.equals("updateRows")
				|| varName.equals("insertRows")) {
			System.out.println("Variable cannot be a keyword.");
		} else {
			try {
				stmt = conn.createStatement();
				
				
				
				String sql = "DELETE FROM " + retriveTable +
							 " WHERE " + secondArg;
				System.out.println(sql);
				stmt.executeUpdate(sql);
				System.out.println("Deleted records from the table");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			/*try {
				stmt = conn.createStatement();
				System.out.println("Retrieving Row....)");
				
				String sql = "SELECT *" +
							 " FROM "  + retriveTable + " WHERE " + secondArg;
				System.out.println(sql);
				ResultSet rs = stmt.executeQuery(sql);
				System.out.println("Retrieved records from table");
				while (rs.next()) {
					String retrievedData = rs.getString("firstname");	
					String contact = rs.getString("contact");
					String lastName = rs.getString("lastName");
					String address = rs.getString("address");
					System.out.println(retrievedData);
					System.out.println(contact);
					System.out.println(lastName);
					System.out.println(address);
				}
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			/*try {
				stmt = conn.createStatement();
				
				String sql = "CREATE TABLE " + varName + 
						"(" + tableName + ")";
				System.out.println(sql);
				//stmt.executeUpdate(sql);
				//System.out.println("Created table...");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
			if( name.equals("table")){
				try {
					stmt = conn.createStatement();
					
					String sql = "CREATE TABLE " + varName + 
							"(" + tableName + ")";
					System.out.println(sql);
					stmt.executeUpdate(sql);
					System.out.println("Created table...");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				try {
					stmt = conn.createStatement();
					
					String vars = "";
					String temp = "";
					for (int i = 0; i < tableColumns.length; i++){
						temp = mapValues.get(tableColumns[i]);
						vars = vars.concat(temp);
						if (i != tableColumns.length - 1) {
							vars = vars.concat("','");
						}
					}
					vars = vars.concat("'");
					System.out.println("vars: "+ vars);
					String sql = "INSERT INTO " + varName +
								 " VALUES (" + "'" + vars + ")";
					System.out.println(sql);
					stmt.executeUpdate(sql);
					System.out.println("Inserted records into the table");
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				formattedPrint(Pattern.matches(anyArg, line));
			}
			else if (name.equals("table") || name2.equals("insertRow")) {
				/*if (name2.equals("insertRow")) {
					try {
						stmt = conn.createStatement();
						
						String vars = "";
						for (int i = 0; i < tableColumns.length; i++){
							vars = mapValues.get(tableColumns[i]);
							vars = vars.concat("','");
						}
						
						String sql = "INSERT INTO " + tableName +
									 " VALUES (" + "'" + vars + "')";
						System.out.println(sql);
						stmt.executeUpdate(sql);
						System.out.println("Inserted records into the table");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}*/
				formattedPrint(Pattern.matches(oneArg, line));
			} else if(name.equals("retrieveRows") || name.equals("updateRows")) {
				if (name.equals("retrieveRows")) {
					try {
						stmt = conn.createStatement();
						System.out.println("Retrieving Row....)");
						String vars = "";
						String temp = "";
						for (int i = 0; i < tableColumns.length; i++){
							temp = mapValues.get(tableColumns[i]);
							vars = vars.concat(temp);
							if (i != tableColumns.length - 1) {
								vars = vars.concat("','");
							}
						}
						vars = vars.concat("'");
						System.out.println("vars: "+ vars);
						String sql = "SELECT *" +
									 " FROM " + "'" + retriveTable + "WHERE " + secondArg;
						System.out.println(sql);
						ResultSet rs = stmt.executeQuery(sql);
						System.out.println("Retrieved records from table");
						while (rs.next()) {
							for (int i = 0; i < tableColumns.length; i++) {
								String retrievedData = rs.getString(tableColumns[i]);
								System.out.println(tableColumns[i] + retrievedData);
							}
						}
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				formattedPrint(Pattern.matches(twoArg, line));
			} else {
				if (name2.equals("deleteRow")) {
					try {
						stmt = conn.createStatement();
						
						
						
						String sql = "DELETE FROM " + retriveTable +
									 " WHERE " + secondArg;
						System.out.println(sql);
						stmt.executeUpdate(sql);
						System.out.println("Inserted records into the table");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				formattedPrint(Pattern.matches(threeArg, line));
			}
		}
	
		
		
		
		
		//Other group
		
		String pat_stack = "let\\s+\\w+\\s+(\\=\\s+stack\\(([1-9][0-9]*|[a-zA-Z][\\d\\w\\_]*)?\\))|" +
				"\\s*stack\\s*\\(\\w[\\w\\d\\_]*\\s*\\)\\s*|" +
				"\\s*pop\\s*\\(\\w[\\w\\d\\_]*\\s*\\)\\s*|" +
				"\\s*size\\s*\\(\\w[\\w\\d\\_]*\\s*\\)\\s*|" +
				"\\s*push\\s*\\(\\w[\\w\\d\\_]*\\s*\\,\\s*\\w[\\w\\d\\_]*\\s*\\)\\s*";

		String pat_queue = "let\\s+\\w+\\s+(\\=\\s+queue\\(([1-9]+[0-9]*|\\w[\\w\\d\\_]*)?\\))|" +
						"\\s*enqueue\\s*\\(\\w[\\w\\d\\_]*\\s*\\,\\s*\\w[\\w\\d\\_]*\\s*\\)\\s*" +
						"\\s*dequeue\\s*\\(\\w[\\w\\d\\_]*\\s*\\,\\s*\\w[\\w\\d\\_]*\\s*\\)\\s*";
		
		String pat_sort = "sortAsc\\((\\w[\\w\\d\\_]*)?\\)|sortDesc\\(\\w[\\w\\d\\_]*\\)";
		String samp = "push(rawr, 10a)";
		
		formattedPrint(Pattern.matches(pat_stack, samp));
		
			
	}
	
	private static void formattedPrint(boolean status) {
		if(status) {
			System.out.println("\n\nSyntax is correct");
		} else {
			System.out.println("\n\nSyntax is correct");
		}
	}
}

