package br.com.inatel.steamclub.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.inatel.steamclub.model.Post;
import br.com.inatel.steamclub.repository.PostRepository;

public class PostUpdateForm {

	@NotNull @NotEmpty @Length(min=5)
    private String title;

    @NotNull @NotEmpty @Length(min=5)
    private String authorName;

    @NotNull @NotEmpty @Length(min=5)
    private String content;
    
    @NotNull
    private boolean isPublic;

	public String getTitle() {
		return title;
	}

	public String getAuthorName() {
		return authorName;
	}

	public String getContent() {
		return content;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}
	
	public Post update(Long id, PostRepository postRepository) {
		Post post = postRepository.getById(id);
		post.setTitle(title);
		post.setContent(content);
		post.setPublic(isPublic);
		return post;
	}

}
