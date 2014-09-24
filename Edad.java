import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;

public class Edad extends Applet implements ActionListener {
	private TextField edadCampo;
	private int edad;

	public void init () {
		edadCampo = new TextField("10");
		add(edadCampo);
		edadCampo.addActionListener(this);
	}

	public void actionPerformed (ActionEvent e) {
		try {
			edad = Integer.parseInt(edadCampo.getText());
		} catch (NumberFormatException ex) {

		}
		repaint();
	}
	public void paint (Graphics g) {
		g.drawString("La edad es:" + edad, 50, 50);
		if (edad >= 18) 
			g.drawString("Eres mayor de edad", 50, 100);
		else 
			g.drawString("Aún no eres mayor", 50, 100);
	}
}