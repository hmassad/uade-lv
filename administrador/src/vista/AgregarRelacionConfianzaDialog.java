package vista;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import beans.OficinaVO;
import controlador.ControladorGestion;

public class AgregarRelacionConfianzaDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JLabel oficinaOrigenLabel;
	private JComboBox oficinaOrigenComboBox;

	private JLabel oficinaDestinoLabel;
	private JComboBox oficinaDestinoComboBox;

	private JButton aceptarButton;
	private JButton cancelarButton;

	private ControladorGestion controladorGestion;

	public AgregarRelacionConfianzaDialog(ControladorGestion controladorGestion) {
		super();
		this.setControladorGestion(controladorGestion);
		initGUI();
		cargarDatos();
	}

	private void cargarDatos() {
		try {
			for (OficinaVO o : controladorGestion.obtenerOficinas()) {
				oficinaOrigenComboBox.addItem(o);
				oficinaDestinoComboBox.addItem(o);
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, String.format("Ocurrió un error al listar Oficinas.\n\"%s\"", e1.getMessage()), "Error", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
			dispose();
		}

	}

	private void initGUI() {
		this.setTitle("Agregar Relación de Confianza");
		this.setLayout(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(340, 140);
		this.setResizable(false);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				AgregarRelacionConfianzaDialog.this.dispose();
			}
		});

		Container contentPane = getContentPane();

		oficinaOrigenLabel = new JLabel();
		oficinaOrigenLabel.setText("Oficina Origen");
		contentPane.add(oficinaOrigenLabel);
		oficinaOrigenLabel.setBounds(10, 40, 100, 20);

		oficinaOrigenComboBox = new JComboBox();
		contentPane.add(oficinaOrigenComboBox);
		oficinaDestinoComboBox.setBounds(110, 10, 200, 20);

		oficinaDestinoLabel = new JLabel();
		oficinaDestinoLabel.setText("Oficina Destino");
		contentPane.add(oficinaDestinoLabel);
		oficinaDestinoLabel.setBounds(10, 10, 100, 20);

		oficinaDestinoComboBox = new JComboBox();
		contentPane.add(oficinaDestinoComboBox);
		oficinaOrigenComboBox.setBounds(110, 40, 200, 20);

		aceptarButton = new JButton();
		aceptarButton.setText("Aceptar");
		contentPane.add(aceptarButton);
		aceptarButton.setBounds(110, 70, 80, 20);
		aceptarButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int idOficinaOrigen = ((OficinaVO) oficinaOrigenComboBox.getSelectedItem()).getId();
				int idOficinaDestino = ((OficinaVO) oficinaDestinoComboBox.getSelectedItem()).getId();
				try {
					getControladorGestion().agregarRelacionConfianza(idOficinaOrigen, idOficinaDestino);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, String.format("Ocurrió un error al agregar la Relación de Confianza.\n\"%s\"", e1.getMessage()), "Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				AgregarRelacionConfianzaDialog.this.dispose();
			}
		});

		cancelarButton = new JButton();
		cancelarButton.setText("Cancelar");
		contentPane.add(cancelarButton);
		cancelarButton.setBounds(230, 70, 80, 20);
		cancelarButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AgregarRelacionConfianzaDialog.this.setVisible(false);
			}
		});

		this.setModal(true);
		this.setVisible(true);
	}

	public ControladorGestion getControladorGestion() {
		return controladorGestion;
	}

	public void setControladorGestion(ControladorGestion controladorGestion) {
		this.controladorGestion = controladorGestion;
	}
}
