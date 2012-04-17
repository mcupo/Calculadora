package ar.edu.ort.operaciones;

import ar.edu.ort.calculadora.Operacion;

public final class Multiplicacion extends Operacion {

	@Override
	public double calcular(double numero1, double numero2)
	{
		return numero1*numero2;
	}

}
