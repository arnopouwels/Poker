package Services.Users;

import com.google.inject.Provider;
import com.google.inject.persist.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;

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

}
