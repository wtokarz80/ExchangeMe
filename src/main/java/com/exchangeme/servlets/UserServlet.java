package com.exchangeme.servlets;

import com.exchangeme.models.User;

import javax.persistence.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UserServlet", urlPatterns = {"/users"}, loadOnStartup = 1)

public class UserServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }



    public List<User> getAll() {

        EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
                .createEntityManagerFactory("exchangeMe");

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String strQuery = "SELECT u FROM User u WHERE u.id IS NOT NULL";

        // Issue the query and get a matching Customer
        TypedQuery<User> tq = em.createQuery(strQuery, User.class);
        List<User> users = new ArrayList<>();
        try {
            // Get matching customer object and output
            users = tq.getResultList();
            users.forEach(user->System.out.println(user.getUserName() + " " + user.getEmail() + " " + user.getPassword()));
        }
        catch(NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        return users;
    }

}
