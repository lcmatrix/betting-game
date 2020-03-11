package de.bettinggame.application.bet;

import de.bettinggame.domain.Identity;
import de.bettinggame.domain.betting.Bet;
import de.bettinggame.domain.game.Game;
import de.bettinggame.domain.betting.BetRepository;
import de.bettinggame.domain.game.GameRepository;
import de.bettinggame.domain.user.UserRepository;
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
    private BetRepository betRepository;
    private GameRepository gameRepository;
    private UserRepository userRepository;

    @Autowired
    public BetService(BetRepository betRepository, GameRepository gameRepository, UserRepository userRepository) {
        this.betRepository = betRepository;
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
    }

    public List<BetTo> findBetsForGame(final String gameIdentifier, final String userIdentifier) {
        Optional<Game> optionalGame = gameRepository.findByIdentifier(Identity.buildIdentifier(gameIdentifier));
        final Game game = optionalGame.orElseThrow(() ->
                new IllegalArgumentException(String.format("Game with Identifier %1$s not found", gameIdentifier))
        );
        List<Bet> betsForGame = betRepository.findAllByGameIdentifierAndUserIdentifierIsNot(
                game.rawIdentifier(), Identity.buildIdentifier(userIdentifier));
        return betsForGame.stream()
                .map(bet -> Pair.of(bet, userRepository.findByIdentifier(bet.getUserIdentifier())))
                .map(pair -> new BetTo(pair.getLeft(), optionalGame.get(), pair.getRight().get()))
                .collect(Collectors.toList());
    }

}
