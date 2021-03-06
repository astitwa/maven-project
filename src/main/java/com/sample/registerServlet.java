package com.sample;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.sql.*;

@WebServlet(
        name = "register",
        urlPatterns = "/registerServlet"
)

public class registerServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        boolean rowInserted = false;

        String name     = req.getParameter("name");
        String username = req.getParameter("userName");
        String password = req.getParameter("password");
        String email    = req.getParameter("email");

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/login_db","root",null);
            String sql = "INSERT INTO login(userName, password, email, name) VALUES(?,?,?,?)";
            PreparedStatement statement=con.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, email);
            statement.setString(4, name);



             rowInserted = statement.executeUpdate() > 0;

            con.close();

            req.setAttribute("rowInserted", rowInserted);
            RequestDispatcher view = req.getRequestDispatcher("/index.html");
            PrintWriter out = resp.getWriter();
            out.println("<font color=red><b>SignUp Successful</b></font>");
            view.include(req, resp);
          //  view.forward(req, resp);
        }
        catch
                (Exception e){
            req.setAttribute("rowInserted", rowInserted);
            RequestDispatcher view = req.getRequestDispatcher("/index.html");
            PrintWriter out = resp.getWriter();
            out.println("<font size=red><b>SignUp Failed</b></font>");
            view.include(req, resp);
          //  view.forward(req, resp);
        }


    }
}
