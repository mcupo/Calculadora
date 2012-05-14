package ar.edu.ort.calculadora;

import java.awt.Button;
import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

/**
 * Clase que obsverva a la clase {@link CalculadoraLineal} para dibujar la calculadora
 */

public class CalculadoraGrafica extends JFrame implements Observer
{
	private static final long serialVersionUID = 1L;
	private static final int anchoButton = 35;
	private static final int altoButton = 35;
	private final TextField txt = new TextField();
	private CalculadoraLineal calc = new CalculadoraLineal();
	private String lastActionPerformed = new String();
	
	public CalculadoraGrafica()
	{
		super("Calculadora");
		calc.addObserver(this);
		crearControles();
	}
	
	@Override
	public void update(Observable who, Object what)
	{
		CalculadoraLineal calc = (CalculadoraLineal) who;
		txt.setText(String.valueOf(calc.getTotal()));
	}
	
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
		txt.setText("0");
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
				btn.addActionListener(new NumberEventHandler(txt,valor));
				numero++;
			}
			y=y-37;
		}

		//0
		btn = new Button("0");
		add(btn);
		btn.addActionListener( new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent e)
			{
				//txt.setText( txt.getText() + "0");
				//lastActionPerformed="number";
				
				//Pregunto si hay un cero o si el último botón presionado fue el de una operación
				if((txt.getText().equals("0")) || (lastActionPerformed.equals("operation")))
				{
					txt.setText("0");
				}
				else
				{	
					txt.setText(txt.getText()+  "0" );
				}
				lastActionPerformed="number";
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
		btn = new Button(".");
		add(btn);
		btn.setName("coma");
		btn.setBounds(75,138,anchoButton,altoButton);
		btn.addActionListener(new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent e)
			{
				//Valido que solo se pueda escribir una sola
				if((txt.getText()).indexOf(".")==-1)
					txt.setText(txt.getText()+  "." );
				lastActionPerformed=".";
			}
		});
	}
	
	public void mostrarCE()
	{
		//Limpiar
		Button btn;
		btn = new Button("CE");
		add(btn);
		btn.setName("ce");
		btn.setBounds(1,175,109,altoButton);
		btn.addActionListener(new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent e)
			{
				calc.limpiar();
				//Sino hago esto me pone 0.0
				txt.setText("0");
				lastActionPerformed="CE";
			}
		});
	}
	
	public void mostrarIgual()
	{
		//Igual
		Button btn;
		btn = new Button("=");
		add(btn);
		btn.setName("igual");
		btn.setBounds(111,175,anchoButton,altoButton);
		btn.addActionListener(new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				calc.agregarNumero(Double.valueOf(txt.getText()));
				lastActionPerformed="=";
			}
		});
	}
	
	public void mostrarOperaciones()
	{
		Button btn;
		
		btn = new Button("+");
		add(btn);
		btn.setName("suma");
		btn.setBounds(111,27,anchoButton,altoButton);
		btn.addActionListener(new OperationEventHandler());
		
		btn = new Button("-");
		add(btn);
		btn.setName("resta");
		btn.setBounds(111,64,anchoButton,altoButton);
		btn.addActionListener(new OperationEventHandler());
		
		btn = new Button("*");
		add(btn);
		btn.setName("multiplicacion");
		btn.setBounds(111,101,anchoButton,altoButton);
		btn.addActionListener(new OperationEventHandler());
		
		btn = new Button("/");
		add(btn);
		btn.setName("division");
		btn.setBounds(111,138,anchoButton,altoButton);
		btn.addActionListener(new OperationEventHandler());
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
	
	/**
	 * Clase para el manejo de los eventos de los botones del 1 al 9
	 * @author MCUPO
	 */
	
	private class NumberEventHandler implements ActionListener 
	{
		private final TextField txt;
		private final String valor;
		
		/**
		 * @param textfield - Display de la calculadora
		 * @param val - Valor que representa el botón
		 */
		NumberEventHandler(TextField textfield, String val)
		{
			txt = textfield;
			valor = val;
		}
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			//Pregunto si hay un cero o si el último botón presionado fue el de una operación
			if((txt.getText().equals("0")) || (lastActionPerformed.equals("operation")))
			{
				txt.setText(valor);
			}
			else
			{	
				txt.setText(txt.getText()+  valor );
			}
			lastActionPerformed="number";
		}
	}
	
	/**
	 * Clase para el manejo de los botones de las operaciones
	 * @author MCUPO
	 */
	
	private class OperationEventHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			//Si el último botón presionado fue el de resultado, no agrego el número
			if(lastActionPerformed.equals("="))
			{
				calc.agregarOperacion(((Button)e.getSource()).getLabel());
			}
			else
			{
				calc.agregarNumero(Double.valueOf(txt.getText()));
				calc.agregarOperacion(((Button)e.getSource()).getLabel());
			}
			lastActionPerformed="operation";
		}
	}
}
