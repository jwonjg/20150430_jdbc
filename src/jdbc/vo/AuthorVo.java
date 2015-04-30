package jdbc.vo;

public class AuthorVo {
	private Long id;
	private String name;
	private String bio;
	
	public AuthorVo() {}

	public AuthorVo(Long id, String name, String bio) {
		super();
		this.id = id;
		this.name = name;
		this.bio = bio;
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

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}
	
}
