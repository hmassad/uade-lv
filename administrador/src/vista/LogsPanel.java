package vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import remoteObserver.EventoObservable;
import beans.LogTraficoVO;
import controlador.ControladorGestion;

public class LogsPanel extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;

	private ControladorGestion controladorGestion;
	private JTable logTable;
	private JButton borrarButton;

	public LogsPanel(ControladorGestion controladorGestion) {
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
			try {
				Collection<LogTraficoVO> logs = getControladorGestion().obtenerLogsTrafico();
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
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, String.format("Ocurrió un error al listar Logs.\n\"%s\"", e1.getMessage()), "Error", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
		}

		public int getColumnCount() {
			return columnNames.length;
		}

		public int getRowCount() {
			if (data == null)
				return -1;
			return data.length;
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}

		public Object getValueAt(int row, int col) {
			if (data == null)
				return null;
			return data[row][col];
		}

		@SuppressWarnings("unchecked")
		public Class getColumnClass(int c) {
			if (data == null)
				return null;
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

	private void initGUI() {
		this.setLayout(new BorderLayout());

		JPanel tablePane = new JPanel();
		tablePane.setLayout(new BoxLayout(tablePane, BoxLayout.Y_AXIS));
		JLabel label = new JLabel();
		label.setText("Logs");
		tablePane.add(label);
		tablePane.add(Box.createRigidArea(new Dimension(0, 5)));

		logTable = new JTable();
		logTable.setModel(new LogTableModel());

		JScrollPane tableScroller = new JScrollPane(logTable);

		tablePane.add(tableScroller);
		tablePane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Lay out the buttons from left to right.
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		buttonPane.add(Box.createHorizontalGlue());

		borrarButton = new JButton();
		borrarButton.setText("Borrar");
		borrarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					getControladorGestion().borrarLogsTrafico();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, String.format("Ocurrió un error al listar Logs.\n\"%s\"", e1.getMessage()), "Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		buttonPane.add(borrarButton);

		this.add(tablePane, BorderLayout.CENTER);
		this.add(buttonPane, BorderLayout.PAGE_END);
	}

	@Override
	public void update(Observable o, Object eventoOvservable) {
		if (eventoOvservable.equals(EventoObservable.LogTrafico)) {
			logTable.setModel(new LogTableModel());
		}
	}
}
