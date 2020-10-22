package com.exchangeme.servlets;

import com.exchangeme.dao.CartDao;
import com.exchangeme.models.Cart;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet(name = "CartServlet", urlPatterns = {"/carts", "/carts/*"}, loadOnStartup = 1)
public class CartServlet extends HttpServlet {

    CartDao cartDao = new CartDao();
    ObjectMapper objectMapper = new ObjectMapper();

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