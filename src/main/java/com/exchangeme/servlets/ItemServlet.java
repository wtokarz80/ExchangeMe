package com.exchangeme.servlets;

import com.exchangeme.dao.ItemDao;
import com.exchangeme.models.Item;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ItemServlet", urlPatterns = {"/items", "/items/*"}, loadOnStartup = 1)
public class ItemServlet extends HttpServlet {

    ItemDao itemDao = new ItemDao();
    ObjectMapper objectMapper = new ObjectMapper();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setCharacterEncoding("UTF-8");
        String[] splitURI = request.getRequestURI().split("/");
        if(splitURI.length == 2){
            List<Item> items = itemDao.getAll();
            System.out.println(items);
            out.println(objectMapper.writeValueAsString(items));

        } else if (splitURI.length == 3 && splitURI[2].matches("\\d+")) {
            long id = Long.parseLong(splitURI[2]);
            Item item = itemDao.getItemById(id);
            System.out.println(item);
            out.println(objectMapper.writeValueAsString(item));
        }
        else {
            out.println(
                    "<html>\n" +
                            "<head><title>ITEMS</title></head>\n" +
                            "<body>\n" +
                            "<h2 align = \"center\"><< SOMETHING GOES WRONG >></h2>\n" +
                            "</body></html>"
            );
        }
    }
}
