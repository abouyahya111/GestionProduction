package util;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;

public class CheckBoxCellRenderer<E extends CheckableItem> implements ListCellRenderer<E> { private final JLabel label = new JLabel(" ");
private final JCheckBox check = new JCheckBox(" ");

@Override public Component getListCellRendererComponent(JList<? extends E> list, E value, int index, boolean isSelected, boolean cellHasFocus) {
  if (index < 0) {
    String txt = getCheckedItemString(list.getModel());
    label.setText(txt.isEmpty() ? " " : txt);
    return label;
  } else {
    check.setText(Objects.toString(value, ""));
    check.setSelected(value.isSelected());
    if (isSelected) {
      check.setBackground(list.getSelectionBackground());
      check.setForeground(list.getSelectionForeground());
    } else {
      check.setBackground(list.getBackground());
      check.setForeground(list.getForeground());
    }
    return check;
  }
}

private static <E extends CheckableItem> String getCheckedItemString(ListModel<E> model) {
		/*
		 * return IntStream.range(0, model.getSize()) .mapToObj(model::getElementAt)
		 * .filter(CheckableItem::isSelected) .map(Objects::toString) .sorted()
		 * .collect(Collectors.joining(", "));
		 */
   List<String> sl = new ArrayList<>();
  for (int i = 0; i < model.getSize(); i++) {
     CheckableItem v = model.getElementAt(i);
    if (v.isSelected()) {
       sl.add(v.toString());
    }
  }
 if (sl.isEmpty()) {
    return " "; // When returning the empty string, the height of JComboBox may become 0 in some cases.
  } else {
    return sl.stream().sorted().collect(Collectors.joining(", "));
   }
}}