import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String fileRoot = "Firma.txt";
        FileReader file=new FileReader(fileRoot);
        file.reader();
        int[] ratings=new int[file.NumberOfProducts];
        for (int i=0;i<file.NumberOfProducts-1;i++){
            System.out.println("Enter the rating of product"+i+" :");
            ratings[i] = Integer.parseInt(scanner.nextLine());
        }
        ArrayList<Integer> SimilarityList=new ArrayList<>();
        int minSimilarity=0;
        for(int i=0;i<file.PointList.size();i++){
            int similarity=0;
            for(int j=1;j<file.NumberOfProducts;j++){
                similarity+=Math.abs(file.PointList.get(i).get(j)-ratings[j-1]);
            }
            if (i==0){
                minSimilarity=similarity;
                SimilarityList.add(file.PointList.get(i).getFirst());
            } else if (minSimilarity>similarity) {
                SimilarityList.clear();
                SimilarityList.add(file.PointList.get(i).getFirst());
            } else if (minSimilarity==similarity) {
                SimilarityList.add(file.PointList.get(i).getFirst());
            }
        }
        int aveRating=0;
        if (SimilarityList.size()==1){
            ratings[file.NumberOfProducts-1]=file.PointList.get(SimilarityList.getFirst()-1).get(file.NumberOfProducts);
        }else {
            for (int data:SimilarityList){
                aveRating+=file.PointList.get(SimilarityList.get(0)).get(file.NumberOfProducts-1);
            }
            aveRating/=SimilarityList.size();
            ratings[file.NumberOfProducts-1]=aveRating;
        }
        for (int i:ratings){
            System.out.print(i+" ");
        }
    }
}
