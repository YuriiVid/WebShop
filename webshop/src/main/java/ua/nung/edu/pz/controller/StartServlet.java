package ua.nung.edu.pz.controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "StartServlet", urlPatterns = "/*", loadOnStartup = 1)
public class StartServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.println("<html>\r\n" + 
						"<body>\r\n" + 
						"<h2>Hello World From Servlet!</h2>\r\n" + 
						"</body>\r\n" + 
						"</html>\r\n" + 
						"");
	}

}
