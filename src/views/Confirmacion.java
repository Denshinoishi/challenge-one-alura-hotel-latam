package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;

import com.alura.controller.ViewController;
import com.alura.model.Huesped;
import com.alura.model.Reservas;
import com.toedter.calendar.JDateChooser;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.text.Format;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Toolkit;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.beans.PropertyChangeEvent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;


@SuppressWarnings("serial")
public class Confirmacion extends JFrame {
	
	Reservas reserva;
	Huesped huesped;
	private JPanel contentPane;
	int xMouse, yMouse;
	private JLabel labelExit;
	private JLabel labelAtras;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		Reservas reserva = new Reservas(new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), "Con Tapas");
		Huesped huesped = new Huesped("Pepe", "Gopanchiro", "646840", "nippon",
				new Date(System.currentTimeMillis()), "798519");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Confirmacion frame = new Confirmacion(reserva, huesped);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Confirmacion(Reservas reserva, Huesped huesped) {
		
		super("Confirmación");
		
		this.reserva = reserva;
		this.huesped = huesped;
		
		ViewController reservaController = new ViewController();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Confirmacion.class.getResource("/imagenes/aH-40px.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 560);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);
		

		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 11, 910, 560);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblCheckIn = new JLabel("FECHA DE CHECK IN");
		lblCheckIn.setForeground(SystemColor.textInactiveText);
		lblCheckIn.setBounds(10, 88, 212, 14);
		lblCheckIn.setFont(new Font("Dialog", Font.BOLD, 16));
		panel.add(lblCheckIn);
		
		JLabel lblCheckOut = new JLabel("FECHA DE CHECK OUT");
		lblCheckOut.setForeground(SystemColor.textInactiveText);
		lblCheckOut.setBounds(296, 88, 233, 14);
		lblCheckOut.setFont(new Font("Dialog", Font.BOLD, 16));
		panel.add(lblCheckOut);
		
		JLabel lblTitulo = new JLabel("NUEVA RESERVA");
		lblTitulo.setBounds(10, 35, 245, 42);
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto", Font.BOLD, 20));
		panel.add(lblTitulo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(518, 0, 392, 560);
		panel_1.setBackground(new Color(12, 138, 199));
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel logo = new JLabel("");
		logo.setBounds(173, 11, 104, 107);
		panel_1.add(logo);
		logo.setIcon(new ImageIcon(Confirmacion.class.getResource("/imagenes/Ha-100px.png")));
		
		JLabel imagenFondo = new JLabel("");
		imagenFondo.setBounds(74, 141, 296, 382);
		panel_1.add(imagenFondo);
		imagenFondo.setBackground(Color.WHITE);
		imagenFondo.setIcon(new ImageIcon(Confirmacion.class.getResource("/imagenes/img-hotel-login-.png")));
		
		JLabel lblValor = new JLabel("VALOR DE LA RESERVA");
		lblValor.setForeground(SystemColor.textInactiveText);
		lblValor.setBounds(10, 138, 196, 14);
		lblValor.setFont(new Font("Dialog", Font.BOLD, 16));
		panel.add(lblValor);
												
		// Componentes para dejar la interfaz con estilo Material Design
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuPrincipal principal = new MenuPrincipal();
				principal.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnexit.setBackground(new Color(12, 138, 199));
			     labelExit.setForeground(Color.white);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(new Color(12, 138, 199));
		btnexit.setBounds(429, 0, 53, 36);
		panel_1.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setForeground(Color.WHITE);
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel header = new JPanel();
		header.setBounds(0, 0, 910, 36);
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		panel.add(header);
		
		labelAtras = new JLabel("<");
		labelAtras.setBounds(0, 0, 53, 36);
		header.add(labelAtras);
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		
		JPanel btnAtras = new JPanel();
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ReservasView r = new ReservasView(reserva);
				r.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);

		JPanel btnValidar = new JPanel();
		
		btnValidar.setToolTipText("");
		btnValidar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				reservaController.registrar(reserva, huesped);
				Exito exito = new Exito();
				exito.setVisible(true);
				dispose();
			
			}						
		});
		btnValidar.setLayout(null);
		btnValidar.setBackground(SystemColor.textHighlight);
		btnValidar.setBounds(296, 405, 122, 35);
		panel.add(btnValidar);
		btnValidar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		
		JLabel lblSiguiente = new JLabel("VALIDAR");
		lblSiguiente.setBounds(0, 0, 122, 35);
		btnValidar.add(lblSiguiente);
		lblSiguiente.setHorizontalAlignment(SwingConstants.CENTER);
		lblSiguiente.setForeground(Color.WHITE);
		lblSiguiente.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JLabel lblFormaDePago = new JLabel("FORMA DE PAGO");
		lblFormaDePago.setForeground(SystemColor.textInactiveText);
		lblFormaDePago.setFont(new Font("Dialog", Font.BOLD, 16));
		lblFormaDePago.setBounds(296, 138, 196, 14);
		panel.add(lblFormaDePago);
		
		JLabel lblNombre = new JLabel("NOMBRE");
		lblNombre.setForeground(SystemColor.textInactiveText);
		lblNombre.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNombre.setBounds(10, 188, 196, 14);
		panel.add(lblNombre);
		
		JLabel lblApellido = new JLabel("APELLIDO");
		lblApellido.setForeground(SystemColor.textInactiveText);
		lblApellido.setFont(new Font("Dialog", Font.BOLD, 16));
		lblApellido.setBounds(296, 188, 196, 14);
		panel.add(lblApellido);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setForeground(SystemColor.textInactiveText);
		lblDni.setFont(new Font("Dialog", Font.BOLD, 16));
		lblDni.setBounds(10, 241, 196, 14);
		panel.add(lblDni);
		
		JLabel lblTelefono = new JLabel("TELEFONO");
		lblTelefono.setForeground(SystemColor.textInactiveText);
		lblTelefono.setFont(new Font("Dialog", Font.BOLD, 16));
		lblTelefono.setBounds(296, 241, 196, 14);
		panel.add(lblTelefono);
		
		JLabel lblNacionalidad = new JLabel("NACIONALIDAD");
		lblNacionalidad.setForeground(SystemColor.textInactiveText);
		lblNacionalidad.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNacionalidad.setBounds(10, 291, 196, 14);
		panel.add(lblNacionalidad);
		
		JLabel lblNacimiento = new JLabel("FECHA DE NACIMIENTO");
		lblNacimiento.setForeground(SystemColor.textInactiveText);
		lblNacimiento.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNacimiento.setBounds(296, 291, 196, 14);
		panel.add(lblNacimiento);
		
		JLabel lblReserva = new JLabel("RESERVA #");
		lblReserva.setForeground(SystemColor.textInactiveText);
		lblReserva.setFont(new Font("Dialog", Font.BOLD, 16));
		lblReserva.setBounds(10, 340, 196, 14);
		panel.add(lblReserva);
		
		JLabel lblReservaout = new JLabel("");
		lblReservaout.setForeground(new Color(0, 0, 255));
		lblReservaout.setFont(new Font("Georgia", Font.ITALIC, 16));
		lblReservaout.setBackground(new Color(64, 128, 128));
		lblReservaout.setBounds(296, 113, 212, 18);
		panel.add(lblReservaout);
		lblReservaout.setText(String.valueOf(reserva.getFecha_de_salida()));
		
		JLabel lblReservaVal = new JLabel("");
		lblReservaVal.setForeground(new Color(0, 0, 255));
		lblReservaVal.setFont(new Font("Georgia", Font.ITALIC, 16));
		lblReservaVal.setBackground(new Color(64, 128, 128));
		lblReservaVal.setBounds(10, 163, 212, 18);
		panel.add(lblReservaVal);
		lblReservaVal.setText(String.valueOf(reserva.getValorTotal()));
		
		JLabel lblReservaPago = new JLabel("");
		lblReservaPago.setForeground(new Color(0, 0, 255));
		lblReservaPago.setFont(new Font("Georgia", Font.ITALIC, 16));
		lblReservaPago.setBackground(new Color(64, 128, 128));
		lblReservaPago.setBounds(296, 163, 212, 18);
		panel.add(lblReservaPago);
		lblReservaPago.setText(reserva.getForma_de_pago());
		
		JLabel lblHuespedNombre = new JLabel("");
		lblHuespedNombre.setForeground(new Color(0, 0, 255));
		lblHuespedNombre.setFont(new Font("Georgia", Font.ITALIC, 16));
		lblHuespedNombre.setBackground(new Color(64, 128, 128));
		lblHuespedNombre.setBounds(10, 213, 212, 18);
		panel.add(lblHuespedNombre);
		lblHuespedNombre.setText(huesped.getNombre());
		
		JLabel lblHuespedApellido = new JLabel("");
		lblHuespedApellido.setForeground(new Color(0, 0, 255));
		lblHuespedApellido.setFont(new Font("Georgia", Font.ITALIC, 16));
		lblHuespedApellido.setBackground(new Color(64, 128, 128));
		lblHuespedApellido.setBounds(296, 213, 212, 18);
		panel.add(lblHuespedApellido);
		lblHuespedApellido.setText(huesped.getApellido());
		
		JLabel lblHuespedDni = new JLabel("");
		lblHuespedDni.setForeground(new Color(0, 0, 255));
		lblHuespedDni.setFont(new Font("Georgia", Font.ITALIC, 16));
		lblHuespedDni.setBackground(new Color(64, 128, 128));
		lblHuespedDni.setBounds(10, 266, 212, 18);
		panel.add(lblHuespedDni);
		lblHuespedDni.setText(huesped.getDni());
		
		JLabel lblHuespedTelefono = new JLabel("");
		lblHuespedTelefono.setForeground(new Color(0, 0, 255));
		lblHuespedTelefono.setFont(new Font("Georgia", Font.ITALIC, 16));
		lblHuespedTelefono.setBackground(new Color(64, 128, 128));
		lblHuespedTelefono.setBounds(296, 266, 212, 18);
		panel.add(lblHuespedTelefono);
		lblHuespedTelefono.setText(huesped.getTelefono());
		
		JLabel lblHuespedNacionalidad = new JLabel("");
		lblHuespedNacionalidad.setForeground(new Color(0, 0, 255));
		lblHuespedNacionalidad.setFont(new Font("Georgia", Font.ITALIC, 16));
		lblHuespedNacionalidad.setBackground(new Color(64, 128, 128));
		lblHuespedNacionalidad.setBounds(10, 316, 212, 18);
		panel.add(lblHuespedNacionalidad);
		lblHuespedNacionalidad.setText(huesped.getNacionalidad());
		
		JLabel lblHuespedNacimiento = new JLabel("");
		lblHuespedNacimiento.setForeground(new Color(0, 0, 255));
		lblHuespedNacimiento.setFont(new Font("Georgia", Font.ITALIC, 16));
		lblHuespedNacimiento.setBackground(new Color(64, 128, 128));
		lblHuespedNacimiento.setBounds(296, 316, 212, 18);
		panel.add(lblHuespedNacimiento);
		lblHuespedNacimiento.setText(String.valueOf(huesped.getFecha_de_nacimiento()));
		
		JLabel lblReservaId = new JLabel("");
		lblReservaId.setForeground(new Color(0, 0, 255));
		lblReservaId.setFont(new Font("Georgia", Font.ITALIC, 16));
		lblReservaId.setBackground(new Color(64, 128, 128));
		lblReservaId.setBounds(10, 365, 212, 18);
		panel.add(lblReservaId);
		lblReservaId.setText(String.valueOf(reserva.getId()));
		
		JLabel lblReservain = new JLabel("null");
		lblReservain.setForeground(new Color(0, 0, 255));
		lblReservain.setFont(new Font("Georgia", Font.ITALIC, 16));
		lblReservain.setBackground(new Color(64, 128, 128));
		lblReservain.setBounds(10, 113, 212, 18);
		panel.add(lblReservain);
		lblReservain.setText(String.valueOf(reserva.getFecha_de_ingreso()));


	}
		
	//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"	
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
}
}
