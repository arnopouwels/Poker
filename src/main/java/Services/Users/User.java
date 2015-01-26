package Services.Users;

import Services.Cards.Hand;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class User
{
    @Id
    @Size(max=12)
    private String name;
    private String password;
    private String salt = "";

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Cardhand> cardHand;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Player> player;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy = "user")
    private List<Host> host;

    public User()
    {
    }

    public List<Host> getHost()
    {
        return this.host;
    }
    public List<Cardhand> getCardHand()
    {
        return cardHand;
    }
    public User(String userN, String passW)
    {
        this.name = userN;

        //moet nog gehash word
        this.password = passW;
    }

    public String getName()
    {
        return name;
    }

    public String getSalt()
    {
        return salt;
    }

    public void setUsername(String username)
    {
        this.name = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }




}
