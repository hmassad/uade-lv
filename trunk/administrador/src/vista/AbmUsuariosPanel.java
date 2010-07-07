package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import controlador.ControladorGestion;

import beans.UsuarioVO;

public class AbmUsuariosPanel extends AbmBasePanel {

	private static final long serialVersionUID = 1L;

	public AbmUsuariosPanel(ControladorGestion controladorGestion) {
		super(controladorGestion);
	}

	@Override
	public TableModel getTableModel() {
		return new UsuariosTableModel();
	}

	class UsuariosTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		private String[] columnNames = { "id", "Nombre" };
		private Object[][] data;

		public UsuariosTableModel() {
			Collection<UsuarioVO> usuarios = getControladorGestion().obtenerUsuarios();
			if (usuarios != null) {
				data = new Object[usuarios.size()][columnNames.length];
				int i = 0;
				for (UsuarioVO u : usuarios) {
					data[i][0] = u.getId();
					data[i][1] = u.getNombre();
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
		return "Usuarios";
	}

	@Override
	public ActionListener getAgregarActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new AgregarUsuarioDialog(getControladorGestion());
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
					getControladorGestion().eliminarUsuario(id);
				}
			}
		};
	}

	@Override
	public ActionListener getModificarActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO: Abrir Ventana de Modificar Usuario
			}
		};
	}

}
