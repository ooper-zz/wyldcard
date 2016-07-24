package hypercard.gui.window;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import hypercard.context.GlobalContext;
import hypercard.gui.HyperCardWindow;
import hypercard.parts.CardPart;
import hypercard.runtime.Interpreter;
import hypercard.runtime.RuntimeEnv;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class StackWindow implements HyperCardWindow {

    private CardPart card;

    private JPanel cardPanel;
    private JPanel stackWindow;

    public CardPart getCurrentCard() {
        return card;
    }

    public void setCurrentCard(CardPart card) {
        this.card = card;
        cardPanel.removeAll();
        cardPanel.add(card);

        stackWindow.invalidate();
        stackWindow.validate();
        stackWindow.repaint();
    }

    @Override
    public JPanel getWindowPanel() {
        return stackWindow;
    }

    @Override
    public void bindModel(Object data) {
        if (data instanceof CardPart) {
            setCurrentCard((CardPart) data);
        } else {
            throw new RuntimeException("Bug! Don't know how to bind data class to window." + data);
        }
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        stackWindow = new JPanel();
        stackWindow.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        cardPanel = new JPanel();
        cardPanel.setLayout(new BorderLayout(0, 0));
        stackWindow.add(cardPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(640, 480), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return stackWindow;
    }
}
