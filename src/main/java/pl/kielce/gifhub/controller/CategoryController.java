package pl.kielce.gifhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.kielce.gifhub.service.CategoryService;

@Controller
public class CategoryController {
	private final CategoryService categoryService;

	@Autowired
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping("/categories")
	public String get(Model model) {
		model.addAttribute("categories", categoryService.getAll());
		return "categories";
	}
}
