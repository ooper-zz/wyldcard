package hypercard.parts.fields;

import hypercard.context.ToolsContext;
import hypercard.gui.util.*;
import hypercard.parts.ToolEditablePart;
import hypercard.parts.fields.styles.OpaqueField;
import hypercard.parts.fields.styles.TransparentField;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;

public abstract class AbstractFieldView implements ToolEditablePart {

    private FieldView fieldView;
    private boolean isBeingEdited;

    public abstract void move();

    public abstract void resize(int fromQuadrant);

    public abstract void invalidateSwingComponent(Component oldComponent, Component newComponent);

    public AbstractFieldView(FieldStyle style) {
        fieldView = getComponentForStyle(style);
    }

    @Override
    public boolean isBeingEdited() {
        Window ancestorWindow = SwingUtilities.getWindowAncestor(getFieldView());
        return ancestorWindow != null && ancestorWindow.isActive() && isBeingEdited;
    }

    @Override
    public void setBeingEdited(boolean beingEdited) {
        fieldView.setEditable(!beingEdited);
        isBeingEdited = beingEdited;
    }

    public void setFieldStyle(FieldStyle style) {
        Component oldComponent = getFieldView();
        fieldView = getComponentForStyle(style);

        partClosed();
        invalidateSwingComponent(oldComponent, (JComponent) fieldView);
        partOpened();
    }

    private FieldView getComponentForStyle(FieldStyle style) {
        switch (style) {
            case TRANSPARENT:
                return new TransparentField(this);
            case OPAQUE:
                return new OpaqueField(this);

            default:
                throw new IllegalArgumentException("No such field style: " + style);
        }
    }

    /**
     * Gets the Swing component representing the field as a whole; this is typically a JTextComponent plus some other
     * hierarchy (like a scroll pane).
     *
     * @return
     */
    public JComponent getFieldView() {
        return (JComponent) fieldView;
    }

    public JTextComponent getTextComponent() {
        return fieldView.getTextComponent();
    }

    public String getText() {
        return fieldView.getText();
    }

    @Override
    public void partOpened() {
        getPartModel().addPropertyChangedObserver(fieldView);
        ToolsContext.getInstance().getToolModeProvider().addObserverAndUpdate((o, arg) -> onToolModeChanged());
        KeyboardManager.addGlobalKeyListener(this);

        fieldView.partOpened();
    }

    @Override
    public void partClosed() {
        getPartModel().removePropertyChangedObserver(fieldView);
        KeyboardManager.removeGlobalKeyListener(this);
    }

}