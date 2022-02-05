package pl.kielce.gifhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kielce.gifhub.model.Gif;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;

@Repository
public interface GifRepo extends JpaRepository<Gif, Long> {
	List<Gif> findGifsByTagOrName(@NotBlank String tag, @NotBlank String name);
	Optional<Gif> findGifByName(@NotBlank String name);
}
