package com.myticketsystem.hys;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

import com.alee.laf.table.WebTable;

public class SeatTable extends WebTable {

	private static class SeatRenderer extends DefaultTableCellRenderer {

		Color backgroundColor = getBackground();

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			setBorder(noFocusBorder);
			if (column == 0) {
				// c = super.getTableCellRendererComponent(table, value, false,
				// hasFocus, row, column);
				// return c;
				c.setBackground(backgroundColor);

				Font tempFont = c.getFont(); // .deriveFont(Font.BOLD);
				c.setForeground(Color.BLACK);
				c.setFont(tempFont);

				return c;
			}

			if ((String) seatData[row][column] == "taken") {
				c.setBackground(Helper.darkenColor(Color.decode("#FFCAD4"), 0.03f * 4));
			} else if (isSelected && hasFocus) {
				// c.setBackground(Color.decode("#D8E2DC"));
				c.setBackground(Helper.darkenColor(Color.decode("#D8E2DC"), 0.03f * 4));
			} else {
				c.setBackground(backgroundColor);
			}

			return c;
		}
	}

	static class SeatTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L; // surpresses stupid warning ....

		private final String[] RowNames = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
				"P", "Q", "R", "S", "T", "U", "V", "W" };

		private final int seatColumns = 16;
		private final int seatRows = RowNames.length;

		public SeatTableModel() {
			seatData = new Object[seatColumns][seatRows];

			for (int i = 0; i < seatColumns; i++) {
				for (int j = 0; j < seatRows; j++) {
					if (j == 0) {
						seatData[i][j] = String.valueOf(i + 1);
					} else {
						seatData[i][j] = Helper.getSeatFillStatus();

					}
				}
			}
		}

		@Override
		public Class getColumnClass(int c) {
			return String.class;
		}

		@Override
		public int getColumnCount() {
			return RowNames.length;
		}

		@Override
		public String getColumnName(int col) {
			if (col == 0) {
				return "";
			}
			return String.valueOf(col); // columnNames[ col ];
		}

		@Override
		public int getRowCount() {
			return seatData.length;
		}

		@Override
		public Object getValueAt(int row, int col) {
			if (col == 0 && row >= 0) {
				return RowNames[row];// data[row][col];
			}
			return ""; // data[ row ][ col ];
		}

		@Override
		public boolean isCellEditable(int row, int col) {
			return false; // (boolean)data[row][col]==true;
		}

		@Override
		public void setValueAt(Object value, int row, int col) {
			seatData[row][col] = String.valueOf(col);
			fireTableCellUpdated(row, col);
		}
	}

	public static Object[][] seatData;

	public SeatTable() {
		super(new SeatTableModel());
		super.setDefaultRenderer(String.class, new SeatRenderer());
		super.setRowHeight(28);
		super.setColumnSelectionAllowed(false);
		TableColumnModel tcm = super.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(20);
		// tcm.getColumn(0).set
		super.getTableHeader().setReorderingAllowed(false);
		super.getTableHeader().setResizingAllowed(false);
	}

	public boolean isSeatAvailable(int col, int row) {
		if (row == 0 || col > (seatData.length - 1) || col < 0 || row < 0 || row > (seatData[0].length - 1) || seatData[col][row] == "taken") {
			return false;
		}
		return true;
	}

}