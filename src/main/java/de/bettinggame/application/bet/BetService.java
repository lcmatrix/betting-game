package de.bettinggame.application.bet;

import de.bettinggame.domain.Identity;
import de.bettinggame.domain.betting.Bet;
import de.bettinggame.domain.game.Game;
import de.bettinggame.domain.repository.BetRepository;
import de.bettinggame.domain.repository.GameRepository;
import de.bettinggame.domain.repository.UserRepository;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Domain service for {@link Bet}.
 */
@Service
public class BetService {

    @Autowired
    private BetRepository betRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserRepository userRepository;

    public List<BetTo> findBetsForGame(final Identity gameIdentifier) {
        Optional<Game> game = gameRepository.findByIdentifier(gameIdentifier);
        final Identity gameId = game.orElseThrow( () ->
                new IllegalArgumentException(String.format("Game with Identifier %1$s not found", gameIdentifier))
        ).identifier();
        List<Bet> betsForGame = betRepository.findAllByGameIdentifier(gameId);
        return betsForGame.stream()
                .map(bet -> Pair.of(bet, userRepository.findByIdentifier(bet.getUserIdentifier())))
                .map(pair -> new BetTo(pair.getLeft(), game.get(), pair.getRight().get()))
                .collect(Collectors.toList());
    }

}
