package pl.kielce.gifhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.kielce.gifhub.model.Gif;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;

@Repository
public interface GifRepo extends JpaRepository<Gif, Long> {

//	@Query("select new pl.kielce.gifhub.model.Gif(g.id, g.name, g.tag, g.favorite, g.categories)"  +
//		"from Gif g " +
//		"where g.name like :name " +
//		"or g.tag like :tag")
	List<Gif> findGifsByTagIsLikeOrNameIsLike(@NotBlank @Param("tag") String tag, @NotBlank @Param("name") String name);
	Optional<Gif> findGifByName(@NotBlank String name);
}
