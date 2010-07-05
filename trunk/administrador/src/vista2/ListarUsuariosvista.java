package vista2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;


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
public class ListarUsuariosvista extends javax.swing.JFrame implements Observer,ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8792032904065150762L;
	private JTextArea jTextArea1;
	private ControladorGestion c;
	private static ListarUsuariosvista instancia=null;
	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		ListarUsuariosvista inst = new ListarUsuariosvista();
		inst.setVisible(true);
	}
	
	public ListarUsuariosvista() {
		super();
		initGUI();
	}
	public static ListarUsuariosvista getinstancia(boolean b){
		if(instancia==null){
			
			instancia=new ListarUsuariosvista();
		}
	return instancia;
	
	
	
	}
	private void initGUI() {
		try {
setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			this.setTitle("Alta usuario");
			{
				jTextArea1 = new JTextArea();
				getContentPane().add(jTextArea1);
				jTextArea1.setText("Listado");
				jTextArea1.setBounds(12, 12, 367, 250);
			
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
		jTextArea1.setText("");
		Collection<UsuarioVO> usuarios = c.obtenerUsuarios();
		for (UsuarioVO usuario : usuarios) {
			jTextArea1.append(usuario.toString() + "\n");
		}
		
	}

}

