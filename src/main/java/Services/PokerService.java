package Services;

import Services.Cards.Hand;
import Services.evaluators.CardDealer;
import Services.evaluators.HandEvaluator;
import com.google.inject.Inject;
import com.google.inject.Singleton;



@Singleton
public class PokerService implements IpokerService
{
    private CardDealer carddealer = new CardDealer();
    private HandEvaluator handEvaluator = new HandEvaluator();
    private Hand hand;
    private String handEvalAs;

    //String evaluateHand(Hand hand);
    //add highCard
    public String getName()
    {
        return "Sharah";
    }

    public Hand getHand()
    {
        return hand;
    }

    public String getEvaluatedHand()
    {
        return handEvalAs;
    }

    public void deal()
    {
        hand = carddealer.dealCards();
        handEvalAs = handEvaluator.evaluateHand(hand);
    }

}
