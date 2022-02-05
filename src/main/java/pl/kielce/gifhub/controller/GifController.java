package pl.kielce.gifhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import pl.kielce.gifhub.model.Gif;
import pl.kielce.gifhub.service.CategoryService;
import pl.kielce.gifhub.service.GifService;

import java.util.List;
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
	public String home(Model model) {
		List<Gif> gifs = gifService.getAll();
		model.addAttribute("gifs", gifs);
		return "home";
	}

	@GetMapping("/{name}")
	public String getGif(Model model, @PathVariable String name) {
		model.addAttribute("editedGif", gifService.getByName(name)
			.orElseThrow(() -> new NoSuchElementException("Nie znaleziono gifa o nazwie " + name))
		);
		return "gif_details";
	}

	@GetMapping("/{name}/favorite")
	public String toggleFavorite(@PathVariable String name, @RequestParam(required = false, defaultValue = "") String r)
		throws NoSuchElementException {
		Gif gif = gifService.getByName(name)
			.orElseThrow(() -> new NoSuchElementException("Nie znaleziono gifa o nazwie " + name));
		gifService.toggleFavorite(gif);
		if (r.equals("details")) {
			return "redirect:/{name}";
		} else {
			return "redirect:/";
		}
	}
}
