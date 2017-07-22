package com.defano.hypercard.gui.util;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class ThreadUtils {

    public static void invokeAndWaitAsNeeded(Runnable r) {
        if (SwingUtilities.isEventDispatchThread()) {
            r.run();
        } else {
            try {
                SwingUtilities.invokeAndWait(r);
            } catch (InterruptedException| InvocationTargetException e) {
                Thread.interrupted();
            }
        }
    }

}
