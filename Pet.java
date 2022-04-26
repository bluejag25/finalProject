import java.text.ParseException;



public abstract class Pet implements Comparable<Pet> {

    protected String name;
    protected int age;
    protected int petID;

    protected static int numPets = 0;

    public Pet(String name, int age){
        this.name = name;
        this.age = age;
        this.petID = generatePetID();

        numPets++;
    }

    public Pet(String name,String age, String petID) throws ParseException {

        this.name = name;
        this.age = Integer.parseInt(age);
        this.petID = Integer.parseInt(petID);

        numPets++;
    }


    private int generatePetID() {
        int tempPetID = (int) (Math.random() * 900000000) + 100000000;
        return tempPetID;
    }

    public double getAge() {
        return age;
    }

    public int getPetID(){
        return petID;
    }


    @Override
    public String toString() {
       

        return name + " [" + petID + "] \n" + age;
    }

    public static int getNumPets() {
        return numPets;
    }

    public boolean equals(Object a){
        if(a instanceof Pet)
            return this.petID == ((Pet) a).petID;
        else
            return false;
    }

    public int compareTo(Pet a){
        if(this.equals(a))
            return 0;

        if(this.petID < a.petID) {
            return -1;
        }else
            return 1;
    }
    public abstract String toText();
}
