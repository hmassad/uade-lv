package vista;
//Dialog
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

public class AgregarRelacionConfianzaDialog extends BaseDialog {

	private static final long serialVersionUID = 1L;

	private JLabel oficinaOrigenLabel;
	private JComboBox oficinaOrigenComboBox;

	private JLabel oficinaDestinoLabel;
	private JComboBox oficinaDestinoComboBox;

	public AgregarRelacionConfianzaDialog(ControladorGestion controladorGestion) {
		super(controladorGestion);
		super.init();
	}

	@Override
	protected void cargarDatos() throws Exception {
		Collection<OficinaVO> oficinas;
		try {
			oficinas = controladorGestion.obtenerOficinas();
		} catch (Exception e1) {
			throw new Exception(String.format("Ocurrió un error al listar Oficinas.\n\"%s\"", e1.getMessage()));
		}
		if (oficinas.size() == 0) {
			throw new Exception("No se registran Oficinas.");
		}
		for (OficinaVO o : oficinas) {
			oficinaOrigenComboBox.addItem(o);
			oficinaDestinoComboBox.addItem(o);
		}
	}

	@Override
	protected String getTitulo() {
		return "Agregar Relación de Confianza";
	}

	@Override
	protected Dimension getTamaño() {
		return new Dimension(340, 140);
	}

	@Override
	protected void inicializarVentanaEspecializada() {
		Container contentPane = getContentPane();

		oficinaOrigenLabel = new JLabel();
		oficinaOrigenLabel.setText("Oficina Origen");
		contentPane.add(oficinaOrigenLabel);
		oficinaOrigenLabel.setBounds(10, 10, 100, 20);

		oficinaOrigenComboBox = new JComboBox();
		contentPane.add(oficinaOrigenComboBox);
		oficinaOrigenComboBox.setBounds(110, 10, 200, 20);

		oficinaDestinoLabel = new JLabel();
		oficinaDestinoLabel.setText("Oficina Destino");
		contentPane.add(oficinaDestinoLabel);
		oficinaDestinoLabel.setBounds(10, 40, 100, 20);

		oficinaDestinoComboBox = new JComboBox();
		contentPane.add(oficinaDestinoComboBox);
		oficinaDestinoComboBox.setBounds(110, 40, 200, 20);
	}

	@Override
	protected ActionListener getAceptarActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int idOficinaOrigen = ((OficinaVO) oficinaOrigenComboBox.getSelectedItem()).getId();
				int idOficinaDestino = ((OficinaVO) oficinaDestinoComboBox.getSelectedItem()).getId();
				try {
					controladorGestion.agregarRelacionConfianza(idOficinaOrigen, idOficinaDestino);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, String.format("Ocurrió un error al agregar la Relación de Confianza.\n\"%s\"", e1.getMessage()), "Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				AgregarRelacionConfianzaDialog.this.dispose();
			}
		};
	}
}
