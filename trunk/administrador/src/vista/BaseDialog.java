package vista;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import controlador.ControladorGestion;

/**
 * @author  hmassad
 */
public abstract class BaseDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	/**
	 * @uml.property  name="controladorGestion"
	 * @uml.associationEnd  
	 */
	protected ControladorGestion controladorGestion;
	private JButton aceptarButton;
	private JButton cancelarButton;

	public BaseDialog(ControladorGestion controladorGestion) {
		super();
		this.controladorGestion = controladorGestion;
	}
	
	protected void init(){
		inicializarVentana();
		agregarBotonesComunes();
		inicializarVentanaEspecializada();
		try {
			cargarDatos();
			display();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			BaseDialog.this.dispose();
		}
	}

	private void inicializarVentana() {
		this.setLayout(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				BaseDialog.this.dispose();
			}
		});
		this.setTitle(getTitulo());
		this.setSize(getTamaño());
	}

	private void agregarBotonesComunes() {
		Container contentPane = this.getContentPane();

		aceptarButton = new JButton();
		aceptarButton.setText("Aceptar");
		contentPane.add(aceptarButton);
		aceptarButton.setBounds(110, 70, 80, 20);
		aceptarButton.addActionListener(getAceptarActionListener());

		cancelarButton = new JButton();
		cancelarButton.setText("Cancelar");
		contentPane.add(cancelarButton);
		cancelarButton.setBounds(230, 70, 80, 20);
		cancelarButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BaseDialog.this.dispose();
			}
		});

	}

	protected abstract String getTitulo();

	protected abstract Dimension getTamaño();

	protected abstract void inicializarVentanaEspecializada();

	protected abstract void cargarDatos() throws Exception;

	protected abstract ActionListener getAceptarActionListener();

	private void display() {
		setModal(true);
		setVisible(true);
	}
}