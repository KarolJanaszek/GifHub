package pl.kielce.gifhub.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Gif {

	@Id
	@GeneratedValue
	private Long id;

	@NotBlank
	private String name;

	@NotBlank
	private String tag;

	@NotNull
	private boolean favorite;

	@ManyToMany(mappedBy = "gifs")
	private List<Category> categories;


	public String getUrl() {
		return "/gifs/" + name + ".gif";
	}

	public String getDetailsUrl() {
		return "/g/" + name;
	}

	public String getToggleFavoriteUrl() {
		return "/g/" + name + "/favorite";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public boolean isFavorite() {
		return favorite;
	}

	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
}
