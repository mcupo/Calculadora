package ar.edu.ort.calculadora;

/**
 * Clase abstracta para las operaciones
 * Deben implementar el m�todo calcular y esBinaria
 */

public abstract class Operacion 
{
	public abstract double calcular(double numero1, double numero2);
	/**
	 * M�todo que indica si la operacion requiere 2 n�meros
	 * @return Boolean
	 */
	public abstract Boolean esBinaria();
}
