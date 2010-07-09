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
import beans.OficinaVO;
import controlador.ControladorGestion;

public class AbmOficinasPanel extends AbmBasePanel {

	private static final long serialVersionUID = 1L;

	public AbmOficinasPanel(ControladorGestion controladorGestion) {
		super(controladorGestion);
	}

	@Override
	public TableModel getTableModel() {
		return new OficinasTableModel();
	}

	class OficinasTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		private String[] columnNames = { "id", "Nombre" };
		private Object[][] data;

		public OficinasTableModel() {
			try {
				Collection<OficinaVO> oficinas = getControladorGestion().obtenerOficinas();
				if (oficinas != null) {
					data = new Object[oficinas.size()][columnNames.length];
					int i = 0;
					for (OficinaVO o : oficinas) {
						data[i][0] = o.getId();
						data[i][1] = o.getNombre();
						i++;
					}
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, String.format("Ocurrió un error al listar Oficinas.\n\"%s\"", e1.getMessage()), "Error", JOptionPane.ERROR_MESSAGE);
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
		return "Oficinas";
	}

	@Override
	public ActionListener getAgregarActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new AgregarOficinaDialog(getControladorGestion());
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
						if (JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar la Oficina? Se eliminarán todas las Relaciones de Confianza.", "Confirmación", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
							getControladorGestion().borrarOficina(id);
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
				// TODO: Abrir Ventana de Modificar Oficina
			}
		};
	}

	@Override
	protected Collection<JButton> getBotonesAdicionales() {
		return new ArrayList<JButton>();
	}

	@Override
	protected boolean getCondicionActualizacion(Object eventoObservable) {
		return eventoObservable.equals(EventoObservable.Oficinas);
	}
}
