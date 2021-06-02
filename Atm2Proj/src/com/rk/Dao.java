package com.rk;

import java.sql.*;
import java.util.Scanner;

public class Dao extends OptionMenu 
{

public void dbase(int c, int p)
{
	Connection con= null;
	PreparedStatement pstmt=null;
	String url="jdbc:mysql://localhost:3306/temprk";
	String uname="root";
	String pass="Mipassword@123";
	String qry="select * from acc where custNo=?";
	String qry2="Update acc Set bal=? where custNo=?";
	String qry3="select bal from acc where custNo=?";
	
	try
	{
		
	Class.forName("com.mysql.cj.jdbc.Driver");
	con=DriverManager.getConnection(url,uname,pass);
	pstmt= con.prepareStatement(qry);
	pstmt.setInt(1,c);
	ResultSet rs= pstmt.executeQuery();
	if(rs.next())
	{
		int id= rs.getInt(1);
		String name=rs.getString(2);
		int cno=rs.getInt(3);
		int pn= rs.getInt(4);
		int bal=rs.getInt(5);
		
		
		if(c==cno && p==pn)
		{	
		Scanner sc= new Scanner(System.in);
			System.out.println("Welcome! "+name);	
		int r=transaction();
		switch(r)
		{
		case 1:
			System.out.println("Your Current Balance is: "+bal);
			break;
			
		case 2:
			System.out.println("Enter Amount to be Deposit");
			int dpt=sc.nextInt();
			bal=bal+dpt;
			PreparedStatement pstmt2= con.prepareStatement(qry2);
			pstmt2.setInt(1, bal);
			pstmt2.setInt(2, c);
			pstmt2.executeUpdate();
			System.out.println("Amount Deposited Sucessfully");
			PreparedStatement pstmt3=con.prepareStatement(qry3);
			pstmt3.setInt(1, c);
			ResultSet rs3=pstmt3.executeQuery();
			if(rs3.next())
			{
				int dptBal=rs3.getInt(1);
				System.out.println("Your Balance is: "+dptBal);
			}
			
			break;
			
		case 3:
			System.out.println("Enter Amount to Withdraw");
			int wtd=sc.nextInt();
			if(wtd>bal)
			{
				System.out.println("Insufficient Balance");
			}
			else
			{
				bal=bal-wtd;
				PreparedStatement pstmt4=con.prepareStatement(qry2);
				pstmt4.setInt(1, bal);
				pstmt4.setInt(2, c);
				pstmt4.executeUpdate();
				System.out.println("Amount Withdrawn Sucessfully");
				PreparedStatement pstmt5=con.prepareStatement(qry3);
						pstmt5.setInt(1, c);
						ResultSet rs5=pstmt5.executeQuery();
						if(rs5.next())
						{
							int wtdBal=rs5.getInt(1);
							System.out.println("Your Remaining Balance is: "+wtdBal);
						}		
			}
			
			break;
			
		case 4:
			System.out.println("Thank You for Using This Atm!...\n");
			System.exit(1);
			break;
			
		default:
			System.out.println("Invalid Option");
			
			break;
			
		}
		
		dbase( c,  p);
		
	}
	else
		{
			System.out.println("Invalid Details");
		}
	}
	else
	{
		System.out.println("invalid Customer Number");
	}
	}
	
	catch(ClassNotFoundException|SQLException e)
	{
		e.printStackTrace();
	
	}
	finally
	{
		if(pstmt!=null)
		{
			try
			{
				pstmt.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		
		if(con!=null)
		{
			try
			{
				con.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
		
}
}
