package com.curso.maquina.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/vender.do")  //sustituye web.xml
public class VenderRefrescoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   //	response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		
		// despachar la petición al jsp que genera el html de salida
		
		RequestDispatcher rd = request.getRequestDispatcher("/vending/ventas.jsp");
		rd.forward(request, response);
		
		
		
		
	}//fin doGet

}
