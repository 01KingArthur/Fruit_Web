package cn.techtutorial.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.techtutorial.connection.DbCon;
import cn.techtutorial.dao.ProductDao;
import cn.techtutorial.model.Product;

/**
 * Servlet implementation class DeleteProductServlet
 */
@WebServlet("/DeleteProductServlet")
public class DeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
    	try (PrintWriter out = response.getWriter()){
    		out.println("<!DOCTYPE html>");
    		out.println("<html>");
    		out.println("<head>");
    		out.println("<title>Add Product</title>");
    		out.println("</head>");
    		out.println("<body>");
    		int bid = Integer.parseInt(request.getParameter("id"));
            
            try{
                ProductDao bkd = new ProductDao(DbCon.getConnection());
                bkd.deleteProduct(bid);
                response.sendRedirect("ProductManager.jsp");
            }catch(Exception e){
                e.printStackTrace();
            }
    		out.println("</body>");
    		out.println("</html>");
    		
    	}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
    	try (PrintWriter out = response.getWriter()){
    		out.println("<!DOCTYPE html>");
    		out.println("<html>");
    		out.println("<head>");
    		out.println("<title>Add Product</title>");
    		out.println("</head>");
    		out.println("<body>");
    		int bid = Integer.parseInt(request.getParameter("id"));
            
            try{
                ProductDao bkd = new ProductDao(DbCon.getConnection());
                bkd.deleteProduct(bid);
                System.out.print("xoaxoa");
                response.sendRedirect("ProductManager.jsp");
            }catch(Exception e){
                e.printStackTrace();
            }
    		out.println("</body>");
    		out.println("</html>");
    		
    	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
