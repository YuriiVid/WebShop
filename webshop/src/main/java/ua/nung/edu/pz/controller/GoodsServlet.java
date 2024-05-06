package ua.nung.edu.pz.controller;

import ua.nung.edu.pz.dao.entity.Cart;
import ua.nung.edu.pz.dao.entity.Good;
import ua.nung.edu.pz.dao.entity.User;
import ua.nung.edu.pz.dao.repository.GoodRepository;
import ua.nung.edu.pz.view.MainPage;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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

		HttpSession httpSession = request.getSession();
		User user = (User) httpSession.getAttribute(User.USER_SESSION_NAME);
		String userName = user == null ? "" : user.getDisplayName();

		Cart cart = AddItem(request, user, response);

		GoodRepository goodRepository = new GoodRepository();
		ArrayList<Good> goods = goodRepository.getByBrand("Naturalis");

		String body = goods.stream().map(good -> {
			String[] carouselItems = new String[good.getPhoto().length];
			for (int i = 0; i < good.getPhoto().length; i++) {
				String active = (i == 0) ? " active" : "";
				carouselItems[i] = "<div class=\"carousel-item" + active + "\">\n" +
						"<img src=\"/assets/img/" + (good.getPhoto().length > 0 ? good.getPhoto()[i] : "")
						+ "\" class=\"d-block w-100\" alt=\"good image\">\n" +
						"</div>\n";
			}
			return "<div class=\"col-12 col-sm-6 col-lg-4 col-xl-3 my-2\">" +
					"<div class=\"card\">\n" +
					"<div id=\"carouselExample" + good.getId() + "\" class=\"carousel carousel-dark slide\">\n" +
					"  <div class=\"carousel-inner\">\n" +
					String.join("", carouselItems) +
					"  </div>\n" +
					"  <button class=\"carousel-control-prev\" type=\"button\" data-bs-target=\"#carouselExample"
					+ good.getId() + "\" data-bs-slide=\"prev\">\n" +
					"    <span class=\"carousel-control-prev-icon\" aria-hidden=\"true\"></span>\n" +
					"    <span class=\"visually-hidden\">Previous</span>\n" +
					"  </button>\n" +
					"  <button class=\"carousel-control-next\" type=\"button\" data-bs-target=\"#carouselExample"
					+ good.getId() + "\" data-bs-slide=\"next\">\n" +
					"    <span class=\"carousel-control-next-icon\" aria-hidden=\"true\"></span>\n" +
					"    <span class=\"visually-hidden\">Next</span>\n" +
					"  </button>\n" +
					"</div>" +
					"  <div class=\"card-body\">\n" +
					"    <div class=\"d-flex justify-content-between \">" +
					"      <h5 class=\"card-title position-relative me-4\">" + good.getName() + "</h5>" +
					"	   <span class=\"d-flex badge rounded-pill\"><h5 class=\"text-body-secondary\">" +
					"<i class=\"bi-heart-fill me-1\" style=\"color: #dc3545;\"></i>"
					+ good.getLikes() + "</h5></span>" +
					"</div>\n" +
					"    <h6 class=\"card-subtitle mb-2 text-body-secondary\">Price:" + good.getPrice().getFor_client()
					+ " UAH</h6>\n" +
					"    <p class=\"card-text\">" + good.getShortDescription() + "</p>\n" +
					"<a href=\"/goods/add-item?priceid=" + good.getPrice().getId() + "\" class=\"btn btn-success\">\n" +
					"   <svg xmlns=\"http://www.w3.org/2000/svg\" width=\"16\" height=\"16\" fill=\"currentColor\" class=\"bi bi-bag-plus\" viewBox=\"0 0 16 16\">\n"
					+
					"  <path fill-rule=\"evenodd\" d=\"M8 7.5a.5.5 0 0 1 .5.5v1.5H10a.5.5 0 0 1 0 1H8.5V12a.5.5 0 0 1-1 0v-1.5H6a.5.5 0 0 1 0-1h1.5V8a.5.5 0 0 1 .5-.5\"></path>\n"
					+
					"  <path d=\"M8 1a2.5 2.5 0 0 1 2.5 2.5V4h-5v-.5A2.5 2.5 0 0 1 8 1m3.5 3v-.5a3.5 3.5 0 1 0-7 0V4H1v10a2 2 0 0 0 2 2h10a2 2 0 0 0 2-2V4zM2 5h12v9a1 1 0 0 1-1 1H3a1 1 0 0 1-1-1z\"></path>\n"
					+ "</svg>\n" +
					"                Add\n" +
					"              </a>" +
					"  </div>\n" +
					"</div>"
					+ "</div>";
		}).collect(Collectors.joining());

		body = "<div class=\"container-fluid\"> <div class=\"row\">" + body + "</div> </div>";

		String builderPage = MainPage.Builder.newInstance()
				.setTitle("Green Shop")
				.setHeader(userName)
				.setBody(body)
				.setFooter()
				.build()
				.getFullPage();

		out.println(builderPage);
	}

	private Cart AddItem(HttpServletRequest request, User user, HttpServletResponse response) throws IOException {
		Cart cart = new Cart();
		String priceStr = request.getParameter("priceid");
		if (priceStr != null) {
			long priceId = 0l;
			try {
				priceId = Long.parseLong(priceStr);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			// check if user logged in
			if (user != null) {
				System.out.println("User " + user);
			}

			if (priceId > 0) {
				response.sendRedirect("/goods/");
			}
		}
		return cart;
	}
}