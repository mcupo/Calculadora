package ar.edu.ort.calculadora;

import java.util.Observable;

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
			operacion.calcular(total, numero);
		}
		setChanged();
		notifyObservers();
	}
	
	public void agregarOperacion(String signo)
	{
		Fabrica faop = Fabrica.instace();
		operacion = faop.crearOperacion(signo);
		
		/*Para sofi
		 * if(signo.equals("+"))
		{
			operacion = new Suma();
		}
		else
		{
			if(signo.equals("-"))
			{
				operacion = new Resta();
			}
			else
			{
				if(signo.equals("/"))
				{
					operacion = new Division();
				}
				else operacion = new Multiplicacion();
			}	
		}*/
	}
}
