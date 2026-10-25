import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> cards = new ArrayList<Card>();

    public Deck() {
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] suits = {"H", "D", "C", "S"};

        for (String rank : ranks) {
            for (String suit : suits) {
                cards.add(new Card(rank, suit));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card deal() {
        return cards.remove(0);
    }
}
