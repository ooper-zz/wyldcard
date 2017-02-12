package hypercard.parts.buttons.styles;

import com.defano.jmonet.tools.util.MarchingAnts;
import hypercard.context.ToolMode;
import hypercard.context.ToolsContext;
import hypercard.parts.ToolEditablePart;
import hypercard.parts.buttons.ButtonView;
import hypercard.parts.model.ButtonModel;
import hypercard.utils.FontUtils;
import hypertalk.ast.common.Value;

import javax.swing.*;
import java.awt.*;


public class MenuButton extends JComboBox implements ButtonView {

    private final ToolEditablePart toolEditablePart;
    private final DefaultComboBoxModel<String> menuItems = new DefaultComboBoxModel();

    public MenuButton(ToolEditablePart toolEditablePart) {
        this.toolEditablePart = toolEditablePart;

        super.addActionListener(toolEditablePart);
        super.addKeyListener(toolEditablePart);
        for (Component thisComponent : super.getComponents()) {
            thisComponent.addMouseListener(toolEditablePart);
        }

        ToolsContext.getInstance().getToolModeProvider().addObserverAndUpdate((o, arg) -> setEnabled(ToolMode.BUTTON != arg));

        setModel(menuItems);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        toolEditablePart.drawSelectionRectangle(g);
    }

    @Override
    public void onPropertyChanged(String property, Value oldValue, Value newValue) {
        switch (property) {
            case ButtonModel.PROP_CONTENTS:
                menuItems.removeAllElements();
                for (String thisItem : newValue.stringValue().split("\n")) {
                    menuItems.addElement(thisItem);
                }
                break;

            case ButtonModel.PROP_TEXTSIZE:
                setFont(new Font(getFont().getFamily(), getFont().getStyle(), newValue.integerValue()));
                break;

            case ButtonModel.PROP_TEXTFONT:
                setFont(new Font(newValue.stringValue(), getFont().getStyle(), getFont().getSize()));
                break;

            case ButtonModel.PROP_TEXTSTYLE:
                setFont(new Font(newValue.stringValue(), FontUtils.getStyleForValue(newValue), getFont().getSize()));
                break;
        }
    }
}
