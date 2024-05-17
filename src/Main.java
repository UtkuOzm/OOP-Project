
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String fileRoot = "Firma.txt";
        FileReader file = new FileReader(fileRoot);
        Scanner scanner = new Scanner(System.in);
        String input= " ";
        while (!input.equals("0")) {
            switch (input) {
                case "1" -> file.reader();

                // A new customer entry is activated from the keyboard
                case "2" -> {
                    System.out.println("Enter information to log in as a new customer:");
                    Node temp = file.Customers.head;
                    int counter = 1;
                    while (temp.next != null) {
                        counter++;
                        temp = temp.next;
                    }
                    Node newNode = new Node();
                    temp.next = newNode;
                    newNode.CustomerNo = counter + 1;
                    System.out.print("Name: ");
                    String name=scanner.nextLine();
                    newNode.customerData.setName(name);

                    System.out.print("Surname: ");
                    String surname=scanner.nextLine();
                    newNode.customerData.setSurname(surname);

                    System.out.print("Country: ");
                    String country=scanner.nextLine();
                    newNode.customerData.setCountry(country);

                    System.out.print("City: ");
                    String city=scanner.nextLine();
                    newNode.customerData.setCity(city);

                    System.out.print("Occupation: ");
                    String occupation=scanner.nextLine();
                    newNode.customerData.setOccupation(occupation);

                    // Scoring for the first (n-1) products from the keyboard
                    ArrayList<Integer> ratings = new ArrayList<>();
                    for (int i = 0; i < file.NumberOfProducts-1; i++) {
                        System.out.print("Score for " + (i + 1) + " product: ");
                        ratings.add(Integer.parseInt(scanner.nextLine()));
                    }

                    ArrayList<Integer> SimilarityList = new ArrayList<>();
                    Similarity.similartyCalc(file, ratings, SimilarityList);
                    System.out.println("Predicted final product score: " + SimilarityList.getLast());
                    System.out.println("Predicted points: " + ratings);
                    ratings.addFirst(newNode.CustomerNo);
                    file.PointList.add(ratings);
                }
                case "3" ->
                    // 3)Print by calculating the average rating score for each product.
                        calculateAndPrintAverageRatings(file);
                case "4" ->
                    // 4) Printing by calculating the average rating score for each product
                    // taking into account only customers whose country is "Turkey".
                        calculateAndPrintAverageRatingsForTurkey(file, file.Customers);
                case "5" ->
                    // 5) Print by calculating the average rating score for each product
                    // taking into account customers whose country is other than "Turkey"
                        calculateAndPrintAverageRatingsForNonTurkish(file, file.Customers);
                case "6" ->

                    // 6) Printing by calculating the average rating score for each product
                    // taking into account only customers whose profession is "Doctor"
                        calculateAndPrintAverageRatingsForDoctors(file, file.Customers);
                case "7" ->
                    // 7) Printing the customer information linked list from start to finish.
                        file.Customers.Writer();
                case "8" ->
                    // 8) Printing a two-dimensional array to the screen.
                        printRatingsArray(file);
            }
            printMenu();
            System.out.print("Please Enter a Number: ");
            input=scanner.nextLine();
        }
    }

    // 3) Print by calculating the average rating score for each product.
    static void calculateAndPrintAverageRatings(FileReader file) {
        for (int i = 0; i < file.NumberOfProducts; i++) {
            double sum = 0;
            for (int j = 0; j < file.PointList.size(); j++) {
                sum += file.PointList.get(j).get(i + 1);
            }
            double averageRating = sum / file.PointList.size();
            System.out.println("Average score for product  " + (i + 1)  + averageRating);
        }
    }

    // 4)  Printing by calculating the average rating score for each product
    // taking into account only customers whose country is "Turkey"
    static void calculateAndPrintAverageRatingsForTurkey(FileReader file,Linked Customer) {
        int turkeyCustomerCount = 0;
        double[] sumRatings = new double[file.NumberOfProducts];
        Node temp=Customer.head;
        int CustomerNo=0;
        while (temp!=null){
            if (temp.customerData.getCountry().equals("Turkey")){
                for (int j = 0; j < file.NumberOfProducts; j++) {
                    sumRatings[j] += file.PointList.get(CustomerNo).get(j + 1);
                }
                turkeyCustomerCount++;
            }
            CustomerNo++;
            temp=temp.next;
        }
        for (int i = 0; i < file.NumberOfProducts; i++) {
            double averageRating = sumRatings[i] / turkeyCustomerCount;
            System.out.println("Average rating from customers in Turkey for product  :" + (i + 1) +  averageRating);
        }
    }

    // 5) Print by calculating the average rating score for each product
    //  taking into account customers whose country is other than "Turkey"
    static void calculateAndPrintAverageRatingsForNonTurkish(FileReader file,Linked Customer) {
        int nonTurkishCustomerCount = 0;
        double[] sumRatings = new double[file.NumberOfProducts];
        Node temp=Customer.head;
        int CustomerNo=0;
        while (temp!=null){
            if (!temp.customerData.getCountry().equals("Turkey")){
                for (int j = 0; j < file.NumberOfProducts; j++) {
                    sumRatings[j] += file.PointList.get(CustomerNo).get(j + 1);
                }
                nonTurkishCustomerCount++;
            }
            CustomerNo++;
            temp=temp.next;
        }
        for (int i = 0; i < file.NumberOfProducts; i++) {
            double averageRating = sumRatings[i] / nonTurkishCustomerCount;
            System.out.println(
                    "Average rating from customers outside Türkiye for product : " + (i + 1) +  averageRating);
        }
    }

    // 6) Printing by calculating the average rating score for each product
    // taking into account only customers whose profession is "Doctor"
    static void calculateAndPrintAverageRatingsForDoctors(FileReader file,Linked Customer) {
        int doctorCustomerCount = 0;
        double[] sumRatings = new double[file.NumberOfProducts];
        Node temp=Customer.head;
        int CustomerNo=0;
        while (temp!=null){
            if (temp.customerData.getOccupation().equals("Doctor")){
                for (int j = 0; j < file.NumberOfProducts; j++) {
                    sumRatings[j] += file.PointList.get(CustomerNo).get(j + 1);
                }
                doctorCustomerCount++;
            }
            CustomerNo++;
            temp=temp.next;
        }
        for (int i = 0; i < file.NumberOfProducts; i++) {
            double averageRating = sumRatings[i] / doctorCustomerCount;
            System.out.println("Doctor customers' average rating for product 5: " + (i + 1) +  averageRating);
        }
    }

    // 8) Printing a two-dimensional array to the screen.
    static void printRatingsArray(FileReader file) {
        for (int i = 0; i < file.PointList.size(); i++) {
            for (int j = 0; j < file.NumberOfProducts+1; j++) {
                System.out.print(file.PointList.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }
    static void printMenu(){
        System.out.println("1) Reading the data in the file and creating the relevant data structures.\n" +
                "2) Entry of a new customer from the keyboard (the number of customers cannot exceed 200).\n" +
                "In other words, the entered data must be added to the relevant data structures,but\n" +
                "the customer will only be allowed to score the first (n-1) product, and\n" +
                "the score for the last product will be found by guessing. The value found by guessing will be printed on the screen.\n" +
                "3) Calculate and print the average rating score for each product.\n" +
                "4) Printing by calculating the average rating score for each product,\n" +
                "taking into account only customers whose country is \"Turkey\".\n" +
                "5) Printing by calculating the average rating score for each product,\n" +
                "taking into account customers whose country is other than \"Turkey\".\n" +
                "6) Printing by calculating the average rating score for each product,\n" +
                "taking into account only customers whose profession is "Doctor".\n" +
                "7) Printing the customer information linked list from start to finish.\n" +
                "8) Printing a two-dimensional array to the screen.\n"+
                "0) Exit"
                );
    }
}
