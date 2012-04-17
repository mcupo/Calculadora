package ar.edu.ort.calculadora;

import ar.edu.ort.operaciones.*;

public final class Fabrica 
{
	private static Fabrica instance;
	
	private Fabrica(){}
	
	public static Fabrica instace()
	{
		if(instance==null) instance = new Fabrica();
		return instance;
	}
	
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
