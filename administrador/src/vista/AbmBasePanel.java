package vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import controlador.ControladorGestion;

public abstract class AbmBasePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private ControladorGestion controladorGestion;

	private JTable table;
	private JButton agregarButton;
	private JButton modificarButton;
	private JButton eliminarButton;

	public AbmBasePanel(ControladorGestion controladorGestion) {
		super();
		this.controladorGestion = controladorGestion;
		initGUI();
	}

	protected void initGUI() {
		this.setLayout(new BorderLayout());

		JPanel tablePane = new JPanel();
		tablePane.setLayout(new BoxLayout(tablePane, BoxLayout.Y_AXIS));
		JLabel label = new JLabel();
		label.setText(getTitulo());
		tablePane.add(label);
		tablePane.add(Box.createRigidArea(new Dimension(0, 5)));

		table = new JTable();
		table.setModel(getTableModel());
		JScrollPane tableScroller = new JScrollPane(table);

		tablePane.add(tableScroller);
		tablePane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Lay out the buttons from left to right.
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

		eliminarButton = new JButton();
		eliminarButton.setText("Eliminar");
		eliminarButton.addActionListener(getBorrarActionListener());
		buttonPane.add(eliminarButton);

		// Put everything together
		this.add(tablePane, BorderLayout.CENTER);
		this.add(buttonPane, BorderLayout.PAGE_END);
	}

	protected ControladorGestion getControladorGestion() {
		return controladorGestion;
	}

	protected Object[] getSelectedRow() {
		if (table.getSelectedRow() == -1) {
			return null;
		}

		Vector<Object> row = new Vector<Object>();
		for (int i = 0; i < table.getModel().getColumnCount(); i++) {
			row.add(table.getModel().getValueAt(table.getSelectedRow(), i));
		}
		return row.toArray();
	}

	protected abstract String getTitulo();

	protected abstract TableModel getTableModel();

	protected abstract ActionListener getAgregarActionListener();

	protected abstract ActionListener getModificarActionListener();

	protected abstract ActionListener getBorrarActionListener();
}
