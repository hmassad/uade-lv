package vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import beans.LogTraficoVO;
import controlador.ControladorGestion;

public class LogsPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private ControladorGestion controladorGestion;
	private JTable table;
	private JButton eliminarButton;
	
	public LogsPanel(ControladorGestion controladorGestion){
		super();
		setControladorGestion(controladorGestion);
		initGUI();
	}

	public ControladorGestion getControladorGestion() {
		return controladorGestion;
	}

	public void setControladorGestion(ControladorGestion controladorGestion) {
		this.controladorGestion = controladorGestion;
	}

	class LogTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		private String[] columnNames = { "Fecha", "Orgien", "Destinos" };
		private Object[][] data;

		public LogTableModel() {
			Collection<LogTraficoVO> logs = getControladorGestion().obtenerLogs();
			if (logs != null) {
				data = new Object[logs.size()][columnNames.length];
				int i = 0;
				for (LogTraficoVO l : logs) {
					data[i][0] = l.getFecha();
					data[i][1] = l.getOrigen();
					data[i][2] = l.getDestinos().toString();
					i++;
				}
			}
		}

		public int getColumnCount() {
			return columnNames.length;
		}

		public int getRowCount() {
			return data.length;
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}

		public Object getValueAt(int row, int col) {
			return data[row][col];
		}

		@SuppressWarnings("unchecked")
		public Class getColumnClass(int c) {
			if (data.length > 0) {
				return getValueAt(0, c).getClass();
			} else {
				return Object.class;
			}
		}

		public boolean isCellEditable(int row, int col) {
			return false;
		}
	}

	private void initGUI(){
		this.setLayout(new BorderLayout());
		
		JPanel tablePane = new JPanel();
		tablePane.setLayout(new BoxLayout(tablePane, BoxLayout.Y_AXIS));
		JLabel label = new JLabel();
		label.setText("Logs");
		tablePane.add(label);
		tablePane.add(Box.createRigidArea(new Dimension(0, 5)));

		table = new JTable();
		table.setModel(new LogTableModel());
		
		JScrollPane tableScroller = new JScrollPane(table);

		tablePane.add(tableScroller);
		tablePane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Lay out the buttons from left to right.
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		buttonPane.add(Box.createHorizontalGlue());

		eliminarButton = new JButton();
		eliminarButton.setText("Eliminar");
		eliminarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				getControladorGestion().borrarLogs();
			}
		});
		buttonPane.add(eliminarButton);

		this.add(tablePane, BorderLayout.CENTER);
		this.add(buttonPane, BorderLayout.PAGE_END);
	}
}
