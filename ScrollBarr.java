/*
		Manejo de eventos
*/

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Scrollbar;
import java.awt.event.*;

public class ScrollBarr extends Applet implements AdjustmentListener {
	private Scrollbar barra;
	private int barraValor = 0;

	public void init() {
		barra = new Scrollbar(Scrollbar.VERTICAL, 0, 1, 0, 101);
		add(barra);
		barra.addAdjustmentListener(this);
	}
	public void paint (Graphics g) {
		g.drawString("El valor actual es: " + barraValor, 100, 100);
		g.drawRect(40, 80, 60, 100);
		g.fillRect(40, 80, 60, barraValor);
	}
	public void adjustmentValueChanged (AdjustmentEvent e) {
		barraValor = barra.getValue();
		repaint();
	}
}