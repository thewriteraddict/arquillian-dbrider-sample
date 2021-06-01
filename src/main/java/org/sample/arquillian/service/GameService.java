package org.sample.arquillian.service;

import org.sample.arquillian.model.Game;

import javax.ejb.Stateless;
import javax.validation.constraints.NotNull;

public interface GameService {
    @NotNull
    Game queryGame(@NotNull Long id);
}
