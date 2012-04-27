package ar.edu.ort.calculadora;

import java.util.Observable;
import java.util.Observer;

/**
 * Clase observadora que imprime por pantalla los resultados parciales de la calculadora
 *
 */

public class PantallaCalculadora implements Observer 
{

	@Override
	public void update(Observable who, Object what)
	{
		CalculadoraLineal calc = (CalculadoraLineal) who;
		System.out.println("subtotal: "+ calc.getTotal());
	}

}
