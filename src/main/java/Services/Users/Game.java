package Services.Users;

import javax.persistence.*;
import java.util.List;

@Entity
public class Game
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    private List<Cardhand> cardHand;

    public Long getId()
    {
        return id;
    }

    public List<Cardhand> getCardHand()
    {
        return cardHand;
    }



}
