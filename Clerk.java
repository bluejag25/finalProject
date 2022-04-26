import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;

public class Clerk {

    // small PetShop initially holds four pets.
    private static PetShop PetShop = new PetShop(4);

    public static void main(String[] args) {
        char command;
        int animal = 0;
        Scanner input = new Scanner(System.in);
        printMenu();
        do {
            // ask a user to choose a command
            System.out.println("\nPlease enter a command or type ?");
            command = input.nextLine().toLowerCase().charAt(0);

            switch (command) {
                case 'a': // add an Account

                    int petType = -1;
                    while (petType < 1 || petType > 2) {
                        System.out.print("\nEnter 1 for Dog or 2 for Cat: ");
                        petType = Integer.parseInt(input.nextLine());
                    }
                    System.out.print("Enter Pet name: ");
                    String name = input.nextLine();
                    System.out.print("Enter age of the pet ");
                    int age = Integer.parseInt(input.nextLine());

                    Pet a;
                    if (petType == 1) {
                        System.out.print("Enter type of dog ");
                        String d = input.nextLine();
                        a = new Dog(name, age , d);
                    } else {
                        System.out.print("Enter whether the cat has long or short hair ");
                        String c = input.nextLine();
                        a = new Cat(name, age , c);
                    }
                    if (PetShop.add(a)) System.out.print("\nPetShop Pet successfully added.\n");
                    else System.out.print("PetShop Pet not added. No duplicates please.\n");
                    break;
                case 'b': // remove a Pet
                    System.out.print("\nEnter pet ID number: ");
                    animal = Integer.parseInt(input.nextLine());

                    Pet c = PetShop.find(animal);

                    if(PetShop.remove(c)) {
                        System.out.println("\nPetShop Pet successfully removed.\n");
                    }else
                        System.out.print("PetShop Pet not removed.\n");
                    break;
                case 'c': // display the Pets
                    System.out.println(PetShop.toString());
                    break;
                case 'd': // count the Pets
                    System.out.println("\nThere are " + PetShop.getCount() + " Pets in the PetShop");
                    break;
                case 'e': // sort the Pets
                    PetShop.sort();
                    System.out.println("Pets sorted.");
                    break;
                case 'h':
                    System.out.println("Enter a file name: ");
                    String fileName = input.nextLine();

                    int numPetsWritten = 0;
                    try{
                        numPetsWritten = PetShop.writePetIDs(fileName);
                    }catch(FileNotFoundException fnfe){
                        fnfe.printStackTrace();
                    }

                    System.out.println(numPetsWritten + " pets written to file.");
                    break;

                case 'g':

                    System.out.println("Enter a file name: ");
                    String filename = input.nextLine();

                    int numPetsRead = 0;
                    try{
                        numPetsRead = Clerk.readPets(filename);
                    }catch(FileNotFoundException fnfe){
                        System.out.println("Bad filename, try again.");
                    }

                    System.out.println(numPetsRead + " pets read to file.");
                    break;

                case '?':
                    printMenu();
                    break;
                case 'q':
                    System.out.println("GOOD BYE!");
                    break;
                default:
                    System.out.println("Invalid Input");

            }

        } while (command != 'q');

        input.close();
    }
    public static void printMenu() {
        System.out.print("\nPetShop Teller Options\n" + "-----------------------------------\n"
                + "a: add a pet to the PetShop\n" + "b: remove an pet from the PetShop\n"
                + "c: display the pets in the PetShop\n" + "d: count the pets in the PetShop\n"
                + "e: sort the pets in the PetShop\n" + "h: write file\n"
                + "g: read file \n" + "?: display the menu again\n"
                + "q: quit this program\n\n");

    } // end of the printMenu method


    public static int readPets(String filename) throws FileNotFoundException{
        int count = 0;
        Scanner fileScan = new Scanner(new File(filename));

        while(fileScan.hasNext()){
            String currentLine = fileScan.nextLine();
            try{
                PetShop.add(parsePet(currentLine));
                count++;
            }catch(PetShopFileFormatException | ParseException bffe){
                System.out.println("Bad line skipped.");
            }
        }

        fileScan.close();
        return count;
    }

    public static Pet parsePet(String accountText) throws PetShopFileFormatException, ParseException {
        String[] tokens = accountText.split("\t");


        if(tokens.length != 5) throw new PetShopFileFormatException("Invalid account input line.");
        if(tokens[0].equals("DOG")) return new Dog(tokens);
        else if(tokens[0].equals("CAT")) return new Cat(tokens);
        else throw new PetShopFileFormatException("line must with DOG or CAT.");

    }



}


