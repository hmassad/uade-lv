package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import remoteObserver.EventoObservable;
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
			try {
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
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, String.format("Ocurrió un error al listar Relaciones de Confianza.\n\"%s\"", e1.getMessage()), "Error", JOptionPane.ERROR_MESSAGE);
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
		return "Relaciones de Confianza";
	}

	@Override
	public ActionListener getAgregarActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new AgregarRelacionConfianzaDialog(getControladorGestion());
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
					try {
						if (JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar la Relación de Confianza?", "Confirmación", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
							getControladorGestion().eliminarRelacionConfianza(idOficinaOrigen, idOficinaDestino);
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, String.format("Ocurrió un error al eliminar la Relación de Confianza.\n\"%s\"", e1.getMessage()), "Error", JOptionPane.ERROR_MESSAGE);
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
				// TODO: Abrir Ventana de Modificar Relación Confianza
			}
		};
	}

	@Override
	protected JButton[] getBotonesAdicionales() {
		return null;
	}

	@Override
	protected boolean getCondicionActualizacion(Object eventoObservable) {
		return eventoObservable.equals(EventoObservable.Oficinas) || eventoObservable.equals(EventoObservable.RelacionesConfianza);
	}
}
