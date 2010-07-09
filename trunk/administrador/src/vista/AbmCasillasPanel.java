package vista;

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

		private String[] columnNames = { "id", "Direcci�n" };
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
				JOptionPane.showMessageDialog(null, String.format("Ocurri� un error al listar Casillas.\n\"%s\"", e1.getMessage()), "Error", JOptionPane.ERROR_MESSAGE);
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

	public AbmCasillasPanel(ControladorGestion controladorGestion) {
		super(controladorGestion);
	}

	@Override
	public TableModel getTableModel() {
		return new CasillasTableModel();
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
			public void actionPerformed(ActionEvent ae) {
				Object[] row = getSelectedRow();
				if (row != null) {
					int id = (Integer) row[0];
					try {
						if (JOptionPane.showConfirmDialog(null, "�Est� seguro de eliminar la Casilla? Se borrar�n todos los mensajes.", "Confirmaci�n", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
							getControladorGestion().borrarCasilla(id);
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, String.format("Ocurri� un error al eliminar la Casilla.\n\"%s\"", e1.getMessage()), "Error", JOptionPane.ERROR_MESSAGE);
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
				// TODO: Abrir Ventana de Modificar Casilla
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
