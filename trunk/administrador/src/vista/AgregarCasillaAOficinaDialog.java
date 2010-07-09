package vista;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import beans.OficinaVO;
import controlador.ControladorGestion;

public class AgregarCasillaAOficinaDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private ControladorGestion controladorGestion;
	private int idCasilla;
	private String direccionCasilla;

	private JLabel casillaLabel;
	private JLabel casillaNombreLabel;

	private JLabel oficinaLabel;
	private JComboBox oficinasComboBox;

	private JButton aceptarButton;
	private JButton cancelarButton;

	public AgregarCasillaAOficinaDialog(ControladorGestion controladorGestion, int idCasilla, String direccionCasilla) {
		super();
		this.controladorGestion = controladorGestion;
		this.idCasilla = idCasilla;
		this.direccionCasilla = direccionCasilla;
		initGUI();
	}

	private void cargarDatos() {
		try {
			Collection<OficinaVO> oficinasActuales = controladorGestion.obtenerOficinasPorCasilla(idCasilla);
			for (OficinaVO o : controladorGestion.obtenerOficinas()) {
				if (!oficinasActuales.contains(o)) {
					oficinasComboBox.addItem(o);
				}
			}
			if (oficinasComboBox.getItemCount() == 0) {
				JOptionPane.showMessageDialog(null, "La Casilla ya pertenece a todas las Oficinas.", "Information", JOptionPane.ERROR_MESSAGE);
				dispose();
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, String.format("Ocurrió un error al listar las Oficinas de la Casilla.\n\"%s\"", e1.getMessage()), "Error", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
			dispose();
		}
	}

	private void initGUI() {
		this.setTitle("Agregar Casilla");
		this.setLayout(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(340, 140);
		this.setResizable(false);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				AgregarCasillaAOficinaDialog.this.dispose();
			}
		});

		Container contentPane = getContentPane();

		casillaLabel = new JLabel();
		casillaLabel.setText("Dirección");
		contentPane.add(casillaLabel);
		casillaLabel.setBounds(10, 10, 100, 20);

		casillaNombreLabel = new JLabel();
		casillaNombreLabel.setText(direccionCasilla);
		contentPane.add(casillaNombreLabel);
		casillaNombreLabel.setBounds(110, 10, 200, 20);

		oficinaLabel = new JLabel();
		oficinaLabel.setText("Usuario");
		contentPane.add(oficinaLabel);
		oficinaLabel.setBounds(10, 40, 100, 20);

		oficinasComboBox = new JComboBox();
		contentPane.add(oficinasComboBox);
		oficinasComboBox.setBounds(110, 40, 200, 20);
		cargarDatos();

		aceptarButton = new JButton();
		aceptarButton.setText("Aceptar");
		contentPane.add(aceptarButton);
		aceptarButton.setBounds(110, 70, 80, 20);
		aceptarButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int idOficina = ((OficinaVO) oficinasComboBox.getSelectedItem()).getId();
				try {
					controladorGestion.agregarCasillaAOficina(idOficina, idCasilla);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, String.format("Ocurrió un error al agregar la Casilla a la Oficina.\n\"%s\"", e1.getMessage()), "Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				AgregarCasillaAOficinaDialog.this.dispose();
			}
		});

		cancelarButton = new JButton();
		cancelarButton.setText("Cancelar");
		contentPane.add(cancelarButton);
		cancelarButton.setBounds(230, 70, 80, 20);
		cancelarButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AgregarCasillaAOficinaDialog.this.setVisible(false);
			}
		});

		this.setModal(true);
		this.setVisible(true);
	}
}
