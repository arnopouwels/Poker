package Services.Cards;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Zuhnja on 2015-01-20.
 */
public class Deck
{
    private ArrayList<Card> deck = new ArrayList<Card>();
    private static Random generator;

    public Deck(Random generator)
    {
        populateDeck();
        this.generator = generator;
    }

    private void populateDeck()
    {
        Rank[] ranks = Rank.values();

        for(Suit suit : Suit.values())
        {
            for(Rank rank: Rank.values())
            {
                deck.add(new Card(rank, suit));
            }
        }
    }

    public Card takeCard()
    {
        System.out.println(deck.size() - 1);
        return deck.remove(randomIntGenerator(deck.size() - 1));
    }

    private static int randomIntGenerator(int maxRange)
    {
        int num = generator.nextInt(maxRange + 1);
        return num;
    }
}
