/*
 *	log in
 */

import java.io.*;
import java.sql.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class Register extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		/* parameters */
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String passwordRepeat=request.getParameter("password_repeat");
		
		/* database */
		ConnectionPool cp=ConnectionPool.getInstance();
		Connection c=cp.getConnection();
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query=null;
		
		/* check if username exists */
		query="SELECT 0 FROM users WHERE name=?;";
		
		try{
			ps=c.prepareStatement(query);
			
			ps.setString(1,username);
			
			rs=ps.executeQuery();
			
			if(rs.next()){
				request.setAttribute("msg","username already exists; choose another username!");

				DBUtil.closePreparedStatement(ps);
				DBUtil.closeResultSet(rs);
				cp.freeConnection(c);

				RequestDispatcher rd=getServletContext().getRequestDispatcher("/register.jsp");
				rd.forward(request,response);
				return;
			}
		}catch(SQLException e){
			e.printStackTrace();
		
			request.setAttribute("msg","internal error (1); try again!");
			
			DBUtil.closePreparedStatement(ps);
			DBUtil.closeResultSet(rs);
			cp.freeConnection(c);
			
			RequestDispatcher rd=getServletContext().getRequestDispatcher("/register.jsp");
			rd.forward(request,response);
			return;
		}
		
		/* check if passwords match */
		if(!password.equals(passwordRepeat)){
			request.setAttribute("msg","error; passwords don't match!");
			
			DBUtil.closePreparedStatement(ps);
			DBUtil.closeResultSet(rs);
			cp.freeConnection(c);
			
			RequestDispatcher rd=getServletContext().getRequestDispatcher("/register.jsp");
			rd.forward(request,response);
			return;
		}
		
		/* add new user to database table */
		query="INSERT INTO users (name,password) VALUES (?,?);";
		
		try{
			ps=c.prepareStatement(query);
			
			ps.setString(1,username);
			ps.setString(2,password);
			
			ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		
			request.setAttribute("msg","internal error (2); try again!");
			
			DBUtil.closePreparedStatement(ps);
			DBUtil.closeResultSet(rs);
			cp.freeConnection(c);
			
			RequestDispatcher rd=getServletContext().getRequestDispatcher("/register.jsp");
			rd.forward(request,response);
			return;
		}
		
		/* free connection */
		DBUtil.closePreparedStatement(ps);
		DBUtil.closeResultSet(rs);
		cp.freeConnection(c);
		
		/* redirect user */
		request.setAttribute("msg","user registered successfully!");
			
		DBUtil.closePreparedStatement(ps);
		DBUtil.closeResultSet(rs);
		cp.freeConnection(c);
		
		RequestDispatcher rd=getServletContext().getRequestDispatcher("/register.jsp");
		rd.forward(request,response);
		return;
	}
	
}
