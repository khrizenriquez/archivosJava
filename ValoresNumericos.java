import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

public class ValoresNumericos extends Applet implements ActionListener {
	private JTextField txtIngesoValores;
	private JPanel pIngreso, pBotones, pRespuestas;
	private JTextArea txtarea;
	private JLabel mensaje;
	public int arr1[], arr2[];
	private int inicio = 0, limite = 10;
	private String nombreBotones[] = {"Sumar valores\n ingresados", "Vector inverso", "Mostrar vectores", "Reiniciar valores"};
	private Color mensajeV = Color.decode("#05e87e");//		Color verde
	private Color mensajeR = Color.decode("#de1111");//		Color rojo
	private Color btnVerde = Color.decode("#00cc6d");
	private Color btnAzul = Color.decode("#0265b5");
	private Color fondo = Color.decode("#fffff2");
	private Color colorLetras = Color.decode("#ffffff");

	public void init () {
		resize(900, 350);
		arr1 = new int[limite];
		arr2 = new int[limite];
	}

	public void ventanaRespuestas () {
		pRespuestas = new JPanel();
		pRespuestas.setBackground(fondo);
		pRespuestas.setLayout(new GridLayout(1, 1, 0, 0));
		txtarea = new JTextArea("");
		txtarea.setLineWrap(true);
		pRespuestas.add(txtarea);
	}
	public void ventanaIngreso () {
		pIngreso = new JPanel();
		pIngreso.setBackground(fondo);
		pIngreso.setLayout(new GridLayout(4, 1, 1, 1));
		txtIngesoValores = new JTextField(20);
		txtIngesoValores.setFont(new Font("Helvetica", Font.PLAIN, 30));
		JLabel lblEncabezado = new JLabel("Ingresa un valor numérico entero");
		lblEncabezado.setHorizontalAlignment(JLabel.CENTER);
		mensaje = new JLabel();
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBackground(btnVerde);
		btnAgregar.setForeground(colorLetras);

		//	Agregando el borde al JTextField
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		txtIngesoValores.setBorder(border);

		pIngreso.add(lblEncabezado);
		pIngreso.add(txtIngesoValores);
		pIngreso.add(btnAgregar);
		pIngreso.add(mensaje);

		txtIngesoValores.addActionListener(this);
		btnAgregar.addActionListener(this);
	}
	public void ventanaBotones () {
		pBotones = new JPanel();
		pBotones.setBackground(fondo);
		pBotones.setLayout(new GridLayout(1, 4, 5, 5));

		//		Tendré 4 botones que los generaré por un ciclo
		for (int i = 0, j = nombreBotones.length; i < j; i++) {
			JButton btn = new JButton(nombreBotones[i]);
			pBotones.add(btn);
			btn.setBackground(btnAzul);
			btn.setForeground(colorLetras);
			btn.addActionListener(this);
		}
	}
	public ValoresNumericos () {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		ventanaIngreso();
		ventanaRespuestas();
		ventanaBotones();

		add(pIngreso);
		add(pRespuestas);
		add(pBotones);
	}
	private void push (int arr[]) {
		try {
			if (inicio < limite) 
				arr[inicio] = Integer.parseInt(txtIngesoValores.getText());
			else 
				throw new ArrayIndexOutOfBoundsException("0");

			mensaje.setForeground(mensajeV);
			mensaje.setText("Valor " + Integer.parseInt(txtIngesoValores.getText()) + " agregado, " + (inicio + 1) + " de " + limite);
			inicio++;
			txtIngesoValores.setText("");
		} catch (NullPointerException eArray) {
			//		Si entra acá es xq no tiene valores el arreglo
			mensaje.setForeground(mensajeR);
			mensaje.setText("No podemos agregar valores por el momento");
		} catch (ArrayIndexOutOfBoundsException eArray) {
			mensaje.setForeground(mensajeR);
			mensaje.setText("Has llegado al límite de valores que puedes ingresar");
		}		
	}
	private void sumaArreglo () {
		//		La suma es del arreglo 1
		txtIngesoValores.setText("");
		mensaje.setForeground(mensajeV);
		mensaje.setText("Suma de valores");
		if (inicio >= 1 && inicio <= limite) 
			txtarea.setText("La suma de los " + inicio + " valores que tienes es: " + sumandoArreglo(arr1) + " y los valores son:\n" + sumandoArregloStr(arr1, " + "));
		else 
			txtarea.setText("Valores vacíos");
	}
	private String sumandoArregloStr (int arr[], String delimitador) {
		String arrSuma = "";
		for (int i = 0, j = (inicio - 1); i <= j; i++) {
			if (i == (inicio - 1)) 
				arrSuma += arr[i];
			else
				arrSuma += arr[i] + delimitador;
		}
		return arrSuma;
	}
	private int sumandoArreglo (int arr[]) {
		int sumatoria = 0;
		for (int suma: arr) 
			sumatoria += suma;
		return sumatoria;
	}
	private void girarArreglo () {
		try {
			if (inicio >= 1 && inicio <= limite) {
				for (int i = 1, j = inicio; i <= j; i++) 
					arr2[i - 1] = arr1[inicio - i];
			} else {
				txtarea.setText("");
				throw new NullPointerException("0");
			}
			mensaje.setForeground(mensajeV);
			mensaje.setText("Clonando e invirtiendo arreglo");
			txtIngesoValores.setText("");
			txtarea.setText("El arreglo ingresado e invertido es este: \n" + sumandoArregloStr(arr2, ", "));
		} catch (NullPointerException eArray) {
			//		Si entra acá es xq no tiene valores el arreglo
			mensaje.setForeground(mensajeR);
			mensaje.setText("No podemos agregar valores por el momento");
		} catch (ArrayIndexOutOfBoundsException eArray) {
			mensaje.setForeground(mensajeR);
			mensaje.setText("Has llegado al límite de valores que puedes ingresar");
		}
	}
	private void mostrandoArreglos () {
		txtIngesoValores.setText("");
		mensaje.setForeground(mensajeV);
		mensaje.setText("Mostrando los valores");
		if (inicio >= 1 && inicio <= limite) {
			//		Valido que el segundo arreglo este lleno
			if (arr1[0] == arr2[inicio - 1]) {
				txtarea.setText("Has ingresado un total de " + inicio + " valores. \n\n" + 
					"Los valores del primer arreglo son: " + sumandoArregloStr(arr1, ", ") +
					"\nLos valores del segundo arreglo son: " + sumandoArregloStr(arr2, ", "));
			} else {
				txtarea.setText("Has ingresado un total de " + inicio + " valores. \n\n" + 
					"Los valores del primer arreglo son: " + sumandoArregloStr(arr1, ", "));
			}
		}
		else 
			txtarea.setText("Valores vacíos");
	}
	public void limpiarValores () {
		inicio = 0;
		txtIngesoValores.setText("");
		mensaje.setForeground(mensajeV);
		mensaje.setText("Se han elimando los valores");
		txtarea.setText("");
	}

	public void actionPerformed (ActionEvent e) {
		if (e.getSource().equals(txtIngesoValores) || e.getActionCommand().equals("Agregar")) 
			guardarValores();
		if (e.getActionCommand().equals(nombreBotones[0])) 
			sumaArreglo();
		if (e.getActionCommand().equals(nombreBotones[1])) 
			girarArreglo();
		if (e.getActionCommand().equals(nombreBotones[2])) 
			mostrandoArreglos();
		if (e.getActionCommand().equals(nombreBotones[3])) 
			limpiarValores();
	}
	public void guardarValores () {
		//		Primero válido que el valor ingresado sea un entero
		try {
			int valorTxt = Integer.parseInt(txtIngesoValores.getText());
			//		Guardo los valores ingresados en el arreglo
			push(arr1);
		} catch (NumberFormatException e) {
			mensaje.setForeground(mensajeR);
			mensaje.setText("El valor ingresado no es valido :(, ingresalo de nuevo.");
		}
	}
}
