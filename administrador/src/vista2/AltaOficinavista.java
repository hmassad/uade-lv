package vista2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.WindowConstants;


import beans.OficinaVO;

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
public class AltaOficinavista extends javax.swing.JFrame implements Observer,ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8792032904065150762L;
	private JButton jButton1;
	private JTextField jTextField3;
	private JLabel jLabel3;
	private ControladorGestion c;
	private static AltaOficinavista instancia=null;
	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		AltaOficinavista inst = new AltaOficinavista();
		inst.setVisible(true);
	}
	
	public AltaOficinavista() {
		super();
		initGUI();
	}
	public static AltaOficinavista getinstancia(boolean b){
		if(instancia==null){
			
			instancia=new AltaOficinavista();
		}
	return instancia;
	
	
	
	}
	private void initGUI() {
		try {
setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			this.setTitle("Alta Oficina");
			{
				jButton1 = new JButton();
				getContentPane().add(jButton1);
				jButton1.setText("Aceptar");
				jButton1.setBounds(138, 166, 105, 26);
				jButton1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						System.out.println("jButton1.actionPerformed, event="+evt);
						//TODO add your code for jButton1.actionPerformed
						
						OficinaVO u= new OficinaVO();
						u.setNombre(jTextField3.getText());				
						c.agregarOficina(u);
					}
				});
			}
			{
				jLabel3 = new JLabel();
				getContentPane().add(jLabel3);
				jLabel3.setText("Nombre");
				jLabel3.setBounds(52, 58, 60, 16);
			}
			{
				jTextField3 = new JTextField();
				getContentPane().add(jTextField3);
				jTextField3.setBounds(151, 55, 105, 23);
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

