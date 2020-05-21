import java.util.ArrayList;
import java.util.List;

public class MLP {

    private List<Matrix> weights;
    private List<Matrix> bias;

    public MLP(List<Integer> sizesOfMLP, int numOfInputs, int numOfOutputs){
        List<Matrix> weights = new ArrayList<>();
        List<Matrix> bias = new ArrayList<>();
        Matrix weightsInConsideredLayer;
        Matrix biasInConsideredLayer;
        //Adding weights on the first layer(connected to inputs)
        weightsInConsideredLayer = new Matrix(sizesOfMLP.get(0), numOfInputs, true);
        weights.add(weightsInConsideredLayer);
        for(int i = 0; i < sizesOfMLP.size(); i++) {
            weightsInConsideredLayer = new Matrix(
                    ((i == sizesOfMLP.size() - 1)?numOfOutputs:sizesOfMLP.get(i+1)),
                    sizesOfMLP.get(i),
                    true);
            biasInConsideredLayer = new Matrix(sizesOfMLP.get(i), 1, true);
            weights.add(weightsInConsideredLayer);
            bias.add(biasInConsideredLayer);
        }
        //Adding bias on the output layer
        biasInConsideredLayer = new Matrix(sizesOfMLP.get(sizesOfMLP.size() - 1), numOfOutputs, true);
        bias.add(biasInConsideredLayer);

        this.weights = weights;
        this.bias = bias;
    }

    List<Matrix> feedForward(Matrix input){

        List<Matrix> calculatedValuesOnNeurons = new ArrayList<>();
        calculatedValuesOnNeurons.add( (weights.get(0).multiplyMatrices(input) ).addMatrices(bias.get(0)));
        calculatedValuesOnNeurons.get(0).activationFunction();
        for(int i = 1; i < weights.size(); i++){
            calculatedValuesOnNeurons.add( (
                    weights.get(i).
                    multiplyMatrices(calculatedValuesOnNeurons.get(i - 1)) ).
                    addMatrices(bias.get(i)));
            calculatedValuesOnNeurons.get(i).activationFunction();
        }
        return calculatedValuesOnNeurons;
    }

    void backpropagation(Matrix input, Matrix expectedResult){
        List<Matrix> valuesOnNeurons = feedForward(input);
        int outputSize = valuesOnNeurons.size() - 1;

//        valuesOnNeurons.set(outputSize, valuesOnNeurons.get(outputSize).subtract(expectedResult));
//        for(int i = outputSize - 1; i >= 0; i--){
//            valuesOnNeurons.set(i,
//                valuesOnNeurons.get(i).
//                    transpose().
//                    multiplyMatrices(valuesOnNeurons.get(i + 1)));
//        }

    }
}
