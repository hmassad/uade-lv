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

public class AgregarCasillaAOficinaDialog extends BaseDialog {

	private static final long serialVersionUID = 1L;

	private int idCasilla;
	private String direccionCasilla;

	private JLabel casillaLabel;
	private JLabel casillaDireccionLabel;

	private JLabel oficinaLabel;
	private JComboBox oficinasComboBox;

	public AgregarCasillaAOficinaDialog(ControladorGestion controladorGestion, int idCasilla, String direccionCasilla) {
		super(controladorGestion);
		this.idCasilla = idCasilla;
		this.direccionCasilla = direccionCasilla;
		super.init();
	}

	@Override
	protected void cargarDatos() throws Exception {
		Collection<OficinaVO> oficinasActuales;
		try {
			oficinasActuales = controladorGestion.obtenerOficinasPorCasilla(idCasilla);
		} catch (Exception e1) {
			throw new Exception(String.format("Ocurrió un error al listar las Oficinas de la Casilla.\n\"%s\"", e1.getMessage()));
		}
		Collection<OficinaVO> oficinas;
		try {
			oficinas = controladorGestion.obtenerOficinas();
		} catch (Exception e1) {
			throw new Exception(String.format("Ocurrió un error al listar las Oficinas de la Casilla.\n\"%s\"", e1.getMessage()));
		}
		if (oficinas.size() == 0) {
			throw new Exception("No se registran Oficinas.");
		}
		for (OficinaVO o : oficinas) {
			if (!oficinasActuales.contains(o)) {
				oficinasComboBox.addItem(o);
			}
		}
		if (oficinasComboBox.getItemCount() == 0) {
			throw new Exception("La Casilla ya pertenece a todas las Oficinas.");
		}
	}

	@Override
	protected String getTitulo() {
		return "Agregar Casilla a Oficina";
	}

	@Override
	protected Dimension getTamaño() {
		return new Dimension(340, 140);
	}

	@Override
	protected void inicializarVentanaEspecializada() {
		Container contentPane = getContentPane();

		casillaLabel = new JLabel();
		casillaLabel.setText("Dirección");
		contentPane.add(casillaLabel);
		casillaLabel.setBounds(10, 10, 100, 20);

		casillaDireccionLabel = new JLabel();
		casillaDireccionLabel.setText(direccionCasilla);
		contentPane.add(casillaDireccionLabel);
		casillaDireccionLabel.setBounds(110, 10, 200, 20);

		oficinaLabel = new JLabel();
		oficinaLabel.setText("Usuario");
		contentPane.add(oficinaLabel);
		oficinaLabel.setBounds(10, 40, 100, 20);

		oficinasComboBox = new JComboBox();
		contentPane.add(oficinasComboBox);
		oficinasComboBox.setBounds(110, 40, 200, 20);
	}

	@Override
	protected ActionListener getAceptarActionListener() {
		return new ActionListener() {

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
		};
	}
}
