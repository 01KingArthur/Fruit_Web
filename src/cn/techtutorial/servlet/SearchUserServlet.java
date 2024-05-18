package cn.techtutorial.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import cn.techtutorial.dao.UserDao;
import cn.techtutorial.model.User;
import cn.techtutorial.connection.DbCon;

@WebServlet("/SearchUserServlet")
public class SearchUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SearchUserServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String searchTerm = request.getParameter("search");
            UserDao userDao = new UserDao(DbCon.getConnection());
            List<User> searchResults;

            if (searchTerm != null && !searchTerm.isEmpty()) {
                // Thực hiện tìm kiếm và lưu kết quả vào attribute
                searchResults = userDao.searchUser(searchTerm);
                request.setAttribute("SEARCH_RESULTS", searchResults);
            } else {
                // Nếu không có dữ liệu tìm kiếm, lấy toàn bộ người dùng
                List<User> users = userDao.getAllUser();
                request.setAttribute("USERS_LIST", users);
            }

            // Forward request về trang hiện tại để hiển thị kết quả
            RequestDispatcher dispatcher = request.getRequestDispatcher("UserManager.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("An error occurred. Please check the server logs.");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
