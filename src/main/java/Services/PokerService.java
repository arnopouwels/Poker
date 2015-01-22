package Services;

import Services.Cards.Deck;
import Services.Cards.Hand;
import Services.evaluators.CardDealer;
import Services.evaluators.DetermineWinner;
import Services.evaluators.HandEvaluator;
import Services.evaluators.PokerHand;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.Random;

@Singleton
public class PokerService implements IpokerService
{
    int numHands = 4;
    private CardDealer carddealer = new CardDealer();
    private HandEvaluator handEvaluator = new HandEvaluator();
    private Hand[] hands = new Hand[numHands];
    private String handEvalAs[] = new String[numHands];
    private PokerHand[] pokerHands = new PokerHand[numHands];
    private String[] winnerHands = new String[numHands];

    public String getName()
    {
        return "Sharah";
    }

    public Hand getHandAt(int index)
    {
        return hands[index];
    }

    public String[] getEvaluatedHands()
    {
        return handEvalAs;
    }

    public int getNumHands()
    {
        return numHands;
    }


    public void deal()
    {
        Random generator = new Random(System.currentTimeMillis());
        Deck deck = new Deck(generator);
        hands = carddealer.dealCards(deck);

        for(int i = 0; i < hands.length; i++)
        {
            pokerHands[i] = handEvaluator.evaluateHand(hands[i]);
            handEvalAs[i] = pokerHands[i].getPokerHandState().toString();
        }

        DetermineWinner determineWinner = new DetermineWinner();
        winnerHands = determineWinner.determinePotentialWinners(pokerHands);
        winnerHands = determineWinner.checkWinnerIfTies(winnerHands, pokerHands);
    }

    public String[] getWinnerHandsMessages()
    {
        return winnerHands;
    }

    public Hand[] getHands()
    {
        return hands;
    }

}
