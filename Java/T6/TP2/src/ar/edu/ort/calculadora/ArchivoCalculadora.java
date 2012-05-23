package ar.edu.ort.calculadora;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Observable;
import java.util.Observer;

/**
 * Clase observadora que escribe en un archivo de texo los subtotales de un conjunto 
 * de operaciones realizadas por {@link CalculadoraLineal}
 *
 */

public class ArchivoCalculadora implements Observer
{
	private PrintStream ps;
	
	public ArchivoCalculadora(String filename)
	{
		try
		{
			ps = new PrintStream(filename);
		}
		catch(FileNotFoundException e){}
	}
	
	@Override
	public void update(Observable who, Object what)
	{
		CalculadoraLineal calc = (CalculadoraLineal) who;
		ps.println("subtotal: "+calc.getTotal());
	}

}