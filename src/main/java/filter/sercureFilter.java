package filter;
import ninja.*;
//import com.google.inject.inject;
/**
 * Created by Zuhnja on 1/16/2015.
 */
public class sercureFilter implements Filter
{

    public String username = "userN";
    @Override
    public Result filter(FilterChain filterChain, Context context)
    {
        if(context.getSession() == null || context.getSession().get(username) == null)
            return Results.forbidden().html().template("/views/forbidden403.ftl.html");
        else
            return filterChain.next(context);
    }
}

