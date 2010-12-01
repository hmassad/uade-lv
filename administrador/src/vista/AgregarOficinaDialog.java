package vista;
//Dialog
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controlador.ControladorGestion;

public class AgregarOficinaDialog extends BaseDialog {

	private static final long serialVersionUID = 1L;

	private JLabel nombreLabel;
	private JTextField nombreTextField;

	public AgregarOficinaDialog(ControladorGestion controladorGestion) {
		super(controladorGestion);
		super.init();
	}

	protected void cargarDatos() {
	}

	@Override
	protected String getTitulo() {
		return "Agregar Oficina";
	}

	@Override
	protected Dimension getTamaño() {
		return new Dimension(340, 140);
	}

	@Override
	protected void inicializarVentanaEspecializada() {
		Container contentPane = getContentPane();

		nombreLabel = new JLabel();
		nombreLabel.setText("Nombre");
		contentPane.add(nombreLabel);
		nombreLabel.setBounds(10, 10, 100, 20);

		nombreTextField = new JTextField();
		nombreTextField.setText("");
		contentPane.add(nombreTextField);
		nombreTextField.setBounds(110, 10, 200, 20);
	}

	@Override
	protected ActionListener getAceptarActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String nombre = nombreTextField.getText();
				try {
					controladorGestion.agregarOficina(nombre);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, String.format("Ocurrió un error al agregar la Oficina.\n\"%s\"", e1.getMessage()), "Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				AgregarOficinaDialog.this.dispose();
			}
		};
	}
}
