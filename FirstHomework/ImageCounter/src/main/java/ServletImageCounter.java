import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

@WebServlet(name="Image Counter", urlPatterns = "/imagecounter")
public class ServletImageCounter extends HttpServlet {
    private static int counter = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        counter++;
        resp.setContentType("image/jpeg");
        BufferedImage image =
                new BufferedImage(500,200,BufferedImage.TYPE_INT_RGB);
        Graphics2D graph = image.createGraphics();
        graph.setFont(new Font("Serif",Font.BOLD, 48));
        graph.setColor(Color.blue);
        graph.drawString(String.valueOf(counter),100,100);
        ServletOutputStream out = resp.getOutputStream();
        ImageIO.write(image, "jpeg", out);
    }
}
