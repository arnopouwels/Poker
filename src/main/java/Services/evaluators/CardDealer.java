package Services.evaluators;


import Services.Cards.*;

import java.util.ArrayList;
import java.util.Random;

public class CardDealer
{
    public static Hand[] dealCards(Deck deck)
    {
        Hand[] hands = new Hand[4];
        for(int i = 0; i < hands.length; i++)
        {
            hands[i] = new Hand(deck.takeCard(), deck.takeCard(), deck.takeCard(), deck.takeCard(), deck.takeCard());
        }
        return hands;
    }

}
