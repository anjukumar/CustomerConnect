import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CustomerApp{
	@SuppressWarnings("resource")
	public static void main(String[] args) 
	{
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		//String sql = "select user from dual";
		Search sr = new Search();
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//con = DriverManager.getConnection("jdbc:oracle:thin:sys as sysdba/oracle@localhost:1521:orcl");
			con = DriverManager.getConnection("jdbc:oracle:thin:ora1/ora1@localhost:1521:orcl");
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM CUSTOMERS INNER JOIN COMPANY ON CUSTOMERS.COMPANYID = COMPANY.COMPANYID INNER JOIN STATE ON CUSTOMERS.STATEID = STATE.STATEID WHERE CUSTOMERS.LASTNAME = ?");
			PreparedStatement pstmt1 = con.prepareStatement("UPDATE CUSTOMERS SET STREETADDRESS = ? WHERE LASTNAME= ? AND CUSTID =?");

			Scanner sc = new Scanner(System.in);
			while(true)
			{
				System.out.println("Enter Last Name :  ");
				String s =sc.nextLine();
				pstmt.setString(1, s);
				stmt = con.createStatement();
				rs = pstmt.executeQuery();
				String CustomerNum=null;
				int flag;
				while(rs.next())
				{
					System.out.println("Customer number: " +rs.getString("CustId"));
					System.out.println(rs.getString("Title")+"  " +rs.getString("Fullname") + "   " + "\n" +rs.getString("StreetAddress") + "\n"+rs.getString("City")+ " " +rs.getString("zipcode")+ "\n" +rs.getString("emailaddress"));
					System.out.println();
				}
				
				System.out.println("would you  like to seatch for a record or update or quit");
				System.out.println("Press (1) to search for another customer or press (2) to Edit the customer's address. or (0) to quit");
				flag=sc.nextInt();
				sc.nextLine();
				if(flag==2)
				{
					System.out.println("Enter Customer ID");
					CustomerNum =sc.nextLine();
					System.out.println("Enter New Street Address: ");
					String sa =sc.nextLine();
					pstmt1.setString(1, sa);
					pstmt1.setString(2, s);
					pstmt1.setString(3, CustomerNum);
					int r = pstmt1.executeUpdate();
					System.out.println("You have sucessfully updated this query");
					sr.search();
				}
				if(flag==0)
				{
					System.out.println("The program has ended.");
					break;
				}
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
				e.printStackTrace();
			}
		}
	}
}