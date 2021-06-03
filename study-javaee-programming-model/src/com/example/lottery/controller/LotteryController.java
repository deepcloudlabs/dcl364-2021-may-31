package com.example.lottery.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.lottery.model.LotteryModel;
import com.example.lottery.service.LotteryService;

@WebServlet(urlPatterns = "/command")
public class LotteryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @Inject
    private LotteryModel lotteryModel;
    @Inject
    private LotteryService lotteryService;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         request.getRequestDispatcher("home.jsp")
                .forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var asyncContext = request.startAsync();
        asyncContext.start(() -> {
        	System.err.println("doPost: "+lotteryService.getClass().getName());
        	var command = request.getParameter("command");
        	switch (command.toLowerCase()) {
        	case "draw":
        		var column = 1;
        		try {
        			column = Integer.parseInt(request.getParameter("column"));
        		} catch (NumberFormatException e) {	}
        		lotteryModel.getNumbers().addAll(lotteryService.draw(60, 6, column));
        		break;
        	case "reset":
        		lotteryModel.getNumbers().clear();
        		break;
        		
        	default:
        		break;
        	}
        	try {
				request.getRequestDispatcher("home.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			} 
        	asyncContext.complete();
        });
	}

}
