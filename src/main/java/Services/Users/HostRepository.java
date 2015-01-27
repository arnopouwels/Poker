package Services.Users;

import com.google.inject.Singleton;
import ninja.jpa.UnitOfWork;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Singleton
public class HostRepository extends BaseRepository<Host>
{
    @UnitOfWork
    public List<Host> findHostByName(User user)
    {
        Query query = getEntityManager().createQuery("SELECT h FROM Host h WHERE h.user = :user").setParameter("user",user);
        return query.getResultList();
    }

    @UnitOfWork
    public List<Host> findAllHostsWithout(User user)
    {
        Query query = getEntityManager().createQuery("SELECT h FROM Host h WHERE h.user != :user").setParameter("user",user);
        return query.getResultList();
    }

    @UnitOfWork
    public List<Host> findAllHosts()
    {
        Query query = getEntityManager().createQuery("SELECT h FROM Host h");
        return query.getResultList();
    }


}
