package com.servlets;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Registration extends HttpServlet{
public void doGet(HttpServletRequest req,HttpServletResponse res) {
	res.setContentType("text/html");
	try {
		PrintWriter out=res.getWriter();
		String name=req.getParameter("name");
		String mail=req.getParameter("mailID");
		String pwd=req.getParameter("password");
		String phno=req.getParameter("phno");
		String dob=req.getParameter("dob");
		Class.forName("org.mariadb.jdbc.Driver");
		String url="jdbc:mariadb://localhost:3306/students";
		String username="root";
		String password="root";
		Connection con=DriverManager.getConnection(url,username,password);
		PreparedStatement ps=con.prepareStatement("insert into register values(?,?,?,?,?)");
		ps.setString(1, name);
		ps.setString(2, mail);
		ps.setString(3, pwd);
		ps.setLong(4, Long.parseLong(phno));
		ps.setString(5, dob);
		
		ps.executeQuery();
		out.println("<font color=red>Inserted Succesfully</font>");
		RequestDispatcher rd=req.getRequestDispatcher("register.html");
		rd.include(req, res);
	}catch(Exception e) {
		e.printStackTrace();
	}
}
public void doPost(HttpServletRequest req,HttpServletResponse res) {
	doGet(req,res);
}
}
