/*
	Author: Khriz Enríquez
*/
import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Scrollbar;
import javax.swing.Timer;

public class Ciudad extends Applet implements AdjustmentListener, ActionListener {
	//		Definiendo los colores generales
	public ActionListener naynCat;

	private Color colorAsfalto;
	private Color colorCielo;
	private Color colorEstrellas;
	private Color colorLava;
	private Color colorMar;
	private Color colorNubes;
	private Color colorPanel;
	private Color colorPasto;
	private Color colorSol;
	private Color colorTac;
	private Color colorTapandoLuna;
	private Color colorTecho;
	private Color colorVentanas;
	private Color colorVolcan;
	private Color cuadradosTac;
	private Color waffleTac;

	String colorEdificios[] = {"#563d7c", "#e31220", "#00334d", "#4d2e00", "#4d0000", "#004d16", "#a8a8a8", "#1833c9", "#d9ebf2", "#eaff00", "#b3860b"};
	private String diaNoche;
	private String eCielo;
	private String faseLunar;

	private int tamanioXIni;
	private int tamanioYIni;
	private int posInicialSol;
	private int posInicialLuna;
	private int valorFaseLunar;
	private int largoLlamaVolcan;
	private int largoTac;

	private Scrollbar scDiaNoche, scErupcion, scFaseLunar;
	private Button tacNayn;
	private Label lblDiaNoche, lblErupcion, lblFaseLunar, lblTacCat;

	public void init () {
		diaNoche = "Mañana";
		eCielo = "Nubes";
		faseLunar = "";
		//		Lo inicializo como luna llena
		valorFaseLunar = 0;
		/*		Método que crea los objetos de scrollbar, label, etc*/
		montandoElementos();

		largoLlamaVolcan = 1;

		tamanioXIni = 1350;
		tamanioYIni = 650;

		posInicialSol = 225;
		posInicialLuna = 275;

		largoTac = 1400;

		//		Asignando los colores necesarios
		colorAsfalto = Color.decode("#a8a8a8");
		colorCielo = Color.decode("#70d9ff");
		colorEstrellas = Color.decode("#d9ebf2");
		colorLava = Color.decode("#fc2121");
		colorMar = Color.decode("#1833c9");
		colorNubes = Color.decode("#d9ebf2");
		colorPanel = Color.decode("#5fcfa2");
		colorPasto = Color.decode("#61ad1f");
		colorSol = Color.decode("#eaff00");
		colorTac = Color.decode("#101010");
		colorTecho = Color.decode("#202020");
		colorVentanas = Color.decode("#ebedd8");
		colorVolcan = Color.decode("#b3860b");
		waffleTac = Color.decode("#d1b200");
		cuadradosTac = Color.decode("#a89410");
		colorTapandoLuna = colorCielo;

		resize(tamanioXIni, tamanioYIni);
	}
	public void paint (Graphics g) {
		//		Lienzo principal
		dibujoCuadrado(g, 0, 0, tamanioXIni - 150, tamanioYIni, colorCielo);
		//		Dibujando el sol. Necesito en el array int pX, int pY, int ancho, int alto
		int valoresSol[] = {tamanioXIni - posInicialSol, 20, 50, 50};
		sol(g, valoresSol);
		//		Luna
		int tLuna[] = {tamanioXIni - posInicialLuna, 20, 50, 50};
		tapandoLuna(g, tLuna);
		//		Dibujando las nubes por defecto
		elementosCielo(g, 10, eCielo);
		//		Dibujando el mar
		dibujoCuadrado(g, 0, 200, tamanioXIni - 150, tamanioYIni, colorMar);
		//		Dibujando el volcán
		volcan(g);
		//		Dibujando el asfalto
		erupcionVolcan(g);
		dibujoCuadrado(g, 0, 300, tamanioXIni - 150, tamanioYIni, colorAsfalto);
		//		Dibujando el pasto
		dibujoCuadrado(g, 0, 325, tamanioXIni - 150, tamanioYIni, colorPasto);
		//		Dibujando los edificios
		edificios(g, 7);
		//		Dibujando las casas
		casas(g, 8);
		//		Dibujando el carro
		carros(g, 8);
		//		Separando el área donde estarán los Scrollbar
		dibujoCuadrado(g, 1200, 0, 150, tamanioYIni, colorPanel);

		/*		Área de texto de los Scrollbar*/
		g.setColor(Color.BLACK);
		g.drawString(diaNoche, 1200, 70);
		g.drawString(faseLunar, 1200, 150);
		//		Mostrando Tac Cat
		dibujandoTacNaynCat(g);
	}

