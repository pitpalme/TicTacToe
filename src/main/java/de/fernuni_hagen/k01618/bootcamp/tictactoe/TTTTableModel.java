package de.fernuni_hagen.k01618.bootcamp.tictactoe;

import javax.swing.table.AbstractTableModel;

import de.fernuni_hagen.k01618.IBrettspielstellung;

public class TTTTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;

    IBrettspielstellung delegate;

    public TTTTableModel(final IBrettspielstellung delegate) {
        this.delegate = delegate;
    }

    @Override
    public int getRowCount() {
        return 3;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(final int rowIndex, final int columnIndex) {
        return delegate.getFeldZustand(columnIndex, rowIndex);
    }

    @Override
    public boolean isCellEditable(final int rowIndex,
            final int columnIndex) {
        return true;
    }

    @Override
    public void setValueAt(final Object aValue, final int rowIndex,
            final int columnIndex) {
        delegate.setFeldZustand(columnIndex, rowIndex,
                TTTZustand.N.parseValue((String) aValue));
    }
}
