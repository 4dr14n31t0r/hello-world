package main;

import static java.lang.Math.PI;
import static main.Rubik.xNegativo;
import static main.Rubik.xPositivo;
import static main.Rubik.yNegativo;
import static main.Rubik.yPositivo;
import static main.Rubik.zNegativo;
import static main.Rubik.zPositivo;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Interfaz {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz window = new Interfaz();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interfaz() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		System.out.println("CREAR VENTANA ID_VENTANA 400 400 Rubik");
		System.out.println("USAR VENTANA ID_VENTANA");

		final Camara camara = new Camara();
		camara.mover(0.0f, 0.0f, -3.0f);
		final Rubik rubik = new Rubik(camara);
		
		frame = new JFrame();
		frame.setBounds(100, 100, 345, 328);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(12, 12, 452, 292);
		panel_4.setLayout(null);
		frame.getContentPane().add(panel_4);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(0, 0, 787, 308);
		panel_4.add(panel_5);
		panel_5.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, -24, 320, 371);
		panel_5.add(panel);
		panel.setLayout(null);
		
		JButton rubik_button_0 = new JButton("←");
		rubik_button_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				synchronized (rubik) {
					int n = 0;
					while(n++ < 16){
						rubik.girarCara(xNegativo, (float)PI/(2*16));
						rubik.actualizar();
					}
					rubik.establecerRotacionCara(xNegativo, true);
				}
			}
		});
		rubik_button_0.setBounds(12, 30, 44, 25);
		panel.add(rubik_button_0);
		
		JButton rubik_button_1 = new JButton("←");
		rubik_button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				synchronized (rubik) {
					int n = 0;
					while(n++ < 16){
						rubik.girarCara(yNegativo, (float)PI/(2*16));
						rubik.actualizar();
					}
					rubik.establecerRotacionCara(yNegativo, true);
				}
			}
		});
		rubik_button_1.setBounds(12, 67, 44, 25);
		panel.add(rubik_button_1);
		
		JButton rubik_button_2 = new JButton("←");
		rubik_button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				synchronized (rubik) {
					int n = 0;
					while(n++ < 16){
						rubik.girarCara(zNegativo, (float)PI/(2*16));
						rubik.actualizar();
					}
					rubik.establecerRotacionCara(zNegativo, true);
				}
			}
		});
		rubik_button_2.setBounds(12, 104, 44, 25);
		panel.add(rubik_button_2);
		
		JButton rubik_button_3 = new JButton("→");
		rubik_button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				synchronized (rubik) {
					int n = 0;
					while(n++ < 16){
						rubik.girarCara(xNegativo, -(float)PI/(2*16));
						rubik.actualizar();
					}
					rubik.establecerRotacionCara(xNegativo, false);
				}
					
			}
		});
		rubik_button_3.setBounds(109, 30, 44, 25);
		panel.add(rubik_button_3);
		
		JButton rubik_button_4 = new JButton("→");
		rubik_button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				synchronized (rubik) {
					int n = 0;
					while(n++ < 16){
						rubik.girarCara(yNegativo, -(float)PI/(2*16));
						rubik.actualizar();
					}
					rubik.establecerRotacionCara(yNegativo, false);
				}
			}
		});
		rubik_button_4.setBounds(109, 67, 44, 25);
		panel.add(rubik_button_4);
		
		JButton rubik_button_5 = new JButton("→");
		rubik_button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				synchronized (rubik) {
					int n = 0;
					while(n++ < 16){
						rubik.girarCara(zNegativo, -(float)PI/(2*16));
						rubik.actualizar();
					}
					rubik.establecerRotacionCara(zNegativo, false);
				}
			}
		});
		rubik_button_5.setBounds(109, 104, 44, 25);
		panel.add(rubik_button_5);
		
		JButton rubik_button_6 = new JButton("←");
		rubik_button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				synchronized (rubik) {
					int n = 0;
					while(n++ < 16){
						rubik.girarCara(xPositivo, -(float)PI/(2*16));
						rubik.actualizar();
					}
					rubik.establecerRotacionCara(xPositivo, false);
				}
			}
		});
		rubik_button_6.setBounds(165, 30, 44, 25);
		panel.add(rubik_button_6);
		
		JButton rubik_button_7 = new JButton("←");
		rubik_button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				synchronized (rubik) {
					int n = 0;
					while(n++ < 16){
						rubik.girarCara(yPositivo, -(float)PI/(2*16));
						rubik.actualizar();
					}
					rubik.establecerRotacionCara(yPositivo, false);
				}
			}
		});
		rubik_button_7.setBounds(165, 67, 44, 25);
		panel.add(rubik_button_7);
		
		JButton rubik_button_8 = new JButton("←");
		rubik_button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				synchronized (rubik) {
					int n = 0;
					while(n++ < 16){
						rubik.girarCara(zPositivo, -(float)PI/(2*16));
						rubik.actualizar();
					}
					rubik.establecerRotacionCara(zPositivo, false);
				}
			}
		});
		rubik_button_8.setBounds(165, 104, 44, 25);
		panel.add(rubik_button_8);
		
		JButton rubik_button_9 = new JButton("→");
		rubik_button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				synchronized (rubik) {
					int n = 0;
					while(n++ < 16){
						rubik.girarCara(xPositivo, (float)PI/(2*16));
						rubik.actualizar();
					}
					rubik.establecerRotacionCara(xPositivo, true);
				}
			}
		});
		rubik_button_9.setBounds(262, 30, 44, 25);
		panel.add(rubik_button_9);
		
		JButton rubik_button_10 = new JButton("→");
		rubik_button_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				synchronized (rubik) {
					int n = 0;
					while(n++ < 16){
						rubik.girarCara(yPositivo, (float)PI/(2*16));
						rubik.actualizar();
					}
					rubik.establecerRotacionCara(yPositivo, true);
				}
			}
		});
		rubik_button_10.setBounds(262, 67, 44, 25);
		panel.add(rubik_button_10);
		
		JButton rubik_button_11 = new JButton("→");
		rubik_button_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				synchronized (rubik) {
					int n = 0;
					while(n++ < 16){
						rubik.girarCara(zPositivo, (float)PI/(2*16));
						rubik.actualizar();
					}
					rubik.establecerRotacionCara(zPositivo, true);
				}
			}
		});
		rubik_button_11.setBounds(262, 104, 44, 25);
		panel.add(rubik_button_11);
		
		JButton rubik_button_12 = new JButton("←");
		rubik_button_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				synchronized(rubik) {
					int n = 0;
					while(n++ < 16){
						System.out.println("LIMPIAR");
						rubik.rotar(Eje.Y, (float)PI/(2*16));
						rubik.actualizar();
					}
				}
			}
		});
		rubik_button_12.setBounds(84, 187, 44, 25);
		panel.add(rubik_button_12);
		
		JButton rubik_button_13 = new JButton("→");
		rubik_button_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				synchronized(rubik) {
					int n = 0;
					while(n++ < 16){
						System.out.println("LIMPIAR");
						rubik.rotar(Eje.Y, -(float)PI/(2*16));
						rubik.actualizar();
					}
				}
			}
		});
		rubik_button_13.setBounds(196, 187, 44, 25);
		panel.add(rubik_button_13);
		
		JButton rubik_button_14 = new JButton("↑");
		rubik_button_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				synchronized(rubik) {
					int n = 0;
					while(n++ < 16){
						System.out.println("LIMPIAR");
						rubik.rotar(Eje.X, (float)PI/(2*16));
						rubik.actualizar();
					}
				}
			}
		});
		rubik_button_14.setBounds(140, 150, 44, 25);
		panel.add(rubik_button_14);
		
		JButton rubik_button_15 = new JButton("↓");
		rubik_button_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				synchronized(rubik) {
					int n = 0;
					while(n++ < 16){
						System.out.println("LIMPIAR");
						rubik.rotar(Eje.X, -(float)PI/(2*16));
						rubik.actualizar();
					}
				}
			}
		});
		rubik_button_15.setBounds(140, 224, 44, 25);
		panel.add(rubik_button_15);
		
		JButton rubik_button_16 = new JButton("R");
		rubik_button_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				synchronized(rubik){
					rubik.restaurar();
					camara.restaurar();
					rubik.actualizar();
				}
			}
		});
		rubik_button_16.setBounds(140, 187, 44, 25);
		panel.add(rubik_button_16);
		
		panel_4.setPreferredSize(panel_5.getSize());
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("TERMINAR");
				System.exit(0);
			}
		});
		btnSalir.setBounds(189, 272, 117, 25);
		panel.add(btnSalir);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.BLUE);
		panel_1.setBounds(68, 104, 29, 25);
		panel.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.RED);
		panel_2.setBounds(68, 30, 29, 25);
		panel.add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.GREEN);
		panel_3.setBounds(68, 67, 29, 25);
		panel.add(panel_3);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.CYAN);
		panel_6.setBounds(221, 104, 29, 25);
		panel.add(panel_6);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.YELLOW);
		panel_7.setBounds(221, 67, 29, 25);
		panel.add(panel_7);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Color.ORANGE);
		panel_8.setBounds(221, 30, 29, 25);
		panel.add(panel_8);
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("TERMINAR");
			}
		});
	}
}
