
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String fileRoot = "Firma.txt";
        FileReader file = new FileReader(fileRoot);
        Scanner scanner = new Scanner(System.in);
        String input= " ";
        while (!input.equals("0")) {
            printMenu();
            switch (input) {
                case "1" -> file.reader();

                // Klavyeden yeni bir müşteri girişi sağlanması
                case "2" -> {
                    System.out.println("Yeni bir müşteri girişi yapmak için bilgileri giriniz:");
                    Node temp = file.Customers.head;
                    int counter = 1;
                    while (temp.next != null) {
                        counter++;
                        temp.next = temp;
                    }
                    Node newNode = new Node();
                    temp.next = newNode;
                    newNode.CustomerNo = counter + 1;
                    System.out.print("Adı: ");
                    newNode.customerData.setName(scanner.nextLine());

                    System.out.print("Soyadı: ");
                    newNode.customerData.setSurname(scanner.nextLine());

                    System.out.print("Ülke: ");
                    newNode.customerData.setCountry(scanner.nextLine());

                    System.out.print("Şehir: ");
                    newNode.customerData.setCity(scanner.nextLine());

                    System.out.print("Meslek: ");
                    newNode.customerData.setOccupation(scanner.nextLine());

                    // Klavyeden ilk (n-1) ürünler için puanlama yapılması
                    ArrayList<Integer> ratings = new ArrayList<>();
                    for (int i = 0; i < file.NumberOfProducts; i++) {
                        System.out.print("Ürün " + (i + 1) + " için puan: ");
                        ratings.add(Integer.parseInt(scanner.nextLine()));
                    }

                    ArrayList<Integer> SimilarityList = new ArrayList<>();
                    Similarity.similartyCalc(file, ratings, SimilarityList);
                    System.out.println("Tahmin edilen son ürün puanı: " + SimilarityList.getLast());
                    System.out.println("Tahmin edilen puanlar: " + ratings);
                    ratings.addFirst(newNode.CustomerNo);
                    file.PointList.add(ratings);
                }
                case "3" ->
                    // 3) Her bir ürün için ortalama derecelendirme puanını hesaplayarak yazdırma.
                        calculateAndPrintAverageRatings(file);
                case "4" ->
                    // 4) Her bir ürün için sadece ülkesi "Turkey" olan müşterileri dikkate alarak
                    // elde edilen ortalama derecelendirme puanını hesaplayarak yazdırma.
                        calculateAndPrintAverageRatingsForTurkey(file, file.Customers);
                case "5" ->
                    // 5) Her bir ürün için ülkesi "Turkey" dışındaki değerler olan müşterileri
                    // dikkate alarak elde edilen ortalama derecelendirme puanını hesaplayarak
                    // yazdırma.
                        calculateAndPrintAverageRatingsForNonTurkish(file, file.Customers);
                case "6" ->

                    // 6) Her bir ürün için sadece mesleği "Doctor" olan müşteriler dikkate alınarak
                    // elde edilen ortalama derecelendirme puanını hesaplayarak yazdırma.
                        calculateAndPrintAverageRatingsForDoctors(file, file.Customers);
                case "7" ->
                    // 7) Müşteri bilgileri bağlı listesini baştan sonra ekrana yazdırma.
                        file.Customers.Writer();
                case "8" ->
                    // 8) İki boyutlu diziyi ekrana yazdırma.
                        printRatingsArray(file);
            }
            input=scanner.nextLine();
        }
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
    static void printMenu(){
        System.out.println("1) Dosyadaki verileri okuyup ilgili veri yapılarını oluşturmak.\n" +
                "2) Klavyeden yeni bir müşteri girişi sağlanması (müşteri sayısı 200'ü aşamayacaktır). Diğer deyiş\n" +
                "ile girilen verilerin ilgili veri yapılarına eklenmesi gerekmektedir ancak müşterinin sadece ilk (n-1)\n" +
                "ürün hakkında puanlama yapmasına izin verilecek, son ürün ile ilgili puan tahmin ile bulunacaktır.\n" +
                "Tahmin ile bulunan değer ekrana yazdırılacaktır.\n" +
                "3) Her bir ürün için o ürüne ait ortalama derecelendirme puanını hesaplayarak yazdırma.\n" +
                "4) Her bir ürün için sadece ülkesi \"Turkey\" olan müşterileri dikkate alarak elde edilen ortalama\n" +
                "derecelendirme puanını hesaplayarak yazdırma.\n" +
                "5) Her bir ürün için ülkesi \"Turkey\" dışındaki değerler olan müşterileri dikkate alarak elde edilen\n" +
                "ortalama derecelendirme puanını hesaplayarak yazdırma.\n" +
                "6) Her bir ürün için sadece mesleği \"Doctor\" olan müşteriler dikkate alınarak elde edilen ortalama\n" +
                "derecelendirme puanını hesaplayarak yazdırma.\n" +
                "7) Müşteri bilgileri bağlı listesini baştan sonra ekrana yazdırma.\n" +
                "8) İki boyutlu diziyi ekrana yazdırma.");
    }
}