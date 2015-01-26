package Services.Cards;

import javax.persistence.*;
import java.util.*;

public class Hand {

    private ArrayList<Card> cards = new ArrayList<Card>();

    private String cardOne = "";
    private String cardTwo = "";
    private String cardThree = "";
    private String cardFour = "";
    private String cardFive = "";

    public Hand()
    {

    }

    public void makeHand(String c1, String c2, String c3, String c4, String c5)
    {
        //public Hand(... cardReprese)
        Card card1 = new Card(c1);
        cards.add(card1);
        Card card2 = new Card(c2);
        cards.add(card2);
        Card card3 = new Card(c3);
        cards.add(card3);
        Card card4 = new Card(c4);
        cards.add(card4);
        Card card5 = new Card(c5);
        cards.add(card5);

    }

    public int size()
    {
        return cards.size();
    }

    public Hand(Card c1, Card c2, Card c3, Card c4, Card c5)
    {
        cards.add(c1);
        cards.add(c2);
        cards.add(c3);
        cards.add(c4);
        cards.add(c5);
    }
    public ArrayList<Card> getCards()
    {
        return cards;
    }

    public Card getCardAt(int i)
    {
        if(i < 5)
            return cards.get(i);
        return null;
    }

    @Override
    public String toString()
    {
        //StringJoiner = sit self as { , , }
        StringBuilder sb = new StringBuilder();

        sb.append('{');
        for(int i = 0; i < cards.size() - 1; i++)
            sb.append(cards.get(i) + ", ");
        sb.append(cards.get(cards.size() - 1) + "}");

        return sb.toString();
    }
}
