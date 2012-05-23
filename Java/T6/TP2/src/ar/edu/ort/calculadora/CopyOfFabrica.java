package ar.edu.ort.calculadora;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import ar.edu.ort.operaciones.*;

/**
 * Fabrica singleteada que devuelve objetos de tipo operacion
 *
 */

public final class CopyOfFabrica 
{
	private static CopyOfFabrica instance;
	private Properties props;
	
	private HashMap<String, Operacion> op;
	
	private CopyOfFabrica()
	{
		props=new Properties();
		op = new HashMap<String, Operacion>();
		try
		{
			props.load(getClass().getResourceAsStream("/ar/edu/ort/operaciones/calculadora.properties"));
		}
		catch(IOException ex){}
		
		
		for ( Object key : props.keySet() ){
			try
			{
				Class<?> clase;
				//Verifico que exista la clase
				clase= Class.forName((String)props.get(key));
				//Si existe creo una instancia de la misma
				Object objeto = clase.newInstance();
				if (objeto instanceof Operacion)
					op.put( (String)key , (Operacion)objeto);
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			
		}
		
		try{
			op.put("+", Suma.class.newInstance());
			op.put("-", Resta.class.newInstance());
			op.put("/", Division.class.newInstance());
			op.put("*", Multiplicacion.class.newInstance());
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	public static CopyOfFabrica instace()
	{
		if(instance==null) instance = new CopyOfFabrica();
		return instance;
	}
	
	public Operacion crearOperacion(String signo)
	{
		//Obtengo el nombre de la clase buscandola en las properties
		String nombreClase=props.getProperty(signo);
		
		try
		{
			Class clase;
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
