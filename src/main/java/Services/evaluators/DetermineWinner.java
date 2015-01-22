package Services.evaluators;

public class DetermineWinner
{
    public String[] determinePotentialWinners(PokerHand[] pokerHands)
    {
        String[] message = new String[pokerHands.length];
        for(int i = 0; i < message.length; i++)
            message[i] = "";

        int bestRank = 0;
        for(PokerHand ph : pokerHands)
        {
            int temp = ph.getPokerHandState().ordinal();
            if(temp > bestRank)
                bestRank = temp;
        }

        for(int i = 0; i < pokerHands.length; i++)
        {
            int temp = pokerHands[i].getPokerHandState().ordinal();
            if(temp == bestRank)
                message[i] = "Tied...";
        }

        return message;
    }

    public String[] checkWinnerIfTies(String[] messages, PokerHand[] pokerHands)
    {
        int bestDeterminant = 0;
        int index = 0;
        int count = 0;
        for(int i = 0; i < pokerHands.length; i++)
        {
            if(!messages[i].equals(""))
            {
                count++;
                if(pokerHands[i].getDeterminant() > bestDeterminant)
                {
                    bestDeterminant = pokerHands[i].getDeterminant();
                    index = i;
                }
            }
        }

        if(count == 1)
            messages[index] = "Winner!";
        else
            messages[index] = "Tied... Winner!";
        return messages;
    }
}
