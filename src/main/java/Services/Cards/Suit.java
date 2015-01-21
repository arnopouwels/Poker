package Services.Cards;

public enum Suit {

    S ("S"), H("H"), D("D"), C("C");

    private final String symbol;
    private Suit (String symbol)
    {
        this.symbol = symbol;
    }

    @Override
    public String toString()
    {
        return  symbol;
    }
}
