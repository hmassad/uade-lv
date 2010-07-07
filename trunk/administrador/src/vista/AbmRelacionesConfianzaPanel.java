package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import beans.RelacionConfianzaVO;
import controlador.ControladorGestion;

public class AbmRelacionesConfianzaPanel extends AbmBasePanel {

	private static final long serialVersionUID = 1L;

	public AbmRelacionesConfianzaPanel(ControladorGestion controladorGestion) {
		super(controladorGestion);
	}

	@Override
	public TableModel getTableModel() {
		return new RelacionesConfianzaTableModel();
	}

	class RelacionesConfianzaTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		private String[] columnNames = { "ID Oficina Origen", "Nombre Oficina Origen", "ID Oficina Destino", "Nombre Oficina Destino" };
		private Object[][] data;

		public RelacionesConfianzaTableModel() {
			Collection<RelacionConfianzaVO> relacionesConfianza = getControladorGestion().obtenerRelacionesConfianza();
			if (relacionesConfianza != null) {
				data = new Object[relacionesConfianza.size()][columnNames.length];
				int i = 0;
				for (RelacionConfianzaVO rc : relacionesConfianza) {
					data[i][0] = rc.getOrigen().getId();
					data[i][1] = rc.getOrigen().getNombre();
					data[i][2] = rc.getDestino().getId();
					data[i][3] = rc.getDestino().getNombre();
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

		@SuppressWarnings({ "unchecked", "rawtypes" })
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

	protected String getTitulo() {
		return "Relaciones de Confianza";
	}

	@Override
	public ActionListener getAgregarActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO: Abrir Ventana de Agregar Relación Confianza
			}
		};
	}

	@Override
	public ActionListener getBorrarActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object[] row = getSelectedRow();
				if (row != null) {
					int idOficinaOrigen = (Integer) row[0];
					int idOficinaDestino = (Integer) row[2];
					getControladorGestion().eliminarRelacionConfianza(idOficinaOrigen, idOficinaDestino);
				}
			}
		};
	}

	@Override
	public ActionListener getModificarActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO: Abrir Ventana de Modificar Relación Confianza
			}
		};
	}

}
