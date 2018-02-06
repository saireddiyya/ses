package com.servlets;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class UserHome extends HttpServlet {
	public void doGet(HttpServletRequest req,HttpServletResponse res) {
		res.setContentType("text/html");
		try {
			PrintWriter out=res.getWriter();
			HttpSession session=req.getSession();
			String user=session.getAttribute("nameref").toString();
			String pno=session.getAttribute("phnoref").toString();
			
			out.println("<p align=right>Welcome "+user+"<button onclick=window.location''>LogOut</button></p>");
			out.println("<p align=left>My Number "+pno);
			
			
			
}catch(Exception e) {
	e.printStackTrace();
}
}
	public void doPost(HttpServletRequest req,HttpServletResponse res) {
		doGet(req,res);
	}
	
}
