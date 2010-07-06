package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import beans.CasillaVO;
import controlador.ControladorGestion;

public class AbmCasillas extends AbmBase {

	private static final long serialVersionUID = 1L;

	public AbmCasillas(ControladorGestion controladorGestion) {
		super(controladorGestion);
	}

	@Override
	public TableModel getTableModel() {
		return new CasillasTableModel();
	}

	class CasillasTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		private String[] columnNames = { "id", "Dirección" };
		private Object[][] data;

		public CasillasTableModel() {
			Collection<CasillaVO> casillas = getControladorGestion().obtenerCasillas();
			if (casillas != null) {
				data = new Object[casillas.size()][columnNames.length];
				int i = 0;
				for (CasillaVO u : casillas) {
					data[i][0] = u.getId();
					data[i][1] = u.getDireccion();
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

	protected String getTitulo() {
		return "Casillas";
	}

	@Override
	public ActionListener getAgregarActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new AgregarCasillaDialog(getControladorGestion());
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
					int id = (Integer) row[0];
					getControladorGestion().eliminarCasilla(id);
				}
			}
		};
	}

	@Override
	public ActionListener getModificarActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO: Abrir Ventana de Modificar Casilla
			}
		};
	}

}
