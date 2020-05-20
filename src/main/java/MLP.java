import java.util.ArrayList;
import java.util.List;

public class MLP {

    private List<List<Double>> weights;
    private List<List<Double>> bias;

    public MLP(List<Integer> sizesOfMLP){
        List<List<Double>> weights = new ArrayList<>();
        List<List<Double>> bias = new ArrayList<>();
        List<Double> weightsInConsideredLayer = new ArrayList<>();
        List<Double> biasInConsideredLayer = new ArrayList<>();
        for(Integer amountOfNeutrons : sizesOfMLP){
            for(int i = 0; i < amountOfNeutrons; i++){
                weightsInConsideredLayer.add(Math.random());
                biasInConsideredLayer.add(Math.random());
            }
            weights.add(new ArrayList<>(weightsInConsideredLayer));
            bias.add(new ArrayList<>(biasInConsideredLayer));
            weightsInConsideredLayer.clear();
            biasInConsideredLayer.clear();
        }
        this.weights = weights;
        this.bias = bias;
    }
}
