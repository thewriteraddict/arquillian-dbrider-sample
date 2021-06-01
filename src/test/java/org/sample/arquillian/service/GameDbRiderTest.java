package org.sample.arquillian.service;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.DBUnitExtension;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sample.arquillian.model.Game;
import org.sample.arquillian.service.impl.ejb.GameServiceBean;

import javax.ejb.EJB;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith({ArquillianExtension.class, DBUnitExtension.class})
public class GameDbRiderTest {

    private static final Logger log = Logger.getLogger(GameDbRiderTest.class.getName());

    @EJB
    private GameService gameService;

    @Deployment
    public static WebArchive createDeployment() {
        WebArchive webArchive = ShrinkWrap.create(WebArchive.class)
                .addPackage(GameService.class.getPackage())
                .addClass(GameServiceBean.class)
                .addClass(Game.class)
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource("jbossas-ds.xml")
                .addAsWebInfResource("META-INF/beans.xml");

        log.info("WAR " + webArchive.toString(true));
        return webArchive;
    }

    @Test
    @DataSet(value = "game.yml", executeScriptsBefore = "META-INF/sql/create.sql")
    public void queryGame() {
        long id = 1L;
        Game game = gameService.queryGame(id);
        assertEquals("DOOM 3", game.getTitle());
    }
}
