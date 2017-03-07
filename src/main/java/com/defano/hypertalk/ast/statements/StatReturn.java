/*
 * StatReturn
 * hypertalk-java
 *
 * Created by Matt DeFano on 2/19/17 3:11 PM.
 * Copyright © 2017 Matt DeFano. All rights reserved.
 */

/**
 * StatReturn.java
 * @author matt.defano@gmail.com
 * 
 * Encapsulation of the "return" statement
 */

package com.defano.hypertalk.ast.statements;

import com.defano.hypercard.context.GlobalContext;
import com.defano.hypertalk.ast.expressions.Expression;
import com.defano.hypertalk.ast.expressions.ExpLiteral;
import com.defano.hypertalk.exception.HtSemanticException;

public class StatReturn extends Statement {

    public final Expression returnValue;
    
    public StatReturn () {
        this.returnValue = new ExpLiteral("");
    }
    
    public StatReturn (Expression returnValue) {
        this.returnValue = returnValue;
    }

    public void execute () throws HtSemanticException {
        GlobalContext.getContext().setReturnValue(returnValue.evaluate());
        this.breakExecution = true;
    }
}