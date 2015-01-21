package evaluators;

import Services.Cards.Hand;
import Services.evaluators.HandEvaluator;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class HandEvaluatorTest {

    //____________________________________________________________________Straight Flush
    @Test
    public void shouldMatchStraightFlush_imp()
    {
        Hand hand1 = new Hand("2S", "3S", "4S", "5S", "6S");
        Hand hand2 = new Hand("3S", "5S", "4S", "2S", "6S");

        assertTrue(HandEvaluator.isStraightFlush_Imp(hand1));
        assertTrue(HandEvaluator.isStraightFlush_Imp(hand2));
    }

    @Test
    public void shouldNotMatchStraightFlush_imp()
    {
        Hand hand1 = new Hand("2S", "3H", "AD", "10C", "KD");
        Hand hand2 = new Hand("2S", "3S", "AS", "10S", "KS");

        assertFalse(HandEvaluator.isStraightFlush_Imp(hand1));
        assertFalse(HandEvaluator.isStraightFlush_Imp(hand2));
    }

    @Test
    public void shouldMatchStraightFlush_func()
    {
        Hand hand1 = new Hand("2S", "7S", "4S", "5S", "6S");
        assertTrue(HandEvaluator.isStraightFlush_Func(hand1));
    }

    @Test
    public void shouldNotMatchFlush_func()
    {
        Hand hand1 = new Hand("2S", "3S", "4S", "5S", "6S");
        Hand hand2 = new Hand("2S", "3H", "4S", "5S", "6S");

        assertFalse(HandEvaluator.isStraightFlush_Func(hand1));
        assertFalse(HandEvaluator.isStraightFlush_Func(hand2));
    }

    //____________________________________________________________________Is Flush
    @Test
    public void shouldMatchFlush_Imp()
    {
        Hand hand1 = new Hand("2S", "10S", "4S", "5S", "6S");

        assertTrue(HandEvaluator.isFlush_Imp(hand1));
    }

    @Test
    public void shouldNotMatchFlush_Imp()
    {
        Hand hand1 = new Hand("2S", "3S", "4S", "5S", "6S");
        Hand hand2 = new Hand("2S", "3S", "AS", "10C", "KS");

        assertFalse(HandEvaluator.isFlush_Imp(hand1));
        assertFalse(HandEvaluator.isFlush_Imp(hand2));
    }

    @Test
    public void shouldMatchFlush_Func()
    {
        Hand hand1 = new Hand("2S", "10S", "4S", "5S", "6S");

        assertTrue(HandEvaluator.isFlush_Func(hand1));
    }

    @Test
    public void shouldNotMatchFlush_Func()
    {
        Hand hand1 = new Hand("2S", "3S", "4S", "5S", "6S");
        Hand hand2 = new Hand("2S", "3S", "AS", "10C", "KS");

        assertFalse(HandEvaluator.isFlush_Func(hand1));
        assertFalse(HandEvaluator.isFlush_Func(hand2));
    }

    //____________________________________________________________________Is Straight
    //alles volg op mekaar, maar is nie dieselfde suites nie
    @Test
    public void shouldMatchStraight_Imp()
    {
        Hand hand1 = new Hand("2S", "3S", "4C", "5C", "6S");
        Hand hand2 = new Hand("2S", "3C", "4H", "5D", "6S");

        assertTrue(HandEvaluator.isStraight_Imp(hand1));
        assertTrue(HandEvaluator.isStraight_Imp(hand2));
    }

    @Test
    public void shouldNotMatchStraight_Imp()
    {
        Hand hand1 = new Hand("2S", "3S", "4S", "5S", "6S");
        Hand hand2 = new Hand("2S", "3S", "7C", "5C", "6S");
        Hand hand3 = new Hand("9S", "10S", "JC", "KC", "KS");

        assertFalse(HandEvaluator.isStraight_Imp(hand1));
        assertFalse(HandEvaluator.isStraight_Imp(hand2));
        assertFalse(HandEvaluator.isStraight_Imp(hand3));
    }

    @Test
    public void shouldMatchStraight_Func()
    {
        Hand hand1 = new Hand("2S", "3S", "4C", "5C", "6S");
        Hand hand2 = new Hand("2S", "3C", "4H", "5D", "6S");

        assertTrue(HandEvaluator.isStraight_Func(hand1));
        assertTrue(HandEvaluator.isStraight_Func(hand2));
    }

    @Test
    public void shouldNotMatchStraight_Func()
    {
        Hand hand1 = new Hand("2S", "3S", "4S", "5S", "6S");
        Hand hand2 = new Hand("2S", "3S", "7C", "5C", "6S");
        Hand hand3 = new Hand("9S", "10S", "JC", "KC", "KS");

        assertFalse(HandEvaluator.isStraight_Imp(hand1));
        assertFalse(HandEvaluator.isStraight_Imp(hand2));
        assertFalse(HandEvaluator.isStraight_Imp(hand3));
    }

    //____________________________________________________________________Is Four of a Kind
    //4 met dieselfde rank, en daai vier moet distinct wees
    @Test
    public void shouldMatch4OfAKind_Imp()
    {
        Hand hand1 = new Hand("2S", "5S", "2C", "2D", "2H");
        assertTrue(HandEvaluator.isFourOfAKind_Imp(hand1));
    }

    @Test
    public void shouldNotMatch4OfAKind_Imp()
    {
        Hand hand1 = new Hand("2S", "5S", "3C", "2D", "2H");
        Hand hand2 = new Hand("2S", "5S", "2S", "2D", "2H");
        Hand hand3 = new Hand("2S", "5S", "2S", "2D", "2H");

        assertFalse(HandEvaluator.isFourOfAKind_Imp(hand1));
        assertFalse(HandEvaluator.isFourOfAKind_Imp(hand2));
        assertFalse(HandEvaluator.isFourOfAKind_Imp(hand3));
    }

    /*
    @Test
    public void shouldMatch4OfAKind_Func()
    {
        Hand hand1 = new Hand("2S", "5S", "2C", "2D", "2H");
        assertTrue(HandEvaluator.isFourOfAKind_Func(hand1));
    }

    @Test
    public void shouldNotMatch4OfAKind_Func()
    {
        Hand hand1 = new Hand("2S", "5S", "3C", "2D", "2H");
        Hand hand2 = new Hand("2S", "5S", "2S", "2D", "2H");
        Hand hand3 = new Hand("2S", "5S", "2S", "2D", "2H");

        assertFalse(HandEvaluator.isFourOfAKind_Func(hand1));
        assertFalse(HandEvaluator.isFourOfAKind_Func(hand2));
        assertFalse(HandEvaluator.isFourOfAKind_Func(hand3));
    }*/

    //____________________________________________________________________Is Full House
    //4 met dieselfde rank, en daai vier moet distinct wees
    @Test
    public void shouldMatchFullHouse_Imp()
    {
        Hand hand1 = new Hand("2S", "2C", "2H", "3D", "3C");
        Hand hand2 = new Hand("2S", "2C", "3H", "3D", "3C");
        assertTrue(HandEvaluator.isFullHouse_imp(hand1));
        assertTrue(HandEvaluator.isFullHouse_imp(hand2));
    }

    @Test
    public void shouldNotMatchFullHouse_Imp()
    {
        Hand hand1 = new Hand("2S", "2S", "2H", "3D", "3C");
        Hand hand2 = new Hand("2S", "2C", "4H", "3D", "3C");
        assertFalse(HandEvaluator.isFullHouse_imp(hand1));
        assertFalse(HandEvaluator.isFullHouse_imp(hand2));
    }

    //____________________________________________________________________Is 3 of a Kind
    //4 met dieselfde rank, en daai vier moet distinct wees
    @Test
    public void shouldMatchIs3OfAKind_Imp()
    {
        Hand hand1 = new Hand("2S", "2C", "4D", "2H", "3C");
        assertTrue(HandEvaluator.is3OfAKind_imp(hand1));
    }

    @Test
    public void shouldNotMatchIs3OfAKind_Imp()
    {
        Hand hand1 = new Hand("2S", "2C", "3D", "2H", "3C");
        Hand hand2 = new Hand("2S", "2S", "4D", "2H", "3C");
        assertFalse(HandEvaluator.is3OfAKind_imp(hand1));
        assertFalse(HandEvaluator.is3OfAKind_imp(hand2));
    }

    //____________________________________________________________________Is 2 Pair
    @Test
    public void shouldMatchIs2Pair_Imp()
    {
        Hand hand1 = new Hand("2S", "2C", "4D", "4H", "3C");
        assertTrue(HandEvaluator.isTwoPair_imp(hand1));
    }

    @Test
    public void shouldNotMatchIs2Pair()
    {
        Hand hand1 = new Hand("2S", "2C", "4D", "2H", "4C");
        Hand hand2 = new Hand("2S", "2S", "4D", "4H", "3C");
        assertFalse(HandEvaluator.isTwoPair_imp(hand1));
        assertFalse(HandEvaluator.isTwoPair_imp(hand2));
    }

    //____________________________________________________________________Is 1 Pair
    @Test
    public void shouldMatchIs1Pair_Imp()
    {
        Hand hand1 = new Hand("2S", "2C", "4D", "5H", "3C");
        assertTrue(HandEvaluator.isOnePair_imp(hand1));
    }

    @Test
    public void shouldNotMatchIs1Pair()
    {
        Hand hand1 = new Hand("2S", "2C", "4D", "2H", "4C");
        Hand hand2 = new Hand("2S", "2C", "4D", "4H", "3C");
        Hand hand3 = new Hand("2S", "5S", "2C", "2D", "2H");
        Hand hand4 = new Hand("2S", "2S", "4D", "5H", "3C");
        assertFalse(HandEvaluator.isOnePair_imp(hand1));
        assertFalse(HandEvaluator.isOnePair_imp(hand2));
        assertFalse(HandEvaluator.isOnePair_imp(hand3));
        assertFalse(HandEvaluator.isOnePair_imp(hand4));
    }

    //____________________________________________________________________Is High card
    @Test
    public void shouldMatchIsHighCard_Imp()
    {
        Hand hand1 = new Hand("2S", "6C", "4D", "5H", "3C");
        Hand hand2 = new Hand("2S", "2S", "4D", "5H", "3C");
        assertTrue(HandEvaluator.isHighCard_imp(hand1));
        assertTrue(HandEvaluator.isHighCard_imp(hand2));
    }

    @Test
    public void shouldNotMatchIsHighCard_imp()
    {
        Hand hand1 = new Hand("2S", "2C", "4D", "2H", "4C");
        Hand hand2 = new Hand("2S", "2C", "4D", "4H", "3C");
        Hand hand3 = new Hand("2S", "5S", "2C", "2D", "2H");
        assertFalse(HandEvaluator.isHighCard_imp(hand1));
        assertFalse(HandEvaluator.isHighCard_imp(hand2));
        assertFalse(HandEvaluator.isHighCard_imp(hand3));
    }















    //verduideliking van hoe func logic werk
    @Test
    public void example()
    {
        List<Integer> input = Arrays.asList(1, 2, 3);
        input.stream().map(x -> x + 2).forEach(System.out::println);

        //Sysem.out::println, roep dit nie, maar gee 'n handle

    }

    @Test
    public void example2()
    {
       // List<Integer> input = Arrays.asList(new Card("al"), (new Card("a"), (new Card("a"), (new Card("a"), (new Card("a"));
        //input.stream().map(x -> x.getRank() + 2).forEach(System.out::println);

        //Sysem.out::println, roep dit nie, maar gee 'n handle

    }

   /* @Test
    public void example3()
    {
        //infinite stream, bv x element van Z (integer)
        //int : primitive, Integer is an object wrapper
        IntStream stream = IntStream.iterate(0, x -> x + 1);

        stream.map(x -> x * 2).filter(x -> x > 10).findFirst().getAsInt();
        //invase A: all elements: anyMatch
        //invase E: at least one:anyMatch

        IntStream stream2 = IntStream.iterate(0, x -> {
            System.out.println(x);
            return x + 1;
        });

        //alles wat met 'n element gebeur gebeur eers met een elment dan die volgendes.
        //f(x) = x + 2
        //g(x) = x * 2
        //f(g(x))

       // stream.map(x -> x * 2).map(x -> x + 2).filter(x -> x > 10).forEach(x -> {
         //         ++x;
                    //print uit. kan klomp goed doen.
                }
        );

    }*/

    //deck, shuffle, dealing

    @Test
    public void example4()
    {
        // List<Integer> input = Arrays.asList(new Card("al"), (new Card("a"), (new Card("a"), (new Card("a"), (new Card("a"));
        //input.stream().map(x -> x.getRank() + 2).forEach(System.out::println);

        //Sysem.out::println, roep dit nie, maar gee 'n handle

    }








    //full house: 5S, 5H, 5D, 8S, 8H

    //Flush: AS, JS, 10S, 6S, 3S

    //Straight: 9S, 8D, 7S, 6H, 3S

    //Three of a kind: 3C, 3D, 3H, QS, 2S

    //Two pair: KS, KC, 9H, 9D, JH

    //One pair: 2D, 2H, QH, 7H, 6C




}