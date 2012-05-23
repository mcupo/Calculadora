package ar.edu.ort.calculadora;

import java.awt.Button;
import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;

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
	private Properties operacionesAgregadas = getOperacionesAgregadas();
	
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
		int y=170;
		//Seteo el layout en null para manejar la disposición de elementos a mano con setBounds
		setLayout(null);
		//Pregunto si existen operaciones agregadas
		if (operacionesAgregadas.isEmpty())
		{
			setBounds(0, 0, y, 250);
		}
		else
		{
			/* Agrando la ventana en base al tamaño del hash de operaciones agregadas.
			 * Divido el tamaño por 5, ya que entran 5 botones por columna, y lo multiplico
			 * por 37, que es el tamaño necesario para que se vea la columna en la ventana.
			 * Es necesario castear la división a float para luego redondear siempre hacia
			 * arriba los decimales, ya que por más que se dibuje 1 solo botón en una columna,
			 * es necesario agrandar por un total de 37.
			 */
			BigDecimal bd = new BigDecimal((float)operacionesAgregadas.size()/5);
			y+=(bd.setScale(0, RoundingMode.UP)).intValue()*37;
			setBounds(0, 0, y, 250);
		}
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
		txt.setBounds(1,1,145,25);
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
		btn.addActionListener(new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				if(!txt.getText().equals("0"))
				{
					txt.setText(String.valueOf(Double.valueOf(txt.getText())*-1));
				}
			}
		});
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
				if(lastActionPerformed.equals("operation"))
				{
					txt.setText("0.");
				}
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
	
	public void mostrarOperacionesAgregadas()
	{
		Button btn;
		Properties prop = getOperacionesAgregadas();
		int y=27;
		int x=148;
		int c=1;
		
		for( Object key : prop.keySet() )
		{
				//System.out.println( key.toString() + ":" + prop.get(key));
				btn = new Button(key.toString());
				add(btn);
				btn.setName(key.toString());
				btn.setBounds(x,y,anchoButton,altoButton);
				btn.addActionListener(new OperationEventHandler());
				y+=37;
				//Corro el eje x y vuelvo a inicializar el eje y cuando ya se dibujaron 5 botones y aun restan más
				if(c==5)
				{
					x+=37;
					y=27;
					c=0;
				}
				c++;
		}
	}
	
	public void mostrarBotones()
	{
		mostrarNumeros();
		mostrarSigno();
		mostrarComa();
		mostrarCE();
		mostrarIgual();
		mostrarOperaciones();
		mostrarOperacionesAgregadas();
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
				if(lastActionPerformed.equals("="))
				{
					calc.limpiar();
					txt.setText(valor);
				}
				else
				{
					txt.setText(txt.getText()+  valor );
				}
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
				if(lastActionPerformed.equals("operation"))
				{
					calc.agregarOperacion(((Button)e.getSource()).getLabel());
				}
				else
				{
					calc.agregarNumero(Double.valueOf(txt.getText()));
					calc.agregarOperacion(((Button)e.getSource()).getLabel());
				}
			}
			//Si la operación no es binaria, la última acción es similar a mostrar un resultado
			if(calc.getOperacion().esBinaria())
			{
				lastActionPerformed="operation";
			}
			else
			{
				lastActionPerformed="=";
			}
		}
	}
	
	private Properties getOperacionesAgregadas()
	{
		//Clono el objeto porque necesito una nueva instancia
		Properties operacionesAgregadas=(Properties) calc.getOperaciones().clone();
		//Remuevo las operaciones básicas
		operacionesAgregadas.remove("+");
		operacionesAgregadas.remove("-");
		operacionesAgregadas.remove("*");
		operacionesAgregadas.remove("/");
		return operacionesAgregadas;
	}
}