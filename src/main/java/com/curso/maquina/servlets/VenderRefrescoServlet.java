package com.curso.maquina.servlets;

import java.io.IOException;
import java.util.Collection;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.curso.maquina.modelo.Refresco;
import com.curso.maquina.modelo.VentaRefrescoException;
import com.curso.maquina.servicios.MaquinaRefrescosService;
import com.curso.maquina.servicios.MaquinaRefrescosServiceImp;

@WebServlet("/vende.do")  //sustituye web.xml
public class VenderRefrescoServlet extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
	
	//atributo
	@EJB    //inyecta el contenedor de S.A. una instancia del MaquinaRefrescosServiceImp
	        // mas exactamente proxy que te permite llamar al pool de instancias
	private MaquinaRefrescosService maquina;
	
	

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   //	response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// obtener lista refrescos que vende la maquina
		//MaquinaRefrescosService maquina = new MaquinaRefrescosServiceImp();
		Collection<Refresco> lista = maquina.getListaRefrescosAlaVenta();
		
		//tengo que pasar al JSP la lista de refrescos para qeu la muestre
		// se añade un atributo con nombre "refrescos" y el objeto lista
		
		request.setAttribute("refrescos", lista);
		
		// despachar la petición al jsp que genera el html de salida
		
		RequestDispatcher rd = request.getRequestDispatcher("/vending/ventas.jsp");
		rd.forward(request, response);
		
		
		
		
	}//fin doGet

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//leer param del formulario
		
		String paramTipo = request.getParameter("tipo");
		String paramImporte = request.getParameter("importe");
		
		// convertir y validar datos
		
		
		 int importe = 0;
		 String msgError = null;
		 
		 
		 try {	
			 importe = Integer.parseInt(paramImporte);
		 }catch(NumberFormatException e) {
			 msgError = "Debe indicar un importe valido.";
		 }

		
		// MaquinaRefrescosService maquina = new MaquinaRefrescosServiceImp();
		 double cambios = 0;
		
		 if(msgError == null) {
				//llamar a la capa de negocio  a través del service para comprar un refresco

				 try {
					cambios = maquina.vender(paramTipo, importe);
				} catch (VentaRefrescoException e) {
					msgError = e.getMessage();
					e.printStackTrace();
				}
		 }
		
		//redibujar formulario  diciendo si ha comprado ok o sino mensaje con
		//el motivo por que el que no compre
		 
		 //lista actualizada tras la venta o no venta
		 Collection<Refresco> lista = maquina.getListaRefrescosAlaVenta();
		 request.setAttribute("refrescos", lista); 
		 
          if(msgError == null) {
        	  request.setAttribute("msgOk", "El refesco " + paramTipo + " ha sido vendido. ");
        	  request.setAttribute("cambios", cambios);
          }else {
        	  request.setAttribute("msgError", msgError);
          }
		 	
			// despachar la petición al jsp que genera el html de salida
			
			RequestDispatcher rd = request.getRequestDispatcher("/vending/ventas.jsp");
			rd.forward(request, response);
			
		
	}
	
	
	
}
