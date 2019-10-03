package com.example.helloworld.db;

import com.example.helloworld.core.Player;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

@Service
public class PlayerDAO extends AbstractDAO<Player> implements AdvancedDAO<Player> {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerDAO.class);

    @Inject
    public PlayerDAO(SessionFactory factory) {
        super(factory);
    }

    public Optional<Player> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    public Player create(Player player) {
        return persist(player);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Player> findAll() {
        LOGGER.info("Query for all players");
        return list((Query<Player>)namedQuery("com.example.helloworld.core.Player.findAll"));
    }
}
