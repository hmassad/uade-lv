package vista;
//casillaspanel
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import rmi.observer.EventoObservable;
import beans.CasillaVO;
import controlador.ControladorGestion;

public class AbmCasillasPanel extends AbmBasePanel {

	private static final long serialVersionUID = 1L;

	class CasillasTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		private String[] columnNames = { "id", "Dirección" };
		private Object[][] data;

		public CasillasTableModel() {
			try {
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
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, String.format("Ocurrió un error al listar Casillas.\n\"%s\"", e1.getMessage()), "Error", JOptionPane.ERROR_MESSAGE);
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

	public AbmCasillasPanel(ControladorGestion controladorGestion) {
		super(controladorGestion);
	}

	@Override
	protected TableModel getTableModel() {
		return new CasillasTableModel();
	}

	@Override
	protected String getTitulo() {
		return "Casillas";
	}

	@Override
	protected ActionListener getAgregarActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new AgregarCasillaDialog(getControladorGestion());
			}
		};
	}

	@Override
	protected ActionListener getBorrarActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				Object[] row = getSelectedRow();
				if (row != null) {
					int id = (Integer) row[0];
					try {
						if (JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar la Casilla? Se borrarán todos los mensajes.", "Confirmación", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
							getControladorGestion().borrarCasilla(id);
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
	protected ActionListener getModificarActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Object[] row = getSelectedRow();
				if (row != null) {
					new ModificarCasillaDialog(getControladorGestion(), (Integer) row[0], (String) row[1]);
				}
			}
		};
	}

	@Override
	protected Collection<JButton> getBotonesAdicionales() {
		Collection<JButton> botones = new ArrayList<JButton>();
		JButton agregarAOficinaButton = new JButton();
		agregarAOficinaButton.setText("Agregar A Oficina");
		agregarAOficinaButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				Object[] row = getSelectedRow();
				if (row != null) {
					int idCasilla = (Integer) row[0];
					String direccionCasilla = (String) row[1];
					new AgregarCasillaAOficinaDialog(getControladorGestion(), idCasilla, direccionCasilla);
				}

			}
		});
		botones.add(agregarAOficinaButton);
		JButton borrarDeOficinaButton = new JButton();
		borrarDeOficinaButton.setText("Borrar De Oficina");
		borrarDeOficinaButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				Object[] row = getSelectedRow();
				if (row != null) {
					int idCasilla = (Integer) row[0];
					String direccionCasilla = (String) row[1];
					new BorrarCasillaDeOficinaDialog(getControladorGestion(), idCasilla, direccionCasilla);
				}

			}
		});
		botones.add(borrarDeOficinaButton);
		return botones;
	}

	@Override
	protected boolean getCondicionActualizacion(Object eventoObservable) {
		return eventoObservable.equals(EventoObservable.Usuarios) || eventoObservable.equals(EventoObservable.Casillas);
	}
}
