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
import Services.Cards.Rank;
import Services.Cards.Suit;
import Services.PokerService;
import Services.Users.*;
import com.google.inject.Inject;
import ninja.FilterWith;
import ninja.Result;
import ninja.Results;


import com.google.inject.Singleton;
import ninja.Context;
import filter.sercureFilter;
import ninja.params.PathParam;
import ninja.session.Session;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;


@Singleton
//filterWith
public class PlayController
{
    @Inject
    private PokerService pokerService;

    @Inject
    private HandRepository handRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private GameRepository gameRepository;

    @Inject
    private HostRepository hostRepository;

    @Inject PlayerRepository playerRepository;

    private volatile Boolean changeTrigger = true;
    private volatile ConcurrentHashMap<Integer, Integer> map;

    public Result index(Context context)
    {
        Result result = Results.html();
        String username = context.getSession().get("userN");
        result.render("username", username);

        //get user
        Optional<User> opUser = userRepository.findUserByName(username);
        User user = null;
        if(opUser.isPresent())
            user = opUser.get();

        //get lists of host that do not contain user
        List<Host> hostList = hostRepository.findAllHostsWithout(user);
        String[] hostStrings = hostAsStrings(hostList);
        result.render("hosts", hostStrings);
        return result;
    }

    public Result hostedSpec(Context context)
    {
        Result result = Results.html();
        String username = context.getSession().get("userN");
        Optional<User> opUser = userRepository.findUserByName(username);
        User user = null;
        if(opUser.isPresent())
            user = opUser.get();

        if(user != null)
        {
            //kyk of al in databasis is
            List<Host> hostList = hostRepository.findHostByName(user);
            if(hostList.size() != 0)
                return result.redirect("/LoggedIn");

            //add new host to database if host does not already exists
            Host host = new Host();
            host.setUser(user);
            hostRepository.persist(host);
            updateChange();
            String pathParameter = host.getId() + "";
            result.redirect("/LoggedIn/hosted/"+pathParameter);
        }

        return result;
    }

    private synchronized void updateChange()
    {
        changeTrigger = !changeTrigger;
    }

    public Result hostedgames(Context context) throws InterruptedException
    {
        Result result = Results.html();
        boolean change=changeTrigger.booleanValue();

        while(change == changeTrigger.booleanValue())
        {
            Thread.sleep(100);
        }

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

    public Result hosted(Context context, @PathParam("hostNum") String hostNum)
    {
        Result result = Results.html();

        result.render("id",hostNum);

        //get hostUser
        String username = context.getSession().get("userN");
        User hostUser = getUserByName(username);
        if(hostUser == null)
            return result;

        //getHost
        List<Host> hostList = hostRepository.findHostByName(hostUser);
        if(hostList == null || hostList.size() == 0)
            return result;
        Host host = hostList.get(0);

        //get all players with hostUser as id
        String[] playerNames = playerRepository.findPlayerNamesOfHost(host);
        if(playerNames == null || playerNames.length == 0)
        {
            result.render("playerNames", playerNames);
            return result;
        }

        result.render("playerNames", playerNames);
        return result;
    }

    public Result join(Context context, @PathParam("hostName") String hostName)
    {
        Result result = Results.html();

        //get user met name hostname
        User hostUser = getUserByName(hostName);
        if(hostUser == null)
            return result;

        //getHosted id met username
        List<Host> hostList = hostRepository.findHostByName(hostUser);
        if(hostList == null || hostList.size() == 0)
            return result;
        Host host = hostList.get(0);

        //get the playerName
        String username = context.getSession().get("userN");
        User playerUser = getUserByName(username);
        if(playerUser == null)
            return result;

        //create player
        Player player = new Player();
        player.setHost(host);
        player.setUser(playerUser);

        if(!playerRepository.doesPlayerExist(player))
            playerRepository.persist(player);

        return result;
    }

    private User getUserByName(String username)
    {
        Optional<User> opUser = userRepository.findUserByName(username);
        User user = null;
        if(opUser.isPresent())
            user = opUser.get();
        return user;
    }




    public Result game(Context context)
    {
        Result result = Results.html();
        Session session = context.getSession();
        if(session == null)
        {
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

        Optional<User> opUser = userRepository.findUserByName(username);
        User user = null;
        if(opUser.isPresent())
            user = opUser.get();

        HashMap<Game, List<Cardhand>> map = null;
        if(user != null)
        {
            //kry al die hands van ingelogde user
            List<Cardhand> cardhandList = handRepository.findHandByName(user);
           //System.out.println(cardhandList.get(0).getHand());

            //kry al die hands in 'n groep waarin die elke hand van die user is
            //map = getMapOfHandsPerGame_fromHands(cardhandList);
        }
        return result;
    }

    private void outputMap(HashMap<Game, List<Cardhand>> map)
    {
        Object[] key = map.keySet().toArray();

        List<Cardhand> list = (List<Cardhand>) map.get((Game) key[0]);
        for(int a = 0; a < list.size(); a++)
            System.out.println(((Cardhand) list.get(a)).getHand());
    }

    private HashMap<Game, List<Cardhand>> getMapOfHandsPerGame_fromHands(List<Cardhand> cardhandList)
    {
        HashMap<Game, List<Cardhand>> map = new HashMap<Game, List<Cardhand>>();

        for(int i = 0; i < cardhandList.size(); i++)
        {
            Game game = cardhandList.get(i).getGame();
            List<Cardhand> cardhandsListTemp = handRepository.findHandByGame(game);
            map.put(game, cardhandsListTemp);
        }

        return map;
    }



    @FilterWith(sercureFilter.class)
    public Result play(Context context)
    {
        Result result = Results.html();

        pokerService.deal();

        Hand[] hands = pokerService.getHands();

        String[][] handsAsStrings = new String[pokerService.getNumHands()][];

        for(int i = 0; i < hands.length; i++)
        {
            handsAsStrings[i] = new String[5];
            for(int j = 0; j < handsAsStrings[i].length; j++)
                handsAsStrings[i][j] = hands[i].getCardAt(j).toString();
        }

        String[] winStates = pokerService.getWinnerHandsMessages();
        result.render("hands", handsAsStrings);
        result.render("evalHands", pokerService.getEvaluatedHands());
        result.render("winnerMes", winStates);

        String username = context.getSession().get("userN");
        Optional<User> opUser = userRepository.findUserByName(username);
        User user = null;
        if(opUser.isPresent())
            user = opUser.get();

        if(user != null)
        {
            Game game = new Game();
            gameRepository.persist(game);
            for(int i = 0; i < hands.length; i++)
            {
                Cardhand ch = new Cardhand();
                ch.setHand(hands[i].toString());
                ch.setWinnerstate(winStates[i]);
                ch.setGame(game);
                if(i == 0)
                    ch.setUser(user);
                handRepository.persist(ch);
            }
        }
        return result;

    }

    //PBKDF2 perform hashing

}
