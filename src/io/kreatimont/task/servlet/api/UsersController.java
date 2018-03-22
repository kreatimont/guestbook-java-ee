package io.kreatimont.task.servlet.api;

import com.google.gson.Gson;
import io.kreatimont.task.model.User;
import io.kreatimont.task.model.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class UsersController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> allUsers = UserRepository.instance().findAllUsers();
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print(new Gson().toJson(allUsers));
    }
}
