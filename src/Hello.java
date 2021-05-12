

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Hello() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		try {
		 Context initContext = new InitialContext();
         Context envContext = (Context) initContext.lookup("java:comp/env");
         System.out.println(envContext);
         DataSource ds = (DataSource) envContext.lookup("jdbc/reporting");
         System.out.println(ds);
         
         Connection conn = ds.getConnection();
         response.setContentType("text/html");
         
		 out.println("<h3>JNDI successful<h3>");
		} catch(Exception ex) {
			ex.printStackTrace();
			out.println("<h3>JNDI failed<h3>");
		}
		out.println("</body></html>");
	}

}
