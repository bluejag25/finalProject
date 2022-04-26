import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Arrays;
import java.util.*;

public class PetShop {

    private int count;
    private Pet[] petID;

    public PetShop(int num){
        count = 0;
        petID = new Pet[num];
    }

    public boolean add(Pet a) {
        if (contains(a)) return false;
        if (full()) doubleCapacity();
        petID[count++] = a;
        return true;
    }

    public boolean remove(Pet a){

        if(!contains(a))
            return false;

        int loc = indexOf(a);
        petID[loc] = petID[count - 1];
        petID[count - 1] = null;
        count--;
        return true;
    }

    public boolean contains(Pet a){
        for(int i = 0; i < count; i++){
            if(petID[i].equals(a))
                return true;
        }
        return false;
    }

    private int indexOf(Pet a){
        for(int i = 0; i < count; i++){
            if(petID[i].equals(a))
                return i;
        }
        return -1;
    }

    private void resize(int size){

        Pet[] copy = new Pet[size];
        for (int i = 0; i < petID.length; i++) {
            copy[i] = petID[i];

        }
        petID = copy;
    }

    private void doubleCapacity() {
        Pet[] a = new Pet[petID.length * 2];
        System.arraycopy(petID, 0, a, 0, count);
        petID = a;
    }

    private boolean full() {
        return count == petID.length;
    }

    public Pet find(long numPetID){
        for(int i = 0; i < count; i++){
            if(petID[i].getPetID() == numPetID)
                return petID[i];
        }
        return null;
    }

    public int getCount(){
        return count;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < count; i++){
            sb.append(petID[i].toString() + "\n");

        }
        return sb.toString();
    }

    public void sort(){
        for(int i = 0; i < count - 1; i++){
            if(petID[i].compareTo(petID[i+1]) > 0 ){
                count++;
            }
        }
    }

    public int writePetIDs(String filename) throws FileNotFoundException{
        PrintWriter out = new PrintWriter(filename);
        for(int i = 0; i < count; i++){
            out.write(petID[i].toText());
        }
        out.close();
        return count;
    }






}
