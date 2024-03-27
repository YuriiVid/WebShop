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
import java.util.stream.Collectors;

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

		String body = goods.stream().map(good -> {
			return "<div class=\"col-12 col-sm-6 col-lg-4 col-xl-3\">" +
					"<div class=\"card\" style=\"width: 18rem;\">\n" +
					"  <div class=\"card-body\">\n" +
					"    <h5 class=\"card-title\">" + good.getName() + "</h5>\n" +
					"    <h6 class=\"card-subtitle mb-2 text-body-secondary\">Card subtitle</h6>\n" +
					"    <p class=\"card-text\">" + good.getDescription() + "</p>\n" +
					"    <a href=\"#\" class=\"card-link\">Card link</a>\n" +
					"    <a href=\"#\" class=\"card-link\">Another link</a>\n" +
					"  </div>\n" +
					"</div>"
					+ "</div>";
		}).collect(Collectors.joining());

		body = "<div class=\"container-fluid\"> <div class=\"row\">" + body + "</div> </div>";

		String builderPage = MainPage.Builder.newInstance()
				.setTitle("Green Shop")
				.setHeader("")
				.setBody(body)
				.setFooter()
				.build()
				.getFullPage();

		out.println(builderPage);
	}
}
