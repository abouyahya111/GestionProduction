package util;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;


public class ComboBoxRenderer extends JPanel implements ListCellRenderer<Object> {
    private JCheckBox checkBox;
    private JTextField textField;

    public ComboBoxRenderer() {
        setLayout(new BorderLayout());
        checkBox = new JCheckBox();
        textField = new JTextField();
        textField.setEditable(true);
        add(checkBox, BorderLayout.WEST);
        add(textField, BorderLayout.CENTER);
    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if (value != null) {
            checkBox.setSelected(false);
            textField.setText(value.toString());
        }
        return this;
    }
}