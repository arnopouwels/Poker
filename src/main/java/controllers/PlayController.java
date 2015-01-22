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

import Services.Cards.Card;
import Services.Cards.Hand;
import Services.PokerService;
import com.google.inject.Inject;
import ninja.FilterWith;
import ninja.Result;
import ninja.Results;


import com.google.inject.Singleton;
import ninja.Context;
import filter.sercureFilter;
import ninja.session.Session;


@Singleton
//filterWith
public class PlayController {

    @Inject
    private PokerService pokerService;

    public Result index(Context context)
    {
        Result result = Results.html();
        Session session = context.getSession();
        if(session == null) {
            result.redirect("/");
            return result;
        }

        String username = session.get("userN");
        if(username == null || username.equals(""))
        {
            result.redirect("/");
            return  result;
        }

        result.render("username", username);
        return result;
    }

    public Result history(Context context)
    {
        Result result = Results.html();
        Session session = context.getSession();
        if(session == null) {
            result.redirect("/");
            return result;
        }

        String username = session.get("userN");
        if(username == null || username.equals(""))
        {
            result.redirect("/");
            return  result;
        }

        return result;
    }

    @FilterWith(sercureFilter.class)
    public Result play(Context context)
    {
        Result result = Results.html();

        pokerService.deal();

        Hand[] hands = pokerService.getHands();

        String[] users = {"A", "B"};

        String[][] handsAsStrings = new String[pokerService.getNumHands()][];

        for(int i = 0; i < hands.length; i++)
        {
            handsAsStrings[i] = new String[5];
            for(int j = 0; j < handsAsStrings[i].length; j++)
                handsAsStrings[i][j] = hands[i].getCardAt(j).toString();
        }

        result.render("users", users);
        result.render("hands", handsAsStrings);
        result.render("evalHands", pokerService.getEvaluatedHands());
        result.render("winnerMes", pokerService.getWinnerHandsMessages());

        String username = context.getSession().get("userN");
        if(username != null)
        {
        }
        else
            result.redirect("/");
        return result;

    }

    //PBKDF2 perform hashing

}
