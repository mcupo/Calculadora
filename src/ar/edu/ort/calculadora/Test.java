package ar.edu.ort.calculadora;

import java.util.Observer;

/**
 * Clase para testear la calculadora
 *
 */

public class Test 
{

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		CalculadoraLineal calc = new CalculadoraLineal();
		CalculadoraGrafica gCalc = new CalculadoraGrafica();
		gCalc.setVisible(true);
		
		Observer obs1 = new PantallaCalculadora();
		calc.addObserver(obs1);
		
		Observer obs2 = new ArchivoCalculadora("C:\\calc.log");
		calc.addObserver(obs2);
		
		/* 
		calc.limpiar();
		calc.agregarNumero(10);
		calc.agregarOperacion("+");
		calc.agregarNumero(-5);
		calc.agregarOperacion("*");
		calc.agregarNumero(2);
		calc.agregarOperacion("+");
		calc.agregarNumero(10);
		System.out.println("El total es: " + calc.getTotal());
		*/
	}
}
