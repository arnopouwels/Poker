/**
 * Copyright (C) 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package controllers;

import Services.Cards.Hand;
import Services.PokerService;
import com.google.inject.Inject;
import ninja.FilterWith;
import ninja.Result;
import ninja.Results;


import com.google.inject.Singleton;
import ninja.Context;
import filter.sercureFilter;


@Singleton
//filterWith
public class PlayController {

    @Inject
    private PokerService pokerService;

    @FilterWith(sercureFilter.class)
    public Result index(Context context)
    {
        Result result = Results.html();

        pokerService.deal();

        Hand hand = pokerService.getHand();
        result.render("c0", hand.getCardAt(0).toString());
        result.render("c1", hand.getCardAt(1).toString());
        result.render("c2", hand.getCardAt(2).toString());
        result.render("c3", hand.getCardAt(3).toString());
        result.render("c4", hand.getCardAt(4).toString());

        result.render("Eval", pokerService.getEvaluatedHand());

        /*String username = context.getSession().get("userN");
        if(username != null)
            result.render("username",context.getParameter(username));
        else
            result.render("username", "Not found");*/

        String username = context.getSession().get("userN");
        if(username != null)
        {

        }
           // result.render("username",username);
        else
            result.redirect("/");
        return result;
        //return Results.notFound();

    }

    /*
    User user = new User()
    user.setName
    userRepository.persist(user);


     */

    //result index(context context)
    /*

     */



    //maak 'n singleton UserRepository
    //stoor 'n list van users

    /*
    //publi Result logout(Context
        context.getSession().clera();
        return Result.redirect()
     */

    //PBKDF2 perform hashing
    
    public Result helloWorldJson() {

        SimplePojo simplePojo = new SimplePojo();
        simplePojo.content = "Hello World! Hello Json!";

        return Results.json().render(simplePojo);

    }



    public void setPokerService(PokerService pokerService)
    {
        this.pokerService = pokerService;
    }
    
    public static class SimplePojo {

        public String content;
        
    }
}
