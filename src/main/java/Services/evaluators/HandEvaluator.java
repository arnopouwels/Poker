package Services.evaluators;

import Services.Cards.Card;
import Services.Cards.Hand;
import Services.Cards.Rank;
import Services.Cards.Suit;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HandEvaluator {
    public String evaluateHand(Hand hand)
    {
        if(isStraight_Func(hand))
            return "Straight flush!";
        else if(isFourOfAKind_Imp(hand))
            return "Four of a kind!";
        else if(isFullHouse_imp(hand))
            return "Full House!";
        else if(isFlush_Func(hand))
            return "Flush!";
        else if(isStraight_Imp(hand))
            return "Straight";
        else if(is3OfAKind_imp(hand))
            return "Three of a kind!";
        else if(isTwoPair_imp(hand))
            return "Two Pair!";
        else if(isOnePair_imp(hand))
            return "One Pair!";
        else
            return "High card...";


    }

    public static boolean isStraightFlush_Func(Hand hand) {
        List<Rank> ranks = hand.getCards().stream().map(c -> c.getRank()).collect(Collectors.toList());

        return (hand.getCards().stream().allMatch(c -> c.getSuit() == hand.getCards().get(0).getSuit())) //kyk dat al die kaarte dieselfde suit het;
                && (ranks.stream().mapToInt(r -> r.ordinal()).max().getAsInt() - ranks.stream().mapToInt(r -> r.ordinal()).min().getAsInt() == 4)
                && ranks.stream().distinct().count() == 5;
    }

    public static boolean isStraightFlush_Imp(Hand hand) {
        //is Straight flush: JS, 10S, 9S, 8S, 7S
        if (allOfSameRank(hand) && ranksVolgOpMekaar(hand))
            return true;
        else
            return false;
    }

    public static boolean allOfSameRank(Hand hand) {
        String firstRank = hand.getCards().get(0).getSuit();
        for (Card card : hand.getCards()) {
            if (!card.getSuit().equals(firstRank))
                return false;
        }
        return true;
    }

    public static boolean ranksVolgOpMekaar(Hand hand) {
        hand = sortHand(hand);
        int a = 1;
        for(int i = 0; i < hand.size() - 1; i++)
        {
            if (hand.getCardAt(i + 1).getRankInt() - hand.getCardAt(i).getRankInt() != 1)
                return false;
        }
        return true;
    }

    public static boolean isFlush_Imp(Hand hand) {
        //as all suites match en nie 'n straight flush is nie
        if (allOfSameRank(hand) && !ranksVolgOpMekaar(hand))
            return true;
        else
            return false;
    }

    public static boolean isFlush_Func(Hand hand) {
        //as all suites match en nie 'n straight flush is nie
        List<Rank> ranks = hand.getCards().stream().map(c -> c.getRank()).collect(Collectors.toList());

        return (hand.getCards().stream().allMatch(c -> c.getSuit() == hand.getCards().get(0).getSuit())) //kyk dat al die kaarte dieselfde suit het;
                && !(ranks.stream().mapToInt(r -> r.ordinal()).max().getAsInt() - ranks.stream().mapToInt(r -> r.ordinal()).min().getAsInt() == 4);
    }

    public static boolean isStraight_Imp(Hand hand) {
        //alles volg op mekaar, maar is nie dieselfde suites nie
        if (!allOfSameRank(hand) && ranksVolgOpMekaar(hand))
            return true;
        else
            return false;
    }

    public static boolean isStraight_Func(Hand hand) {
        List<Rank> ranks = hand.getCards().stream().map(c -> c.getRank()).collect(Collectors.toList());

        return !(hand.getCards().stream().allMatch(c -> c.getSuit() == hand.getCards().get(0).getSuit()))
                && (ranks.stream().mapToInt(r -> r.ordinal()).max().getAsInt() - ranks.stream().mapToInt(r -> r.ordinal()).min().getAsInt() == 4)
                && ranks.stream().distinct().count() == 5;
    }

    //pom sit in model. die packages
    public static boolean isFourOfAKind_Imp(Hand hand) {
        //4 met dieselfde rank, en daai vier moet distinct wees
        HashMap map = (HashMap) getMap_RankToSuit_imp(hand);
        Object[] key = map.keySet().toArray();

        if (map.size() == 2) {
            List<Suit> list1 = (List) map.get((Rank) key[0]);
            List<Suit> list2 = (List) map.get((Rank) key[1]);

            if ((list1.size() == 4 && list2.size() == 1) || (list1.size() == 1 && list2.size() == 4))
                return true;
        }
        return false;
    }

    public static boolean isFullHouse_imp(Hand hand)
    {
        HashMap map = (HashMap) getMap_RankToSuit_imp(hand);
        Object[] key = map.keySet().toArray();

        if (map.size() == 2) {
            List<Suit> list1 = (List) map.get((Rank) key[0]);
            List<Suit> list2 = (List) map.get((Rank) key[1]);

            if ((list1.size() == 3 && list2.size() == 2) || (list1.size() == 2 && list2.size() == 3))
                return true;
        }
        return false;
    }

    public static boolean is3OfAKind_imp(Hand hand)
    {
        HashMap map = (HashMap) getMap_RankToSuit_imp(hand);
        Object[] key = map.keySet().toArray();

        if (map.size() == 3) {
            List<Suit> list1 = (List) map.get((Rank) key[0]);
            List<Suit> list2 = (List) map.get((Rank) key[1]);
            List<Suit> list3 = (List) map.get((Rank) key[2]);

            if ((list1.size() == 3 || list2.size() == 3 || list3.size() == 3))
                return true;
        }
        return false;
    }

    public static boolean isTwoPair_imp(Hand hand)
    {
        HashMap map = (HashMap) getMap_RankToSuit_imp(hand);
        Object[] key = map.keySet().toArray();

        if (map.size() == 3) {
            List<Suit> list1 = (List) map.get((Rank) key[0]);
            List<Suit> list2 = (List) map.get((Rank) key[1]);
            List<Suit> list3 = (List) map.get((Rank) key[2]);

            if ((list1.size() == 2 && list2.size() == 2) || (list2.size() == 2 && list3.size() == 2) || (list1.size() == 2 && list3.size() == 2 ))
                return true;
        }
        return false;
    }

    public static boolean isOnePair_imp(Hand hand)
    {
        HashMap map = (HashMap) getMap_RankToSuit_imp(hand);
        Object[] key = map.keySet().toArray();

        if (map.size() == 4)
        {
            List<Suit> list1 = (List) map.get((Rank) key[0]);
            List<Suit> list2 = (List) map.get((Rank) key[1]);
            List<Suit> list3 = (List) map.get((Rank) key[2]);
            List<Suit> list4 = (List) map.get((Rank) key[3]);

            if ((list1.size() == 2 || list2.size() == 2 || list3.size() == 2 || list4.size() == 2))
                return true;
        }
        return false;
    }

    public static boolean isHighCard_imp(Hand hand)
    {
        HashMap map = (HashMap) getMap_RankToSuit_imp(hand);
        Object[] key = map.keySet().toArray();

        for(int i = 0; i < key.length; i++)
        {
            List<Suit> list = (List) map.get((Rank) key[i]);
            if(list.size() != 1)
                return false;
        }
        return true;
    }

    public static boolean isFourOfAKind_Func(Hand hand)
    {
        //4 met dieselfde rank, en daai vier moet distinct wees
        Map<Rank, List<Card>> map = hand.getCards().stream().collect(Collectors.groupingBy(Card::getRank));

        return false;
    }

    public static Map getMap_RankToSuit_imp(Hand hand)//distinct
    {
        Map<Rank, List<Suit>> map = new HashMap<Rank, List<Suit>>();

        for(Card card : hand.getCards())
        {
            //5S
            if(map.containsKey(card.getRank()))//contains 5
            {
                if(!map.get(card.getRank()).contains(card.getSuitObject()))//S
                {
                    List<Suit> list = map.get(card.getRank());
                    list.add(card.getSuitObject());
                    map.put(card.getRank(), list);
                }
            }
            else
            {
                List<Suit> list = new ArrayList<>();
                list.add(card.getSuitObject());
                map.put(card.getRank(), list);
            }
        }
        return map;
    }















    /*public static boolean anderIsStraightFlush(Hand hand)
    {
        List<Rank> ranks = hand.getCards().stream().map(c -> c.getRank()).collect(Collectors.toList());


        //eenders as: List<Rank> newRanks = new ArrayList<>();
        //hand.getCards.stream().map(c _> c.getRank().forEach(r -> iets)

        //kyk na groupingby


        return hand.getCards().stream().allMatch(c -> c.getSuit() == hand.getCards().get(0).getSuit())// kyk of strait
            && ranks.stream().mapToInt(r -> r.ordinal().max.getAsInt() - ranks.stream().mapToInt(r -> r.ordinal().min().getAsInt()) == 4)
                && ranks.stream().distinct().count == 5;

        //voorbeeld: ranks.stram().map(r -> r.ordinal()).reduce(null, (int i,int p) -> if (i > p) return i else return p);
        /*
        @Test
        public void example()
        {
            IntStream stream = IntStream.iterate(0, i -> i + 1);
            stream.limit(100).forEach(System.out::println());
            //.skip(100)

        }



        return true;
    }*/






    private static Hand sortHand(Hand hand)
    {
        Collections.sort(hand.getCards(), new Comparator<Card>()
        {
            public int compare(Card o1, Card o2)
            {
                return o1.getRankInt() - o2.getRankInt();
            }
        });
        return hand;
    }

    private static int[] ranksInHand(Hand hand)
    {
        int[] ranks = new int[5];
        for(int i = 0; i < 5; i++)
            ranks[i] = hand.getCards().get(i).getRankInt();
        return ranks;
    }


}
