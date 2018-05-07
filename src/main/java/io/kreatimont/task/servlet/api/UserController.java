package main.java.io.kreatimont.task.servlet.api;

import com.google.gson.Gson;
import main.java.io.kreatimont.task.model.User;
import main.java.io.kreatimont.task.db.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class UserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws   ServletException, IOException {
        super.doGet(req, resp);



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    //    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        List<User> allUsers = UserRepository.instance().findAllUsers();
//        resp.setContentType("application/json");
//        PrintWriter out = resp.getWriter();
//        out.print(new Gson().toJson(allUsers));
//    }
}