	/*		Método para dibujar el sol*/
	public void sol (Graphics g, int arrValores[]) {
		//		En su valor inicial el sol estará en la parte superior derecha
		dibujoCirculo(g, arrValores[0], arrValores[1], arrValores[2], arrValores[3], colorSol);
	}
	/*		Método para dibujar las nubes*/
	public void elementosCielo (Graphics g, int cantidad, String tipoElemento) {
		int vAleatorioX, vAleatorioY;
		if (tipoElemento.equals("Nubes")) {
			for (int i = 0; i < cantidad; i++) {
				vAleatorioX = (int)(Math.random()*(1050-100+1)+100);
				vAleatorioY = (int)(Math.random()*(150-0+1)+0);
				//		Nubes superiores
				dibujoCirculo(g, vAleatorioX, vAleatorioY, 30, 30, colorNubes);
				dibujoCirculo(g, vAleatorioX + 15, vAleatorioY, 30, 30, colorNubes);
				dibujoCirculo(g, vAleatorioX + 40, vAleatorioY, 30, 30, colorNubes);
				//		Nubes inferiores
				dibujoCirculo(g, vAleatorioX + 15, vAleatorioY + 15, 30, 30, colorNubes);
				dibujoCirculo(g, vAleatorioX + 35, vAleatorioY + 15, 30, 30, colorNubes);
				dibujoCirculo(g, vAleatorioX + 60, vAleatorioY + 15, 30, 30, colorNubes);
			}	
		} else 
		if (tipoElemento.equals("Estrellas")) {
			cantidad *= 10;
			for (int i = 0; i < cantidad; i++) {
				vAleatorioX = (int)(Math.random()*(1100-100+1)+50);
				vAleatorioY = (int)(Math.random()*(150-0+1)+0);
				dibujoCirculo(g, vAleatorioX, vAleatorioY, 3, 3, colorEstrellas);
			}
		}
	}
	/*		Método para dibujar el volcán*/
	public void volcan (Graphics g) {
		for (int i = 20; i < 200; i += 2) 
			dibujoCuadrado(g, 100 - i, 1 * i + 80, 2 * i, 5, colorVolcan);
	}
	/*		Metodo para dibujar el edificio*/
	public void edificios (Graphics g, int cantidad) {
		for (int i = 0; i < cantidad; i++) {
			Color colorEd = Color.decode(colorEdificios[i]);
			dibujoCuadradoR(g, 150 + (i * 170), 100, 150, 210, 3, 3, colorEd);
			ventanas(g, "Edificio", 160 + (i * 170), 110, 20, 20, 35, 6);
		}
	}
	/*		Método para dibujar la casa*/
	public void casas (Graphics g, int cantidad) {
		for (int i = 0; i < cantidad; i++) {
			dibujoCuadrado(g, 0 + (i * 150), 325, 125, 100, colorAsfalto);
			ventanas(g, "Casa", 10 + (i * 150), 335, 30, 30, 40, 1);
			techoCasa(g, -15 + (i * 150), 310, 140, 20, 10);
		}
		for (int i = 0; i < cantidad; i++) {
			dibujoCuadrado(g, 50 + (i * 150), 375, 125, 100, colorAsfalto);
			ventanas(g, "Casa", 60 + (i * 150), 385, 35, 35, 37, 1);
			techoCasa(g, 35 + (i * 150), 360, 140, 20, 10);
		}
	}
	/*		Metodo para dibujar las ventajas*/
	public void ventanas (Graphics g, String tipo, int posX, int posY, int ancho, int alto, int separacion, int repeticiones) {
		//	cantidadVentanas hace referencia a cuantas ventanas horizontales apareceran
		int cantidadVentanas = 0;
		if (tipo.equals("Edificio")) 
			cantidadVentanas = 4;
		else 
		if (tipo.equals("Casa")) 
			cantidadVentanas = 3;
		else 
		if (tipo.equals("Carro")) 
			cantidadVentanas = 1;

		for (int i = 0; i < repeticiones ; i++) 
			for (int j = 0; j < cantidadVentanas; j++) 
				dibujoCuadrado(g, posX + (j * separacion), posY + (i * separacion), ancho, alto, colorVentanas);
	}
	/*		Método para dibujar el techo de las casas, tendrán un color en específico*/
	public void techoCasa (Graphics g, int posX, int posY, int ancho, int alto, int separacion) {
		for (int i = 0; i < 3; i++) {
			dibujoCuadradoR(g, (posX + separacion) + (i * 10), posY - (i * 10), ancho - (i * 20), alto, 5, 5, colorTecho);
		}
	}
	public void carros (Graphics g, int cantidad){
		String colorCarros[] = {"#e31220", "#b3860b", "#4d2e00", "#1833c9", "#4d0000", "#004d16", "#a8a8a8", "#d9ebf2", "#eaff00", "#563d7c", "#00334d",};
		for (int i = 0; i < cantidad; i++) {
			Color colorCarro = Color.decode(colorCarros[i]);
			carro(g, i * 150, 450, 100, 30, colorCarro);
		}
	}
	/*		Método para dibujar el carro*/
	public void carro (Graphics g, int posX, int posY, int ancho, int alto, Color colorCarro) {
		//		Llantas que estan por detras
		dibujoCirculo(g, posX + 10, posY + 20, 20, 20, colorTecho);
		dibujoCirculo(g, posX + 70, posY + 20, 20, 20, colorTecho);
		//		Carro
		dibujoCuadradoR(g, posX, posY, ancho, alto, 20, 20, colorCarro);
		dibujoCuadradoR(g, posX + 35, posY - 15, ancho / 2, alto / 2, 5, 5, colorCarro);
		//dibujoCuadrado(g, 700, 500, 80, 50, ventanaCarro);
		//		Llantas que estan por enfrente
		dibujoCirculo(g, posX + 13, posY + 22, 20, 20, colorTecho);
		dibujoCirculo(g, posX + 73, posY + 22, 20, 20, colorTecho);
	}

