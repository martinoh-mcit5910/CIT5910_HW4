public class Card {
    private String rank;
    private String suit;

    public Card (String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public String toString() {
        return (rank + suit);
    }

    public int getVal() {
        if (rank.equals("J") || rank.equals("Q") || rank.equals("K")) {
            return 10;
        } else if (rank.equals("A")) {
            return 1;
        } else {
            return Integer.parseInt(rank);
        }
    }
}
