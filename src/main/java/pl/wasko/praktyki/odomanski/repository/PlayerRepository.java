package pl.wasko.praktyki.odomanski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wasko.praktyki.odomanski.model.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