	/*		Método para hacer cuadrados*/
	public void dibujoCuadrado (Graphics g, int posX, int posY, int ancho, int alto, Color color) {
		g.setColor(color);
		g.fillRect(posX, posY, ancho, alto);
	}
	/*		Método para hacer cuadrados redondeados*/
	public void dibujoCuadradoR (Graphics g, int posX, int posY, int ancho, int alto, int redondeoHori, int redondeoVer, Color color) {
		g.setColor(color);
		g.fillRoundRect(posX, posY, ancho, alto, redondeoHori, redondeoVer);
	}
	/*		Método para hacer circulos*/
	public void dibujoCirculo (Graphics g, int posX, int posY, int ancho, int alto, Color color) {
		g.setColor(color);
		g.fillOval(posX, posY, ancho, alto);
	}

	/*		Se encargará de montar los objetos necesarios en mi applet */
	public void montandoElementos () {
		this.setLayout(null);
		String arr[] = {"Partes del día", "Fases lunares", "Volcán"};
		Label lbl[] = {lblDiaNoche, lblFaseLunar, lblErupcion};
		Scrollbar scB[] = {scDiaNoche, scFaseLunar, scErupcion};
		montandoScrollbars(arr, lbl, scB);

		Button btn[] = {tacNayn};
		Label lblBtn[] = {lblTacCat};
		String arrBtn[] = {"Tac Nayn cat"};
		montandoBotones(arrBtn, lblBtn, btn);
	}
	public void montandoScrollbars (String valoresEtiquetas[], Label nombreEtiquetas[], Scrollbar scBar[]) {
		int valLabelY = 5;
		for (int i = 0, j = nombreEtiquetas.length; i < j; i++) {
			if (valoresEtiquetas[i].equals("Partes del día")) {
				scBar[i] = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 0, 3);
				scBar[i].addAdjustmentListener(new java.awt.event.AdjustmentListener() {
					public void adjustmentValueChanged(AdjustmentEvent e) {
						metodoDiaNoche(e, scBar[0]);
					}
			    });
			} else 
			if (valoresEtiquetas[i].equals("Volcán")) {
				scBar[i] = new Scrollbar(Scrollbar.HORIZONTAL, 0, 5, 0, 201);
				scBar[i].addAdjustmentListener(new java.awt.event.AdjustmentListener() {
					public void adjustmentValueChanged(AdjustmentEvent e) {
						largoLlamaVolcan = scBar[2].getValue();
						repaint();
					}
				});
			} else
			if (valoresEtiquetas[i].equals("Fases lunares")) {
				scBar[i] = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 0, 4);
				scBar[i].addAdjustmentListener(new java.awt.event.AdjustmentListener() {
					public void adjustmentValueChanged(AdjustmentEvent e) {
						valorFaseLunar = scBar[1].getValue();
						fasesLunares(e, scBar[1], valorFaseLunar);
					}
			    });
			} else {
				scBar[i] = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 0, 3);
			}
			nombreEtiquetas[i] = new Label(valoresEtiquetas[i], 1);
			nombreEtiquetas[i].setBackground(Color.decode("#5fcfa2"));

			nombreEtiquetas[i].setBounds(1200, valLabelY, 150, 20);

			scBar[i].setBounds(1200, valLabelY + 25, 150, 20);

			add(nombreEtiquetas[i]);
			add(scBar[i]);
			valLabelY += 80;
		}
	}
	public void montandoBotones (String valoresEtiquetas[], Label nombreEtiquetas[], Button scBar[]) {
		int valLabelY = 300;
		for (int i = 0, j = nombreEtiquetas.length; i < j; i++) {
			if (valoresEtiquetas[i].equals("Tac Nayn cat")) {
			 	scBar[i] = new Button("Mostrar Tac");

			 	naynCat = new ActionListener() {
			      public void actionPerformed (ActionEvent e) {
		 				largoTac = 1400;
			 			Timer timer = new Timer (300, new ActionListener () {
						    public void actionPerformed(ActionEvent e) {
					    		if (largoTac < -100) {
					    		} else {
						    		largoTac -= 50;
		    						repaint();
					    		}
						    }
						});
						timer.start();
			 		}
			    };

	 			scBar[i].removeActionListener(naynCat);
			 	scBar[i].addActionListener(naynCat);
			} else {
				scBar[i] = new Button(":)");
			}
			nombreEtiquetas[i] = new Label(valoresEtiquetas[i], 1);
			nombreEtiquetas[i].setBackground(Color.decode("#5fcfa2"));

			nombreEtiquetas[i].setBounds(1200, valLabelY, 150, 20);

			scBar[i].setBounds(1200, valLabelY + 25, 150, 20);

			add(nombreEtiquetas[i]);
			add(scBar[i]);
			valLabelY += 80;
		}
	}

	public void metodoDiaNoche(AdjustmentEvent ev, Scrollbar bar) {
	    if (bar.getValue() == 0) {
			diaNoche = "Mañana";
			eCielo = "Nubes";
			faseLunar = "";
			posInicialSol = 225;
			posInicialLuna = 275;
			colorSol = Color.decode("#eaff00");
			colorCielo = Color.decode("#70d9ff");
			colorNubes = Color.decode("#d9ebf2");
			colorVentanas = Color.decode("#ebedd8");
			colorTapandoLuna = colorCielo;
	    }
		else
		if (bar.getValue() == 1) { 
			diaNoche = "Tarde";
			eCielo = "Nubes";
			faseLunar = "";
			posInicialSol = 1000;
			posInicialLuna = 275;
			colorSol = Color.decode("#e1f038");
			colorCielo = Color.decode("#00bfff");
			colorNubes = Color.decode("#d9ebf2");
			colorVentanas = Color.decode("#ebedd8");
			colorTapandoLuna = colorCielo;
		}else
		if (bar.getValue() == 2) {
			diaNoche = "Noche";
			eCielo = "Estrellas";
			faseLunar = "";
			posInicialLuna = 275;
			posInicialSol = 210;
			colorSol = Color.decode("#ffffff");
			colorCielo = Color.decode("#426775");
			colorNubes = Color.decode("#426775");
			colorVentanas = Color.decode("#f5ff85");
			colorTapandoLuna = colorCielo;

			fasesLunares(ev, bar, valorFaseLunar);
	    }
		repaint();
	}
	public void fasesLunares (AdjustmentEvent ev, Scrollbar bar, int valorScroll) {
		//		Fases lunares, 0 cuarto creciente, 1 luna llena, 2 cuarto menguante, 3 luna nueva
		if (valorFaseLunar == 0 && diaNoche.equals("Noche")) {
			faseLunar = "Cuarto creciente";
			colorSol = Color.decode("#ffffff");
			posInicialLuna = 220;
		} else
		if (valorFaseLunar == 1 && diaNoche.equals("Noche")) {
			faseLunar = "Lunea llena";
			colorSol = Color.decode("#ffffff");
			posInicialLuna = 275;
		} else
		if (valorFaseLunar == 2 && diaNoche.equals("Noche")) {
			faseLunar = "Cuarto menguante";
			colorSol = Color.decode("#ffffff");
			posInicialLuna = 190;
		} else
		if (valorFaseLunar == 3 && diaNoche.equals("Noche")) {
			faseLunar = "Luna nueva";
			colorSol = Color.decode("#426775");
			posInicialLuna = 215;
		}
		repaint();
	}
	public void tapandoLuna (Graphics g, int valores[]) {
		dibujoCirculo(g, valores[0], valores[1], valores[2], valores[3], colorTapandoLuna);
	}
	public void erupcionVolcan (Graphics g) {
		if (largoLlamaVolcan > 50)
			faldaVolcan(g, true);
		else 
			faldaVolcan(g, false);
		dibujoCuadrado(g, 85, 100 - largoLlamaVolcan, 30, largoLlamaVolcan, colorLava);
		if (largoLlamaVolcan > 10) {
			for (int i = 0; i < 3; i++) 
				dibujoCirculo(g, 82 + (i * 10), 90 - largoLlamaVolcan + 5, 15, 15, colorLava);
		} else {
			for (int i = 0; i < 3; i++) 
				dibujoCirculo(g, 82 + (i * 10), 90 - largoLlamaVolcan + 5, 0, 0, colorLava);
		}
	}
	public void faldaVolcan (Graphics g, boolean visible) {
		if (visible) {
			dibujoCuadrado(g, 80, 100, 40, 10, colorLava);
			for (int i = 0; i < 4; i++) {
				dibujoCirculo(g, 70 + (i * 13), 100, 20, 20, colorLava);
			}
		} else {
			dibujoCuadrado(g, 80, 100, 0, 0, colorLava);
			for (int i = 0; i < 4; i++) {
				dibujoCirculo(g, 70 + (i * 13), 100, 0, 0, colorLava);
			}
		}
	}
	public void dibujandoTacNaynCat (Graphics g) {
		//		Arco iris Tac
		dibujoCuadrado(g, largoTac + 70, 25, largoTac - 1, 15, colorTecho);
		dibujoCuadrado(g, largoTac + 70, 40, largoTac - 1, 15, colorAsfalto);
		dibujoCuadrado(g, largoTac + 70, 55, largoTac - 1, 15, colorTecho);
		//		Patas del lado del fondo
		dibujoCuadrado(g, largoTac + 28, 55, 10, 20, colorTac);
		dibujoCuadrado(g, largoTac + 48, 55, 10, 20, colorTac);
		//		Cola Tac
		dibujoCuadrado(g, largoTac + 70, 43, 20, 10, colorTac);
		//		Cuerpo Tac
		dibujoCuadradoR(g, largoTac + 20, 20, 50, 40, 5, 5, waffleTac);
		//		Cuadrados dentro del waffle de Tac
		for (int i = 0; i < 2; i++) 
			for (int j = 0; j < 3; j++) 
				dibujoCuadrado(g, largoTac + 25 + (j * 15), 25 + (i * 15), 10, 10, cuadradosTac);
		//		Patas del lado del frente
		dibujoCuadrado(g, largoTac + 35, 55, 10, 20, colorTac);
		dibujoCuadrado(g, largoTac + 55, 55, 10, 20, colorTac);
		//		Dibujando la cara
		dibujoCirculo(g, largoTac, 30, 35, 35, colorTac);
		//		Ojos
		dibujoCirculo(g, largoTac + 5, 40, 10, 10, colorLava);
		dibujoCirculo(g, largoTac + 20, 40, 10, 10, colorLava);
		//		Orejas
		dibujoCirculo(g, largoTac + 5, 25, 10, 15, colorTac);
		dibujoCirculo(g, largoTac + 20, 25, 10, 15, colorTac);
	}

	public void adjustmentValueChanged (AdjustmentEvent e) {}
	public void actionPerformed (ActionEvent e) {}
}