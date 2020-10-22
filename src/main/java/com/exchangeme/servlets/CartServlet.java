package com.exchangeme.servlets;

import com.exchangeme.dao.CartDao;
import com.exchangeme.models.Cart;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "CartServlet", urlPatterns = {"/carts", "/carts/*"}, loadOnStartup = 1)
public class CartServlet extends HttpServlet {

    CartDao cartDao = new CartDao();
    ObjectMapper objectMapper = new ObjectMapper();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setCharacterEncoding("UTF-8");
        String[] splitURI = request.getRequestURI().split("/");
        if (splitURI.length == 2) {
            List<Cart> carts = cartDao.getAll();
            out.println(objectMapper.writeValueAsString(carts));
            response.setStatus(200);

        } else if (splitURI.length == 3 && splitURI[2].matches("\\d+")) {
            getCart(splitURI, out, response);
        } else {
            response.setStatus(404);
        }
    }

    private void getCart(String[] splitURI, PrintWriter out, HttpServletResponse response) {
        long id = Long.parseLong(splitURI[2]);
        Optional<Cart> cart = cartDao.getById(id);
        cart.ifPresentOrElse(
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
}