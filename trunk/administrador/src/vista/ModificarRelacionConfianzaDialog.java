package vista;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import beans.OficinaVO;
import controlador.ControladorGestion;

public class ModificarRelacionConfianzaDialog extends BaseDialog {

	private static final long serialVersionUID = 1L;

	private int idOficinaOrigen;
	private String oficinaOrigenNombre;
	private int idOficinaDestinoOriginal;

	private JLabel oficinaOrigenLabel;
	private JLabel oficinaOrigenNombreLabel;
	private JLabel oficinaDestinoLabel;
	private JComboBox oficinaDestinoComboBox;

	public ModificarRelacionConfianzaDialog(ControladorGestion controladorGestion, int idOficinaOrigen, String oficinaOrigenNombre, int idOficinaDestinoOriginal) {
		super(controladorGestion);
		this.idOficinaOrigen = idOficinaOrigen;
		this.oficinaOrigenNombre = oficinaOrigenNombre;
		this.idOficinaDestinoOriginal = idOficinaDestinoOriginal;
		super.init();
	}

	@Override
	protected String getTitulo() {
		return "Modificar Relación de Confianza";
	}

	@Override
	protected Dimension getTamaño() {
		return new Dimension(340, 140);
	}

	@Override
	protected void inicializarVentanaEspecializada() {
		Container contentPane = getContentPane();

		oficinaOrigenLabel = new JLabel();
		oficinaOrigenLabel.setText("Origen");
		contentPane.add(oficinaOrigenLabel);
		oficinaOrigenLabel.setBounds(10, 10, 100, 20);

		oficinaOrigenNombreLabel = new JLabel();

		contentPane.add(oficinaOrigenNombreLabel);
		oficinaOrigenNombreLabel.setText(oficinaOrigenNombre);
		oficinaOrigenNombreLabel.setBounds(110, 10, 200, 20);

		oficinaDestinoLabel = new JLabel();
		oficinaDestinoLabel.setText("Nuevo Nombre");
		contentPane.add(oficinaDestinoLabel);
		oficinaDestinoLabel.setBounds(10, 40, 100, 20);

		oficinaDestinoComboBox = new JComboBox();
		contentPane.add(oficinaDestinoComboBox);
		oficinaDestinoComboBox.setBounds(110, 40, 200, 20);
	}

	@Override
	protected void cargarDatos() throws Exception {
		Collection<OficinaVO> oficinas;
		try {
			oficinas = controladorGestion.obtenerOficinas();
		} catch (Exception e1) {
			throw new Exception(String.format("Ocurrió un error al listar Oficinas.\n\"%s\"", e1.getMessage()));
		}
		for (OficinaVO o : oficinas) {
			if (o.getId() != idOficinaOrigen) {
				oficinaDestinoComboBox.addItem(o);
			}
		}
	}

	@Override
	protected ActionListener getAceptarActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					int idOficinaDestinoNueva = ((OficinaVO) oficinaDestinoComboBox.getSelectedItem()).getId();
					controladorGestion.modificarRelacionConfianza(idOficinaOrigen, idOficinaDestinoOriginal, idOficinaDestinoNueva);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, String.format("Ocurrió un error al modificar la Relación de Confianza.\n\"%s\"", e.getMessage()), "Error", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
				ModificarRelacionConfianzaDialog.this.dispose();
			}
		};
	}
}
