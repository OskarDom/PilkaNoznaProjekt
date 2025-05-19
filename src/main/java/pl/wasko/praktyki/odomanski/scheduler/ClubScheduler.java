package pl.wasko.praktyki.odomanski.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.wasko.praktyki.odomanski.repository.ClubRepository;

@Component
public class ClubScheduler {

    private static final Logger logger = LoggerFactory.getLogger(ClubScheduler.class);
    private final ClubRepository clubRepository;

    public ClubScheduler(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    // CRON: co 10 sekund (0 */10 * * * * = co 10 sekund)
    @Scheduled(cron = "*/60 * * * * *")
    public void logClubCount() {
        long count = clubRepository.count();
        logger.info(" Liczba klubów w bazie: {}", count);
    }
}
