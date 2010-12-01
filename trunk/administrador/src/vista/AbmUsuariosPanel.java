package vista;
//Panel
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import rmi.observer.EventoObservable;
import beans.UsuarioVO;
import controlador.ControladorGestion;

public class AbmUsuariosPanel extends AbmBasePanel {

	private static final long serialVersionUID = 1L;

	public AbmUsuariosPanel(ControladorGestion controladorGestion) {
		super(controladorGestion);
	}

	@Override
	protected TableModel getTableModel() {
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
				JOptionPane.showMessageDialog(null, String.format("Ocurri� un error al listar Usuarios.\n\"%s\"", e1.getMessage()), "Error", JOptionPane.ERROR_MESSAGE);
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

		public Class<?> getColumnClass(int c) {
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

	@Override
	protected String getTitulo() {
		return "Usuarios";
	}

	@Override
	protected ActionListener getAgregarActionListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new AgregarUsuarioDialog(getControladorGestion());
			}
		};
	}

	@Override
	protected ActionListener getBorrarActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object[] row = getSelectedRow();
				if (row != null) {
					int id = (Integer) row[0];
					try {
						if (JOptionPane.showConfirmDialog(null, "�Est� seguro de eliminar el Usuario? Se eliminar�n todas las Casillas y sus Mensajes.", "Confirmaci�n", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
							getControladorGestion().borrarUsuario(id);
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(AbmUsuariosPanel.this, String.format("Ocurri� un error al eliminar el Usuario.\n\"%s\"", e1.getMessage()), "Error", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
				}
			}
		};
	}

	@Override
	protected ActionListener getModificarActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Object[] row = getSelectedRow();
				if (row != null) {
					new ModificarUsuarioDialog(getControladorGestion(), (Integer) row[0], (String) row[1]);
				}
			}
		};
	}

	@Override
	protected Collection<JButton> getBotonesAdicionales() {
		Collection<JButton> botones = new ArrayList<JButton>();
		JButton resetearContrase�aButton = new JButton();
		resetearContrase�aButton.setText("Resetear Contrase�a");
		resetearContrase�aButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				Object[] row = getSelectedRow();
				if (row != null) {
					String nuevaContrase�a;
					try {
						nuevaContrase�a = getControladorGestion().resetearContrase�a((Integer)row[0]);
						JOptionPane.showMessageDialog(AbmUsuariosPanel.this, String.format("La nueva contrase�a es \"%s\".", nuevaContrase�a), "Information", JOptionPane.INFORMATION_MESSAGE);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(AbmUsuariosPanel.this, String.format("Ocurri� un error al resetear la contrase�a.\n\"%s\"", e.getMessage()), "Error", JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					}
				}
			}
		});
		botones.add(resetearContrase�aButton);
		return botones;
	}

	@Override
	protected boolean getCondicionActualizacion(Object eventoObservable) {
		return eventoObservable.equals(EventoObservable.Usuarios);
	}
}
