package filter;
import ninja.*;
//import com.google.inject.inject;
/**
 * Created by Zuhnja on 1/16/2015.
 */
public class sercureFilter implements Filter
{

    @Override
    public Result filter(FilterChain filterChain, Context context)
    {
        return null;
    }
}

//context.getFlashScope is net daar vir een request en is datn weg

//sit in index.ftl.html
/*
<#if (flash.keyword)??
 */
