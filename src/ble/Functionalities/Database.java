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
import ble.objects.ArrayBle;
import java.util.HashMap;
import java.util.regex.Pattern;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/ble";
	
	
	static final String USER = "root";
	static final String PASS = "";
	
	
	public static Connection conn = null;
	public static Statement stmt = null;
	
	public static void test(String arg){
		
		
		System.out.println("In database na. This is the line" + arg);
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
		
		String oneArg = "Let\\s+\\w+\\s+=\\s+" +
				"(table|insertRow)" +
				"\\(\\s*\\w+\\s*\\)";
		
		String twoArg = "Let\\s+\\w+\\s+=\\s+" +
				"(retrieveRows|updateRows)" +
				"\\(\\s*\\w+\\s*,\\s*\\w+\\s*\\)";
		
		String threeArg = "Let\\s+\\w+\\s+=\\s+" +
				"(deleteRows)" +
				"\\(\\s*\\w+\\s*,\\s*\\w+\\s*,\\s*\\w+\\s*\\)";
		
		String anyArg = "Let\\s+\\w+\\s+=\\s+" +
				"(table)" +
				"\\(\\s*\\w+\\s*"
				+ "(,\\s*\\w+\\s*)+"
				+ "\\)";
		
		String line = arg;

		String name = line.substring(line.indexOf('=') + 1, line.indexOf('(')).trim();
		String varName = line.substring(line.indexOf('t') + 1, line.indexOf('=')).trim();
		String tableName1 = line.substring(line.indexOf('(') + 1, line.indexOf(')')).trim();
		String tableName = null;
		String secondArg = null;
                String retriveTable = null;
		String[] tableColumns = null;
                HashMap<String, String> mapValues = new HashMap<String,String>();
                
                
                
                if (line.contains(",")) {
                    System.out.println("tada");
                
                retriveTable = tableName1.substring(tableName1.indexOf(tableName1.charAt(0)), tableName1.indexOf(',')).trim();
		secondArg = tableName1.substring(tableName1.indexOf('(') + 1).trim();
		secondArg = secondArg.concat(";");
	
		System.out.println("Name: " + name);
		System.out.println("Second Arg: " + tableName1);
		System.out.println("varName: " + retriveTable);
		
		tableColumns = tableName1.split(", "); //createTable
		System.out.println("tableColumns: " + tableColumns[0]);  //createTable
 		tableName = tableName1.replaceAll(", ", " VARCHAR(255), ");   //createTable
		tableName = tableName.concat(" VARCHAR(255)");   //createTable
                 
		
		System.out.println("tableName:" + tableName);
		}
		if(varName.equals("Let") 
				|| varName.equals("table")
				|| varName.equals("retrieveRows")
				|| varName.equals("deleteRows")
				|| varName.equals("updateRows")
				|| varName.equals("insertRows")) {
			System.out.println("Variable cannot be a keyword.");
		} else {
			
		
			if( name.equals("table")){
				createTable (varName, tableName);
			}
			else if (name.equals("table") || name.equals("insertRow")) {
				insert ( varName, tableColumns, mapValues);
			} else if(name.equals("retrieveRows") || name.equals("updateRows")) {
				if (name.equals("retrieveRows")) {
					select(tableColumns,tableName1,secondArg,mapValues);
				}
				formattedPrint(Pattern.matches(twoArg, line));
			} else {
				if (name.equals("deleteRow")) {
					deleteRowFromTable (retriveTable,secondArg);
					
				}
				formattedPrint(Pattern.matches(threeArg, line));
			}
		}
	
	
			
	}
	
	private static void formattedPrint(boolean status) {
		if(status) {
			System.out.println("\n\nSyntax is correct");
		} else {
			System.out.println("\n\nSyntax is correct");
		}
	}
	
	private static void select (String[] tableColumns, String retriveTable, String secondArg, HashMap<String, String> mapValues) {
	
			try {
				stmt = conn.createStatement();
				System.out.println("Retrieving Row....)");
				/*String vars = "";
				String temp = "";
				for (int i = 0; i < tableColumns.length; i++){
					temp = mapValues.get(tableColumns[i]);
					vars = vars.concat(temp);
					if (i != tableColumns.length - 1) {
						vars = vars.concat("','");
					}
				}
				vars = vars.concat("'");
				System.out.println("vars: "+ vars);*/
				String sql = "SELECT *" +
							 " FROM " + retriveTable;
				System.out.println(sql);
				ResultSet rs = stmt.executeQuery(sql);
                                
                                ArrayBle[][] users = new ArrayBle[1][5];
                                
				System.out.println("Retrieved records from table");
                                while (rs.next()) {
                                    
                                            System.out.println(rs.getString("name"));
				}
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	private static void insert (String varName, String[] tableColumns, HashMap<String, String> mapValues) {
		
		
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

	}
	
	private static void deleteRowFromTable (String retriveTable, String secondArg) {
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
		
	}
	
	private static void createTable (String varName, String tableName) {
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
	}
}

