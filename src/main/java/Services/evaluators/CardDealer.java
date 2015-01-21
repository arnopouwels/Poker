package Services.evaluators;


import Services.Cards.*;

import java.util.ArrayList;
import java.util.Random;

public class CardDealer
{
    private static Random generatorO = new Random(System.currentTimeMillis());
    private static Deck deck = new Deck(generatorO);


    public static Hand dealCards()
    {
      return new Hand(deck.takeCard(), deck.takeCard(), deck.takeCard(), deck.takeCard(), deck.takeCard());
    }

}
