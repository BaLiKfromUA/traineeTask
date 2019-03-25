package com.aimprosoft.sandbox.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author BaLiK on 25.03.19
 */
@WebServlet("/test")
public class TestServlet extends HttpServlet {
    @Override
    public void init() {
        System.out.println("hello");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        String varTextA = "<h1>Hello World!</h1>";
        request.setAttribute("textA", varTextA);
        String varTextB = "<p>It JSP.</p>";
        request.setAttribute("textB", varTextB);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("bye");
    }
}
