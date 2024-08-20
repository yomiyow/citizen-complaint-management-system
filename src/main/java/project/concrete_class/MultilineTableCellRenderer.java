package project.concrete_class;

import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;

/**
 *
 * This class is used to modify
 * table cell so that as cell resize
 * the content shrink line by line
 */

public class MultilineTableCellRenderer extends JTextArea 
                                        implements TableCellRenderer{ 
    
    public MultilineTableCellRenderer() {
        setWrapStyleWord(true);
        setLineWrap(true);
        setOpaque(true);
        setRows(2);
        Border bottomColorBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#ebebeb"));
        setBorder(bottomColorBorder);
        
    }    

    @Override
    public Component getTableCellRendererComponent(
                            JTable table, Object value, 
                            boolean isSelected, boolean hasFocus, 
                            int row, int column) {
        
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
        } else {
            setForeground(table.getForeground());
            setBackground(table.getBackground());
        }

        setFont(table.getFont());
        setText((value == null) ? "" : value.toString());
        
        setSize(table.getColumnModel().getColumn(column).getWidth(),
                getPreferredSize().height);
        if (table.getRowHeight(row) != getPreferredSize().height) {
            table.setRowHeight(row, getPreferredSize().height);
        }
        
        
        return this;
        
    }
    
}
