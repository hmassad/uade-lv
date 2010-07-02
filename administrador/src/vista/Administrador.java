package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

import controlador.ControladorGestion;

public class Administrador extends JFrame {

	private static final long serialVersionUID = 1L;

	private JMenuItem usuariosMenuItem;
	private JMenuItem oficinasMenuItem;
	private JMenuItem casillasMenuItem;
	private JMenuItem relacionesConfianzaMenuItem;
	private JMenuItem logMenuItem;
	private JMenuItem alertasMenuItem;
	private JMenuItem salirMenuItem;
	private JMenuBar mainMenuBar;

	private ControladorGestion controladorGestion;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Administrador inst = new Administrador();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public Administrador() {
		super();
		controladorGestion = new ControladorGestion();
		initGUI();
	}

	private void initGUI() {
		try {
			setSize(400, 300);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setTitle("Administrador");
			{
				mainMenuBar = new JMenuBar();
				this.setJMenuBar(mainMenuBar);
				{
					usuariosMenuItem = new JMenuItem();
					mainMenuBar.add(usuariosMenuItem);
					usuariosMenuItem.setText("Usuarios");
					usuariosMenuItem.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							Administrador.this.setContentPane(new AbmUsuarios(controladorGestion));
							Administrador.this.setVisible(true);
						}

					});
				}
				{
					oficinasMenuItem = new JMenuItem();
					mainMenuBar.add(oficinasMenuItem);
					oficinasMenuItem.setText("Oficinas");
					oficinasMenuItem.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							Administrador.this.setContentPane(new AbmOficinas(controladorGestion));
							Administrador.this.setVisible(true);
						}
					});
				}
				{
					casillasMenuItem = new JMenuItem();
					mainMenuBar.add(casillasMenuItem);
					casillasMenuItem.setText("Casillas");
					casillasMenuItem.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							Administrador.this.setContentPane(new AbmCasillas(controladorGestion));
							Administrador.this.setVisible(true);
						}
					});
				}
				{
					relacionesConfianzaMenuItem = new JMenuItem();
					mainMenuBar.add(relacionesConfianzaMenuItem);
					relacionesConfianzaMenuItem.setText("Relaciones de Confianza");
					relacionesConfianzaMenuItem.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							Administrador.this.setContentPane(new AbmRelacionesConfianza(controladorGestion));
							Administrador.this.setVisible(true);
						}
					});
				}
				{
					logMenuItem = new JMenuItem();
					mainMenuBar.add(logMenuItem);
					logMenuItem.setText("Logs");
				}
				{
					alertasMenuItem = new JMenuItem();
					mainMenuBar.add(alertasMenuItem);
					alertasMenuItem.setText("Alertas");
				}
				{
					salirMenuItem = new JMenuItem();
					mainMenuBar.add(salirMenuItem);
					salirMenuItem.setText("Salir");
					salirMenuItem.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							Administrador.this.dispose();
						}
					});
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
