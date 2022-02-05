package pl.kielce.gifhub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kielce.gifhub.model.Gif;
import pl.kielce.gifhub.repository.GifRepo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GifServiceImpl implements GifService {

	private final GifRepo gifRepo;

	@Autowired
	public GifServiceImpl(GifRepo gifRepo) {
		this.gifRepo = gifRepo;
	}

	@Override
	public List<Gif> getAll() {
		return gifRepo.findAll();
	}

	@Override
	public void add(Gif gif) {
		gifRepo.save(gif);
	}

	@Override
	public void delete(Gif gif) {
		gifRepo.delete(gif);
	}

	@Override
	public List<Gif> getByTagOrName(String tagOrName) {
		return gifRepo.findGifsByTagOrName(tagOrName, tagOrName);
	}

	@Override
	public Optional<Gif> getByName(String name) {
		return gifRepo.findGifByName(name);
	}

	@Override
	public List<Gif> getFavorites() {
		return getAll().stream()
			.filter(Gif::isFavorite)
			.collect(Collectors.toList());
	}

	@Override
	public void toggleFavorite(Gif gif) {
		gif.setFavorite(!gif.isFavorite());
		gifRepo.save(gif);
	}
}
