package cn.techtutorial.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.techtutorial.model.Product;
import cn.techtutorial.dao.ProductDao;
import cn.techtutorial.connection.DbCon;
import javax.servlet.http.Part;

@WebServlet("/AddProductServlet")
@MultipartConfig
public class AddProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Add Product</title>");
            out.println("</head>");
            out.println("<body>");
            String name = request.getParameter("name");
            String category = request.getParameter("category");
            Double price = Double.parseDouble(request.getParameter("price"));
            String description = request.getParameter("description");
            String season = request.getParameter("season");
            String origin = request.getParameter("origin");
            Part filePart = request.getPart("image");

            String fileName = "";
            if (filePart != null && filePart.getSize() > 0) {
                fileName = getSubmittedFileName(filePart);
                //filePart.write("C:\\Users\\ASUS\\eclipse-workspace\\Final_Project\\WebContent\\product-image\\" + fileName); // Thay đổi đường dẫn lưu trữ tệp theo ý bạn
            }

            Product product = new Product(name, category, price, fileName, description, season, origin);

            try {
                ProductDao pdao = new ProductDao(DbCon.getConnection());
                if (pdao.addProduct(product)) {
                    response.sendRedirect("ProductManager.jsp");
                } else {
                    out.print("Failed to add product.");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            out.println("</body>");
            out.println("</html>");

        }
    }

    private String getSubmittedFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] items = contentDisposition.split(";");
        for (String item : items) {
            if (item.trim().startsWith("filename")) {
                return item.substring(item.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return "";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
