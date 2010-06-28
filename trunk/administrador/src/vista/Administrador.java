package vista;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class Administrador extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;

	private JMenuItem usuariosMenuItem;
	private JMenuItem oficinasMenuItem;
	private JMenuItem casillasMenuItem;
	private JMenuItem relacionesConfianzaMenuItem;
	private JMenuItem logMenuItem;
	private JMenuItem alertasMenuItem;
	private JMenuItem salirMenuItem;
	private JMenuBar mainMenuBar;

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
		initGUI();
	}
	
	private void initGUI() {
		try {
			setSize(400, 300);
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
							Administrador.this.setContentPane(new JScrollPane());
							JLabel l = new JLabel();
							l.setText("usuarios");
							Administrador.this.getContentPane().add(l);
						}
					});
				}
				{
					oficinasMenuItem = new JMenuItem();
					mainMenuBar.add(oficinasMenuItem);
					oficinasMenuItem.setText("Oficinas");
				}
				{
					casillasMenuItem = new JMenuItem();
					mainMenuBar.add(casillasMenuItem);
					casillasMenuItem.setText("Casillas");
				}
				{
					relacionesConfianzaMenuItem = new JMenuItem();
					mainMenuBar.add(relacionesConfianzaMenuItem);
					relacionesConfianzaMenuItem
							.setText("Relaciones de Confianza");
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
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
