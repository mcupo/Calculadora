package ar.edu.ort.operaciones;

import ar.edu.ort.calculadora.Operacion;

public class Potencia extends Operacion
{
	@Override
	public double calcular(double numero1, double numero2)
	{
		return numero1*numero1;
	}

	@Override
	public Boolean esBinaria()
	{
		return false;
	}

}