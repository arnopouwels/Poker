package Services.Users;

import com.google.inject.Singleton;
import ninja.jpa.UnitOfWork;

import javax.persistence.Query;
import java.util.List;

@Singleton
public class PlayerRepository extends BaseRepository<Player>
{
    @UnitOfWork
    public List<Player> findPlayersOfHost(Host host)
    {
        Query query = getEntityManager().createQuery("SELECT p FROM Player p WHERE p.host = :host").setParameter("host",host);
        return query.getResultList();
    }

    @UnitOfWork
    public String[] findPlayerNamesOfHost(Host host)
    {
        Query query = getEntityManager().createQuery("SELECT p FROM Player p WHERE p.host = :host").setParameter("host",host);
        List<Player> playerList = query.getResultList();

        int size = playerList.size();
        String[] playerNames = new String[size];

        for(int i = 0; i < size; i++)
            playerNames[i] = playerList.get(i).getUser().getName();
        return playerNames;
    }
}

