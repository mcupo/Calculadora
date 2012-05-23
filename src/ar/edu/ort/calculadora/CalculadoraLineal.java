package ar.edu.ort.calculadora;

import java.util.Observable;
import java.util.Properties;

/**
 * Clase encargada de calcular en base a una operacion.
 * Es observada por {@link ArchivoCalculadora}
 */

public class CalculadoraLineal extends Observable
{
	private double total;
	private Operacion operacion;
	
	public void limpiar()
	{
		total=0;
		operacion=null;
		
		setChanged();
		notifyObservers();
	}
	
	public double getTotal()
	{
		return total;
	}
	
	public void agregarNumero(double numero)
	{
		if(operacion==null)
		{
			total=numero;
		}
		else
		{
			total=operacion.calcular(total, numero);
		}
		setChanged();
		notifyObservers();
	}
	
	public void agregarOperacion(String signo)
	{
		Fabrica faop = Fabrica.instace();
		operacion = faop.crearOperacion(signo);
		//Si no es una operaci�n binaria, la ejecuto, ya que necesita un �nico n�mero
		if(!operacion.esBinaria())
		{
			total=operacion.calcular(total, -1);
			setChanged();
			notifyObservers();
		}
	}
	
	public final Operacion getOperacion()
	{
		return operacion;
	}
	
	public final Properties getOperaciones()
	{
		return Fabrica.instace().getProperties();
	}
}
