/**
 * Contains my implementation of Sanctuary.
 * Meets the specifications outlined in PA5.
 */
import java.util.HashMap;

/**
 * Contains an implementation of Sanctuary.
 * Has all the methods stated in PA5.
 */
public class Sanctuary {
    
    //instance variables
    HashMap<String, Integer> sanctuary;
    int maxAnimals;
    int maxSpecies;

    /**
     * Assigns values to their instance variables.
     * @param maxAnimals The maximum number of animals in the sanctuary.
     * @param maxSpecies The maximum number of species in the sanctuary.
     * @throws IllegalArgumentException if either parameter is less than 0.
     */
    public Sanctuary(int maxAnimals, int maxSpecies) {
        if (maxAnimals < 0 || maxSpecies < 0) throw new IllegalArgumentException();
        sanctuary = new HashMap<>();
        this.maxAnimals = maxAnimals;
        this.maxSpecies = maxSpecies;
    }

    /**
     * Returns a count of all of a particular species in the 
     * sanctuary.
     * @param species The species that you want to query for.
     * @return The total number of that particular species. 0
     * if the sanctuary does not contain that species.
     * @throws IllegalArgumentException if {@code species} is null.
     */
    public int getNum(String species) {
        if (species == null) throw new IllegalArgumentException();
        if (!sanctuary.keySet().contains(species)) return 0;
        return sanctuary.get(species);
    }
    
    /**
     * Returns the number of all animals the sanctuary has total.
     * @return Total animals in the sanctuary.
     */
    public int getTotalAnimals() {
        //Get an array with the count of each animal in the array.
        Object[] valueSet = sanctuary.values().toArray();
        //Initialize output.
        int output = 0;
        //for each entry in the value set
        for (int i = 0; i < valueSet.length; i++) {
            //cast it to an int, and add it to the output.
            output += (Integer) valueSet[i];
        }
        //return the output
        return output;
    }
    
    /**
     * Returns the total number of species in the sanctuary.
     * @return A count of all species in the sanctuary.
     */
    public int getTotalSpecies() {
        return sanctuary.keySet().size();
    }

    /**
     * Attempts to add rescued animals to the sanctuary.
     * Any animals that could not be rescued (if the maximum
     * animals or maximum species is hit by adding some or none
     * of the rescued animals) will not be added.
     * @param species The species that you are attempting to rescue.
     * @param num The number of that animal you are attempting to rescue.
     * @return The number of animals that could not be rescued.
     * @throws IllegalArgumentException if {@code species} is null
     * or if {@code num} is less than 0.
     */
    public int rescue(String species, int num) {
        //exception handle
        if (num <= 0 || species == null) throw new IllegalArgumentException();
        //do not add any animals if the sanctuary is at max species.
        if (getTotalSpecies() == maxSpecies) return num;
        //if only some animals may be added.
        if (getTotalAnimals() + num > this.maxAnimals) {
            //calculate how many must be rescued
            int newNum = this.maxAnimals - getTotalAnimals();
            //calculate how many animals could not be rescued
            int output = num - newNum;
            //add the rescued animals to the sanctuary
            sanctuary.put(species, newNum + getNum(species));
            //return how many animals could not be rescued.
            return output;
        }
        //if all animals can be rescued, rescue them
        sanctuary.put(species, num);
        //return 0 since no animal was left behind
        return 0;
    }

    /**
     * Releases {@code num} of a species from the sanctuary.
     * @param species The species you want to release.
     * @param num The number of animals you want to release.
     * @throws IllegalArgumentException if:
     * {@code num} is less than 0, or
     * the sanctuary does not have that {@code species}, or
     * you want to release more than what the sanctuary has, or
     * {@code species} is null
     */
    public void release(String species, int num) {
        //exception handling
        if (
            num <= 0 ||
            !sanctuary.keySet().contains(species) ||
            num >= sanctuary.get(species) ||
            species == null
        ) throw new IllegalArgumentException();
        //remove the animals from the sanctuary
        sanctuary.put(species, sanctuary.get(species) - num);
        //remove the species from the sanctuary if there are none left.
        if (sanctuary.get(species) == 0) sanctuary.remove(species);
    }
}
