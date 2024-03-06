package ua.nung.edu.pz.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.nung.edu.pz.model.User;
import ua.nung.edu.pz.view.IndexView;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "StartServlet", urlPatterns = { "/*" }, loadOnStartup = 1)
public class StartServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String context = "<h2>Hello World from Servlet!</h2>\n";
		context += IndexView.getInstance().getLoginForm();

		String body = IndexView.getInstance().getBody(
				IndexView.getInstance().getHeader(""),
				IndexView.getInstance().getFooter(""),
				context);

		out.println(IndexView.getInstance().getPage("Welcome to the Shop", body));
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// String contextPath = request.getContextPath();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);

		System.out.println(user);

		response.sendRedirect("/");
	}

	@Override
	public void init() throws ServletException {
		super.init();
		String path = getServletContext().getRealPath("html/");
		IndexView.getInstance().setPath(path);
	}
}
