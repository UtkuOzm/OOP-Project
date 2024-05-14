import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        String fileRoot = "Firma.txt";
        FileReader file = new FileReader(fileRoot);
        file.reader();
        Scanner scanner = new Scanner(System.in);
        int[] ratings = new int[file.NumberOfProducts];
        for (int i = 0; i < file.NumberOfProducts - 1; i++) {
            System.out.println("Ürün " + (i + 1) + " için puanı giriniz: ");
            ratings[i] = Integer.parseInt(scanner.nextLine());
        }
        ArrayList<Integer> SimilarityList = new ArrayList<>();
        Similarity.similartyCalc(file, ratings, SimilarityList);
        System.out.println("Tahmin edilen puanlar: " + Arrays.toString(ratings));

        // 3) Her bir ürün için ortalama derecelendirme puanını hesaplayarak yazdırma.
        calculateAndPrintAverageRatings(file);

        // 4) Her bir ürün için sadece ülkesi "Turkey" olan müşterileri dikkate alarak
        // elde edilen ortalama derecelendirme puanını hesaplayarak yazdırma.
        calculateAndPrintAverageRatingsForTurkey(file,file.Customers);

        // 5) Her bir ürün için ülkesi "Turkey" dışındaki değerler olan müşterileri
        // dikkate alarak elde edilen ortalama derecelendirme puanını hesaplayarak
        // yazdırma.
        calculateAndPrintAverageRatingsForNonTurkish(file,file.Customers);

        // 6) Her bir ürün için sadece mesleği "Doctor" olan müşteriler dikkate alınarak
        // elde edilen ortalama derecelendirme puanını hesaplayarak yazdırma.
        calculateAndPrintAverageRatingsForDoctors(file,file.Customers);

        // 7) Müşteri bilgileri bağlı listesini baştan sonra ekrana yazdırma.
        file.Customers.Writer();

        // 8) İki boyutlu diziyi ekrana yazdırma.
        printRatingsArray(file);
    }

    // 3) Her bir ürün için ortalama derecelendirme puanını hesaplayarak yazdırma.
    static void calculateAndPrintAverageRatings(FileReader file) {
        for (int i = 0; i < file.NumberOfProducts; i++) {
            double sum = 0;
            for (int j = 0; j < file.PointList.size(); j++) {
                sum += file.PointList.get(j).get(i + 1);
            }
            double averageRating = sum / file.PointList.size();
            System.out.println("Ürün " + (i + 1) + " için ortalama puan: " + averageRating);
        }
    }

    // 4) Her bir ürün için sadece ülkesi "Turkey" olan müşterileri dikkate alarak
    // elde edilen ortalama derecelendirme puanını hesaplayarak yazdırma.
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
            System.out.println("Ürün " + (i + 1) + " için Türkiye'deki müşterilerin ortalama puanı: " + averageRating);
        }
    }

    // 5) Her bir ürün için ülkesi "Turkey" dışındaki değerler olan müşterileri
    // dikkate alarak elde edilen ortalama derecelendirme puanını hesaplayarak
    // yazdırma.
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
                    "Ürün " + (i + 1) + " için Türkiye dışındaki müşterilerin ortalama puanı: " + averageRating);
        }
    }

    // 6) Her bir ürün için sadece mesleği "Doctor" olan müşteriler dikkate alınarak
    // elde edilen ortalama derecelendirme puanını hesaplayarak yazdırma.
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
            System.out.println("Ürün " + (i + 1) + " için doktor müşterilerin ortalama puanı: " + averageRating);
        }
    }

    // 8) İki boyutlu diziyi ekrana yazdırma.
    static void printRatingsArray(FileReader file) {
        for (int i = 0; i < file.PointList.size(); i++) {
            for (int j = 0; j < file.NumberOfProducts; j++) {
                System.out.print(file.PointList.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }
}