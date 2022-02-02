package com.curso.maquina.servlets;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.curso.maquina.modelo.Refresco;
import com.curso.maquina.servicios.MaquinaRefrescosService;
import com.curso.maquina.servicios.MaquinaRefrescosServiceImp;

@WebServlet("/vender.do")  //sustituye web.xml
public class VenderRefrescoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   //	response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// obtener lista refrescos que vende la maquina
		MaquinaRefrescosService maquina = new MaquinaRefrescosServiceImp();
		Collection<Refresco> lista = maquina.getListaRefrescosAlaVenta();
		
		//tengo que pasar al JSP la lista de refrescos para qeu la muestre
		// se añade un atributo con nombre "refrescos" y el objeto lista
		
		request.setAttribute("refrescos", lista);
		
		// despachar la petición al jsp que genera el html de salida
		
		RequestDispatcher rd = request.getRequestDispatcher("/vending/ventas.jsp");
		rd.forward(request, response);
		
		
		
		
	}//fin doGet

}
