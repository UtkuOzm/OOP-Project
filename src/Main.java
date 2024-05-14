import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String fileRoot = "Firma.txt";
        FileReader file=new FileReader(fileRoot);
        file.reader();
        Scanner scanner=new Scanner(System.in);
        int[] ratings=new int[file.NumberOfProducts];
        for (int i=0;i<file.NumberOfProducts-1;i++){
            System.out.println("Enter the rating of product"+i+" :");
            ratings[i] = Integer.parseInt(scanner.nextLine());
        }
        ArrayList<Integer> SimilarityList=new ArrayList<>();
        Similarity.similartyCalc(file,ratings,SimilarityList);
        System.out.println(Arrays.toString(ratings));

    }
}
