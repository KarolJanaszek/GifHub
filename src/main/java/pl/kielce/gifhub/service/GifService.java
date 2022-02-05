package pl.kielce.gifhub.service;

import pl.kielce.gifhub.model.Gif;

import java.util.List;
import java.util.Optional;

public interface GifService {
	List<Gif> getAll();
	void add(Gif gif);
	void delete(Gif gif);

	List<Gif> getByTagOrName(String tagOrName);
	Optional<Gif> getByName(String name);
	List<Gif> getFavorites();
	void toggleFavorite(Gif gif);
}
