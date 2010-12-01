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

public class ModificarOficinaDialog extends BaseDialog {

	private static final long serialVersionUID = 1L;

	private int idOficina;
	private String nombreOficina;

	private JLabel oficinaOriginalLabel;
	private JLabel oficinaOriginalNombreLabel;
	private JLabel oficinaNuevoNombreLabel;
	private JTextField oficinaNuevoNombreTextField;

	public ModificarOficinaDialog(ControladorGestion controladorGestion, int idOficina, String nombreOficina) {
		super(controladorGestion);
		this.idOficina = idOficina;
		this.nombreOficina = nombreOficina;
		super.init();
	}

	@Override
	protected String getTitulo() {
		return "Modificar Oficina";
	}

	@Override
	protected Dimension getTamaño() {
		return new Dimension(340, 140);
	}

	@Override
	protected void inicializarVentanaEspecializada() {
		Container contentPane = getContentPane();

		oficinaOriginalLabel = new JLabel();
		oficinaOriginalLabel.setText("Oficina");
		contentPane.add(oficinaOriginalLabel);
		oficinaOriginalLabel.setBounds(10, 10, 100, 20);

		oficinaOriginalNombreLabel = new JLabel();
		oficinaOriginalNombreLabel.setText(nombreOficina);
		contentPane.add(oficinaOriginalNombreLabel);
		oficinaOriginalNombreLabel.setBounds(110, 10, 200, 20);

		oficinaNuevoNombreLabel = new JLabel();
		oficinaNuevoNombreLabel.setText("Nuevo Nombre");
		contentPane.add(oficinaNuevoNombreLabel);
		oficinaNuevoNombreLabel.setBounds(10, 40, 100, 20);

		oficinaNuevoNombreTextField = new JTextField();
		oficinaNuevoNombreTextField.setText("");
		contentPane.add(oficinaNuevoNombreTextField);
		oficinaNuevoNombreTextField.setBounds(110, 40, 200, 20);
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
					controladorGestion.modificarOficina(idOficina, oficinaNuevoNombreTextField.getText());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, String.format("Ocurrió un error al modificar la Oficina.\n\"%s\"", e.getMessage()), "Error", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
				ModificarOficinaDialog.this.dispose();
			}
		};
	}

}
