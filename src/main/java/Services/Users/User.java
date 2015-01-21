package Services.Users;

import Services.Cards.Hand;

import javax.persistence.Entity;
import javax.validation.constraints.Size;
import javax.persistence.Id;

@Entity
public class User
{
    @Id
    @Size(max=12)
    private String name;
    private String password;
    private String salt = "";
    private String hand = "";

    public User()
    {
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

    public String getHand() { return hand; }

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
