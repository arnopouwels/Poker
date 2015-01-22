package Services.evaluators;

public enum PokerHandState
{
    highCard ("High card..."), onePair("One Pair!"), twoPair("Two Pair!"), threeOfAKind("Three of a kind!"), straight("Straight"), flush("Flush!"), fullHouse("Full House!"), fourOfAKind("Four of a kind!"), straightFlush("straightFlush");

    private final String symbol;
    private PokerHandState(String symbol)
    {
        this.symbol = symbol;
    }

    @Override
    public String toString()
    {
        return  symbol;
    }
}
