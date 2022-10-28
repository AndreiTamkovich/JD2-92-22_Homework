import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "GetBrowser", urlPatterns = "/getbrowser")
public class BrowserOnUa extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        String info = req.getHeader("User-Agent").toLowerCase();
        PrintWriter out = resp.getWriter();
        out.println("<h1> User-Agent:" + info + "</h1>");
        if ((info.contains("trident/") || (info.contains("; msie ;")))) {
            out.println("<h1> Приветствую пользователя Internet Explorer</h1>");
        } else if ((info.contains("opr/") || (info.contains("opera/")))) {
            out.println("<h1> Приветствую пользователя Opera</h1>");
        } else if ((info.contains("firefox/")) && (!(info.contains("seamonkey/")))) {
            out.println("<h1> Приветствую пользователя Firefox</h1>");
        } else if ((info.contains("seamonkey/"))) {
            out.println("<h1> Приветствую пользователя Seamonkey</h1>");
        } else if ((info.contains("edge/") || (info.contains("edg/")))) {
            out.println("<h1> Приветствую пользователя Microsoft Edge</h1>");
        } else if ((info.contains("crios/")) && ((info.contains("mobile/")))) {
            out.println("<h1> Приветствую пользователя Google Chrome</h1>");
        } else if ((info.contains("safari/")) && (!(info.contains("chrome/")))) {
            out.println("<h1> Приветствую пользователя Safari</h1>");
        } else if ((info.contains("chrome/")) && ((info.contains("yabrowser/")))) {
            out.println("<h1> Приветствую пользователя Яндекс Браузер</h1>");
        } else if ((info.contains("chrome/")) && (!(info.contains("opr/")))) {
            out.println("<h1> Приветствую пользователя Google Chrome</h1>");
        } else if ((info.contains("chrome/")) && (!(info.contains("chromium/")))) {
            out.println("<h1> Приветствую пользователя Google Chrome</h1>");
        } else if ((info.contains("chromium/"))) {
            out.println("<h1> Приветствую пользователя Chromium</h1>");
        }
    }
}
