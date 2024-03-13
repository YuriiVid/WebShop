package ua.nung.edu.pz.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.nung.edu.pz.model.Firebase;
import ua.nung.edu.pz.model.User;
import ua.nung.edu.pz.view.IndexView;

import java.io.*;
import java.util.Properties;


@WebServlet(name = "StartServlet", urlPatterns = {"/*"}, loadOnStartup = 1)
public class StartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String body = "";
        String context = "";

        switch (request.getPathInfo()) {
            case "/contacts":
                context = "<h2>Our Contacts!</h2>\n";
                break;
            case "/login":
                context = "<h2>Login!</h2>\n";
                context += IndexView.getInstance().getLoginForm();
                break;
            case "/forgotpassword":
                context = "<h2>Restore Password!</h2>\n";
                break;
            default:
                context = "<h2>Hello World from Servlet!</h2>\n";
        }


        body = IndexView.getInstance().getBody(
                IndexView.getInstance().getHeader(""),
                IndexView.getInstance().getFooter(""),
                context);

        out.println(IndexView.getInstance().getPage("Green Shop", body));

        User user = new User();
        user.setEmail("email1@email.com");
        user.setPassword("112211221122");
        user.setDisplayName("Test User");
        Firebase firebase = Firebase.getInstance();
        if (firebase.getUserByEmail(user.getEmail()).equals(Firebase.USER_EXISTS)) {
            String firebaseResponse = firebase.signInWithEmailAndPassword(user.getEmail(), user.getPassword());
            if(firebaseResponse.equals(Firebase.PASSWORD_OK)) {
                System.out.println(Firebase.PASSWORD_OK);
            }  else {
                System.out.println("Wrong Password");
            }
        } else {
            System.out.println("User NOT Exist");
            String userMsg = Firebase.getInstance().createUser(user);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String contextPath = request.getContextPath();
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
        String path = getServletContext().getRealPath("assets/html/");
        IndexView indexView = IndexView.getInstance();
        indexView.setPath(path);

        String[] firebaseConfig = readFirebaseConfig();
        Firebase.getInstance().setFirebaseConfigPath(firebaseConfig[0]);
        Firebase.getInstance().setFirebaseName(firebaseConfig[1]);
        Firebase.getInstance().setApiKey(firebaseConfig[2]);
        Firebase.getInstance().setSignInUrl(firebaseConfig[3]);
        Firebase.getInstance().init();
    }

    private String[] readFirebaseConfig() {
        Properties props = new Properties();
        String[] firebasrProp = new String[4];
        InputStream is = getClass().getClassLoader().getResourceAsStream("app.properties");
        try {
            props.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        firebasrProp[0] = props.getProperty("file.path");
        firebasrProp[1] = props.getProperty("firebase.name");
        firebasrProp[2] = props.getProperty("web.api.key");
        firebasrProp[3] = props.getProperty("signInUrl");
        return firebasrProp;
    }
}
