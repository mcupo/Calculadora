package ar.edu.ort.calculadora;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
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
		//Obtengo el nombre de la clase buscandola en las properties
		String nombreClase=props.getProperty(signo);
		
		try
		{
			Class<?> clase;
			//Verifico que exista la clase
			clase=Class.forName(nombreClase);
			//Si existe creo una instancia de la misma
			Object objeto = clase.newInstance();
			return(Operacion) objeto;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
		return null;
	}
	
	public Properties getProperties()
	{
		return props;
	}
}
