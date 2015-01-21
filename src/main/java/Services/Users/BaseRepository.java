package Services.Users;

import com.google.inject.Provider;
import com.google.inject.persist.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Created by Zuhnja on 2015-01-20.
 */
public class BaseRepository<T>
{
    @Inject
    private Provider<EntityManager> entityManagerProvider;

    @Transactional
    public void persist(T entity)
    {
        getEntityManager().persist(entity);
    }

    public EntityManager getEntityManager()
    {
        return entityManagerProvider.get();
    }

    //List query.geResultList()
       // if(resu)

}
