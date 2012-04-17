package ar.edu.ort.calculadora;

import ar.edu.ort.operaciones.*;

public final class Fabrica 
{
	public Operacion crearOperacion(String signo)
	{
		if("+".equals(signo))
			return new Suma();
		if("-".equals(signo))
			return new Resta();
		if("/".equals(signo))
			return new Division();
		if("*".equals(signo))
			return new Multiplicacion();
		
		return null;
	}
}
