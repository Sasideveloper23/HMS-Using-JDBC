package org.jsp;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class Hospital {
	static Scanner s=new Scanner(System.in);
	public static void main(String[] args)	
	{
		while(true)
		{
			System.out.println("1) Register by using patient details");
			System.out.println("2) Update disease based on id ");
			System.out.println("3) Update the age by phone number ");
			System.out.println("4)view all patient rcords ");
			System.out.println("5)View patient by phone num ");
			System.out.println("6) Search patients by disease ");
			System.out.println("7)Search patient by name ");
			System.out.println("8)Delete a patient record by phno and name");
			int val=s.nextInt();
			switch(val)
			{
			case 1:
				register();
				break;
			case 2:
				updateDisease();
				break;
			case 3:
			{
				updatePh();
				break;
			}
			case 4:
				allRecords();
				break;
			case 5:
				viewPatientByPhnum();
				break;
			case 6:
				searchPatientByDisease();
				break;
			case 7:
				searchPatientByName();
				break;
			case 8:
				delete();
			default:
				System.out.println("Invalid choice");
				break;
			}
		}
	}
	
	//Register 
		static void register() {
			Connection con=null;;
			PreparedStatement ps=null;
			System.out.println("Enter patient id: ");
			int id=s.nextInt();
			System.out.println("Enter patient name: ");
			String name=s.next();
			System.out.println("Enter patient age: ");
			int age=s.nextInt();
			System.out.println("Enter patient phonenum: ");
			long phno=s.nextLong();
			System.out.println("Enter patient disease: ");
			String disease=s.next();
			System.out.println("Enter admission date: ");
			String admission_Date=s.next();
			try {
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
				ps=con.prepareStatement("insert into patient values(?,?,?,?,?,?)");
				ps.setInt(1,id);
				ps.setString(2, name);
				ps.setInt(3, age);
				ps.setLong(4, phno);
				ps.setString(5, disease);
				ps.setDate(6,Date.valueOf(admission_Date));
				int row=ps.executeUpdate();
				System.out.println(row+" rows are inserted successfully");
			} 
			catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			finally {
				if(ps!=null)
				{
					try {
						ps.close();
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
				}
				if(con!=null)
				{
					try {
						con.close();
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
				}
			
		}
		}
		//Update based on id
		static void updateDisease()
		{
			Connection con=null;;
			PreparedStatement ps=null;
			System.out.println("Enter patient id: ");
			int id=s.nextInt();
			System.out.println("Enter patient disease: ");
			String disease=s.next();
			try {
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
				ps=con.prepareStatement("Update patient set disease=? where id=?");
				ps.setString(1,disease);
				ps.setInt(2, id);
				int row=ps.executeUpdate();
				System.out.println(row+" rows are inserted successfully");
			} 
			catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			finally {
				if(ps!=null)
				{
					try {
						ps.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if(con!=null)
				{
					try {
						con.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			
		}
		}
		//Update based on Phone num
		static void updatePh()
		{
			Connection con=null;;
			PreparedStatement ps=null;
			System.out.println("Enter patient age: ");
			int age=s.nextInt();
			System.out.println("Enter patient phonenum: ");
			long phno=s.nextLong();
			try {
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
				ps=con.prepareStatement("Update patient set age=? where phone=?");
				ps.setInt(1,age);
				ps.setLong(2,phno);
				int row=ps.executeUpdate();
				System.out.println(row+" rows are updated successfully");
			} 
			catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			finally {
				if(ps!=null)
				{
					try {
						ps.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if(con!=null)
				{
					try {
						con.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}
		//View all patient records
		static void allRecords()
		{
			Connection con=null;;
			PreparedStatement ps=null;
			
			try {
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
				Statement s=con.createStatement();
				
				boolean b=s.execute("select * from patient");
				if(b==true)
				{
					ResultSet rs=s.getResultSet();
					while(rs.next())
					{
						System.out.print(rs.getInt(1)+"\t");
						System.out.print(rs.getString(2)+"\t");
						System.out.print(rs.getInt(3)+"\t");
						System.out.println(rs.getLong(4)+"\t");
						System.out.println(rs.getString(5)+"\t");
						System.out.println(rs.getString(6)+"\t");
					}
			} }
			catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			finally {
				if(ps!=null)
				{
					try {
						ps.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if(con!=null)
				{
					try {
						con.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			
		}
		}
		//View patient by phone num
		static void viewPatientByPhnum()
		{
			Connection con=null;;
			PreparedStatement ps1=null;
			
			System.out.println("Enter patient phonenum: ");
			long phno=s.nextLong();
			try {
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
				
				 ps1=con.prepareStatement("select * from patient where phone=?");
				ps1.setLong(1, phno);
				boolean b=ps1.execute();
				if(b==true)
				{
					ResultSet rs=ps1.getResultSet();
					while(rs.next())
					{
						System.out.print(rs.getInt(1)+"\t");
						System.out.print(rs.getString(2)+"\t");
						System.out.print(rs.getInt(3)+"\t");
						System.out.println(rs.getLong(4)+"\t");
						System.out.println(rs.getString(5)+"\t");
						System.out.println(rs.getString(6)+"\t");
					}
				
			} 
			
			}
		
			
			catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			finally {
				if(ps1!=null)
				{
					try {
						ps1.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if(con!=null)
				{
					try {
						con.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			
		}
		}
		//Search Patient by disease
		static void searchPatientByDisease()
		{
			Connection con=null;;
			PreparedStatement ps1=null;
			System.out.println("Enter patient disease: ");
			String disease=s.next();
			try {	
				Class.forName("com.mysql.cj.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
				ps1=con.prepareStatement("select * from patient where disease=?");
				ps1.setString(1, disease);
				boolean b=ps1.execute();
				if(b==true)
				{
					ResultSet rs=ps1.getResultSet();
					while(rs.next())
					{
						System.out.print(rs.getInt(1)+"\t");
						System.out.print(rs.getString(2)+"\t");
						System.out.print(rs.getInt(3)+"\t");
						System.out.println(rs.getLong(4)+"\t");
						System.out.println(rs.getString(5)+"\t");
						System.out.println(rs.getString(6)+"\t");
					}
				
			} }
			catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			finally {
				if(ps1!=null)
				{
					try {
						ps1.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if(con!=null)
				{
					try {
						con.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			
		}
		}
		//Search patient by name
		static void searchPatientByName()
		{
			Connection con=null;;
			PreparedStatement ps=null;
			System.out.println("Enter patient by name : ");
			String name=s.next();
			try {
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
				PreparedStatement ps1=con.prepareStatement("select * from patient where name=?");
				ps1.setString(1, name);
				boolean b=ps1.execute();
				if(b==true)
				{
					ResultSet rs=ps1.getResultSet();
					while(rs.next())
					{
						System.out.print(rs.getInt(1)+"\t");
						System.out.print(rs.getString(2)+"\t");
						System.out.print(rs.getInt(3)+"\t");
						System.out.println(rs.getLong(4)+"\t");
						System.out.println(rs.getString(5)+"\t");
						System.out.println(rs.getString(6)+"\t");
					}
				
			} }
			catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			finally {
				if(ps!=null)
				{
					try {
						ps.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if(con!=null)
				{
					try {
						con.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			
			}
		}
		//delete patient based on phone and name
		static void delete()
		{
//			patient record by phno and name
			Connection con=null;;
			PreparedStatement ps=null;
			System.out.println("Enter patient name: ");
			String name=s.next();
			System.out.println("Enter patient phonenum: ");
			long phno=s.nextLong();
			try {
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
				ps=con.prepareStatement("delete from patient where name=? and phone=?");
				ps.setString(1,name);
				ps.setLong(2,phno);
				int row=ps.executeUpdate();
				System.out.println(row+" rows are deleted successfully");
			} 
			catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			finally {
				if(ps!=null)
				{
					try {
						ps.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if(con!=null)
				{
					try {
						con.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			
		}
		}
}
