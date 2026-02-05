import java.util.Random;

public class DeckOfCards
{
    public static void main(String[] args)
    {
        // 4 suits, 13 cards each
        String[][] deckOfCards = new String[4][13];

        String[] suits = {"Diamonds", "Hearts", "Clubs", "Spades"};
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7",
                          "8", "9", "10", "Jack", "Queen", "King"};

        // Fill the deck
        for (int row = 0; row < deckOfCards.length; row++)
        {
            for (int col = 0; col < deckOfCards[row].length; col++)
            {
                deckOfCards[row][col] = ranks[col] + " of " + suits[row];
            }
        }

        // Shuffle the deck
        Random rand = new Random();

        for (int i = 0; i < 1000; i++)
        {
            int r1 = rand.nextInt(4);
            int c1 = rand.nextInt(13);
            int r2 = rand.nextInt(4);
            int c2 = rand.nextInt(13);

            String temp = deckOfCards[r1][c1];
            deckOfCards[r1][c1] = deckOfCards[r2][c2];
            deckOfCards[r2][c2] = temp;
        }

        // Deal 4 hands of 5 cards
        int cardIndex = 0;

        System.out.println("Dealing Hands: \n");

        for (int hand = 0; hand < 4; hand++)
        {
            System.out.println(suits[hand] + " Hand:");

            for (int card = 0; card < 5; card++)
            {
                int row = cardIndex / 13;
                int col = cardIndex % 13;

                System.out.println(deckOfCards[row][col]);
                cardIndex++;
            }

            System.out.println();
        }
    }
}
