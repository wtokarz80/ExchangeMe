package com.exchangeme.servlets;

import com.exchangeme.dao.ItemDao;
import com.exchangeme.models.Item;
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

@WebServlet(name = "ItemServlet", urlPatterns = {"/items", "/items/*"}, loadOnStartup = 1)
public class ItemServlet extends HttpServlet {

    private ItemDao itemDao = new ItemDao();
    private ObjectMapper objectMapper = new ObjectMapper();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setCharacterEncoding("UTF-8");
        String[] splitURI = request.getRequestURI().split("/");
        if(splitURI.length == 2){
            List<Item> items = itemDao.getAll();
            out.println(objectMapper.writeValueAsString(items));
            response.setStatus(200);
        } else if (splitURI.length == 3 && splitURI[2].matches("\\d+")) {
            getItem(splitURI, out, response);
        }
        else {
            response.setStatus(404);
        }
    }

    private void getItem(String[] splitURI, PrintWriter out, HttpServletResponse response) {
        long id = Long.parseLong(splitURI[2]);
        Optional<Item> item = itemDao.getById(id);
        item.ifPresentOrElse(
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

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] splitURI = request.getRequestURI().split("/");
        if (!(splitURI.length == 3 && splitURI[2].matches("\\d+"))) {
            response.setStatus(404);
        }
        long id = Long.parseLong(splitURI[2]);
        itemDao.delete(id);
        response.setStatus(204);
    }
}
