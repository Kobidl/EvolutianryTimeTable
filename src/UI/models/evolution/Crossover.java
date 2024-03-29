package UI.models.evolution;

import UI.ValidationException;
import UI.models.LessonSortType;
import engine.models.ICrossoverData;
import schema.models.ETTCrossover;

import java.io.Serializable;

public class Crossover implements ICrossoverData , Serializable
{
    private LessonSortType name;
    private int cuttingPoints;

    public Crossover(ETTCrossover ettCrossover) throws ValidationException {
        setName(ettCrossover);
        setCuttingPoints(ettCrossover);
    }

    public LessonSortType getName() {
        return name;
    }

    public void setName(ETTCrossover ettCrossover) throws ValidationException {
        this.name = LessonSortType.valueOfLabel(ettCrossover.getName());
        if(this.name == null){
            throw new ValidationException("Invalid crossover name: " + ettCrossover.getName());
        }
    }

    public int getCuttingPoints() {
        return cuttingPoints;
    }

    @Override
    public String getSortOperator() {
        return name.toString();
    }

    public void setCuttingPoints(ETTCrossover ettCrossover) throws ValidationException {
        this.cuttingPoints = ettCrossover.getCuttingPoints();
        if(this.cuttingPoints < 1){
            throw new ValidationException("Crossover cutting points can't be lower then 1");
        }
    }
}
