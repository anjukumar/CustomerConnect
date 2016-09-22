//import java.sql.PreparedStatement;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.Scanner;
//public class Update extends Search {
//	public void update(){
//		
//		Class.forName("oracle.jdbc.driver.OracleDriver");
//		con = DriverManager.getConnection("jdbc:oracle:thin:ora1/ora1@localhost:1521:orcl");
//		PreparedStatement pstmt = con.prepareStatement("SELECT * FROM CUSTOMERS INNER JOIN COMPANY ON CUSTOMERS.COMPANYID = COMPANY.COMPANYID INNER JOIN STATE ON CUSTOMERS.STATEID = STATE.STATEID WHERE CUSTOMERS.LASTNAME = ?");
//		PreparedStatement pstmt1 = con.prepareStatement("UPDATE CUSTOMERS SET STREETADDRESS = ? WHERE LASTNAME= ? AND CUSTID =?");
//		System.out.println("Enter New Address  ");
//		String sa =sc.nextLine();
//		String CustomerNum = rs.getString("CustId");
//		pstmt1.setString(1, sa);
//		pstmt1.setString(2, s);
//		pstmt1.setString(3, CustomerNum);
//		int r = pstmt1.executeUpdate();
//		System.out.println("You have sucessfully updated this query");
//		sr.search();
//	}
//}