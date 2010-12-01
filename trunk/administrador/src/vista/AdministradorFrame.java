package vista;
//AdministradorFrame
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import controlador.ControladorGestion;


public class AdministradorFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JMenuItem usuariosMenuItem;
	private JMenuItem oficinasMenuItem;
	private JMenuItem casillasMenuItem;
	private JMenuItem relacionesConfianzaMenuItem;
	private JMenuItem logsMenuItem;
	private JMenuItem salirMenuItem;
	private JMenuBar mainMenuBar;

	/**
	 * @uml.property  name="controladorGestion"
	 * @uml.associationEnd  
	 */
	private ControladorGestion controladorGestion;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				AdministradorFrame inst = new AdministradorFrame();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public AdministradorFrame() {
		super();
		try {
			controladorGestion = new ControladorGestion();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, String.format("Ocurrió un conectarse al servidor.\n\"%s\"", e1.getMessage()), "Error", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
			System.exit(-1);
		}
		initGUI();
	}

	private void initGUI() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, String.format("No se pudo cargar el Administrador de Ventanas del sistema operativo.\n\"%s\"", e.getMessage()), "Warning", JOptionPane.ERROR_MESSAGE);
		}
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		try {
			setSize(600, 400);
			setTitle("Administrador");

			mainMenuBar = new JMenuBar();
			this.setJMenuBar(mainMenuBar);

			usuariosMenuItem = new JMenuItem();
			mainMenuBar.add(usuariosMenuItem);
			usuariosMenuItem.setText("Usuarios");
			usuariosMenuItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					AdministradorFrame.this.setContentPane(new AbmUsuariosPanel(controladorGestion));
					AdministradorFrame.this.setVisible(true);
				}

			});

			oficinasMenuItem = new JMenuItem();
			mainMenuBar.add(oficinasMenuItem);
			oficinasMenuItem.setText("Oficinas");
			oficinasMenuItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					AdministradorFrame.this.setContentPane(new AbmOficinasPanel(controladorGestion));
					AdministradorFrame.this.setVisible(true);
				}
			});

			casillasMenuItem = new JMenuItem();
			mainMenuBar.add(casillasMenuItem);
			casillasMenuItem.setText("Casillas");
			casillasMenuItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					AdministradorFrame.this.setContentPane(new AbmCasillasPanel(controladorGestion));
					AdministradorFrame.this.setVisible(true);
				}
			});

			relacionesConfianzaMenuItem = new JMenuItem();
			mainMenuBar.add(relacionesConfianzaMenuItem);
			relacionesConfianzaMenuItem.setText("Relaciones de Confianza");
			relacionesConfianzaMenuItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					AdministradorFrame.this.setContentPane(new AbmRelacionesConfianzaPanel(controladorGestion));
					AdministradorFrame.this.setVisible(true);
				}
			});

			logsMenuItem = new JMenuItem();
			mainMenuBar.add(logsMenuItem);
			logsMenuItem.setText("Logs");
			logsMenuItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					AdministradorFrame.this.setContentPane(new LogsPanel(controladorGestion));
					AdministradorFrame.this.setVisible(true);
				}
			});

			salirMenuItem = new JMenuItem();
			mainMenuBar.add(salirMenuItem);
			salirMenuItem.setText("Salir");
			salirMenuItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
