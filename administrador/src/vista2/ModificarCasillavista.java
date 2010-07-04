package vista2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.WindowConstants;


import beans.CasillaVO;
import beans.OficinaVO;
import beans.UsuarioVO;

import controlador.ControladorGestion;


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
public class ModificarCasillavista extends javax.swing.JFrame implements Observer,ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8792032904065150762L;
	private JButton jButton1;
	private JTextField jTextField2;
	private JLabel jLabel2;
	private JTextField jTextField1;
	private JLabel jLabel1;
	private JButton jButton2;
	private JTextField jTextField3;
	private JLabel jLabel3;
	private ControladorGestion c;
	private static ModificarCasillavista instancia=null;
	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		ModificarCasillavista inst = new ModificarCasillavista();
		inst.setVisible(true);
	}
	
	public ModificarCasillavista() {
		super();
		initGUI();
	}
	public static ModificarCasillavista getinstancia(boolean b){
		if(instancia==null){
			
			instancia=new ModificarCasillavista();
		}
	return instancia;
	
	
	
	}
	private void initGUI() {
		try {
setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			this.setTitle("Modificar Casilla");
			{
				jButton1 = new JButton();
				getContentPane().add(jButton1);
				jButton1.setText("Aceptar");
				jButton1.setBounds(142, 194, 105, 26);
				jButton1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						System.out.println("jButton1.actionPerformed, event="+evt);
						c.modificarCasilla(jTextField1.getText(),jTextField2.getText());
						
					
					}
				});
			}
			{
				jLabel3 = new JLabel();
				getContentPane().add(jLabel3);
				jLabel3.setText("Nro Casilla");
				jLabel3.setBounds(21, 58, 91, 16);
			}
			{
				jTextField3 = new JTextField();
				getContentPane().add(jTextField3);
				jTextField3.setBounds(151, 55, 130, 23);
			}
			{
				jButton2 = new JButton();
				getContentPane().add(jButton2);
				jButton2.setText("Buscar");
				jButton2.setBounds(309, 55, 49, 23);
				jButton2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						System.out.println("jButton2.actionPerformed, event="+evt);
						CasillaVO u= new CasillaVO();
						u=c.buscarCasilla(Integer.parseInt(jTextField1.getText()));
						
						jTextField1.setText(u.getDireccion());
						jTextField2.setText(u.getPassword());
						
						
					}
				});
			}
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("Nombre");
				jLabel1.setBounds(21, 101, 44, 16);
			}
			{
				jTextField1 = new JTextField();
				getContentPane().add(jTextField1);
				jTextField1.setBounds(151, 98, 130, 23);
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2);
				jLabel2.setText("Password");
				jLabel2.setBounds(28, 154, 50, 16);
			}
			{
				jTextField2 = new JTextField();
				getContentPane().add(jTextField2);
				jTextField2.setBounds(151, 151, 130, 23);
			}
			pack();
			this.setSize(400, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
