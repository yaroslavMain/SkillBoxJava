import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Кофемашина");

        System.out.print("Введите сумму: ");
        int moneyAmount = scan.nextInt();

        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get("drinks.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int Nline = 1;

        ArrayList<Product> products = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split("\s+");
            try {
                int price = Integer.parseInt(parts[1]);
                products.add(new Product(parts[0], price));
            } catch (Exception ex) {
                System.out.println("В файле под номером строки " + Nline + " некорректно написаны данные");
            }

            Nline++;
        }

        checkPrices(products, moneyAmount);


    }

    public static void checkPrices(ArrayList<Product> products, int moneyAmount) {

        boolean canBuyAnything = false;


        for (Product product : products) {
            if (moneyAmount >= product.getPrice()) {
                System.out.println("Вы можете купить " + product.getName());
                canBuyAnything = true;
            }
        }

        if (!canBuyAnything) {
            System.out.println("Недостаточно средств :( Изучайте Java и зарабатывайте много!))");
        }
    }

}
