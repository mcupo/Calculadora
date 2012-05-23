package ar.edu.ort.calculadora;

/**
 * Clase abstracta para las operaciones
 * Deben implementar el método calcular y esBinaria
 */

public abstract class Operacion 
{
	public abstract double calcular(double numero1, double numero2);
	/**
	 * Método que indica si la operacion requiere 2 números
	 * @return Boolean
	 */
	public abstract Boolean esBinaria();
}
