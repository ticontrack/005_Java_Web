package com.curso.maquina.servicios;

import java.util.Collection;

import com.curso.maquina.modelo.MaquinaExpendedora;
import com.curso.maquina.modelo.Refresco;

public class MaquinaRefrescosServiceImp implements MaquinaRefrescosService {

	private static  MaquinaExpendedora maquina;
	
	static {
		maquina = new MaquinaExpendedora();
	}
	
	@Override
	public Collection<Refresco> getListaRefrescosAlaVenta() {
		
		return maquina.getRefrescosVenta();
	}

}
