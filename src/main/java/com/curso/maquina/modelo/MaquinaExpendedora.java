package com.curso.maquina.modelo;

import java.util.*;
/**
 * Maquina expendedora con 3 tipos de refrescos
 * 
 * @author begonaolea
 *
 */
public class MaquinaExpendedora {
	
	private Map<TiposRefresco, Refresco> casillerosRefrescos;
	private double cambios;
	private double recaudacion = 0;
	
	public MaquinaExpendedora() {
		this.casillerosRefrescos = new HashMap<TiposRefresco, Refresco>();
		this.casillerosRefrescos.put(TiposRefresco.AQUARIUS, 
				                     new Refresco(TiposRefresco.AQUARIUS) );
		this.casillerosRefrescos.put(TiposRefresco.COCA_COLA, 
                new Refresco(TiposRefresco.COCA_COLA) );
		this.casillerosRefrescos.put(TiposRefresco.KAS_LIMON, 
                new Refresco(TiposRefresco.KAS_LIMON) );
		
		this.cambios = 100;
	}
	
	
	public String display() {
		StringBuilder  sb = new StringBuilder();
		//Map   el método values() devuelve una coledcions con lso valors del map
		//key tipo refresco, value el propio refescos
		for(Refresco r: casillerosRefrescos.values()) {
			sb.append(". " + r + "\n");
		}
		sb.append(". cambios disponibles: " + this.cambios);
		
		return sb.toString();
	}
	
	
	public double vender(TiposRefresco tipo, double dinero) 
			throws VentaRefrescoException {
		
		// validaciones
		// 1. hay el tipo solicitado
		if ( ! this.casillerosRefrescos.containsKey(tipo)) {
			throw new VentaRefrescoException("El tipo de refesco no existe");
		}
		// 2. el usuario a introducido dinero
		if( dinero <= 0) {
			throw new VentaRefrescoException("Debe introducir importe válido");
		}
		// 3. hay stock
		if( this.casillerosRefrescos.get(tipo).getStock() == 0) {
			throw new VentaRefrescoException("No hay stock.");
		}
		// 4. Importe suficiente
		if(this.casillerosRefrescos.get(tipo).getPrecio() > dinero) {
			throw new VentaRefrescoException("Saldo insuficiente." );
		}
		// 4. No hay cambios
		double cambiosVenta =  dinero - this.casillerosRefrescos.get(tipo).getPrecio();
		if(cambiosVenta > this.cambios) {
			throw new VentaRefrescoException("No hay cambios." );
		}

		
		//ok - saco un refresco 
	
		
		this.casillerosRefrescos.get(tipo).sacarRefresco();
		this.recaudacion += this.casillerosRefrescos.get(tipo).getPrecio();
		if(cambiosVenta > 0 ) {
			cambios -= cambiosVenta;
		}
		return cambiosVenta;
	}
	
	public Collection<Refresco> getRefrescosVenta(){
		return this.casillerosRefrescos.values();
	}

}
