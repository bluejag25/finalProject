import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
public class Cat extends Pet{

    private String hair;

    public Cat(String name, int age , String hair){
        super(name , age);
        this.hair = hair;

    }
    public Cat(String[] tokens) throws ParseException{
        super(tokens[1],tokens[2],tokens[3]);

        this.hair = (tokens[4]);
    }
    public String toString(){

        return "Cat  \n" + name + " [" + petID + "] \n" + age + "\n" + hair;


    }
    public String toText(){
        return "CAT\t" + name + "\t" + petID + "\t" + age + "\t" + hair + "\n";

    }
}