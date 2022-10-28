import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Scanner;

@WebServlet(name = "Counter", urlPatterns = "/counter")
public class CounterServlet extends HttpServlet {
    private static int counter = 0;
    private File file = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        file = new File(config.getServletContext().getRealPath("/counter.txt"));
        try (FileReader reader = new FileReader(file);
             Scanner scan = new Scanner(reader)) {
            while (scan.hasNextLine()) {
                counter = scan.nextInt();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        counter++;
        PrintWriter out = resp.getWriter();
        out.println("<h2>Счетчик посещений:</h2>");
        out.println("<div>" + counter + "</div>");
        countWriter();
        out.println("<a href=\"/myapp/counter.txt\">Counter file</a>");
    }

    private void countWriter() throws IOException {
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write("Counter visits: " + counter + "" + " at "
                    + LocalDateTime.now() + "\n");
        }
    }
}