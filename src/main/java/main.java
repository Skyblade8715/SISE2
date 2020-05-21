import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class main {
    public static void main(String[] args) {

        List<String[]> listFromFile = null;
        try {
            listFromFile = Files.readAllLines(Paths.get("src\\main\\resources\\data.csv")).stream().map(line -> line.split(",")).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert listFromFile != null;
        List<Matrix> inputValues = new ArrayList<>();
        List<Matrix> correctValues = new ArrayList<>();
        List<Double> tempListInput = new ArrayList<>();
        List<Double> tempListValues = new ArrayList<>();
        String[] tempString;
        for(int i = 1; i < listFromFile.size(); i++){
            tempString = listFromFile.get(i);
            for(int j = 1; j < tempString.length; j++){
                if(j < 3){
                    tempListInput.add(Double.valueOf(tempString[j]));
                } else {
                    tempListValues.add(Double.valueOf(tempString[j]));
                }
            }
            inputValues.add(new Matrix(tempListInput));
            correctValues.add(new Matrix(tempListValues));
            tempListInput.clear();
            tempListValues.clear();
        }
        System.out.println(inputValues);
        System.out.println(correctValues);
        List<Integer> sizesOfMLP = new ArrayList<>();
        sizesOfMLP.add(4);
        sizesOfMLP.add(2);
        sizesOfMLP.add(4);
        MLP mlp = new MLP(sizesOfMLP, 2 , 2);
        mlp.backpropagation(inputValues.get(0), correctValues.get(0));

    }
}
