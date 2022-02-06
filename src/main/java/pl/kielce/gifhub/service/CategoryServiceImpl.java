package pl.kielce.gifhub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kielce.gifhub.model.Category;
import pl.kielce.gifhub.repository.CategoryRepo;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
	private final CategoryRepo categoryRepo;

	@Autowired
	public CategoryServiceImpl(CategoryRepo categoryRepo) {
		this.categoryRepo = categoryRepo;
	}


	@Override
	public List<Category> getAll() {
		return categoryRepo.findAll();
	}
}
