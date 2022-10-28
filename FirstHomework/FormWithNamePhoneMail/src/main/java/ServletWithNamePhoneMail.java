import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "NamePhoneEmail", urlPatterns = "/npe")
public class ServletWithNamePhoneMail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        if (name == "") {
            out.println("<h1>Warning! Name is empty.</h1>");
            out.println("<h1><a href=\"/myapp/index.html\">Form</a><h1>");
        } else if ((phone == "") && (email == "")) {
            out.println("<h1>Warning! Phone and e-mail is empty.</h1>");
            out.println("<h1><a href=\"/myapp/index.html\">Form</a></h1>");
        } else {
            out.println("<html><head><title>First Servlet</title></head>");
            out.println("<body><h1>User name: " + name + "</h1>");
            out.println("<body><h1>User phone: " + phone + "</h1>");
            out.println("<body><h1>User e-mail: " + email + "</h1>");
            out.println("<body></html>");
        }
    }
}
