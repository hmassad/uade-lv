package vista2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import javax.swing.WindowConstants;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.SwingUtilities;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class Administrador2 extends javax.swing.JFrame {
	private JMenuBar jMenuBar1;
	private JMenu jMenu5;
	private JMenu jMenu7;
	private JMenuItem jMenuItem10;
	private JMenuItem jMenuItem9;
	private JMenuItem jMenuItem8;
	private JMenu jMenu6;
	private JMenu jMenu4;
	private JMenu jMenu3;
	private JMenuItem jMenuItem7;
	private JMenuItem jMenuItem6;
	private JMenuItem jMenuItem5;
	private JMenuItem jMenuItem4;
	private JMenu jMenu2;
	private JMenuItem jMenuItem3;
	private JMenuItem jMenuItem2;
	private JMenuItem jMenuItem1;
	private JMenu jMenu1;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Administrador2 inst = new Administrador2();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public Administrador2() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				jMenuBar1 = new JMenuBar();
				setJMenuBar(jMenuBar1);
				{
					jMenu1 = new JMenu();
					jMenuBar1.add(jMenu1);
					jMenu1.setText("Usuario");
					{
						jMenuItem1 = new JMenuItem();
						jMenu1.add(jMenuItem1);
						jMenuItem1.setText("Alta de usuario");
						jMenuItem1.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								System.out.println("jMenuItem1.actionPerformed, event="+evt);
							AltaUsuariovista.getinstancia(true).setVisible(true);
							
							}
						});
					}
					{
						jMenuItem2 = new JMenuItem();
						jMenu1.add(jMenuItem2);
						jMenuItem2.setText("Baja usuario");
					}
					{
						jMenuItem3 = new JMenuItem();
						jMenu1.add(jMenuItem3);
						jMenuItem3.setText("Modificar usuario");
					}
					{
						jMenuItem4 = new JMenuItem();
						jMenu1.add(jMenuItem4);
						jMenuItem4.setText("Listar usuarios");
					}
				}
				{
					jMenu2 = new JMenu();
					jMenuBar1.add(jMenu2);
					jMenu2.setText("Oficina");
					{
						jMenuItem5 = new JMenuItem();
						jMenu2.add(jMenuItem5);
						jMenuItem5.setText("Alta oficina");
					}
					{
						jMenuItem6 = new JMenuItem();
						jMenu2.add(jMenuItem6);
						jMenuItem6.setText("Baja oficina");
						jMenuItem6.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								System.out.println("jMenuItem6.actionPerformed, event="+evt);
								BajaCasillavista.getinstancia(true).setVisible(true);
								//TODO add your code for jMenuItem6.actionPerformed
							}
						});
					}
					{
						jMenuItem7 = new JMenuItem();
						jMenu2.add(jMenuItem7);
						jMenuItem7.setText("Modificar oficina");
					}
				}
				{
					jMenu3 = new JMenu();
					jMenuBar1.add(jMenu3);
					jMenu3.setText("Casilla");
					{
						jMenuItem8 = new JMenuItem();
						jMenu3.add(jMenuItem8);
						jMenuItem8.setText("Alta casilla");
						jMenuItem8.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								System.out.println("jMenuItem8.actionPerformed, event="+evt);
								AltaCasillavista.getinstancia(true).setVisible(true);//TODO add your code for jMenuItem8.actionPerformed
							}
						});
					}
					{
						jMenuItem9 = new JMenuItem();
						jMenu3.add(jMenuItem9);
						jMenuItem9.setText("Baja Casilla");
						jMenuItem9.addAncestorListener(new AncestorListener() {
							public void ancestorRemoved(AncestorEvent evt) {
								System.out.println("jMenuItem9.ancestorRemoved, event="+evt);
								//TODO add your code for jMenuItem9.ancestorRemoved
								BajaCasillavista.getinstancia(true).setVisible(true);
								
							}
							public void ancestorAdded(AncestorEvent evt) {
								System.out.println("jMenuItem9.ancestorAdded, event="+evt);
								//TODO add your code for jMenuItem9.ancestorAdded
							}
							public void ancestorMoved(AncestorEvent evt) {
								System.out.println("jMenuItem9.ancestorMoved, event="+evt);
								//TODO add your code for jMenuItem9.ancestorMoved
							}
						});
					}
				}
				{
					jMenu4 = new JMenu();
					jMenuBar1.add(jMenu4);
					jMenu4.setText("Relacion Confianza");
					{
						jMenuItem10 = new JMenuItem();
						jMenu4.add(jMenuItem10);
						jMenuItem10.setText("Baja Relacion Confianza");
						jMenuItem10.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								System.out.println("jMenuItem10.actionPerformed, event="+evt);
								BajaRelacionConfianzavista.getinstancia(true).setVisible(true);
							}
						});
					}
				}
				{
					jMenu5 = new JMenu();
					jMenuBar1.add(jMenu5);
					jMenu5.setText("Logs");
				}
				{
					jMenu6 = new JMenu();
					jMenuBar1.add(jMenu6);
					jMenu6.setText("Alertas");
				}
				{
					jMenu7 = new JMenu();
					jMenuBar1.add(jMenu7);
					jMenu7.setText("Salir");
					jMenu7.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							System.out.println("jMenu7.actionPerformed, event="+evt);
						
						}
					});
				}
			}
			pack();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
