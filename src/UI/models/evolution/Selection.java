package UI.models.evolution;

import UI.ValidationException;
import engine.models.ISelectionData;
import engine.models.SelectionType;
import schema.models.ETTSelection;

import java.io.Serializable;

public class Selection implements ISelectionData , Serializable
{
    private SelectionType type;
    private int value;

    public Selection(ETTSelection ettSelection) throws ValidationException {
        setType(ettSelection.getType());
        setValue(ettSelection.getConfiguration());
    }

    public SelectionType getType() {
        return type;
    }

    public void setType(SelectionType type) throws ValidationException {
        if(type == null){
            throw new ValidationException("Invalid selection type");
        }
        this.type = type;
    }

    public void setType(String type) throws ValidationException {
        SelectionType selectionType = SelectionType.valueOfLabel(type);
        setType(selectionType);
    }

    @Override
    public int getValue() {
        return value;
    }

    public void setValue(String configuration) throws ValidationException {
        if(configuration == null || !configuration.contains("=")){
            throw new ValidationException("Invalid selection config");
        }
        int value = Integer.parseInt(configuration.split("=")[1]);
        setTopPercent(value);
    }

    public void setTopPercent(int value) throws ValidationException {
        if(value < 1){
            throw new ValidationException("Invalid selection config");
        }
        this.value = value;
    }
}
