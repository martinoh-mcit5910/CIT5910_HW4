import java.util.Scanner;

public class BlackjackSolitaire {
    private Deck deck = new Deck();
    private Card[] state = new Card[20];
    private int discardsLeft = 4;

    public void play() {
        Scanner s = new Scanner(System.in);
        deck.shuffle();
        int filled = 0;

        while (filled < 16) {
            Card card = deck.deal();
            printGrid();
            System.out.println("Your card: " + card);

            int choice = playerChoice(s);
            state[choice - 1] = card;

            if (choice > 16) {
                discardsLeft--;
            } else {
                filled++;
            }
        }

        printGrid();
        System.out.println("Game over! You scored " + totalSum() + " points.");
    }

    private void printGrid() {
        System.out.println(show(1) + "\t" + show(2) + "\t" + show(3) + "\t" + show(4) + "\t" + show(5));
        System.out.println(show(6) + "\t" + show(7) + "\t" + show(8) + "\t" + show(9) + "\t" + show(10));
        System.out.println("\t" + show(11) + "\t" + show(12) + "\t" + show(13) + "\t");
        System.out.println("\t" + show(14) + "\t" + show(15) + "\t" + show(16));
        System.out.println("Discards remaining: " + discardsLeft);
    }

    private String show(int pos) {
        if (state[pos - 1] == null) {
            return "" + pos;
        }
        return state[pos - 1].toString();
    }

    private boolean isValid(int choice) {
        if (choice < 1 || choice > 20) {
            System.out.println("Invalid slot. Please try again.");
            return false;
        } else if (state[choice - 1] != null) {
            System.out.println("It's already filled. Please try again.");
            return false;
        }
        return true;
    }

    private int playerChoice(Scanner s) {
        int choice = 0;
        boolean valid = false;

        while (!valid) {
            System.out.println("Select a spot to place the card (1-16). 17-20 are for discards.");

            if (s.hasNextInt()) {
                choice = s.nextInt();
                valid = isValid(choice);
            } else {
                System.out.println("Please enter a number.");
                s.next();
            }
        }
        return choice;
    }

    private int partialSum(int[] positions) {
        int sum = 0;
        boolean hasAce = false;

        for (int pos : positions) {
            int cardVal = state[pos - 1].getVal();
            sum += cardVal;
            if (cardVal == 1) {
                hasAce = true;
            }
        }

        if (hasAce && sum + 10 <= 21) {
            sum += 10;
        }

        if (sum > 21) {
            return 0;
        } else if (sum == 21 && positions.length == 2) {
            return 10;
        } else if (sum == 21) {
            return 7;
        } else if (sum == 20) {
            return 5;
        } else if (sum == 19) {
            return 4;
        } else if (sum == 18) {
            return 3;
        } else if (sum == 17) {
            return 2;
        } else {
            return 1;
        }
    }

    private int totalSum() {
        int[][] rows = {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13},
                {14, 15, 16}
        };
        int[][] cols = {
                {1, 6},
                {2, 7, 11, 14},
                {3, 8, 12, 15},
                {4, 9, 13, 16},
                {5, 10}
        };

        int totalSum = 0;
        for (int r = 0; r < rows.length; r++) {
            totalSum += partialSum(rows[r]);
        }
        for (int c = 0; c < cols.length; c++) {
            totalSum += partialSum(cols[c]);
        }
        return totalSum;
    }
}