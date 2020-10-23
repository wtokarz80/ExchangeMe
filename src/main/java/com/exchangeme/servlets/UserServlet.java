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
            response.setStatus(200);

        } else if (splitURI.length == 3 && splitURI[2].matches("\\d+")) {
            getOneUser(splitURI, out, response);
        }
        else {
            response.setStatus(404);
        }
    }

    private void getOneUser(String[] splitURI, PrintWriter out, HttpServletResponse response) {
        long userId = Long.parseLong(splitURI[2]);
        Optional<User> user = userDao.getById(userId);
        user.ifPresentOrElse(
                u -> {
                    try {
                        out.println(objectMapper.writeValueAsString(u));
                        response.setStatus(200);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                },
                () -> response.setStatus(404)
        );
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String queryString = request.getQueryString();
        Map<String,String> parsedQuery = parseQueryString(queryString);
        if(parsedQuery.containsKey("name")
                && parsedQuery.containsKey("email")
                && parsedQuery.containsKey("password"))
        {
            User user = new User (parsedQuery.get("name"), parsedQuery.get("email"), parsedQuery.get("password"));
            userDao.insert(user);
        } else {
            response.setStatus(422);
        }
    }


    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] splitURI = request.getRequestURI().split("/");
        String queryString = request.getQueryString();
        Map<String,String> parsedQuery = parseQueryString(queryString);
        long userId = Long.parseLong(splitURI[2]);
        Optional<User> user = userDao.getById(userId);
        user.ifPresentOrElse((u -> updateUser(u, parsedQuery, response)),
                () -> response.setStatus(404)
        );
    }

    private void updateUser(User user, Map<String, String> parsedQuery, HttpServletResponse response) {
        if (parsedQuery.containsKey("name")) {
            user.setUserName(parsedQuery.get("name"));
        }
        if (parsedQuery.containsKey("email")) {
            user.setEmail(parsedQuery.get("email"));
        }
        if (parsedQuery.containsKey("password")) {
            user.setPassword(parsedQuery.get("password"));
        }
        userDao.update(user);
        response.setStatus(204);
    }


    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] splitURI = request.getRequestURI().split("/");
        if (!(splitURI.length == 3 && splitURI[2].matches("\\d+"))) {
            response.setStatus(404);
        }
        long userId = Long.parseLong(splitURI[2]);
        userDao.delete(userId);
        response.setStatus(204);

    }


}
