package Services.Users;

import com.google.inject.Singleton;
import ninja.jpa.UnitOfWork;

import javax.persistence.Query;
import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Singleton
public class UserRepository extends BaseRepository<User>
{
    @UnitOfWork
    public Optional<User> findUserByName(String name)
    {
        Query query = getEntityManager().createQuery("SELECT u FROM User u WHERE u.name = :name").setParameter("name",name);
        return getSingleResult(query);
    }

    private Optional<User> getSingleResult(Query query)
    {
        Optional<User> opUser;
        if(query == null)
            opUser = Optional.empty();

        List list = query.getResultList();
        if(list == null)
            opUser = Optional.empty();
        else if(list.isEmpty())
            opUser = Optional.empty();
        else
            opUser = Optional.ofNullable((User) list.get(0));
        return opUser;
    }

    private ArrayList<User> users = new ArrayList<User>();

    public boolean nameExists(String username)
    {
        Optional<User> opUser = findUserByName(username);
        User user = null;
        if(opUser.isPresent())
            return true;
        else
            return false;
    }

    public void addUser(User user)
    {
        users.add(user);
    }

    private User getUser(String username)
    {
        for(User user: users)
        {
            if(user.getName().equals(username))
                return user;
        }
        return null;
    }

    public boolean validPassword(String username, String password)
    {
        for(User user: users)
        {
            if(user.getName().equals(username))
            {
                if(user.getPassword().equals(password))
                    return true;
                else
                    return false;
            }
        }
        return false;
    }


}
