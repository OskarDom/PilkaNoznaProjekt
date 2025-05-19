package pl.wasko.praktyki.odomanski;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling; // ← WAŻNE
import pl.wasko.praktyki.odomanski.model.Club;
import pl.wasko.praktyki.odomanski.model.Player;
import pl.wasko.praktyki.odomanski.model.Transaction;
import pl.wasko.praktyki.odomanski.repository.ClubRepository;
import pl.wasko.praktyki.odomanski.repository.PlayerRepository;
import pl.wasko.praktyki.odomanski.repository.TransactionRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class FootballApplication {

	private static final Logger logger = LoggerFactory.getLogger(FootballApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(FootballApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(ClubRepository clubRepo, PlayerRepository playerRepo, TransactionRepository transactions) {
		return args -> {
			// KLUBY
			Club barcelona = new Club("Barcelona", "Hiszpania");
			Club nassr = new Club("All-Nassr", "Arabia Saudyjska");
			Club interMiami = new Club("Inter Miami", "Ameryka");
			Club plymouth = new Club("Plymouth Argyle F.C.", "Anglia");
			Club bayern = new Club("Bayern Monachium", "Niemcy");

			clubRepo.saveAll(List.of(barcelona, nassr, interMiami, plymouth, bayern));
			logger.info("Zapisano kluby: {}", List.of(barcelona, nassr, interMiami, plymouth, bayern));

			// PIŁKARZE
			Player yamal = new Player("Lamine", "Yamal", 17, barcelona);
			Player lewy = new Player("Robert", "Lewandowski", 36, barcelona);
			Player puchacz = new Player("Tymoteusz", "Puchacz", 26, plymouth);
			Player ronaldo = new Player("Cristiano", "Ronaldo", 40, nassr);
			Player messi = new Player("Lionel", "Messi", 37, interMiami);

			playerRepo.saveAll(List.of(yamal, lewy, puchacz, ronaldo, messi));
			logger.info("Zapisano piłkarzy: {}", List.of(yamal, lewy, puchacz, ronaldo, messi));

			// TRANSAKCJE
			Transaction t1 = new Transaction(messi, barcelona, LocalDate.of(2023, 6, 30), new BigDecimal("30000000"));
			Transaction t2 = new Transaction(ronaldo, nassr, LocalDate.of(2022, 12, 30), new BigDecimal("20000000"));
			Transaction t3 = new Transaction(yamal, barcelona, LocalDate.of(2023, 8, 15), new BigDecimal("0"));
			Transaction t4 = new Transaction(lewy, bayern, LocalDate.of(2022, 7, 16), new BigDecimal("45000000"));
			Transaction t5 = new Transaction(puchacz, plymouth, LocalDate.of(2024, 1, 5), new BigDecimal("2000000"));

			transactions.saveAll(List.of(t1, t2, t3, t4, t5));
			logger.info("Zapisano transakcje: {}", List.of(t1, t2, t3, t4, t5));
		};
	}
}
