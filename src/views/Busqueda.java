package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.alura.controller.ViewController;
import com.alura.dao.ReservasDao;
import com.alura.model.Huesped;
import com.alura.model.Reservas;
import com.alura.utiles.MiTablaModelo;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private MiTablaModelo modeloReservas;
	private MiTablaModelo modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	private ViewController busquedaController = new ViewController();
	List<Reservas> reservas = new ArrayList<>();
	List<Huesped> huespedes = new ArrayList<>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
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
	public Busqueda() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		
		
		
		tbReservas = new JTable();
		
		boolean[] columnasEditablesReservas = {false, true, true, true, true};
		
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloReservas = new MiTablaModelo(new String[] {"Número de Reserva",
				"Check In",
				"Check out",
				"Valor",
				"Forma de Pago"}, columnasEditablesReservas);
		tbReservas.setModel(modeloReservas);
	
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table, null);
		scroll_table.setVisible(true);
		
		
		
		cargarTablaReservas();
		
		
		tbHuespedes = new JTable();
		
		boolean[] columnasEditablesHuespedes = {false, false, true, true, true, true, true, true};
		
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHuesped = new MiTablaModelo(new String[] {"Número de Huesped",
				"Número de Reserva",
				"Nombre",
				"Apellido",
				"DNI",
				"Fecha de nacimiento",
				"Nacionalidad",
				"Telefono"}, columnasEditablesHuespedes);
		tbHuespedes.setModel(modeloHuesped);
		

	
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		
		cargarTablaHuespedes();
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
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
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
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
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(pestanaActiva(panel) == 0) {
					buscarTabla(txtBuscar, modeloReservas, tbReservas);
				}else if (pestanaActiva(panel) == 1) {
					buscarTabla(txtBuscar, modeloHuesped, tbHuespedes);
				}

			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
	
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnEditar.setBackground(new Color(118, 187, 233));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnEditar.setBackground(new Color(118, 187, 223));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(pestanaActiva(panel) == 0) {
					modificarTablaReservas();
					limpiarTablaReservas();
					cargarTablaReservas();
				}else if(pestanaActiva(panel) == 1) {
					modificarTablaHuespedes();
					limpiarTablaHuespedes();
					cargarTablaHuespedes();
				}
				
			}
			
		});
		

		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		JPanel btnEliminar = new JPanel();
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);
		
		
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnEliminar.setBackground(new Color(118, 187, 233));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnEliminar.setBackground(new Color(118, 187, 223));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int confirm = JOptionPane.showConfirmDialog(null,
						"Se eliminara la reserva y el huesped asociado al registro",
						"Confirmación", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
				if(confirm == JOptionPane.OK_OPTION) {
					if(pestanaActiva(panel) == 0) {
						//Pestaña de Reservas Activa
					
						eliminarReserva();
						limpiarTablaReservas();
						limpiarTablaHuespedes();
						cargarTablaReservas();
						cargarTablaHuespedes();
						
					} else if(pestanaActiva(panel) == 1) {
						//Pestaña de Huespedes Activa
				
						eliminarHuespede();
						limpiarTablaHuespedes();
						limpiarTablaReservas();
						cargarTablaHuespedes();
						cargarTablaReservas();
					} else {
						//
						System.out.println("Nada Activo");
				
					}
					
				}
				
					
				
			}
		});
		
		
		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		
		JPanel btnActualizar = new JPanel();
		btnActualizar.setLayout(null);
		btnActualizar.setBackground(new Color(12, 138, 199));
		btnActualizar.setBounds(20, 508, 122, 35);
		contentPane.add(btnActualizar);
		
		
		btnActualizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnActualizar.setBackground(new Color(118, 187, 233));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnActualizar.setBackground(new Color(118, 187, 223));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				limpiarTablaHuespedes();
				limpiarTablaReservas();
				cargarTablaHuespedes();
				cargarTablaReservas();					
				
			}
		});
		
		
		
		
		JLabel lblActualizar = new JLabel("ACTUALIZAR");
		lblActualizar.setHorizontalAlignment(SwingConstants.CENTER);
		lblActualizar.setForeground(Color.WHITE);
		lblActualizar.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblActualizar.setBounds(0, 0, 122, 35);
		btnActualizar.add(lblActualizar);
		setResizable(false);
	}
	
	
	public int pestanaActiva(JTabbedPane panel) {
		return panel.getSelectedIndex();
	}
	


	public void cargarTablaHuespedes() {
		huespedes = busquedaController.listarHuespedes();
		
		DefaultTableModel modelx = (DefaultTableModel) tbHuespedes.getModel();
		
		for (Huesped huesped : huespedes) {
			Object[] row = { huesped.getId(),
					huesped.getId_reserva(),
					huesped.getNombre(),
					huesped.getApellido(),
					huesped.getDni(),
					huesped.getFecha_de_nacimiento(),
					huesped.getNacionalidad(),
					huesped.getTelefono()};
			modelx.addRow(row);
			
		}
	}

	public void cargarTablaReservas() {
		reservas = busquedaController.listarReservas();
		DefaultTableModel model = (DefaultTableModel) tbReservas.getModel();
		reservas = busquedaController.listarReservas();
		for (Reservas reserva : reservas) {
			Object[] row = { reserva.getId(),
					reserva.getFecha_de_ingreso(),
					reserva.getFecha_de_salida(),
					reserva.getValorTotal(),
					reserva.getForma_de_pago()};
			model.addRow(row);
			
		}
	}
	
	
	
	private void buscarTabla(JTextField txt, DefaultTableModel modelo, JTable table) {
		String busqueda = txt.getText();
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);
		table.setRowSorter(sorter);
		sorter.setRowFilter(RowFilter.regexFilter(busqueda));
	}
	
	
	private boolean tieneFilaElegidaReservas() {
		return tbReservas.getSelectedRowCount() == 0 || tbReservas.getSelectedColumnCount() == 0;
	}

	
	private boolean tieneFilaElegidaHuespedes() {
		return tbHuespedes.getSelectedRowCount() == 0 || tbHuespedes.getSelectedColumnCount() == 0;
	}
	private void modificarTablaReservas() {
		if (tieneFilaElegidaReservas()) {
			JOptionPane.showMessageDialog(this, "Por favor, selecciona un item");
			return;
		}

		Optional.ofNullable(modeloReservas.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
				.ifPresentOrElse(fila -> {
					
					Integer id = Integer.valueOf( modeloReservas.getValueAt(tbReservas.getSelectedRow(), 0).toString());
					Date in = Date.valueOf( modeloReservas.getValueAt(tbReservas.getSelectedRow(), 1).toString());
					Date out = Date.valueOf( modeloReservas.getValueAt(tbReservas.getSelectedRow(), 2).toString());
					BigDecimal valor = new BigDecimal (modeloReservas.getValueAt(tbReservas.getSelectedRow(), 3).toString());
					String formaPago = modeloReservas.getValueAt(tbReservas.getSelectedRow(), 4).toString();
					
					
					
					var filasModificadas = this.busquedaController.modificarReserva(id, in, out, valor, formaPago);
					
					JOptionPane.showMessageDialog(this, String.format("%d item modificado con éxito!", filasModificadas));
				}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
	}
	
	
	private void modificarTablaHuespedes() {
		if(tieneFilaElegidaHuespedes()) {
			JOptionPane.showMessageDialog(this, "Por favor, selecciona un huesped");
			return;
			
		}
		
		Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
		.ifPresentOrElse(fila ->{
			Integer id = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
			
			String nombre = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 2).toString();
			String apellido = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 3).toString();
			String dni = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 4).toString();
			Date fecha_nacimiento = Date.valueOf((modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 5).toString()));
			String nacionalidad = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 6).toString();
			String telefono = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 7).toString();
			
			Huesped hues = new Huesped(nombre, apellido, dni,nacionalidad, fecha_nacimiento, telefono);
			System.out.println(hues);
			System.out.println(fecha_nacimiento);
			
			
			var filasModificadas = this.busquedaController.modificarHuesped( hues,id);
			JOptionPane.showMessageDialog(this, String.format("%d item modificado con éxito", filasModificadas));
		}, () -> JOptionPane.showMessageDialog(this, "Por favor, seleccione un huesped"));
		
	}
	
	
	private void limpiarTablaReservas() {
		modeloReservas.getDataVector().clear();
	}
	
	private void limpiarTablaHuespedes() {
		modeloHuesped.getDataVector().clear();
	}

	
	private void eliminarReserva() {
		if (tieneFilaElegidaReservas()) {
			JOptionPane.showMessageDialog(this, "Por favor, elije una reserva");
			return;
		}

		Optional.ofNullable(modeloReservas.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
				.ifPresentOrElse(fila -> {
					Integer id = Integer.valueOf( modeloReservas.getValueAt(tbReservas.getSelectedRow(), 0).toString());
					
					
					var cantidadEliminada = this.busquedaController.eliminarReserva(id);
					
					modeloReservas.removeRow(tbReservas.getSelectedRow());

					JOptionPane.showMessageDialog(this,  cantidadEliminada +  " Item eliminado con éxito!");
				}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije una reserva"));
	}
	
	
	private void eliminarHuespede() {
		if (tieneFilaElegidaHuespedes()) {
			JOptionPane.showMessageDialog(this, "Por favor, elije un Huesped");
			return;
		}

		Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
				.ifPresentOrElse(fila -> {
					Integer id = Integer.valueOf( modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
					Integer idReserva = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 7 ).toString());
					
					
					var cantidadEliminada = this.busquedaController.eliminarHuesped(id, idReserva);
					
					modeloHuesped.removeRow(tbHuespedes.getSelectedRow());

					JOptionPane.showMessageDialog(this,  cantidadEliminada +  " Item eliminado con éxito!");
				}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un Huesped"));
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
