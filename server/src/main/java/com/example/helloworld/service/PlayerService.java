package com.example.helloworld.service;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import com.example.helloworld.core.Player;
import com.example.helloworld.db.PlayerDAO;
import com.example.helloworld.errorhandling.AppException;

import org.jvnet.hk2.annotations.Service;


@Service
public class PlayerService implements com.example.helloworld.service.Service<Player>{
    
    private PlayerDAO playerDao;

    @Inject
    public PlayerService(PlayerDAO playerDao){
        this.playerDao = playerDao;
    }

    public Player findById (long id) throws AppException {
        Optional<Player> ply = playerDao.findById(id);

        if (ply.isPresent()) {
            return ply.get();
        } else {
			throw new AppException(Response.Status.NOT_FOUND.getStatusCode(), 
					404, 
					"The player you requested with id " + id + " was not found in the database",
					"Verify the existence of the player with the id " + id + " in the database",
					"Some link to get administrator contact info");                      
        }
    }

    public List<Player> findAll(){
        List<Player> players = playerDao.findAll();

        return players;
    }
}