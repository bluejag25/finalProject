import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
public class Dog extends Pet{

    private String typeOfDog;

    public Dog(String name, int age, String typeOfDog){
        super(name,age);
        this.typeOfDog = typeOfDog;

    }
    public Dog(String[] tokens) throws ParseException{
        super(tokens[1],tokens[2],tokens[3]);

        this.typeOfDog = (tokens[4]);
    }
    public String toString(){

        return "Dog  \n" + name + " [" + petID + "] \n" + "age:" + age + "\n" + "Breed:" + typeOfDog;


    }
    public String toText(){
        return "DOG\t" + name + "\t" + petID + "\t" + age + "\t" + typeOfDog + "\n";

    }
}
