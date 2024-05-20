import java.util.ArrayList;
public class Similarity {

    public static void similartyCalc(FileReader file,ArrayList<Integer> ratings,ArrayList<Integer> SimilarityList){
    int minSimilarity=999999;
    //Finds maximum similarity with formula
        for(int i=0;i<file.PointList.size();i++){
        int similarity=0;
        for(int j=1;j<file.NumberOfProducts;j++){
            similarity+=Math.abs(file.PointList.get(i).get(j)-ratings.get(j-1));
        }
        if (i==0){
            minSimilarity=similarity;
            SimilarityList.add(file.PointList.get(i).getFirst());
        } else if (minSimilarity>similarity) {
            SimilarityList.clear();
            SimilarityList.add(file.PointList.get(i).getFirst());
            minSimilarity=similarity;
        } else if (minSimilarity==similarity) {
            SimilarityList.add(file.PointList.get(i).getFirst());
        }
    }
        //Adds last point the list according to similarity
    int aveRating=0;
        if (SimilarityList.size()==1){
        ratings.addLast(file.PointList.get(SimilarityList.getFirst()-1).get(file.NumberOfProducts));
    }else {
        for (int data:SimilarityList){
            aveRating+=file.PointList.get(data-1).get(file.NumberOfProducts-1);
        }
        aveRating/=SimilarityList.size();
        ratings.addLast(aveRating);
    }
        for (int i:ratings) {
            System.out.print(i + " ");
        }
    }
}
