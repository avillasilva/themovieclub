package br.com.inatel.steamclub.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.inatel.steamclub.model.Post;

public class PostForm {
    
	@NotNull @NotEmpty @Length(min=5)
    private String title;

    @NotNull @NotEmpty
    private String authorName;

    @NotNull @NotEmpty
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

	public Post toPost() {
		return new Post(title, authorName, content, isPublic);
	}
}
