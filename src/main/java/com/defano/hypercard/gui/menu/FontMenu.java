/*
 * FontMenu
 * hypertalk-java
 *
 * Created by Matt DeFano on 2/19/17 3:10 PM.
 * Copyright © 2017 Matt DeFano. All rights reserved.
 */

package com.defano.hypercard.gui.menu;

import com.defano.hypercard.context.ToolsContext;
import com.defano.jmonet.model.ImmutableProvider;

import javax.swing.*;
import java.awt.*;

public class FontMenu extends HyperCardMenu {

    public final static FontMenu instance = new FontMenu();

    private FontMenu() {
        super("Font");

        for (String thisFamily : GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()) {
            MenuItemBuilder.ofCheckType()
                    .named(thisFamily)
                    .withAction(e -> ToolsContext.getInstance().setFontFamily(thisFamily))
                    .withCheckmarkProvider(ImmutableProvider.derivedFrom(ToolsContext.getInstance().getFontProvider(), f -> f.getFamily().equalsIgnoreCase(thisFamily)))
                    .fontFamily(thisFamily)
                    .build(this);
        }
    }
}
