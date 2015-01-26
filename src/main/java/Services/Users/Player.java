package Services.Users;

import javax.persistence.*;

@Entity
public class Player
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Host host;

    @ManyToOne
    private User user;

    public Long getId()
    {
        return id;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public void setHost(Host host)
    {
        this.host = host;
    }

    public Host getHost()
    {
        return host;
    }

}
