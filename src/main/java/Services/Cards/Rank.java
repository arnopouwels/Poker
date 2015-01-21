package Services.Cards;

/**
 * Created by Zuhnja on 1/9/2015.
 */
public enum Rank {
    A ("A"), two("2"), three("3"), four("4"), five("5"), six("6"), seven("7"), agt("8"), NEGE("9"), TIEN("10"), JACK("J"), QUEEN("Q"), KING("K");

    private final String symbol;
    private Rank (String symbol)
    {
        this.symbol = symbol;
    }

    @Override
    public String toString()
    {
        return  symbol;
    }





}
