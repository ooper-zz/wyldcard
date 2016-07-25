package hypercard.gui.menu;

import hypercard.runtime.RuntimeEnv;

import javax.swing.*;

public class EditMenu extends JMenu {
    
    public EditMenu () {
        super("Edit");

        MenuItemBuilder.ofDefaultType()
                .named("Undo")
                .disabled()
                .withShortcut('Z')
                .build(this);

        this.addSeparator();

        MenuItemBuilder.ofDefaultType()
                .named("Cut")
                .disabled()
                .withShortcut('X')
                .build(this);

        MenuItemBuilder.ofDefaultType()
                .named("Copy")
                .disabled()
                .withShortcut('C')
                .build(this);

        MenuItemBuilder.ofDefaultType()
                .named("Paste")
                .disabled()
                .withShortcut('V')
                .build(this);

        MenuItemBuilder.ofDefaultType()
                .named("Clear")
                .disabled()
                .build(this);

        this.addSeparator();

        MenuItemBuilder.ofDefaultType()
                .named("New Card")
                .withAction(e -> RuntimeEnv.getRuntimeEnv().getStack().newCard())
                .withShortcut('N')
                .build(this);

        MenuItemBuilder.ofDefaultType()
                .named("Delete Card")
                .disabled()
                .build(this);

        MenuItemBuilder.ofDefaultType()
                .named("Cut Card")
                .disabled()
                .build(this);

        MenuItemBuilder.ofDefaultType()
                .named("Copy Card")
                .disabled()
                .build(this);

        this.addSeparator();

        MenuItemBuilder.ofDefaultType()
                .named("Text Style...")
                .disabled()
                .withShortcut('T')
                .build(this);

        MenuItemBuilder.ofDefaultType()
                .named("Background")
                .disabled()
                .withShortcut('B')
                .build(this);

        MenuItemBuilder.ofDefaultType()
                .named("Icon")
                .disabled()
                .withShortcut('I')
                .build(this);

        this.addSeparator();

        MenuItemBuilder.ofDefaultType()
                .named("Audio...")
                .disabled()
                .build(this);

        MenuItemBuilder.ofDefaultType()
                .named("Audio Help")
                .disabled()
                .build(this);
    }
}
