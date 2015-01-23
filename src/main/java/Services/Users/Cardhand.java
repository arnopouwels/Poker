package Services.Users;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Cardhand
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String winnerstate;
    private String hand;

    @ManyToOne
    private Game game;

    @ManyToOne
    private User user;

    public Cardhand() {
        this.id = id;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Game getGame()
    {
        return game;
    }

    public void setGame(Game game)
    {
        this.game = game;
    }
    public String getWinnerstate()
    {
        return winnerstate;
    }

    public String getHand()
    {
        return hand;
    }

    public void setWinnerstate(String name)
    {
        this.winnerstate = name;
    }

    public void setHand(String hand)
    {
        this.hand = hand;
    }
}
