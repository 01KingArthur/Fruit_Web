package cn.techtutorial.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.techtutorial.dao.ProductDao;
import cn.techtutorial.model.Product;
import cn.techtutorial.connection.DbCon;

@WebServlet("/description")
public class DescriptionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {int productId = Integer.parseInt(request.getParameter("id"));
        
        ProductDao productDao = new ProductDao(DbCon.getConnection());
        Product product = productDao.getSingleProduct(productId);
        
        if (product != null) {
            request.setAttribute("product", product);
            request.getRequestDispatcher("description.jsp").forward(request, response);
        } else {
            response.sendRedirect("index.jsp");
        }}
    	
    	
    	catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        
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
