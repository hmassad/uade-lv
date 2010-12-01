package vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import rmi.observer.EventoObservable;
import rmi.observer.LocalObserver;
import rmi.observer.RemoteObserverLocalObservable;
import controlador.ControladorGestion;

public abstract class AbmBasePanel extends JPanel implements LocalObserver {

	private static final long serialVersionUID = 1L;

	/**
	 * @uml.property  name="controladorGestion"
	 * @uml.associationEnd  
	 */
	private ControladorGestion controladorGestion;

	private JTable abmTable;
	private JButton agregarButton;
	private JButton modificarButton;
	private JButton borrarButton;

	public AbmBasePanel(ControladorGestion controladorGestion) {
		super();
		this.controladorGestion = controladorGestion;
		try {
			controladorGestion.addLocalObserver(this);
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(this, String.format("Ocurrió un error al suscribirse al Server.\n\"%s\"", e.getMessage()), "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		initGUI();
	}

	@Override
	protected void finalize() throws Throwable {
		controladorGestion.deleteLocalObserver(this);
		super.finalize();
	}

	protected void initGUI() {
		this.setLayout(new BorderLayout());

		JPanel tablePane = new JPanel();
		tablePane.setLayout(new BoxLayout(tablePane, BoxLayout.Y_AXIS));
		JLabel label = new JLabel();
		label.setText(getTitulo());
		tablePane.add(label);
		tablePane.add(Box.createRigidArea(new Dimension(0, 5)));

		abmTable = new JTable();
		abmTable.setModel(getTableModel());
		JScrollPane tableScroller = new JScrollPane(abmTable);

		tablePane.add(tableScroller);
		tablePane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		buttonPane.add(Box.createHorizontalGlue());

		agregarButton = new JButton();
		agregarButton.setText("Agregar");
		agregarButton.addActionListener(getAgregarActionListener());
		buttonPane.add(agregarButton);
		buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));

		modificarButton = new JButton();
		modificarButton.setText("Modificar");
		modificarButton.addActionListener(getModificarActionListener());
		buttonPane.add(modificarButton);
		buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));

		borrarButton = new JButton();
		borrarButton.setText("Borrar");
		borrarButton.addActionListener(getBorrarActionListener());
		buttonPane.add(borrarButton);

		for (JButton boton : getBotonesAdicionales()) {
			buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
			buttonPane.add(boton);
		}

		this.add(tablePane, BorderLayout.CENTER);
		this.add(buttonPane, BorderLayout.PAGE_END);
	}

	/**
	 * @return
	 * @uml.property  name="controladorGestion"
	 */
	protected ControladorGestion getControladorGestion() {
		return controladorGestion;
	}

	@Override
	public void update(RemoteObserverLocalObservable remoteObserverLocalObservable, EventoObservable eventoObservable) {
		if (getCondicionActualizacion(eventoObservable)) {
			actualizarTabla();
		}
	}

	private void actualizarTabla() {
		abmTable.setModel(getTableModel());
	}

	protected Object[] getSelectedRow() {
		if (abmTable.getSelectedRow() == -1) {
			return null;
		}

		Vector<Object> row = new Vector<Object>();
		for (int i = 0; i < abmTable.getModel().getColumnCount(); i++) {
			row.add(abmTable.getModel().getValueAt(abmTable.getSelectedRow(), i));
		}
		return row.toArray();
	}

	protected abstract String getTitulo();

	protected abstract TableModel getTableModel();

	protected abstract ActionListener getAgregarActionListener();

	protected abstract ActionListener getModificarActionListener();

	protected abstract ActionListener getBorrarActionListener();

	protected abstract boolean getCondicionActualizacion(Object eventoObservable);

	protected abstract Collection<JButton> getBotonesAdicionales();

}
