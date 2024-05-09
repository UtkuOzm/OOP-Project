import java.io.File;
import java.io.FileNotFoundException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {



    public static void main(String[] args) {
        String fileRoot = "Firma.txt";
        LinkedList Customers=new LinkedList();
        ArrayList<ArrayList<Integer>> PointList = new ArrayList<>();
        try {
            File file = new File(fileRoot);
            
            Scanner scanner = new Scanner(file);
            String line = scanner.nextLine();
            String[] firstLine=line.split(",");
            int NumberOfProducts=Integer.parseInt(firstLine[0]);
            List<String> NamesOfProducts=new ArrayList<>();
            for (int i = 1; i < firstLine.length; i++){
                NamesOfProducts.add(firstLine[i]);
            }
            line = scanner.nextLine();
            String[] FirstCustomerLine=line.split(",");
            Customers.addHead(Integer.parseInt(FirstCustomerLine[0]),new CustomerData(FirstCustomerLine[1],FirstCustomerLine[2],FirstCustomerLine[3],FirstCustomerLine[4],FirstCustomerLine[5]));
            line = scanner.nextLine();
            System.out.println(line);
            String[] RatingLine=line.split(",");
            ArrayList<Integer> FirstList = new ArrayList<>();
            FirstList.add(Integer.parseInt(FirstCustomerLine[0]));
            for (String rating:RatingLine){
                FirstList.add(Integer.parseInt(rating));
            }
            PointList.add(FirstList);
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                String[] CustomerLine=line.split(",");
                Customers.addLast(Integer.parseInt(CustomerLine[0]),new CustomerData(CustomerLine[1],CustomerLine[2],CustomerLine[3],CustomerLine[4],CustomerLine[5]));
                line = scanner.nextLine();
                RatingLine=line.split(",");
                FirstList = new ArrayList<>();
                FirstList.add(Integer.parseInt(FirstCustomerLine[0]));
                for (String rating:RatingLine){
                    FirstList.add(Integer.parseInt(rating));
                }
                PointList.add(FirstList);
                System.out.println(line);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Dosya bulunamadı: " + fileRoot);
            e.printStackTrace();
        }
        Customers.Writer();
    }
}