import java.util.Scanner;

class voltorbFlip {
    voltorbFlip() {
    }

    public static int voltorbMain(int points) {
        Scanner sc = new Scanner(System.in);
        int lv = 1;
        String stop = "y";
        if (points == 0) {
            System.out.println("Welcome to voltorb flip. In this game, you will be given a board sized based on you level(You start at level 1). All tiles will be covered with a '?', and can either cover a 1, 2, 3, or voltorb(Represented by 0).You are given the option to flip a tile, mark a tile off, or chek how many bombs/points are in each row/column.");
            System.out.println("If you flip a tile, your points of that level are multiplied by the value of the tile you flipped.");
            System.out.println("If you mark a tile, the '?' will become a 'X', and will be marked off for perseonal notation. To unmark a tile, you can choose to mark that tile again(Note that a tile being marked will not stop you from flipping that tile).");
            System.out.println("By checking for bombs/points, you will input the specific row/column you want, and the sum of all bombs and the sum of all points in that row/column will be returned.");
            System.out.println("You lose the level if you points of the level reach 0, and you win the level if your points of the level reach the maximum. At the end of the level, you will either be sent down or up 1 level, also the points you earnt that level will be added to your total points for use in the shop. The game ends either when you lose level 1, win level 5, or choose to leave with your winnings");
        }

        do {
            System.out.println("You are currently playing at level: " + lv);
            int dim = 2 + lv;
            int simPoints = 1;
            int[][] board = new int[dim][dim];
            board = boardSet(board, dim, simPoints);
            simPoints = pointCheck(board, 1, dim);
            char[][] simBoard = new char[dim][dim];
            simBoard = simBoardSet(simBoard, dim);
            int lvPoints = 1;
            boardPrint(board, simBoard, dim);

            do {
                System.out.println("Would you like to flip a tile(g), mark a tile(m), or check the bombs and points in a row/column(c)?");
                String choose = sc.nextLine();
                choose = checkIn1(choose);
                if (choose.equals("c")) {
                    chooseC(board, dim);
                } else {
                    System.out.println("What is the column of the tile");
                    int x = sc.nextInt();
                    x = checkGuess(x, dim);
                    System.out.println("what is the row of the tile");
                    int y = sc.nextInt();
                    y = checkGuess(y, dim);
                    simBoard = checkTile(x, y, dim, simBoard, choose);
                    if (choose.equals("g")) {
                        lvPoints *= board[y - 1][x - 1];
                    }

                    boardPrint(board, simBoard, dim);
                }
            } while(lvPoints != simPoints && lvPoints != 0);

            System.out.print("\nFull Board:");
            boardPrint(board, dim);
            points += lvPoints;
            if (lvPoints == 0) {
                System.out.println("Sorry, you have lost because you fliped over a bomb tile");
                --lv;
            }

            if (lvPoints == simPoints) {
                System.out.println("Congratulations, you have won by flipping over all the point tiles");
                ++lv;
            }

            if (lv != 0 && lv != 6) {
                System.out.println("Would you like to back out(y/n)?");
                stop = sc.nextLine();
                stop = checkStop(stop);
            }
        } while(lv > 0 && lv < 6 && !stop.equals("y"));

        if (lv == 0) {
            System.out.println("You have lost the game. Thank you for playing");
        } else if (lv == 6) {
            System.out.println("You win. Thank you for playing");
        } else if (stop.equals("y")) {
            System.out.println("Thank you for playing");
        }

        return points;
    }

    public static int[][] boardSet(int[][] board, int dim, int simPoints) {
        int bomb = 0;

        for(int i = 0; i < dim; ++i) {
            for(int j = 0; j < dim; ++j) {
                int vari = (int)(Math.random() * 30.0);
                if (vari < 2) {
                    board[i][j] = 3;
                } else if (vari < 4) {
                    board[i][j] = 2;
                } else if (vari < 15) {
                    board[i][j] = 0;
                    ++bomb;
                } else {
                    board[i][j] = 1;
                }
            }
        }

        simPoints = pointCheck(board, 1, dim);
        if (simPoints >= 6 * (dim - 2) && !((double)simPoints > Math.pow(2.0, (double)(dim * 2))) && bomb >= (int)((double)(dim * dim) * 0.3)) {
            return board;
        } else {
            return boardSet(board, dim, 1);
        }
    }

    public static char[][] simBoardSet(char[][] board, int dim) {
        for(int i = 0; i < dim; ++i) {
            for(int j = 0; j < dim; ++j) {
                board[i][j] = '?';
            }
        }

        return board;
    }

