import java.util.Scanner;

public class BlackjackSolitaire {
    private Deck deck = new Deck();
    private Card[] state = new Card[20];
    private int discardsLeft = 4;


    private void printGrid() {
        System.out.println(show(1) + "\t" + show(2) + "\t" + show(3) + "\t" + show(4) + "\t" + show(5));
        System.out.println(show(6) + "\t" + show(7) + "\t" + show(8) + "\t" + show(9) + "\t" + show(10));
        System.out.println("\t" + show(11) + "\t" + show(12) + "\t" + show(13) + "\t");
        System.out.println("\t" + show(14) + "\t" + show(15) + "\t" + show(16));
        System.out.println("Discards remaining: " + discardsLeft);
    }

    private String show(int spot) {
        if (state[spot - 1] == null) {
            return "" + spot;
        }
        return state[spot - 1].toString();
    }

    private boolean isValid(int choice) {
        if (choice < 1 || choice > 20) {
            System.out.println("Invalid slot. Please try again.");
            return false;
        } else if (state[choice-1] != null) {
            System.out.println("It's already filled. Please try again.");
            return false;
        }
        return true;
    }

    private int playerChoice (Scanner s) {
        int choice = 0;
        boolean valid = false;

        while (valid == true) {
            System.out.println("Select a spot to place the card (1-16). 17-20 are for discards.");

            if (s.hasNextInt()) {
                choice = s.nextInt();
                valid = isValid(choice);
            } else {
                System.out.println("PLease enter a number.");
            }
        }
        return choice;
    }
}
