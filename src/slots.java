import java.util.Scanner;

public class slots {
    public slots() {
    }

    public static int slots(int points) {
        Scanner sc = new Scanner(System.in);
        String[] order = new String[]{"s", "b", "6", "s", "s", "b", "s", "7", "s", "b", "m", "m", "s", "b", "m", "b", "m", "s", "m", "s", "b", "s", "6", "m", "6", "s", "m", "7", "s", "b"};
        System.out.println("Would you like to see the the jackpots. y/n.");
        String stop = checkStop();
        if (stop.equals("y")) {
            System.out.println("SSS: 10");
            System.out.println("MMM: 20");
            System.out.println("BBB: 30");
            System.out.println("666: 200");
            System.out.println("777: 300");
        }

        do {
            System.out.println("How many points will you enter");
            int input = inputCheck(sc.nextLine(), points);
            String[][] pos = boardSet(new String[3][3], order);
            boardPrint(pos);
            points -= input;
            points = check(isolate1(pos), points, same(isolate1(pos)));
            if (input > 1) {
                points = check(isolate2(pos)[0], points, same(isolate2(pos)[0]));
                points = check(isolate2(pos)[1], points, same(isolate2(pos)[1]));
            }

            if (input == 3) {
                points = check(isolate3(pos)[0], points, same(isolate3(pos)[0]));
                points = check(isolate3(pos)[1], points, same(isolate3(pos)[1]));
            }

            System.out.println("Would you like to back out?(y/n)");
            stop = checkStop();
            if (stop.equals("n")) {
                System.out.println("You now have " + points + " points");
            }
        } while(points != 0 && !stop.equals("y"));

        return points;
    }

    public static String[][] boardSet(String[][] pos, String[] order) {
        int first = (int)(Math.random() * 28.0) + 1;
        int second = (int)(Math.random() * 28.0) + 1;
        int third = (int)(Math.random() * 28.0) + 1;

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                if (j == 0) {
                    pos[i][j] = order[first + (i - 1)];
                } else if (j == 1) {
                    pos[i][j] = order[second + (i - 1)];
                } else {
                    pos[i][j] = order[third + (i - 1)];
                }
            }
        }

        return pos;
    }

    public static void boardPrint(String[][] pos) {
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                System.out.print(pos[i][j] + " ");
            }

            System.out.println("");
        }

    }

    public static String isolate1(String[][] pos) {
        String hold = "";
        hold = hold + pos[1][0];
        hold = hold + pos[1][1];
        hold = hold + pos[1][2];
        return hold;
    }

    public static String[] isolate2(String[][] pos) {
        String[] hold = new String[]{"", ""};
        hold[0] = hold[0] + pos[0][0];
        hold[0] = hold[0] + pos[0][1];
        hold[0] = hold[0] + pos[0][2];
        hold[1] = hold[1] + pos[2][0];
        hold[1] = hold[1] + pos[2][1];
        hold[1] = hold[1] + pos[2][2];
        return hold;
    }

    public static String[] isolate3(String[][] pos) {
        String[] hold = new String[]{"", ""};
        hold[0] = hold[0] + pos[0][0];
        hold[0] = hold[0] + pos[1][1];
        hold[0] = hold[0] + pos[2][2];
        hold[1] = hold[1] + pos[2][0];
        hold[1] = hold[1] + pos[1][1];
        hold[1] = hold[1] + pos[0][2];
        return hold;
    }

    public static boolean same(String str) {
        return str.charAt(0) == str.charAt(1) && str.charAt(2) == str.charAt(1);
    }

    public static int check(String str, int points, boolean same) {
        if (same) {
            if (str.charAt(0) == 's') {
                points += 10;
                System.out.println("You have earned 10 points");
            } else if (str.charAt(0) == 'm') {
                points += 20;
                System.out.println("You have earned 20 points");
            } else if (str.charAt(0) == 'b') {
                points += 30;
                System.out.println("You have earned 30 points");
            } else if (str.charAt(0) == '6') {
                points += 200;
                System.out.println("You have earned 200 points");
            } else if (str.charAt(0) == '7') {
                points += 300;
                System.out.println("You have earned 300 points");
            }
        }

        return points;
    }

    public static int inputCheck(String input, int points) {
        Scanner sc = new Scanner(System.in);

        int in;
        try {
            in = Integer.parseInt(input);
        } catch (Exception var5) {
            System.out.println("That is an invalid input. You must input an amount of coins you can enter between 1 and 3");
            input = sc.nextLine();
            return inputCheck(input, points);
        }

        if (in <= 3 && in >= 1 && in < points) {
            return in;
        } else {
            System.out.println("That is an invalid input. You must input an amount of coins you can enter between 1 and 3");
            input = sc.nextLine();
            return inputCheck(input, points);
        }
    }

    public static String checkStop() {
        Scanner sc = new Scanner(System.in);

        String stop;
        do {
            stop = sc.nextLine();
            if (!stop.equals("y") && !stop.equals("n")) {
                System.out.println("That is invalid. Please enter either y or n");
            }
        } while(!stop.equals("y") && !stop.equals("n"));

        return stop;
    }
}