    public static void boardPrint(int[][] board, char[][] simBoard, int dim) {
        int i;
        for(i = 0; i < dim; ++i) {
            System.out.print(i + 1 + " ");
        }

        System.out.println("\n");

        for(i = 0; i < dim; ++i) {
            for(int j = 0; j < dim; ++j) {
                if (simBoard[i][j] == '?') {
                    System.out.print("? ");
                } else if (simBoard[i][j] == 'm') {
                    System.out.print("X ");
                } else {
                    System.out.print(board[i][j] + " ");
                }
            }

            System.out.println("  " + (i + 1));
        }

    }

    public static void boardPrint(int[][] board, int dim) {
        System.out.println();

        for(int i = 0; i < dim; ++i) {
            for(int j = 0; j < dim; ++j) {
                System.out.print(board[i][j] + " ");
            }

            System.out.println("");
        }

    }

    public static int pointCheck(int[][] board, int simPoints, int dim) {
        for(int i = 0; i < dim; ++i) {
            for(int j = 0; j < dim; ++j) {
                if (board[i][j] != 0) {
                    simPoints *= board[i][j];
                }
            }
        }

        return simPoints;
    }

    public static void getRow(int[][] board, int dim, int x) {
        int bomb = 0;
        int simPoints = 0;

        for(int i = 0; i < dim; ++i) {
            simPoints += board[x - 1][i];
            if (board[x - 1][i] == 0) {
                ++bomb;
            }
        }

        System.out.println("Bombs: " + bomb);
        System.out.println("Points: " + simPoints);
    }

    public static void getCol(int[][] board, int dim, int x) {
        int bomb = 0;
        int simPoints = 0;

        for(int i = 0; i < dim; ++i) {
            simPoints += board[i][x - 1];
            if (board[i][x - 1] == 0) {
                ++bomb;
            }
        }

        System.out.println("Bombs: " + bomb);
        System.out.println("Points: " + simPoints);
    }

    public static void chooseC(int[][] board, int dim) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Column(c) or Row(r)");
        String choose = checkIn2(sc.nextLine());
        if (choose.equals("c")) {
            System.out.println("Which column do you want to check");
        } else {
            System.out.println("Which row do you want to check");
        }

        int x = checkGuess(sc.nextInt(), dim);
        if (choose.equals("c")) {
            getCol(board, dim, x);
        }

        if (choose.equals("r")) {
            getRow(board, dim, x);
        }

    }

    public static String checkIn1(String choose) {
        Scanner sc = new Scanner(System.in);
        if (!choose.equals("g") && !choose.equals("c") && !choose.equals("m")) {
            System.out.println("That is invalid. Please enter either g to guess, m to mark a tile, or c to check a column/row");
            choose = sc.nextLine();
            return checkIn1(choose);
        } else {
            return choose;
        }
    }

    public static String checkIn2(String choose) {
        Scanner sc = new Scanner(System.in);
        if ((choose.equals("r") || choose.equals("c")) && choose.length() == 1) {
            return choose;
        } else {
            System.out.println("That is invalid. Please enter either r to check a row, or c to check a column");
            choose = sc.nextLine();
            return checkIn2(choose);
        }
    }

    public static int checkGuess(int guess, int dim) {
        Scanner sc = new Scanner(System.in);
        if (guess >= 1 && guess <= dim) {
            return guess;
        } else {
            System.out.println("That is invalid. Please enter a guess between 1 and " + dim + ".");
            guess = sc.nextInt();
            return checkGuess(guess, dim);
        }
    }

    public static char[][] checkTile(int x, int y, int dim, char[][] simBoard, String choose) {
        Scanner sc = new Scanner(System.in);
        if (simBoard[y - 1][x - 1] == 'g') {
            System.out.println("That tile has already been flipped. Please enter a new tile");
            System.out.println("what is the column of the tile");
            x = sc.nextInt();
            x = checkGuess(x, dim);
            System.out.println("What row of the tile");
            y = sc.nextInt();
            y = checkGuess(y, dim);
            return checkTile(x, y, dim, simBoard, choose);
        } else {
            if (simBoard[y - 1][x - 1] == 'm') {
                if (choose.equals("m")) {
                    simBoard[y - 1][x - 1] = '?';
                } else {
                    simBoard[y - 1][x - 1] = 'g';
                }
            } else {
                simBoard[y - 1][x - 1] = choose.charAt(0);
            }

            return simBoard;
        }
    }

    public static String checkStop(String stop) {
        Scanner sc = new Scanner(System.in);
        if (!stop.equals("y") && !stop.equals("n")) {
            System.out.println("That is invalid. Please enter either y or n");
            stop = sc.nextLine();
            return checkStop(stop);
        } else {
            return stop;
        }
    }
}
