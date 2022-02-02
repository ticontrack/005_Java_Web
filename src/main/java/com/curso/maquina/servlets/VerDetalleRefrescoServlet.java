package com.curso.maquina.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.curso.maquina.modelo.TiposRefresco;

/**
 * Servlet implementation class VerDetalleRefrescoServlet
 */
@WebServlet({ "/verDetalleRefresco", "/detalle" })
public class VerDetalleRefrescoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doPost(HttpServletRequest request, 
		                  HttpServletResponse response) throws ServletException, IOException {
		
			System.out.println(".... doPost de VerDetalleRefrescoServlet");

			String tipo = request.getParameter("tipo");
			if (tipo == null || tipo.length() == 0){
				tipo = "desconocido";
			}
			
			if(! tipo.equals("desconocido")) {
				//busco
				int ordinal = Integer.parseInt(tipo);
				//falta validar que recibo un int 
				
				TiposRefresco tipoRef = TiposRefresco.values()[ordinal];
				System.out.println(
						String.format(
						" Refresco %s , precio coste %f centimos " ,
						tipoRef.getNombre() ,
						tipoRef.getCoste())
				);
				
				response.setContentType("text/plain");
				response.getOutputStream().print("<html>");
				response.getOutputStream().print("<body>");
				response.getOutputStream().print("<h2>Detalle Refresco</h2>");
				response.getOutputStream().print( tipoRef.getNombre());
				response.getOutputStream().print( "<br/>Precio Coste: " + tipoRef.getCoste());
				response.getOutputStream().print("</body>");
				response.getOutputStream().print("</html>");
                response.flushBuffer();
                response.getOutputStream().close();
				
				
				
			}else {
				System.out.println("Tipo de refresco desconocido");
			}
			
			
			

	}

}
