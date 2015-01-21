package cards;

import Services.Cards.Hand;
import org.junit.*;

import static org.junit.Assert.assertEquals;


public class HandTest {

    @Before
    public void setup()
    {

    }

    @After
    public void cleanup()
    {

    }

    @Test
    public void shouldPrintHand()
    {
        //1.Setup
        Hand hand = new Hand("2S", "3H", "AD", "10C", "KD");
        //2.Execute functionality
        String representation = hand.toString();
        //3.Assert Expectations
        assertEquals("{2S, 3H, AD, 10C, KD}", representation);
        //4.Cleanup





    }
}
