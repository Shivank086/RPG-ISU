import java.util.Scanner;

class shop {
    public static String[][] shopHold = new String[][]{{"Dragonite", "Alakazam", "Laprass", "Scyther", "Eevee", "Porygon"}, {"99999", "40000", "25000", "15000", "5000", "5000"}, {"Type: Dragon & Flying\nWeight: 2463.0lbs\nHeight: 7'03\"\nClassification: Dragon Pokemon\nPokedex Entry: Dragonite is capable of circling the globe in just 16 hours. It is a kindhearted Pokémon that leads lost and foundering ships in a storm to the safety of land\nCost: 99,999\n", "Type: Psychic\nWeight: 105.8lbs\nHeight: 4'11\"\nClassification: Psi Pokemon\nPokedex Entry: Alakazam's brain continually grows, infinitely multiplying brain cells. This amazing brain gives this Pokémon an astoundingly high IQ of 5,000. It has a thorough memory of everything that has occurred in the world\nCost: 40,000\n", "Type: Water & Ice\nWeight: 485.0lbs\nHeight: 8'02\"\nClassification: Transport Pokemon\nPokedex Entry: \tIt loves crossing the sea with people and Pokémon on its back. It understands human speech\nCost: 25,000\n", "Type: Bug & Flying\nWeight: 123.5lbs\nHeight: 4'11\"\nClassification: Mantis Pokemon\nPokedex Entry: Scyther is blindingly fast. Its blazing speed enhances the effectiveness of the twin scythes on its forearms. This Pokémon's scythes are so effective, they can slice through thick logs in one wicked stroke.\nCost: 15,000\n", "Type: Normal\nWeight: 14.3lbs\nHeight: 1'00\"\nIt can evolve into a variety of forms. Eevee’s genes are the key to solving the mysteries of Pokémon evolution\nCost: 5,000\n", "Type: Normal\nWeight: 80.5lbs\nHeight: 2'07\"\nClassification: Virtual Pokemon\nPokedex Entry: Porygon is capable of reverting itself entirely back to program data and entering cyberspace. This Pokémon is copy protected so it cannot be duplicated by copying\nCost: 5,000\n"}};
    public static String[] party = new String[6];

    shop() {
    }

    public static int shop(int points) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the shop. Here you can use points that you have earnt to buy special pokemon that can never be found anywhere else.");

        String choose;
        do {
            System.out.println("You currently have " + points + " points.\nWould you like to view our products(c), view the pokemon you have bought(p), buy a pokemon(b), or leave the shop(l)");
            choose = inCheck(sc.nextLine());
            int i;
            if (choose.equals("c")) {
                System.out.println("Would you like the information of each available pokemon as well(y/n)?");
                choose = inCheck2(sc.nextLine());
                System.out.println("Here are the pokemon you can buy:");

                for(i = 0; i < 6; ++i) {
                    System.out.println(shopHold[0][i]);
                    if (choose.equals("y")) {
                        System.out.println(shopHold[2][i]);
                    }
                }
            } else if (choose.equals("p")) {
                if (empty(party)) {
                    System.out.println("You have not bought any pokemon yet");
                } else {
                    System.out.println("Would you like the information of each available pokemon as well(y/n)?");
                    choose = inCheck2(sc.nextLine());
                    System.out.println("Your pokemon are the following: ");

                    for(i = 0; i < 6; ++i) {
                        if (party[i] != null) {
                            System.out.println(party[i]);
                            if (choose.equals("y")) {
                                System.out.println(shopHold[2][i]);
                            }
                        }
                    }
                }
            } else if (choose.equals("b")) {
                System.out.println("What pokemon do you want to buy(case sensitive first letter capital)");
                String pokemon = pokeCheck(sc.nextLine());
                points = buy(pokemon, points);
            }
        } while(!choose.equals("l"));

        return points;
    }

    public static boolean empty(String[] arr) {
        for(int i = 0; i < arr.length; ++i) {
            if (arr[i] != null) {
                return false;
            }
        }

        return true;
    }

    public static int buy(String pokemon, int points) {
        int index = 0;

        for(int i = 0; i < 6; ++i) {
            if (shopHold[0][i].equals(pokemon)) {
                index = i;
            }
        }

        if (points < Integer.parseInt(shopHold[1][index])) {
            System.out.println("You do not have enough points to buy that");
        } else if (shopHold[0][index].equals("")) {
            System.out.println("You have already bought that pokemon");
        } else {
            System.out.println("You have added " + pokemon + " to your party");
            party[index] = shopHold[0][index];
            shopHold[0][index] = "";
            points -= Integer.parseInt(shopHold[1][index]);
        }

        return points;
    }

    public static String pokeCheck(String pokemon) {
        Scanner sc = new Scanner(System.in);
        if (!pokemon.equals("Dragonite") && !pokemon.equals("Alakazam") && !pokemon.equals("Laprass") && !pokemon.equals("Scyther") && !pokemon.equals("Eevee") && !pokemon.equals("Porygon")) {
            System.out.println("That is invalid. Please enter a pokemon from the available list. Only the first letter must be camptalized.");
            return pokeCheck(sc.nextLine());
        } else {
            return pokemon;
        }
    }

    public static String inCheck(String choose) {
        Scanner sc = new Scanner(System.in);
        if (!choose.equals("c") && !choose.equals("p") && !choose.equals("b") && !choose.equals("l")) {
            System.out.println("That is an invalid input. Please enter either c to view the products, p to view the pokemon you have bought, b to buy a pokemon, or l to leave the shop");
            return inCheck(sc.nextLine());
        } else {
            return choose;
        }
    }

    public static String inCheck2(String choose) {
        Scanner sc = new Scanner(System.in);
        if (!choose.equals("y") && !choose.equals("n")) {
            System.out.println("That is an invalid input. Please enter either y to recieve the information of each pokemon, or n to not recieve that information");
            return inCheck(sc.nextLine());
        } else {
            return choose;
        }
    }
}