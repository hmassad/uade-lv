package vista2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.WindowConstants;


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
public class AltaRelacionConfianzavista extends javax.swing.JFrame implements Observer,ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8792032904065150762L;
	private JLabel jLabel1;
	private JTextField jTextField1;
	private JButton jButton1;
	private JTextField jTextField2;
	private JLabel jLabel2;
	private ControladorGestion c;
	private static AltaRelacionConfianzavista instancia=null;
	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		AltaRelacionConfianzavista inst = new AltaRelacionConfianzavista();
		inst.setVisible(true);
	}
	
	public AltaRelacionConfianzavista() {
		super();
		initGUI();
	}
	public static AltaRelacionConfianzavista getinstancia(boolean b){
		if(instancia==null){
			
			instancia=new AltaRelacionConfianzavista();
		}
	return instancia;
	
	
	
	}
	private void initGUI() {
		try {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			this.setTitle("Alta Relacion Confianza");
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("Casilla Origen");
				jLabel1.setBounds(12, 69, 95, 30);
			}
			{
				jTextField1 = new JTextField();
				getContentPane().add(jTextField1);
				jTextField1.setBounds(146, 74, 105, 21);
			}
			{
				jButton1 = new JButton();
				getContentPane().add(jButton1);
				jButton1.setText("Aceptar");
				jButton1.setBounds(141, 178, 105, 28);
				jButton1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						System.out.println("jButton1.actionPerformed, event="+evt);
						//TODO add your code for jButton1.actionPerformed
						
						c.agregarRelacionConfianza(Integer.parseInt(jTextField1.getText()),Integer.parseInt(jTextField2.getText()));					}
				});
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2);
				jLabel2.setText("Casilla Destino");
				jLabel2.setBounds(12, 116, 86, 16);
			}
			{
				jTextField2 = new JTextField();
				getContentPane().add(jTextField2);
				jTextField2.setBounds(146, 113, 105, 23);
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
