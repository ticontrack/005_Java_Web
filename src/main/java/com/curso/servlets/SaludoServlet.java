package com.curso.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
		urlPatterns = {"/saludo"},
		loadOnStartup = 1
)
public class SaludoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Logger log = Logger.getLogger("SaludoServlet");
	
	private int contador;
	

	public SaludoServlet() {
		log.info("...............instanciando SaludoServlet");
	}
	
	@Override
	public void init() throws ServletException {
		super.init();
		contador = 0;
	    log.info("...............aqui inicializo cosas");
	}
	
	@Override
	public void destroy() {
		log.info("......... se hace un undeploy del Servler");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("......... llama al doGet"); 
		
		this.contador ++;
		
		PrintWriter out = response.getWriter();
		out.println("<html><head/><body>");
		out.println("<h1>Hola Mundo " + contador  + " !</h1>");
		out.println("The date is:" + new Date());
		out.println("</body></html>");
		out.close();
	}

}
