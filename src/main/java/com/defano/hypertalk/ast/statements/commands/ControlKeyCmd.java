package com.defano.hypertalk.ast.statements.commands;

import com.defano.hypertalk.ast.expressions.Expression;
import com.defano.hypertalk.ast.preemptions.Preemption;
import com.defano.hypertalk.ast.statements.Statement;
import com.defano.hypertalk.exception.HtException;
import com.defano.wyldcard.awt.RoboticTypist;
import com.defano.wyldcard.runtime.context.ExecutionContext;
import org.antlr.v4.runtime.ParserRuleContext;

public class ControlKeyCmd extends Statement {

    private final Expression keyExpr;

    public ControlKeyCmd(ParserRuleContext context, Expression keyExpr) {
        super(context);
        this.keyExpr = keyExpr;
    }

    @Override
    protected void onExecute(ExecutionContext context) throws HtException, Preemption {
        RoboticTypist.getInstance().type(keyExpr.evaluate(context).toString(), false, true);
    }
}
