import java.util.Scanner;

public class bJack {
    public static char[][] hold = new char[][]{{'d', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd'}, {'d', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd'}, {'d', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd'}, {'d', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd'}};
    public static char[] suits = new char[]{'C', 'D', 'H', 'S'};
    public static String[][] deck = new String[][]{{"A", "1", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"}, {"A", "1", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"}, {"A", "1", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"}, {"A", "1", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"}};
    public static int bet = 0;

    public bJack() {
    }

    public static int bJack(int points) {
        Scanner sc = new Scanner(System.in);
        System.out.println("How many rounds will you play");
        int rounds = roundCheck(sc.nextLine());

        for(int i = 0; i < rounds; ++i) {
            System.out.println("How much will you bet");
            bet = betCheck(sc.nextLine(), points);
            boolean compBJ = false;
            int sum = 0;
            String card = cardSelect();
            String card2 = cardSelect();
            String compHand = card + " ";
            sum += cardConvert(card, sum) + cardConvert(card2, sum);
            if ((!card.equals("A") || !card.equals("10")) && (!card.equals("10") || !card.equals("A"))) {
                System.out.println("Computer Hand: " + compHand + "?");
                compHand = compHand + card2 + " ";
            } else {
                compHand = compHand + card2 + " ";
                System.out.println("Computer Hand: " + compHand + " Sum:" + sum + "\nBLACKJACK");
                compBJ = true;
            }

            String playerHand = playerHand();
            boolean playerBJ = playerHand.equals("BLACKJACK");
            if (playerBJ) {
                points += bet + bet / 2;
            } else if (compBJ) {
                points -= bet;
            } else {
                int pSum = Integer.parseInt(playerHand.substring(playerHand.lastIndexOf(":") + 1));
                System.out.println("Computer Hand: " + compHand + " Sum:" + sum);
                if (pSum <= 21 && pSum >= sum) {
                    compHand(compHand, sum, pSum);
                }

                if (pSum > sum) {
                    points += bet;
                    System.out.println("You win");
                } else if (pSum < sum) {
                    points -= bet;
                    System.out.println("The computer wins");
                } else {
                    System.out.println("It's a tie");
                }
            }
        }

        return points;
    }

    public static String playerHand() {
        Scanner sc = new Scanner(System.in);
        String bet = "";
        String hand = "";
        int sum = 0;

        String card;
        for(int i = 0; i < 2; ++i) {
            card = cardSelect();
            hand = hand + card + " ";
            sum += cardConvert(card, sum);
        }

        boolean ace = ace(hand);
        if (ace && sum == 21) {
            System.out.println("Your Hand: BLACKJACK");
            return "BLACKJACK";
        } else {
            if (sum + 10 <= 21 && ace) {
                sum += 10;
            }

            System.out.println("Your Hand: " + hand + " Sum:" + sum);

            String choose;
            do {
                System.out.println("Will you hit, or stand");
                choose = chooseCheck(sc.nextLine());
                if (choose.equals("hit")) {
                    card = cardSelect();
                    hand = hand + card + " ";
                    sum += cardConvert(card, sum);
                }

                ace = ace(hand);
                if (sum + 10 <= 21 && ace) {
                    sum += 10;
                }

                System.out.println("Your Hand: " + hand + " " + sum);
                if (sum > 21) {
                    System.out.println("Bust");
                    return "Bust";
                }
            } while(sum <= 21 && !choose.equals("stand"));

            return "Your Hand: " + hand + " Sum:" + sum;
        }
    }

    public static String compHand(String hand, int sum, int pSum) {
        String card = cardSelect();
        hand = hand + card + " ";
        sum += cardConvert(card, sum);
        boolean ace = ace(hand);
        if (sum + 10 <= 21 && ace) {
            sum += 10;
        }

        System.out.println("Computer Hand: " + hand + " Sum:" + sum);
        if (sum > 21) {
            System.out.println("Bust");
            return "Bust";
        } else {
            return sum <= pSum ? compHand(hand, sum, pSum) : hand + " Sum:" + sum;
        }
    }

    public static String cardSelect() {
        int val = (int)(Math.random() * 13.0);
        int suit = (int)(Math.random() * 4.0);
        if (hold[suit][val] == 'c') {
            return cardSelect();
        } else {
            String card = suits[suit] + deck[suit][val];
            hold[suit][val] = 'c';
            return card;
        }
    }

    public static int cardConvert(String val, int sum) {
        val = val.substring(1);
        if (val.equals("A")) {
            return sum < 11 ? 11 : 1;
        } else {
            return !val.equals("J") && !val.equals("Q") && !val.equals("K") ? Integer.parseInt(val) : 10;
        }
    }

    public static boolean ace(String hand) {
        for(int i = 0; i < hand.length(); ++i) {
            if (hand.charAt(i) == 'A') {
                return true;
            }
        }

        return false;
    }

    public static int betCheck(String in, int points) {
        Scanner sc = new Scanner(System.in);

        int bet;
        try {
            bet = Integer.parseInt(in);
        } catch (Exception var5) {
            System.out.println("That is an invalid amount of rounds. Please enter a number of rounds between one and three");
            return betCheck(sc.nextLine(), points);
        }

        if (bet <= points && bet >= 1) {
            return bet;
        } else {
            System.out.println("That bet is invalid. Please enter a valid bet");
            return betCheck(sc.nextLine(), points);
        }
    }

    public static int roundCheck(String input) {
        Scanner sc = new Scanner(System.in);

        int in;
        try {
            in = Integer.parseInt(input);
        } catch (Exception var4) {
            System.out.println("That is an invalid amount of rounds. Please enter a number of rounds between one and three");
            input = sc.nextLine();
            return roundCheck(input);
        }

        if (in <= 3 && in >= 1) {
            return in;
        } else {
            System.out.println("That is an invalid amount of rounds. Please enter a number of rounds between one and three");
            return roundCheck(sc.nextLine());
        }
    }

    public static String chooseCheck(String in) {
        Scanner sc = new Scanner(System.in);
        if (!in.equals("hit") && !in.equals("stand")) {
            System.out.println("Invalid input. Please enter either \"hit\" or \"stand\"");
            return chooseCheck(sc.nextLine());
        } else {
            return in;
        }
    }
}
