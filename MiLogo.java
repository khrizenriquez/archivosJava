/*
	Autor: Khriz Enríquez
*/
import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class MiLogo extends Applet {
	int tamanioX = 700;
	int tamanioY = 400;
	String colorCara = "#523F01";
	String colorOJos = "#1C1201";
	String colorPelo = "#291B00";
	String colorOscuro = "#1d1d1d";
	public void init () {
		resize(tamanioX, tamanioY);
	}
	public void paint (Graphics g) {
		Font font = new Font("monospaced", Font.PLAIN, 32);
		g.setFont(font);
		g.drawString("Khriz", (tamanioX / 2) - 75, 30);
		dibujandoCuadrados(g);
		dibujandoCirculos(g);
	}
	public void dibujandoCuadrados (Graphics g) {
		/*para evitar colocar 3 try catch y para que se vea ese efecto de cargar elemento por elemento
		de manera separada uso un ciclo y dentro de el un try donde comparo el valor de mi índice y en base
		al número que tenga es que voy colocando cada figura, para este caso son rectángulos.*/
			//En esta ocación omitiré el catch
		for (int i = 0; i < 3; i++) {
			try {
				Thread.sleep(300);
				if (i == 0)
					dibujarCuadrado(g, 200, 300, 300, 100, 3, 3, colorCara);
				else
				if (i == 1)
					dibujarCuadrado(g, 325, 390, 50, 3, 0, 0, colorOscuro);
				else
				if (i == 2)
					dibujarCuadrado(g, 200, 205, 300, 100, 3, 3, colorPelo);
			} catch (InterruptedException ex) {}
		}
	}
	public void dibujandoCirculos (Graphics g) {
		//En esta ocación omitiré el catch
		for (int i = 0; i < 6; i++) {
			try {
				Thread.sleep(300);
				if (i == 0) {
					dibujoCirculos(g, 150, 250, 100, 100, colorPelo);
					dibujoCirculos(g, tamanioX - 250, 250, 100, 100, colorPelo);
				} else 
				if (i == 1) {
					dibujoCirculos(g, 175, 175, 100, 100, colorPelo);
					dibujoCirculos(g, tamanioX - 275, 175, 100, 100, colorPelo);
				} else
				if (i == 2) {
					dibujoCirculos(g, 225, 140, 100, 100, colorPelo);
					dibujoCirculos(g, tamanioX - 325, 140, 100, 100, colorPelo);
				} else
				if (i == 3) {
					dibujoCirculos(g, 300, 125, 100, 100, colorPelo);
				}else 
				if (i == 4) {
					dibujoCirculos(g, 200, 210, 100, 100, colorPelo);
					dibujoCirculos(g, 250, 210, 100, 100, colorPelo);
					dibujoCirculos(g, 300, 210, 100, 100, colorPelo);
					dibujoCirculos(g, 350, 210, 100, 100, colorPelo);
					dibujoCirculos(g, 400, 210, 100, 100, colorPelo);
				} else 
				if (i == 5) {
					dibujoCirculos(g, 320, 325, 20, 20, colorOJos);
					dibujoCirculos(g, 360, 325, 20, 20, colorOJos);
					//		Dibujando las cejas
					dibujarCuadrado(g, 320, 325, 20, 2, 0, 0, colorPelo);
					dibujarCuadrado(g, 360, 325, 20, 2, 0, 0, colorPelo);
				}
			} catch (InterruptedException ex) {}
		}
	}
	/*
	Métodos que tiene Graphics
		http://docs.oracle.com/javase/7/docs/api/java/awt/Graphics.html
	*/
	private void dibujoCirculos (Graphics g, int tamanioX, int tamanioY, int ancho, int alto, String colorHex) {
		Color color = Color.decode(colorHex);
		g.setColor(color);
		g.fillOval(tamanioX, tamanioY, ancho, alto);
	}
	private void dibujarCuadrado (Graphics g, int tamanioX, int tamanioY, int ancho, int alto, int redondeoHori, int redondeoVer, String colorHex) {
		Color color = Color.decode(colorHex);
		g.setColor(color);
		g.fillRoundRect(tamanioX, tamanioY, ancho, alto, redondeoHori, redondeoVer);
	}
}