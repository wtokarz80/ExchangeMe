package com.exchangeme.servlets;

import com.exchangeme.dao.UserDao;
import com.exchangeme.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.exchangeme.helpers.DataParser.parseQueryString;


@WebServlet(name = "UserServlet", urlPatterns = {"/users", "/users/*"}, loadOnStartup = 1)

public class UserServlet extends HttpServlet {

    UserDao userDao = new UserDao();
    ObjectMapper objectMapper = new ObjectMapper();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setCharacterEncoding("UTF-8");
        String[] splitURI = request.getRequestURI().split("/");
        if(splitURI.length == 2){
            List<User> users = userDao.getAll();
            out.println(objectMapper.writeValueAsString(users));

        } else if (splitURI.length == 3 && splitURI[2].matches("\\d+")) {
            getOneUser(splitURI, out);
        }
        else {
            String errorMessage = "SOMETHING GOES WRONG";
            out.println(errorMessage);
        }



    }

    private void getOneUser(String[] splitURI, PrintWriter out) {
        long userId = Long.parseLong(splitURI[2]);
        Optional<User> user = userDao.getById(userId);
        user.ifPresentOrElse(
                u -> {
                    try {
                        out.println(objectMapper.writeValueAsString(u));
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                },
                () -> out.println("There is no user with the given id")
        );
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        String queryString = request.getQueryString();
        Map<String,String> parsedQuery = parseQueryString(queryString);
        if(parsedQuery.containsKey("name")
                && parsedQuery.containsKey("email")
                && parsedQuery.containsKey("password"))
        {
            user.setUserName(parsedQuery.get("name"));
            user.setEmail(parsedQuery.get("email"));
            user.setPassword(parsedQuery.get("password"));
            userDao.insert(user);
        } else {
            response.getWriter().println("SOMETHING GOES WRONG");
        }
    }


    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] splitURI = request.getRequestURI().split("/");
        if (!(splitURI.length == 3 && splitURI[2].matches("\\d+"))) {
            response.getWriter().println("SOMETHING GOES WRONG");
        }
        long userId = Long.parseLong(splitURI[2]);
        userDao.delete(userId);
    }


}
