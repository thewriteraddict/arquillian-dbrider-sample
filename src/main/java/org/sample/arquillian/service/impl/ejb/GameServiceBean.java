package org.sample.arquillian.service.impl.ejb;

import org.sample.arquillian.model.Game;
import org.sample.arquillian.service.GameNotFoundException;
import org.sample.arquillian.service.GameService;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static java.util.Objects.isNull;
import static javax.ejb.TransactionAttributeType.REQUIRES_NEW;

@Stateless
@TransactionAttribute(REQUIRES_NEW)
public class GameServiceBean implements GameService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Game queryGame(Long id) {
        Game game = em.find(Game.class, id);
        if (isNull(game)) {
            throw new GameNotFoundException();
        }
        return game;
    }
}
