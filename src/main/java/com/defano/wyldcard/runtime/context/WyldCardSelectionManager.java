package com.defano.wyldcard.runtime.context;

import com.defano.wyldcard.parts.field.AddressableSelection;
import com.defano.wyldcard.parts.model.PartModel;
import com.defano.hypertalk.ast.model.PartType;
import com.defano.hypertalk.ast.model.Value;
import com.defano.hypertalk.ast.model.specifiers.PartSpecifier;
import com.defano.hypertalk.exception.HtSemanticException;
import com.defano.hypertalk.utils.Range;
import com.google.inject.Singleton;

/**
 * A singleton managing HyperCard's view of the 'the selection'; a special container representing the active text
 * selection.
 */
@Singleton
public class WyldCardSelectionManager implements SelectionManager {

    private PartSpecifier theSelectionPart;     // Part holding 'the selection'
    private Range theSelectionRange;            // Range of characters selected
    private Value theClickText;

    @Override
    public void setSelection(PartSpecifier selectionPart, Range selectionRange) {
        // Do not allow a message selection from replacing a field selection
        if (!hasFieldSelection() || !(selectionPart != null && selectionPart.getType() == PartType.MESSAGE_BOX)) {
            this.theSelectionPart = selectionPart;
            this.theSelectionRange = selectionRange;
        }
    }

    @Override
    public Range getSelectionRange() {
        return theSelectionRange;
    }

    @Override
    public PartModel getSelectedPart(ExecutionContext context) throws HtSemanticException {

        // No selection exists
        if (theSelectionPart == null || getSelectionRange() == null || getSelectionRange().length() == 0) {
            throw new HtSemanticException("There isn't any selection.");
        }

        // Find the part holding the selection
        return context.getPart(theSelectionPart);
    }

    @Override
    public AddressableSelection getManagedSelection(ExecutionContext context) throws HtSemanticException {
        PartModel partModel = getSelectedPart(context);

        if (partModel instanceof AddressableSelection) {
            return (AddressableSelection) partModel;
        } else {
            throw new IllegalStateException("Bug! Unexpected part holding selection: " + partModel);
        }
    }

    @Override
    public Value getSelection(ExecutionContext context) throws HtSemanticException {
        return getManagedSelection(context).getSelectedText(context);
    }

    @Override
    public void setClickText(Value clickText) {
        theClickText = clickText;
    }

    @Override
    public Value getClickText() {
        return theClickText;
    }

    private boolean hasFieldSelection() {
        return theSelectionPart != null &&
                theSelectionPart.getType() == PartType.FIELD &&
                theSelectionRange != null &&
                !theSelectionRange.isEmpty();
    }
}
