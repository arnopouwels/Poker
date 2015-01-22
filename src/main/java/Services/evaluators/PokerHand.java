package Services.evaluators;

import Services.Cards.Hand;
import Services.Cards.Rank;
import Services.Cards.Suit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PokerHand
{
    private PokerHandState pokerHandState = null;
    private int determinant = 0;

    public PokerHand(Hand hand, PokerHandState pokerHandState)
    {
        this.pokerHandState = pokerHandState;
        switch(pokerHandState.toString())
        {
            case "straightFlush":
            case "Flush!":
            case "Straight":
            case "High card...":
                determinant = highestCard(hand);
                break;
            case "Four of a kind!":
                determinant = determinantOfCOunt(hand, 4);
                break;
            case "Full House!":
            case "Three of a kind!":
                determinant = determinantOfCOunt(hand, 3);
                break;
            case "Two Pair!":
                determinant = highestDeterminnatOF2Pair(hand);
                break;
            case "One Pair!":
                determinant = determinantOfCOunt(hand, 2);
                break;
        }
    }

    public int getDeterminant()
    {
        return determinant;
    }

    public PokerHandState getPokerHandState()
    {
        return pokerHandState;
    }

    private int highestDeterminnatOF2Pair(Hand hand)
    {
        int[] cardValues = new int[hand.size()];
        for(int i = 0; i < hand.size(); i++)
            cardValues[i] = hand.getCardAt(i).getRankInt();

        HashMap map = getMap(cardValues);
        System.out.println(map);

        Object[] key = map.keySet().toArray();
        int[] count = new int[2];
        int c = 0;

        for(int i = 0; i < key.length; i++)
        {
            if((Integer)map.get((Integer)key[i]) == 2)
                count[c++] = (Integer)key[i];
        }

        if(count[0] > count[1])
            return count[0];
        else
            return count[1];
    }

    private int determinantOfCOunt(Hand hand, int num)
    {
        int[] cardValues = new int[hand.size()];
        for(int i = 0; i < hand.size(); i++)
            cardValues[i] = hand.getCardAt(i).getRankInt();

        HashMap map = getMap(cardValues);

        Object[] key = map.keySet().toArray();

        for(int i = 0; i < key.length; i++)
        {
            if((Integer)map.get((Integer)key[i]) == num)
                return ((Integer) key[i]);
        }
        return -1;
    }

    private HashMap getMap(int[] values)
    {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        for(int i = 0; i < values.length; i++)
        {
            if(map.containsKey(values[i]))
                map.put(values[i], map.get(values[i]) + 1);
            else
                map.put(values[i], 1);
        }

        return map;
    }

    private int highestCard(Hand hand)
    {
        return hand.getCardAt(hand.size() - 1).getRankInt();
    }


}
