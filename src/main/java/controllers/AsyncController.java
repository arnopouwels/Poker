package controllers;

import Services.Users.Host;
import Services.Users.HostRepository;
import com.google.inject.Inject;
import ninja.Context;
import ninja.Result;
import ninja.Results;

import java.util.List;

public class AsyncController
{

    @Inject
    private HostRepository hostRepository;

    public Result joinpart(Context context)
    {
        Result result = Results.html();

        return result;
    }

    public Result hostedgames(Context context)
    {
        Result result = Results.html();

        List<Host> hostList = hostRepository.findAllHosts();
        String[] hostStrings = hostAsStrings(hostList);
        result.render("hosts", hostStrings);
        return result;
    }

    private String[] hostAsStrings(List<Host> hosts)
    {
        int size = hosts.size();
        String[] temp = new String[size];

        for(int i = 0; i < size; i++)
            temp[i] = hosts.get(i).getUser().getName();
        return temp;
    }
}
