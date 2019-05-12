package com.defano.hypertalk.ast.model.specifier;

import com.defano.hypertalk.ast.model.effect.VisualEffectImage;
import com.defano.hypertalk.ast.model.effect.VisualEffectSpeed;
import com.defano.jsegue.SegueName;

public class VisualEffectSpecifier {

    public final SegueName name;
    public final VisualEffectSpeed speed;
    public final VisualEffectImage image;

    public VisualEffectSpecifier(SegueName name, VisualEffectSpeed speed, VisualEffectImage image) {
        this.name = name;
        this.speed = speed;
        this.image = image;
    }

    @Override
    public String toString() {
        return "VisualEffectSpecifier{" +
                "name=" + name +
                ", speed=" + speed +
                ", image=" + image +
                '}';
    }
}