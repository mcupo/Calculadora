package ar.edu.ort.operaciones;
import ar.edu.ort.calculadora.Operacion;

public class Raiz extends Operacion
{
	@Override
	public double calcular(double numero1, double numero2)
	{
		return Math.sqrt(numero1);
	}

	@Override
	public Boolean esBinaria()
	{
		return false;
	}
}