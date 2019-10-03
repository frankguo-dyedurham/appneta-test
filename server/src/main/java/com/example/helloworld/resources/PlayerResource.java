package com.example.helloworld.resources;

import com.codahale.metrics.annotation.Timed;
import com.example.helloworld.api.PlayerRep;
import com.example.helloworld.core.Player;
import com.example.helloworld.db.PlayerDAO;
import com.example.helloworld.service.PlayerService;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.caching.CacheControl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class PlayerResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldResource.class);

    //Better use Service<Player> here to declare the property
    //I had trouble to bind generic type Service<Player> to PlayerService for DI
    //Therefore, I have to use a class PlayerService here instead of an interface
    private PlayerService playerService;

    @Inject
    public PlayerResource(PlayerService ps) {
        this.playerService = ps;
    }

    @GET
    @Path("players")
    @Timed(name = "get-requests")
    @CacheControl(maxAge = 1, maxAgeUnit = TimeUnit.DAYS)
    @UnitOfWork
    public List<PlayerRep> getPlayers() {
        LOGGER.info("Requesting all players");
        List<Player> players = playerService.findAll();
        List<PlayerRep> playerReps = players.stream().map(p -> new PlayerRep(p)).collect(Collectors.toList());
        LOGGER.info("Retrieved all players");
        return playerReps;
    }
}
