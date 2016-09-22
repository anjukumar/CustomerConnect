import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Search {

	public void search() 
	{
	@SuppressWarnings("resource")
	
			Connection con = null;
			Statement stmt = null;
			ResultSet rs = null;
			//String sql = "select user from dual";

			try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				//con = DriverManager.getConnection("jdbc:oracle:thin:sys as sysdba/oracle@localhost:1521:orcl");
				con = DriverManager.getConnection("jdbc:oracle:thin:ora1/ora1@localhost:1521:orcl");
				PreparedStatement pstmt = con.prepareStatement("SELECT * FROM CUSTOMERS INNER JOIN COMPANY ON CUSTOMERS.COMPANYID = COMPANY.COMPANYID INNER JOIN STATE ON CUSTOMERS.STATEID = STATE.STATEID WHERE CUSTOMERS.LASTNAME = ?");
				PreparedStatement pstmt1 = con.prepareStatement("UPDATE CUSTOMERS SET STREETADDRESS = ? WHERE LASTNAME= ? AND CUSTID =?");

				Scanner sc = new Scanner(System.in);
				System.out.println("Enter Last Name :  ");
				String s =sc.nextLine();
				pstmt.setString(1, s);
				stmt = con.createStatement();
				rs = pstmt.executeQuery();
				String CustomerNum;
				int flag;
				while(rs.next())
				{
					
					System.out.println("Customer number: " +rs.getString("CustId"));
					CustomerNum =rs.getString("CustId");
					System.out.println(rs.getString("Title")+"  " +rs.getString("Fullname") + "   " + "\n" +rs.getString("StreetAddress") + "\n"+rs.getString("City")+ " " +rs.getString("zipcode")+ "\n" +rs.getString("emailaddress"));
					System.out.println();
					System.out.println("would you  like to seatch for a record or update or quit");
					System.out.println("Press (1) to search for another customer or press (2) to Edit the customer's address. or (0) to quit");
					flag=sc.nextInt();
					sc.nextLine();
				}

			}catch (SQLException e) {
				e.printStackTrace();
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				try {
					rs.close();
					stmt.close();
					con.close();
				}catch(SQLException e){
				}
			}
		}
	}