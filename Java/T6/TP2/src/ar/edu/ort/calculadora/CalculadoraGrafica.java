package ar.edu.ort.calculadora;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Clase para dibujar la calculadora
 *
 */

public class CalculadoraGrafica extends JFrame implements Observer
{
	private CalculadoraLineal calc;
	
	public CalculadoraGrafica()
	{
		crearControles();
	}
	
	@Override
	public void update(Observable arg0, Object arg1)
	{

	}
	
	private void crearControles()
	{
		setTitle("Grupo X");
		/*Configurar
		setLocation(x1,y1);
		setSize(x2,y2);*/
		setLayout(new BorderLayout());
		JPanel arriba = new JPanel();
		getContentPane().add(arriba,BorderLayout.NORTH);
	}

}
