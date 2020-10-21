package com.exchangeme.servlets;

import com.exchangeme.dao.UserDao;
import com.exchangeme.models.User;
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
            System.out.println(users);
            out.println(objectMapper.writeValueAsString(users));

        } else {
            out.println(
                    "<html>\n" +
                            "<head><title>USERS</title></head>\n" +
                            "<body>\n" +
                            "<h2 align = \"center\"><< NOT IMPLEMENTED YET >></h2>\n" +
                            "</body></html>"
            );
        }



    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        PrintWriter out = response.getWriter();
//        String title = "TEST SERVLET";
//
//        out.println(
//                "<html>\n" +
//                        "<head><title>" + title + "</title></head>\n" +
//                        "<body>\n" +
//                        "<h1 align = \"center\">" + title + "</h1>\n" +
//                        "<h2 align = \"center\"><< HELLO USERS >></h2>\n" +
//                        "</body></html>"
//        );
//    }




}
