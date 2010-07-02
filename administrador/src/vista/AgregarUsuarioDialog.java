package vista;

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import controlador.ControladorGestion;

public class AgregarUsuarioDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JButton aceptarButton;
	private JButton cancelarButton;

	private JLabel nombreLabel;
	private JTextArea nombreTextArea;

	private ControladorGestion controladorGestion;

	public AgregarUsuarioDialog(ControladorGestion controladorGestion) {
		super();
		this.setControladorGestion(controladorGestion);
		initGUI();
	}

	private void initGUI() {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(400, 300);
		
		// TODO: Layout

		Container contentPane = getContentPane();

		nombreLabel = new JLabel();
		nombreLabel.setText("Nombre");
		contentPane.add(nombreLabel);

		nombreTextArea = new JTextArea();
		nombreTextArea.setText("");
		contentPane.add(nombreTextArea);

		aceptarButton = new JButton();
		aceptarButton.setText("Aceptar");
		contentPane.add(aceptarButton);

		cancelarButton = new JButton();
		cancelarButton.setText("Cancelar");
		contentPane.add(cancelarButton);

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
