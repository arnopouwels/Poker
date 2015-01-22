package Services.Users;

import com.google.inject.Singleton;
import ninja.jpa.UnitOfWork;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Singleton
public class HandRepository extends BaseRepository<Cardhand>
{
    @UnitOfWork
    public Optional<Cardhand> findGameByName(String name)
    {
        Query query = getEntityManager().createQuery("SELECT u FROM Cardhand u WHERE u.name = :name").setParameter("name",name);
        return getSingleResult(query);
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
