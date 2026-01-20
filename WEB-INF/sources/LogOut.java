/*
 *	log in
 */

import java.io.*;
import java.sql.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class LogOut extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session=request.getSession(false);
		
		if(session!=null){
			session.invalidate();
		}
		
		String url=response.encodeRedirectURL("index.jsp");
		response.sendRedirect(url);
	}
	
}
