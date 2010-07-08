package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import remoteObserver.EventoObservable;
import beans.UsuarioVO;
import controlador.ControladorGestion;

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
			try {
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
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, String.format("Ocurrió un error al listar Usuarios.\n\"%s\"", e1.getMessage()), "Error", JOptionPane.ERROR_MESSAGE);
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

		@SuppressWarnings( { "unchecked" })
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
					try {
						if (JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el Usuario? Se eliminarán todas las Casillas y sus Mensajes.", "Confirmación", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
							getControladorGestion().eliminarUsuario(id);
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, String.format("Ocurrió un error al eliminar la Casilla.\n\"%s\"", e1.getMessage()), "Error", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
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

	@Override
	protected JButton[] getBotonesAdicionales() {
		return null;
	}

	@Override
	protected boolean getCondicionActualizacion(Object eventoObservable) {
		return eventoObservable.equals(EventoObservable.Usuarios);
	}
}
