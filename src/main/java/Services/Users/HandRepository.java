package Services.Users;

import com.google.inject.Singleton;
import ninja.jpa.UnitOfWork;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Singleton
public class HandRepository extends BaseRepository<Cardhand>
{
    /*@UnitOfWork
    public List<Cardhand> findHandByName(String name)
    {
        Query query = getEntityManager().createQuery("SELECT u FROM Cardhand u WHERE u.user_name = :name").setParameter("name",name);
        return null;
    }*/

    @UnitOfWork
    public List<Cardhand> findHandByName(User user)
    {
        Query query = getEntityManager().createQuery("SELECT u FROM Cardhand u WHERE u.user = :user").setParameter("user",user);
        return query.getResultList();
    }

    @UnitOfWork
    public List<Cardhand> findHandByGame(Game game)
    {
        Query query = getEntityManager().createQuery("SELECT u FROM Cardhand u WHERE u.game = :game").setParameter("game",game);
        return query.getResultList();
    }

    private Optional<Cardhand> getSingleResult(Query query)
    {
        Optional<Cardhand> opUser;
        if(query == null)
            opUser = Optional.empty();

        List list = query.getResultList();
        if(list == null)
            opUser = Optional.empty();
        else if(list.isEmpty())
            opUser = Optional.empty();
        else
            opUser = Optional.ofNullable((Cardhand) list.get(0));
        return opUser;
    }
}
