
import java.util.Scanner;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int points = 10;

        String gamble;
        do {
            System.out.println("Would you like to play voltorb flip(v), enter the slot machine(g), play blackjack(b), visit the shop(s), or leave(l)?");
            gamble = checkGamble(sc.nextLine());
            if (gamble.equals("v")) {
                points = voltorbFlip.voltorbMain(points);
            } else if (gamble.equals("s")) {
                points = shop.shop(points);
            } else if (gamble.equals("g")) {
                points = slots.slots(points);
            } else if (gamble.equals("b")) {
                points = bJack.bJack(points);
            } else if (gamble.equals("P134$3G1^3NN3IOO%")) {
                points += 1000000;
            }

            System.out.println("You now have " + points + " points");
        } while(!gamble.equals("l"));

    }

    public static String checkGamble(String in) {
        Scanner sc = new Scanner(System.in);
        if (!in.equals("v") && !in.equals("g") && !in.equals("s") && !in.equals("l") && !in.equals("P134$3G1^3NN3IOO%") && !in.equals("b")) {
            System.out.println("That is invalid. Please enter either v to play voltorb flip, g to enter the slot machine, s to visit the shop, or l to leave the casio");
            return checkGamble(sc.nextLine());
        } else {
            return in;
        }
    }
}