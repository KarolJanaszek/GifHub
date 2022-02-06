package pl.kielce.gifhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.kielce.gifhub.model.Gif;
import pl.kielce.gifhub.service.CategoryService;
import pl.kielce.gifhub.service.GifService;

import java.util.NoSuchElementException;

@Controller
public class GifController {
	private final GifService gifService;
	private final CategoryService categoryService;

	@Autowired
	public GifController(GifService gifService, CategoryService categoryService) {
		this.gifService = gifService;
		this.categoryService = categoryService;
	}

	@GetMapping("/")
	public String home(Model model, @RequestParam(required = false) String q) {
		model.addAttribute("gifs", q == null ? gifService.getAll() : gifService.getByTagOrName(q));
		return "home";
	}

	@GetMapping("/g/{name}")
	public String getGif(Model model, @PathVariable String name) {
		model.addAttribute("editedGif", gifService.getByName(name)
			.orElseThrow(() -> new NoSuchElementException("Nie znaleziono gifa o nazwie " + name))
		);
		return "gif_details";
	}

	@PostMapping("/gif/{name}")
	public String update(@ModelAttribute Gif editedGif) {
		gifService.add(editedGif);
		return "redirect:/g/{name}";
	}

	@GetMapping("/g/{name}/favorite")
	public String toggleFavorite(@PathVariable String name, @RequestParam(required = false, defaultValue = "") String r)
		throws NoSuchElementException {
		Gif gif = gifService.getByName(name)
			.orElseThrow(() -> new NoSuchElementException("Nie znaleziono gifa o nazwie " + name));
		gifService.toggleFavorite(gif);
		if (r.equals("details")) {
			return "redirect:/g/{name}";
		} else {
			return "redirect:/";
		}
	}

	@GetMapping("/g/{name}/delete")
	public String delete(@PathVariable String name) {
		// pobran nazwe, np przez url
		// pobraz z bazy obiekt
		Gif gif = gifService.getByName(name)
			.orElseThrow(() -> new NoSuchElementException("Nie znaleziono gifa o nazwie " + name));

		// jesli istnieje to usunac
		gifService.delete(gif);
		// przekierowac widok na stone główną
		return "redirect:/";
	}

	@GetMapping("/favorites")
	public String getFav(Model model) {
		model.addAttribute("favoriteGifs", gifService.getFavorites());
		return "favorites";
	}
}
