package ar.edu.ort.calculadora;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		CalculadoraLineal calc = new CalculadoraLineal();
		calc.limpiar();
		calc.agregarNumero(10);
		calc.agregarOperacion("+");
		calc.agregarNumero(-5);
		calc.agregarOperacion("*");
		calc.agregarNumero(2);
		System.out.println("El total es: " + calc.getTotal());
	}
}
