package vista;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controlador.ControladorGestion;

public class ModificarCasillaDialog extends BaseDialog {

	private static final long serialVersionUID = 1L;

	private int idCasilla;
	private String direccionCasilla;

	private JLabel casillaOriginalLabel;
	private JLabel casillaOriginalDireccionLabel;
	private JLabel casillaNuevaLabel;
	private JTextField casillaNuevaDireccionTextField;

	public ModificarCasillaDialog(ControladorGestion controladorGestion, int idCasilla, String direccionCasilla) {
		super(controladorGestion);
		this.idCasilla = idCasilla;
		this.direccionCasilla = direccionCasilla;
		super.init();
	}

	@Override
	protected String getTitulo() {
		return "Modificar Casilla";
	}

	@Override
	protected Dimension getTamaño() {
		return new Dimension(340, 140);
	}

	@Override
	protected void inicializarVentanaEspecializada() {
		Container contentPane = getContentPane();

		casillaOriginalLabel = new JLabel();
		casillaOriginalLabel.setText("Dirección");
		contentPane.add(casillaOriginalLabel);
		casillaOriginalLabel.setBounds(10, 10, 100, 20);

		casillaOriginalDireccionLabel = new JLabel();
		casillaOriginalDireccionLabel.setText(direccionCasilla);
		contentPane.add(casillaOriginalDireccionLabel);
		casillaOriginalDireccionLabel.setBounds(110, 10, 200, 20);

		casillaNuevaLabel = new JLabel();
		casillaNuevaLabel.setText("Nueva Dirección");
		contentPane.add(casillaNuevaLabel);
		casillaNuevaLabel.setBounds(10, 40, 100, 20);

		casillaNuevaDireccionTextField = new JTextField();
		casillaNuevaDireccionTextField.setText("");
		contentPane.add(casillaNuevaDireccionTextField);
		casillaNuevaDireccionTextField.setBounds(110, 40, 200, 20);
	}

	@Override
	protected void cargarDatos() throws Exception {
	}

	@Override
	protected ActionListener getAceptarActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					controladorGestion.modificarCasilla(idCasilla, casillaNuevaDireccionTextField.getText());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, String.format("Ocurrió un error al modificar la Casilla.\n\"%s\"", e.getMessage()), "Error", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
				ModificarCasillaDialog.this.dispose();
			}
		};
	}

}
