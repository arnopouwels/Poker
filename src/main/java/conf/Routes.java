/**
 * Copyright (C) 2012 the original author or authors.
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

package conf;


import controllers.ApplicationController;
import ninja.AssetsController;
import ninja.Results;
import ninja.Router;
import ninja.application.ApplicationRoutes;
import controllers.PlayController;

import javax.security.auth.login.LoginContext;

public class Routes implements ApplicationRoutes {

    @Override
    public void init(Router router) {
        router.GET().route("/").with(ApplicationController.class, "index");
        router.POST().route("/").with(ApplicationController.class, "index");
        router.GET().route("/reg").with(ApplicationController.class, "reg");
        router.POST().route("/reg").with(ApplicationController.class, "reg");

        router.GET().route("/LoggedIn").with(PlayController.class, "index");
        router.GET().route("/LoggedIn/hosted").with(PlayController.class, "hostedSpec");
        router.GET().route("/LoggedIn/hosted/{hostNum}").with(PlayController.class, "hosted");
        router.GET().route("/LoggedIn/joinGame/{hostName}").with(PlayController.class, "join");

        //router.GET().route("/test").with(AsyncController.class, "joinpart");
        router.GET().route("/test").with(PlayController.class, "hostedgames");


        router.GET().route("/game").with(PlayController.class, "game");
        router.GET().route("/hand").with(PlayController.class, "play");
        router.GET().route("/history").with(PlayController.class, "history");
 
        ///////////////////////////////////////////////////////////////////////
        // Assets (pictures / javascript)
        ///////////////////////////////////////////////////////////////////////    
        router.GET().route("/assets/webjars/{fileName: .*}").with(AssetsController.class, "serveWebJars");
        router.GET().route("/assets/{fileName: .*}").with(AssetsController.class, "serveStatic");
        
        ///////////////////////////////////////////////////////////////////////
        // Index / Catchall shows index page
        ///////////////////////////////////////////////////////////////////////
        router.GET().route("/.*").with(ApplicationController.class, "index");
    }

}
