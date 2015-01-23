package controllers;

import Services.Users.*;
import com.google.inject.Inject;
import ninja.Result;
import ninja.Results;
import ninja.Context;

import java.util.Optional;

/**
 * Created by Zuhnja on 1/16/2015.
 */
public class ApplicationController
{
    @Inject
    private UserRepository userRepository;

    @Inject
    private HandRepository handRepository;

    @Inject
    private GameRepository gameRepository;

    public Result index(Context context)
    {
        Result result = Results.html();

        if(context.getSession() != null)
        {
            if(context.getSession().get("userN") == null)
                context.getSession().clear();
        }
        if(context.getParameter("username") != null)
        {
            String username = context.getParameter("username");
            String password = context.getParameter("password");

            Optional<User> opUser = userRepository.findUserByName(username);
            User user = null;
            if(opUser.isPresent())
                user = opUser.get();

            if(user != null)
            {
                if(password.equals(user.getPassword()))
                {
                    context.getSession().put("userN", username);
                    result.redirect("/loggedIn");
                    return result;
                }

            }
                context.getFlashScope().error("Incorrect Password");
        }

        return result;
    }

    public Result reg(Context context)
    {
        Result result = Results.html();
        String username = context.getParameter("username");
        String password = context.getParameter("password");

        if(username == null)
        {

        }
        else if(username.equals("") || username.equals(""))
            context.getFlashScope().error("Empty Field.");
        else if(userRepository.nameExists(username))
            context.getFlashScope().error("Name already exists.");
        else
        {
            User user = new User(username, password);
            userRepository.persist(user);
            context.getFlashScope().success(username + " 's acount has been successfully created.");
        }

        return result;
    }







}
