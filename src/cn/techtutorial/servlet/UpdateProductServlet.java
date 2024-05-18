package cn.techtutorial.servlet;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import cn.techtutorial.model.Product;
import cn.techtutorial.dao.ProductDao;
import cn.techtutorial.connection.DbCon;

@WebServlet("/UpdateProductServlet")
@MultipartConfig
public class UpdateProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        Double price = Double.parseDouble(request.getParameter("price"));
        String description = request.getParameter("description");
        String season = request.getParameter("season");
        String origin = request.getParameter("origin");
        Part filePart = request.getPart("image");
        
        String uploadDir = "C:\\Users\\ASUS\\eclipse-workspace\\Final_Project\\WebContent\\product-image\\";
//        File uploadDirectory = new File(uploadDir);
//        if (!uploadDirectory.exists()) {
//            uploadDirectory.mkdirs();
//        }

        String fileName = "";
        if (filePart != null && filePart.getSize() > 0) {
            fileName = getSubmittedFileName(filePart);
            //filePart.write(uploadDir + fileName);
        } else {
            fileName = request.getParameter("existingImage");
        }

        Product product = new Product(id, name, category, price, fileName, description, season, origin);

        try {
            ProductDao pdao = new ProductDao(DbCon.getConnection());
            if (pdao.editProductInfo(product)) {
                response.sendRedirect("ProductManager.jsp");
            } else { 
                response.getWriter().print("Failed to update product.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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
