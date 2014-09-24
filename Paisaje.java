/*
	Autor: Khriz Enríquez
*/
import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

public class Paisaje extends Applet {
	String colorCarro = "#d43333";
	String colorCasa = "#3a73a1";
	String colorEdificio = "#657862";
	String colorFondo = "#c2f0ff";
	String colorLlantas = "#101010";
	String colorNubes = "#ffffff";
	String colorPasto = "#369925";
	String colorPuerta = "#242424";
	String colorSol = "#fff700";
	String ventanaCasa = "#a2b1bd";
	String ventanaEdificio = "#258a8f";
	String ventanaCarro = "#c9d8e0";
	private int aleatorio = (int)(Math.random()*(15-3+1)+3);

	public void init () {
		resize(1300, 650);
	}
	public void paint (Graphics g) {
		//		Lienzo principal
		dibujoCuadrado(g, 0, 0, 1300, 650, colorFondo);

		//		Dibujando el sol
		sol(g, 20, 20, 75, 75);

		//		Dibujando la nubes
		nubes(g, aleatorio);

		//		Dibujando el pasto
		dibujoCuadrado(g, 0, 500, 1300, 150, colorPasto);

		//		Dibujando el edificio
		edificio(g);

		//		Dibujando la casa, esta será circular
		casa(g);

		//		Dibujando el carro
		carro(g);
	}

	/*		Metodo para dibujar las nubes*/
	public void nubes (Graphics g, int cantidad) {
		for (int i = 0; i < cantidad; i++) {
			int vAleatorioX = (int)(Math.random()*(1200-100+1)+100);
			int vAleatorioY = (int)(Math.random()*(200-0+1)+0);
			//		Nubes superiores
			dibujoCirculo(g, vAleatorioX, vAleatorioY, 50, 50, colorNubes);
			dibujoCirculo(g, vAleatorioX + 25, vAleatorioY, 50, 50, colorNubes);
			dibujoCirculo(g, vAleatorioX + 50, vAleatorioY, 50, 50, colorNubes);
			//		Nubes inferiores
			dibujoCirculo(g, vAleatorioX + 20, vAleatorioY + 20, 50, 50, colorNubes);
			dibujoCirculo(g, vAleatorioX + 45, vAleatorioY + 20, 50, 50, colorNubes);
			dibujoCirculo(g, vAleatorioX + 70, vAleatorioY + 20, 50, 50, colorNubes);
		}
	}
	
	/*		Metodo para dibujar el sol*/
	public void sol (Graphics g, int pX, int pY, int ancho, int alto) {
		dibujoCirculo(g, pX, pY, ancho, alto, colorSol);
		/*		Dibujando los rayos del sol, que apareceran al rededor del sol*/
	}

	/*		Metodo para dibujar el edificio*/
	public void edificio (Graphics g) {
		dibujoCuadradoR(g, 900, 100, 300, 400, 3, 3, colorEdificio);
		ventanas(g, "Edificio", 925, 50, 100, 50, 150, 4);
		//		Dibujando la puerta del edificio
		dibujoCuadrado(g, 1000, 450, 100, 50, colorPuerta);
	}
	/*		Método para dibujar la casa*/
	public void casa (Graphics g) {
		dibujoCuadrado(g, 52, 450, 298, 150, colorCasa);
		dibujoCirculo(g, 50, 300, 300, 300, colorCasa);
		ventanas(g, "Casa", 90, 375, 100, 75, 125, 0);
		//		Dibujando la puerta de la casa
		dibujoCuadrado(g, 150, 525, 100, 75, colorPuerta);
		//		Dibujando la chimenea
	}
	/*		Método para dibujar el carro*/
	public void carro (Graphics g) {
		//		Llantas que estan por detras
		dibujoCirculo(g, 600, 600, 50, 50, colorLlantas);
		dibujoCirculo(g, 800, 600, 50, 50, colorLlantas);
		//		Carro
		dibujoCuadradoR(g, 550, 530, 350, 100, 20, 20, colorCarro);
		dibujoCuadradoR(g, 650, 490, 175, 50, 20, 20, colorCarro);
		dibujoCuadrado(g, 700, 500, 80, 50, ventanaCarro);
		//		Llantas que estan por enfrente
		dibujoCirculo(g, 590, 600, 50, 50, colorLlantas);
		dibujoCirculo(g, 790, 600, 50, 50, colorLlantas);
	}

	/*		Metodo para dibujar las ventajas*/
	public void ventanas (Graphics g, String tipo, int posX, int posY, int ancho, int alto, int separacion, int repeticiones) {
		if (tipo.equals("Edificio")) {
			for (int i = 1; i <= repeticiones ; i++) {
				dibujoCuadrado(g, posX, posY + (i * 75), ancho, alto, ventanaEdificio);
				dibujoCuadrado(g, posX + separacion, posY + (i * 75), ancho, alto, ventanaEdificio);
			}
		} else
		if (tipo.equals("Casa")) {
			dibujoCuadrado(g, posX, posY, ancho, alto, ventanaCasa);
			dibujoCuadrado(g, posX + separacion, posY, ancho, alto, ventanaCasa);
		}
	}

	/*		Método para hacer cuadrados*/
	public void dibujoCuadrado (Graphics g, int posX, int posY, int ancho, int alto, String colorHex) {
		Color color = Color.decode(colorHex);
		g.setColor(color);
		g.fillRect(posX, posY, ancho, alto);
	}

	/*		Método para hacer cuadrados redondeados*/
	public void dibujoCuadradoR (Graphics g, int posX, int posY, int ancho, int alto, int redondeoHori, int redondeoVer, String colorHex) {
		Color color = Color.decode(colorHex);
		g.setColor(color);
		g.fillRoundRect(posX, posY, ancho, alto, redondeoHori, redondeoVer);
	}
	/*		Método para hacer circulos*/
	public void dibujoCirculo (Graphics g, int posX, int posY, int ancho, int alto, String colorHex) {
		Color color = Color.decode(colorHex);
		g.setColor(color);
		g.fillOval(posX, posY, ancho, alto);
	}
}