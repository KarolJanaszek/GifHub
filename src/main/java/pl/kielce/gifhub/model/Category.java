package pl.kielce.gifhub.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class Category {

	@Id
	@GeneratedValue
	private Long id;

	@NotBlank
	private String name;

	@ManyToMany
	private List<Gif> gifs;


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

	public List<Gif> getGifs() {
		return gifs;
	}

	public void setGifs(List<Gif> gifs) {
		this.gifs = gifs;
	}
}
