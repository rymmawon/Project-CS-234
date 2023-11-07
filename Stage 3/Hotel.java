import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

class Hotel {

    public static void main(String[] args) {
        Menu hotel = new Menu();
        Scanner scanner = new Scanner(System.in);

        hotel.menu(scanner);

        scanner.close();
    }

  
}
