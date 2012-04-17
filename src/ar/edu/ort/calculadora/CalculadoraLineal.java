package ar.edu.ort.calculadora;

public class CalculadoraLineal 
{
	private double total;
	private Operacion operacion;
	
	public void limpiar()
	{
		total=0;
		operacion=null;
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
	}
	
	public void agregarOperacion(String signo)
	{
		Fabrica faop = new Fabrica();
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
