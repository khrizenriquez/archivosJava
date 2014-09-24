import java.applet.Applet;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Scrollbar;

public class Etiqueta extends Applet implements AdjustmentListener {
	private Scrollbar barra1, barra2;
	private int barra1val = 0;
	private int barra2val = 0;

	public void init() {
		Label titulo1, titulo2;
		titulo1 = new Label("Arriba");
		barra1 = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 0, 101);
		barra1.addAdjustmentListener(this);

		titulo2 = new Label("Abajo");
		barra2 = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 0, 101);
		barra2.addAdjustmentListener(this);

		add(titulo1);
		add(barra1);
		add(titulo2);
		add(barra2);
	}

	public void paint (Graphics g) {
		g.drawString("El valor Arriba es: " + barra1val, 100, 100);
		g.drawString("El valor Abajo es: " + barra2val, 100, 150);
	}

	public void adjustmentValueChanged (AdjustmentEvent e) {
		barra1val = barra1.getValue();
		barra2val = barra2.getValue();
		repaint();
	}
}