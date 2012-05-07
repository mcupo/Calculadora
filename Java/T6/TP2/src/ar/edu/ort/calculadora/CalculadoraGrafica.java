package ar.edu.ort.calculadora;

import java.awt.Button;
import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
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
	private  static final int anchoButton = 35;
	private  static final int altoButton = 35;
	private CalculadoraLineal calc;
	private final TextField txt = new TextField();
	
	public CalculadoraGrafica()
	{
		super("Calculadora");
		crearControles();
	}
	
	@Override
	public void update(Observable arg0, Object arg1){}
	
	public void crearVentana()
	{
		//Seteo el layout en null para manejar la disposición de elementos a mano con setBounds
		setLayout(null);
		setBounds(0, 0, 220, 250);
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
		add(txt);
		txt.setBounds(1,1,200,25);
		txt.setBackground(Color.WHITE);
		txt.setForeground(Color.BLACK);
		txt.setEnabled(false);
	}
	
	public void mostrarNumeros()
	{
		Button btn;
		int y=101;
		Integer numero=1;

		//1 al 9
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				btn = new Button(String.valueOf(numero));
				final String valor = String.valueOf(numero);
				add(btn);
				btn.setName("numero"+String.valueOf(numero));
				btn.setBounds(1+(j*37),y,anchoButton,altoButton);
				btn.addKeyListener(new KeyAdapter() 
				{
					@Override
					public void keyPressed(KeyEvent e) 
					{
						super.keyPressed(e);
						//Si es un número lo ingresa en el textfield
						if(e.getKeyChar() >= '0' && e.getKeyChar() <= '9')
						{
							txt.setText(txt.getText()+  e.getKeyChar() );
						}
					}
				});
				btn.addActionListener( new ActionListener() 
				{					
					@Override
					public void actionPerformed(ActionEvent e)
					{
						txt.setText(txt.getText()+  valor );
					}
				}
				);
				numero++;
			}
			y=y-37;
		}
		//0
		btn = new Button("0");
		add(btn);
		btn.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyPressed(KeyEvent e) 
			{
				super.keyPressed(e);
				//Si es un número lo ingresa en el textfield
				if(e.getKeyChar() >= '0' && e.getKeyChar() <= '9')
				{
					txt.setText(txt.getText()+  e.getKeyChar() );
				}
			}
		});
		btn.addActionListener( new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent e)
			{
				txt.setText( txt.getText() + "0");
			}
		});
		btn.setName("numero"+String.valueOf(0));
		btn.setBounds(1,138,anchoButton,altoButton);
	}
	
	public void mostrarSigno()
	{
		//Signo
		Button btn;
		btn = new Button("+/-");
		add(btn);
		btn.setName("signo");
		btn.setBounds(38,138,anchoButton,altoButton);
	}
	
	public void mostrarComa()
	{
		//Coma
		Button btn;
		btn = new Button(",");
		add(btn);
		btn.setName("coma");
		btn.setBounds(75,138,anchoButton,altoButton);
	}
	
	public void mostrarCE()
	{
		//Limpiar
		Button btn;
		btn = new Button("CE");
		add(btn);
		btn.setName("ce");
		btn.setBounds(1,175,109,altoButton);
	}
	
	public void mostrarIgual()
	{
		//Igual
		Button btn;
		btn = new Button("=");
		add(btn);
		btn.setName("igual");
		btn.setBounds(111,175,anchoButton,altoButton);
	}
	
	public void mostrarOperaciones()
	{
		Button btn;
		
		btn = new Button("+");
		add(btn);
		btn.setName("suma");
		btn.setBounds(111,27,anchoButton,altoButton);
		btn.addActionListener( new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent e)
			{
				/*calc.agregarNumero(Double.valueOf(txt.getText()));
				calc.agregarOperacion("+");
				txt.setText(String.valueOf(calc.getTotal()));
				*/
			}
		});
		
		btn = new Button("-");
		add(btn);
		btn.setName("resta");
		btn.setBounds(111,64,anchoButton,altoButton);
		
		btn = new Button("*");
		add(btn);
		btn.setName("multiplicacion");
		btn.setBounds(111,101,anchoButton,altoButton);
		
		btn = new Button("/");
		add(btn);
		btn.setName("division");
		btn.setBounds(111,138,anchoButton,altoButton);
	}
	
	public void mostrarBotones()
	{
		mostrarNumeros();
		mostrarSigno();
		mostrarComa();
		mostrarCE();
		mostrarIgual();
		mostrarOperaciones();
	}
	
	private void crearControles()
	{
		crearVentana();
		crearTextField();
		mostrarBotones();
	}

}
