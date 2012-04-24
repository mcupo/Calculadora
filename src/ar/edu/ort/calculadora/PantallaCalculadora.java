package ar.edu.ort.calculadora;

import java.util.Observable;
import java.util.Observer;

public class PantallaCalculadora implements Observer 
{

	@Override
	public void update(Observable who, Object what)
	{
		CalculadoraLineal calc = (CalculadoraLineal) who;
		System.out.println("subtotal: "+ calc.getTotal());
	}

}
