package ua.nung.edu.pz.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.nung.edu.pz.model.Good;
import ua.nung.edu.pz.view.MainPage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "GoodsServlet", urlPatterns = { "/goods/*" })
public class GoodsServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		// TODO: remove test data
		// testing data start
		ArrayList<Good> goods = new ArrayList<>();
		String lorem = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
		String photo[] = new String[3];
		for (int i = 1; i <= 15; i++) {
			goods.add(new Good(i, "Good " + i, lorem, "Brand " + i, photo, i + 4));
		}
		// testing data end
		
		String context = "<h2>Goods!</h2>\n";

		String builderPage = MainPage.Builder.newInstance()
				.setTitle("Green Shop")
				.setHeader("")
				.setBody(context)
				.setFooter()
				.build()
				.getFullPage();

		out.println(builderPage);
	}
}
