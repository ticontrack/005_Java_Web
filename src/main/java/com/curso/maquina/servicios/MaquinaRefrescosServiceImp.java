package com.curso.maquina.servicios;

import java.util.Collection;

import com.curso.maquina.modelo.MaquinaExpendedora;
import com.curso.maquina.modelo.Refresco;
import com.curso.maquina.modelo.TiposRefresco;
import com.curso.maquina.modelo.VentaRefrescoException;

public class MaquinaRefrescosServiceImp implements MaquinaRefrescosService {

	private static  MaquinaExpendedora maquina;
	
	static {
		maquina = new MaquinaExpendedora();
	}
	
	@Override
	public Collection<Refresco> getListaRefrescosAlaVenta() {
		
		return maquina.getRefrescosVenta();
	}

	@Override
	public double vender(String tipo, int importe) throws VentaRefrescoException {
		
		if(tipo == null) {
			throw new VentaRefrescoException("Debe indicar el tipo de bebida");
		}
		TiposRefresco tipoRef = getTiposRefesco(tipo);
		if(tipoRef == null) {
			throw new VentaRefrescoException("El tipo indicado de bebida no existe");
		}
		
        double cambios = maquina.vender(tipoRef, importe);
		return cambios;
	}
	
	private TiposRefresco getTiposRefesco(String nombreTipo) {
		TiposRefresco tipo = null;
		for(TiposRefresco t: TiposRefresco.values()) {
			if (t.getNombre().equals(nombreTipo)) {
				tipo = t;
				break;
			}
		}
		return tipo;
	}
	
	

}
