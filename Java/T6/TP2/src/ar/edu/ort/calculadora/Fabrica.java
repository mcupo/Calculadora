package ar.edu.ort.calculadora;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import ar.edu.ort.operaciones.*;

/**
 * Fabrica singleteada que devuelve objetos de tipo operacion
 *
 */

public final class Fabrica 
{
	private static Fabrica instance;
	private Properties props;
	
	private Fabrica()
	{
		props=new Properties();
		try
		{
			props.load(getClass().getResourceAsStream("/ar/edu/ort/operaciones/calculadora.properties"));
			System.out.println(getClass().getName());
		}
		catch(IOException ex){}
		props.put("+", Suma.class.getCanonicalName());
		props.put("-", Resta.class.getCanonicalName());
		props.put("/", Division.class.getCanonicalName());
		props.put("*", Multiplicacion.class.getCanonicalName());
	}
	
	public static Fabrica instace()
	{
		if(instance==null) instance = new Fabrica();
		return instance;
	}
	
	public Operacion crearOperacion(String signo)
	{
		String nombreClase=props.getProperty(signo);
		//System.out.println(nombreClase);
		
		try
		{
			Class clase;
			clase=Class.forName(nombreClase);
			Object objeto = clase.newInstance();
			return(Operacion) objeto;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		/*if("+".equals(signo))
			return new Suma();
		if("-".equals(signo))
			return new Resta();
		if("/".equals(signo))
			return new Division();
		if("*".equals(signo))
			return new Multiplicacion();*/
		
		return null;
	}
}
