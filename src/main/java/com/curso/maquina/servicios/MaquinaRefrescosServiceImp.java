package com.curso.maquina.servicios;

import java.util.Collection;

import javax.ejb.Stateless;

import com.curso.maquina.modelo.MaquinaExpendedora;
import com.curso.maquina.modelo.Refresco;
import com.curso.maquina.modelo.TiposRefresco;
import com.curso.maquina.modelo.VentaRefrescoException;

//EJB de session sin estado
//COMPONENTE DE NEGOCIO:
//    . autogestionado por el contendor de EBJS
//    . llamadas remotas 
//    . transacconalidad gestionada por contenedor
//    . seguridad g. por contenedor
//    . Pool de instancias de este servicio - Escalabilidad

//@Stateless
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
		
        double cambios = maquina.vender(tipoRef, importe / 100.0);
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
