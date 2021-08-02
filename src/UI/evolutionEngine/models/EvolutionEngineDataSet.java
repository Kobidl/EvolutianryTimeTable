package UI.evolutionEngine.models;

import schema.models.*;

import java.util.ArrayList;
import java.util.List;

public class EvolutionEngineDataSet
{
    private int initialPopulation;
    private Selection selection;
    private Crossover crossover;
    private List<Mutation> mutations;

    public EvolutionEngineDataSet(ETTEvolutionEngine ettEvolutionEngine) {
        setInitialPopulation(ettEvolutionEngine.getETTInitialPopulation());
        setCrossover(ettEvolutionEngine.getETTCrossover());
        setSelection(ettEvolutionEngine.getETTSelection());
        setMutations(ettEvolutionEngine.getETTMutations().getETTMutation());
    }

    public int getInitialPopulation() {
        return initialPopulation;
    }

    //TODO : validation
    public void setInitialPopulation(ETTInitialPopulation ettInitialPopulation) {
        this.initialPopulation = ettInitialPopulation.getSize();
    }

    public Selection getSelection() {
        return selection;
    }

    //TODO : validation
    public void setSelection(ETTSelection ettSelection) {
        this.selection = new Selection(ettSelection);
    }

    public Crossover getCrossover() {
        return crossover;
    }

    //TODO : validation
    public void setCrossover(ETTCrossover ettCrossover) {
        this.crossover = new Crossover(ettCrossover);
    }

    public List<Mutation> getMutations() {
        return mutations;
    }

    //TODO : validation
    public void setMutations(List<ETTMutation> ettMutations) {
        this.mutations = new ArrayList<>();
        for (ETTMutation ettMutation: ettMutations) {
            mutations.add(new Mutation(ettMutation));
        }
    }
}