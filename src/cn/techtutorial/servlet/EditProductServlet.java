package cn.techtutorial.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.techtutorial.model.Product;
import cn.techtutorial.dao.ProductDao;
import cn.techtutorial.connection.DbCon;

@WebServlet("/EditProductServlet")
public class EditProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            ProductDao pdao = new ProductDao(DbCon.getConnection());
            Product product = pdao.getSingleProduct(id);
            request.setAttribute("product", product);
            request.getRequestDispatcher("editproduct.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
