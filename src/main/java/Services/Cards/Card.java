package Services.Cards;

public class Card {

    private Suit suit;
    private Rank rank;

    public String getSuit()
    {
        return suit.toString();
    }

    public String getRankString(){return rank.toString();}

    public Suit getSuitObject()
    {
        return suit;
    }

    public Card(String str)
    {
        if(str.length() == 2)
        {
            getSuit(str.charAt(1));
            getRank(str.substring(0, 1));
        }
        else
        {
            getSuit(str.charAt(2));
            getRank(str.substring(0, 2));
        }
        getRankInt();
    }

    public Card(Rank rank, Suit suit)
    {
        this.rank = rank;
        this.suit = suit;
    }

    public int getRankInt()
    {
        return rank.ordinal() + 1;
    }

    public Rank getRank()
    {
        return rank;
    }


    private void getRank(String s)
    {
        switch(s)
        {
            case "A":
                rank = Rank.A;
                break;
            case "J":
                rank = Rank.JACK;
                break;
            case "Q":
                rank = Rank.QUEEN;
                break;
            case "K":
                rank = Rank.KING;
                break;
            case "2":
                rank = Rank.two;
                break;
            case "3":
                rank = Rank.three;
                break;
            case "4":
                rank = Rank.four;
                break;
            case "5":
                rank = Rank.five;
                break;
            case "6":
                rank = Rank.six;
                break;
            case "7":
                rank = Rank.seven;
                break;
            case "8":
                rank = Rank.agt;
                break;
            case "9":
                rank = Rank.NEGE;
                break;
            case "10":
                rank = Rank.TIEN;
                break;
        }
    }

    private void getSuit(Character c)
    {
        //String s= c.toString();
        //suit = new Suit(c);
        switch (c)
        {
            case 'S':
                suit = Suit.S;
                break;
            case 'D':
                suit = Suit.D;
                break;
            case 'C':
                suit = Suit.C;
                break;
            case 'H':
                suit = Suit.H;
                break;
        }
    }

    @Override
   public String toString()
    {
        return  rank.toString()+ suit.toString();
    }

}
