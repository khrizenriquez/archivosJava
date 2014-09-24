import java.applet.Applet;
import java.awt.Button;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Scrollbar;

public class GrandeChico extends Applet implements ActionListener {
	private Button chico, grande;
	private int diametro = 20;

	public void init () {
		chico = new Button("Disminuir tamaño");
		chico.addActionListener(this);

		grande = new Button("Incrementar tamaño");
		grande.addActionListener(this);

		add(grande);
		add(chico);
	}
	public void paint (Graphics g) {
		g.drawOval(25, 25, diametro, diametro);
	}

	public void actionPerformed (ActionEvent e) {
		if (e.getSource().equals(chico)) 
			diametro = diametro - 10;
		if (e.getSource().equals(grande)) 
			diametro = diametro + 10;
		repaint();
	}
}