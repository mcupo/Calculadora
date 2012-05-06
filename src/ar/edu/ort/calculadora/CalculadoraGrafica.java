package ar.edu.ort.calculadora;

import java.awt.Button;
import java.awt.Color;
import java.awt.TextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;

import javax.swing.JFrame;

/**
 * Clase para dibujar la calculadora
 *
 */

public class CalculadoraGrafica extends JFrame implements Observer
{
	private static final long serialVersionUID = 1L;
	private CalculadoraLineal calc;
	
	public CalculadoraGrafica()
	{
		super("Calculadora");
		crearControles();
	}
	
	@Override
	public void update(Observable arg0, Object arg1)
	{

	}
	
	public void crearVentana()
	{
		//Seteo el layout en null para manejar la disposición de elementos a mano con setBounds
		setLayout(null);
		setBounds(0, 0, 600, 480);
		setBackground(Color.ORANGE);
		setForeground(Color.RED);
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent evt)
			{
				System.exit(0);
			}
		});
	}
	
	public void crearTextField()
	{
		TextField txt = new TextField();
		add(txt);
		txt.setBounds(1,1,200,25);
		txt.setBackground(Color.YELLOW);
		txt.setForeground(Color.MAGENTA);
		txt.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent evt)
			{
				char c = evt.getKeyChar();
				if(c>=KeyEvent.VK_SPACE && (c<KeyEvent.VK_0 || c>KeyEvent.VK_9))
				{
					//Saco al evento
					evt.consume();
				}
			}
		}
		);
	}
	
	public void mostrarNumeros()
	{
		Button btn;
		int y=101;
		int numero=1;
		
		//1 al 9
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				btn = new Button(String.valueOf(numero));
				add(btn);
				btn.setName("numero"+String.valueOf(numero));
				btn.setBounds(1+(j*37),y,35,35);
				numero++;
			}
			y=y-37;
		}
		//0
		btn = new Button(String.valueOf(0));
		add(btn);
		btn.setName("numero"+String.valueOf(0));
		btn.setBounds(1,138,35,35);
	}
	
	public void mostrarSigno()
	{
		//Signo
		Button btn;
		btn = new Button("+/-");
		add(btn);
		btn.setName("signo");
		btn.setBounds(38,138,35,35);
	}
	
	public void mostrarComa()
	{
		//Coma
		Button btn;
		btn = new Button(",");
		add(btn);
		btn.setName("coma");
		btn.setBounds(75,138,35,35);
	}
	
	public void mostrarCE()
	{
		//Limpiar
		Button btn;
		btn = new Button("CE");
		add(btn);
		btn.setName("ce");
		btn.setBounds(1,175,109,35);
	}
	
	public void mostrarOperaciones()
	{
		
	}
	
	public void mostrarBotones()
	{
		mostrarNumeros();
		mostrarSigno();
		mostrarComa();
		mostrarCE();
		mostrarOperaciones();
	}
	
	private void crearControles()
	{
		crearVentana();
		crearTextField();
		mostrarBotones();
	}

}
