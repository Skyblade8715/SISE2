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
        List<List<Double>> inputValues = new ArrayList<>();
        List<List<Double>> correctValues = new ArrayList<>();
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
            inputValues.add(new ArrayList<>(tempListInput));
            correctValues.add(new ArrayList<>(tempListValues));
            tempListInput.clear();
            tempListValues.clear();
        }
        System.out.println(inputValues);
        System.out.println(correctValues);
        List<Integer> sizesOfMLP = new ArrayList<>();
        sizesOfMLP.add(2);
        sizesOfMLP.add(3);
        sizesOfMLP.add(4);
        sizesOfMLP.add(5);
        MLP mlp = new MLP(sizesOfMLP);

    }
}
