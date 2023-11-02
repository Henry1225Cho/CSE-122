import java.util.*;
//CSE 122 quiz part 2
public class Animals {
    public static void main(String[] args) {
        Map<String, Integer> animalCounts = new HashMap<>();
        animalCounts.put("maltese", 0);
        animalCounts.put("eagle", 2);
        animalCounts.put("pigeon", 10);
        animalCounts.put("salmon", 18);

        Map<String, String> animalSpecies = new HashMap<>();
        animalSpecies.put("corgi", "dog");
        animalSpecies.put("maltese", "dog");
        animalSpecies.put("eagle", "bird");
        animalSpecies.put("pigeon", "bird");
        animalSpecies.put("ragamuffin", "cat");

        Map<String, Integer> result = countSpecies(animalCounts, animalSpecies);
        System.out.println(result);
    }

    // TODO: Write your method here!
    public static Map<String, Integer> countSpecies (Map<String, Integer> animalCounts,
                                                    Map<String, String> animalSpecies) {
        Map<String, Integer> speciesCount = new HashMap<>();
        for (String animal : animalSpecies.keySet()) {
            if (animalCounts.containsKey(animal)) {
                String speciesName = animalSpecies.get(animal);
                speciesCount.put(speciesName, speciesCount.getOrDefault(speciesName, 0) + animalCounts.get(animal));
            }
        }
        return speciesCount;         
    }        
}