package com.curso.maquina.servicios;

import java.util.Collection;

import com.curso.maquina.modelo.Refresco;
import com.curso.maquina.modelo.VentaRefrescoException;

public interface MaquinaRefrescosService {

	public Collection<Refresco> getListaRefrescosAlaVenta();
	
	/**
	 * médoto que vende una lata del refresco indicado
	 * @param tipo  - String nombre del refresco
	 * @param importe  -- dinero introduce el usuario
	 * @return  en centimos el importe de los cambios. 0 si no hay
	 * @throws VentaRefrescoException si no hay ref, no hay cabmios, ....
	 */
	public double  vender(String tipo, int importe) throws VentaRefrescoException;
	
}
