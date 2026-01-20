/*
 *	log in
 */

import java.io.*;
import java.sql.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class LogIn extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		/* parameters */
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		/* query */
		ConnectionPool cp=ConnectionPool.getInstance();
		Connection c=cp.getConnection();
		
		String query="SELECT id FROM users WHERE name=? AND password=?;";
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try{
			ps=c.prepareStatement(query);
			
			ps.setString(1,username);
			ps.setString(2,password);
			
			rs=ps.executeQuery();
			
			if(!rs.next()){
				request.setAttribute("msg","access denied!");
				
				DBUtil.closePreparedStatement(ps);
				DBUtil.closeResultSet(rs);
				cp.freeConnection(c);
				
				RequestDispatcher rd=getServletContext().getRequestDispatcher("/log_in.jsp");
				rd.forward(request,response);
				return;
			}else{
				int userId=rs.getInt("id");
			
				HttpSession oldSession=request.getSession(false);
				
				if(oldSession!=null){
					oldSession.invalidate();
				}
			
				HttpSession session=request.getSession(true);
				session.setMaxInactiveInterval(30*60);
				session.setAttribute("user_id",userId);
				session.setAttribute("username",username);
			}
		}catch(SQLException e){
			e.printStackTrace();
		
			request.setAttribute("msg","internal error; try again!");
			
			DBUtil.closePreparedStatement(ps);
			DBUtil.closeResultSet(rs);
			cp.freeConnection(c);
			
			RequestDispatcher rd=getServletContext().getRequestDispatcher("/log_in.jsp");
			rd.forward(request,response);
			return;
		}
		
		/* free connection */
		DBUtil.closePreparedStatement(ps);
		DBUtil.closeResultSet(rs);
		cp.freeConnection(c);
		
		/* redirect user */
		String url=response.encodeRedirectURL("home.jsp");
		response.sendRedirect(url);
		return;
	}
	
}
