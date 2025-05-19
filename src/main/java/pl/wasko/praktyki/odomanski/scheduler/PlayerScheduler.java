package pl.wasko.praktyki.odomanski.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.wasko.praktyki.odomanski.model.Player;
import pl.wasko.praktyki.odomanski.repository.PlayerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlayerScheduler {

    private static final Logger logger = LoggerFactory.getLogger(PlayerScheduler.class);
    private final PlayerRepository playerRepository;

    public PlayerScheduler(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    // Uruchamiane co minutę (0 sekund każdej minuty)
    @Scheduled(cron = "0 * * * * *")
    public void logPlayerInfo() {
        long count = playerRepository.count();
        List<Player> players = playerRepository.findAll();

        String names = players.stream()
                .map(p -> p.getFirstName() + " " + p.getLastName())
                .collect(Collectors.joining(", "));

        logger.info("Liczba piłkarzy w bazie: {}", count);
        logger.info("Piłkarze: {}", names);
    }
}
