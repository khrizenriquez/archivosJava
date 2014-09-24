import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;

public class Escalones extends Applet implements AdjustmentListener {
	private Scrollbar escalon;
	private int contador, numEscalones;

	public void init () {
		escalon = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 0, 100);
		add(escalon);
		escalon.addAdjustmentListener(this);
	}

	public void paint (Graphics g) {
		int contador = 0;
		int x = 20;
		int y = 20;
		while (contador <= numEscalones) {
			g.drawLine(x, y, x + 20, y);
			g.drawLine(x + 20, y, x + 20, y + 20);
			x = x + 20;
			y = y + 20;
			contador++;
		}
		for (int i = 0; i <= 10; i++) {
			g.drawString(".", 100 * i, 100);
		}
	}
	public void adjustmentValueChanged (AdjustmentEvent e) {
		numEscalones = escalon.getValue();
		repaint();
	}
}