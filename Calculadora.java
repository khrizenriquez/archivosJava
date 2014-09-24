import java.applet.Applet;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculadora extends Applet implements ActionListener {

	private Color colorBtnNumeros = Color.decode("#6b88a3");
	private Color colorBtnOperaciones = Color.decode("#1d1f21");
	private Color colorFondo = Color.decode("#00bfff");
	private JPanel pElementos, pTexto, pBorrado;
	private JTextField txtPantalla;
	private JButton btnCE;
	private String valTexto, resultado, estadoOperacion;
	private boolean valorFinal;
	private double rTemporal;
    private String nombreBtn[] = {"7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", ".", "0", "=", "+"};
    public Font fuente = new Font("Helvetica", Font.PLAIN, 30);
    DecimalFormat df;

	public void init () {
		resize(325, 350);
		valTexto = "0";
		resultado = "";
		valorFinal = false;
		estadoOperacion = "";
		df = new DecimalFormat("#.00");//		Decimales con 2 digitos nada mas
	}
	public void dibujandoBotones () {
		//		Panel donde estarán los elementos como números y operaciones
        pElementos = new JPanel();
		pElementos.setLayout(new GridLayout(4, 4, 5, 5));
		for (int i = 0, j = nombreBtn.length; i < j; i++) {
			JButton btn = new JButton(nombreBtn[i]);
			try {
				Integer.parseInt(nombreBtn[i]);
				btn.setBackground(colorBtnNumeros);
			} catch (NumberFormatException e) {
				btn.setBackground(colorBtnOperaciones);
				btn.setForeground(Color.white);
			}
			btn.setFont(fuente);
			btn.addActionListener(this);
			pElementos.setBackground(colorFondo);
			pElementos.add(btn);
		}
	}
	public void dibujandoPantalla () {
		//		Panel solo para la etiqueta donde se despelgará la información
        pTexto = new JPanel();
		pTexto.setLayout(new GridLayout(1, 1, 5, 5));
		txtPantalla = new JTextField("", 15);
		txtPantalla.setFont(fuente);
		txtPantalla.setEditable(false);
		txtPantalla.setHorizontalAlignment(JTextField.RIGHT);
		pTexto.setBackground(colorFondo);
		pTexto.add(txtPantalla);
	}
	public void dibujandoBorrado () {
		//		Panel solo para la etiqueta donde se despelgará la información
        pBorrado = new JPanel();
		pBorrado.setLayout(new GridLayout(1, 5, 5, 5));

		btnCE = new JButton("CE");
		btnCE.setFont(new Font("Helvetica", Font.PLAIN, 20));
		btnCE.setBackground(colorBtnOperaciones);
		btnCE.setForeground(Color.white);
		btnCE.addActionListener(this);
		//		Este ciclo solo es un comodín
		for (int i = 0; i < 3; i++) {
			JLabel label = new JLabel();
			label.setBackground(colorFondo);
			label.setOpaque(true);
			pBorrado.add(label);
		}
		pBorrado.setBackground(colorFondo);
		pBorrado.add(btnCE);
	}
	public Calculadora () {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		dibujandoBotones();
		dibujandoPantalla();
		dibujandoBorrado();

		add(pTexto);
		add(pBorrado);
		add(pElementos);
	}
	public void actionPerformed (ActionEvent e) {
		String valPantalla = txtPantalla.getText().toString();
		try{
			if (valTexto.equals("0"))
				valTexto = "";

			Integer.parseInt(e.getActionCommand());
			valTexto = valPantalla + "" + e.getActionCommand();
			btnCE.setText("DEL");
			txtPantalla.setText("" + valTexto);
		} catch (NumberFormatException ex) {
			if (e.getActionCommand().equals("DEL")) {
				//		Elimino el último carácter ingresado
				if (!valTexto.equals("")){
					valTexto = valPantalla.substring(0, valPantalla.length() - 1);
					txtPantalla.setText(valTexto);
				}

				if (valTexto.length() < 1) {
					valTexto = "0";
					valPantalla = "0";
					btnCE.setText("CE");
				}
			}
			if (e.getActionCommand().equals("CE")) {
				valorFinal = false;
				valTexto = "0";
				rTemporal = 0;
				valPantalla = "0";
				txtPantalla.setText("");
			}
			if (e.getActionCommand().equals(".")) {
				if (valPantalla.indexOf(".") == -1 || valPantalla.indexOf(".") == 1) {
					txtPantalla.setText(txtPantalla.getText().toString() + ".");
					btnCE.setText("DEL");
				}
			}
			if (e.getActionCommand().equals("+")) {
				sumaCalculadora(valPantalla);
				estadoOperacion = "suma";
				txtPantalla.setText("");
			}
			if (e.getActionCommand().equals("-")) {
				restaCalculadora(valPantalla);
				estadoOperacion = "resta";
				txtPantalla.setText("");
			}
			if (e.getActionCommand().equals("*")) {
				multiCalculadora(valPantalla);
				estadoOperacion = "multi";
				txtPantalla.setText("");
			}
			if (e.getActionCommand().equals("/")) {
				divisionCalculadora(valPantalla);
				estadoOperacion = "div";
				txtPantalla.setText("");
			}
			if (e.getActionCommand().equals("=")) {
				double result = 0;
				if (estadoOperacion.equals("suma")) 
					result = sumaCalculadora(valPantalla);
				if (estadoOperacion.equals("resta")) 
					result = restaCalculadora(valPantalla);
				if (estadoOperacion.equals("multi")) 
					result = multiCalculadora(valPantalla);
				if (estadoOperacion.equals("div")) {
					result = divisionCalculadora(valPantalla);
					btnCE.setText("CE");
				}

				txtPantalla.setText(Double.valueOf(df.format(result)) + "");
				rTemporal = 0;
			}
			btnCE.setText("CE");
		}
	}

	//		Operaciones básicas, suma, resta, multiplicación, división, punto decimal y resultado
	public double sumaCalculadora (String valorTemporal) {
		return rTemporal += Double.valueOf(valorTemporal);
	}
	public double restaCalculadora (String valorTemporal) {
		if (Double.valueOf(valorTemporal) == 0) {
			valorFinal = true;
			if (rTemporal == 0) 
				return rTemporal = Double.valueOf(valorTemporal);
			else 
				return rTemporal -= Double.valueOf(valorTemporal);
		} else {
			if (rTemporal == 0) {
				if (valorFinal) 
					rTemporal -= Double.valueOf(valorTemporal);
				else {
					rTemporal = Double.valueOf(valorTemporal);
					valorFinal = true;
				}
			} else
				rTemporal -= Double.valueOf(valorTemporal);
		}
		return rTemporal;
	}
	public double multiCalculadora (String valorTemporal) {
		if (Double.valueOf(valorTemporal) == 0) {
			rTemporal = 0;
			valorFinal = true;
		} else {
			if (rTemporal == 0) {
				System.out.println(valorFinal);
				if (valorFinal) 
					rTemporal *= Double.valueOf(valorTemporal);
				else 
					rTemporal = Double.valueOf(valorTemporal);
			} else
				rTemporal *= Double.valueOf(valorTemporal);
		}
		return rTemporal;
	}
	public double divisionCalculadora (String valorTemporal) {
		//		No se puede dividir sobre 0
		if (Double.valueOf(valorTemporal) == 0) {
			valorFinal = true;
			if (rTemporal == 0) {
				rTemporal = 0;
			} else {
				txtPantalla.setText("Valor indefinido");
				btnCE.setText("CE");
				throw new NumberFormatException("0");
			}
		} else {
			if (rTemporal == 0) {
				if (valorFinal) 
					rTemporal /= Double.valueOf(valorTemporal);
				else 
					rTemporal = Double.valueOf(valorTemporal);
			} else
				rTemporal /= Double.valueOf(valorTemporal);
		}
		return rTemporal;
	}
	public double resultado () {
		return Double.parseDouble(resultado);
	}
}
