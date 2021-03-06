package Services.Users;

import javax.persistence.*;
import java.util.List;

@Entity
public class Host
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Player> player;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    public Host() {
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

    public Long getId()
    {
        return id;
    }

}
