package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class Administrador extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;

	private JMenuItem oficinasMenuItem;
	private JMenuItem listarOficinasMenuItem;
	private JMenuItem agregarOficinaMenuItem;
	private JMenuItem modificarOficinaMenuItem;
	private JMenuItem eliminarOficinaMenuItem;

	private JMenuItem casillasMenuItem;

	private JMenuItem relacionesConfianzaMenuItem;

	private JMenuItem usuariosMenuItem;

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
				setJMenuBar(mainMenuBar);
				{
					oficinasMenuItem = new JMenu();
					mainMenuBar.add(oficinasMenuItem);
					oficinasMenuItem.setText("Oficinas");
					{
						listarOficinasMenuItem = new JMenuItem();
						oficinasMenuItem.add(listarOficinasMenuItem);
						listarOficinasMenuItem.setText("Listar");
						listarOficinasMenuItem.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent arg0) {
								Administrador.this.setTitle("listarOficinasMenuItem");
							}
						});
					}
					{
						agregarOficinaMenuItem = new JMenuItem();
						oficinasMenuItem.add(agregarOficinaMenuItem);
						agregarOficinaMenuItem.setText("Agregar");
					}
					{
						modificarOficinaMenuItem = new JMenuItem();
						oficinasMenuItem.add(modificarOficinaMenuItem);
						modificarOficinaMenuItem.setText("Modificar");
					}
					{
						eliminarOficinaMenuItem = new JMenuItem();
						oficinasMenuItem.add(eliminarOficinaMenuItem);
						eliminarOficinaMenuItem.setText("Eliminar");
					}
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
