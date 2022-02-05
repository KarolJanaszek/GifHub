package pl.kielce.gifhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kielce.gifhub.model.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
}
