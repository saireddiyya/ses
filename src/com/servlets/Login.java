package com.servlets;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res) {
		res.setContentType("text/html");
		try {
			PrintWriter out=res.getWriter();
			String mail=req.getParameter("mailID");
			String pwd=req.getParameter("password");
			Class.forName("org.mariadb.jdbc.Driver");
			String url="jdbc:mariadb://localhost:3306/students";
			String username="root";
			String password="root";
			Connection con=DriverManager.getConnection(url,username,password);
			PreparedStatement ps=con.prepareStatement("select * from register where mail=? and pass=?");
			ps.setString(1, mail);
			ps.setString(2, pwd);
			ResultSet rs=ps.executeQuery();
			HttpSession session=req.getSession();
			if(rs.next()) {
				String name=rs.getString("name");
				String pno=rs.getString("phno");
			
				session.setAttribute("mailref",mail);
				session.setAttribute("phnoref",pno);
				session.setAttribute("nameref",name);
			
				res.sendRedirect("welcomePage");
			}
			else
			{
				out.println("<font color=red>Sorry mail or password is incorrect</font>");
				req.getRequestDispatcher("login.html").include(req, res);
				
			}
           }catch(Exception e){
        	   e.printStackTrace();
           }
		}
	public void doPost(HttpServletRequest req,HttpServletResponse res) {
		doGet(req,res);
	}
}