package Services;

import Services.Cards.Hand;

/**
 * Created by Zuhnja on 1/12/2015.
 */
public interface IpokerService {
    String getName();
    Hand getHand();
    String getEvaluatedHand();
    void deal();


}
