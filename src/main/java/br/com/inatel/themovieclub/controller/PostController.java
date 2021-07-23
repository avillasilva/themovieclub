package br.com.inatel.themovieclub.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.inatel.themovieclub.controller.dto.PostDto;
import br.com.inatel.themovieclub.controller.form.PostForm;
import br.com.inatel.themovieclub.controller.form.PostUpdateForm;
import br.com.inatel.themovieclub.model.Post;
import br.com.inatel.themovieclub.repository.PostRepository;
import br.com.inatel.themovieclub.service.ApiService;

@RestController
@RequestMapping("/posts")
public class PostController {
	
	@Autowired
	private PostRepository postRepository;
	
	@GetMapping
	public List<Post> list() {
		List<Post> posts = postRepository.findAll();
		return posts;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PostDto> detail(@PathVariable Long id) {
		Optional<Post> post = postRepository.findById(id);
		if (post.isPresent()) {
			return ResponseEntity.ok(new PostDto(post.get())); 
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<PostDto> create(@RequestBody @Valid PostForm form, UriComponentsBuilder uriBuilder) {
		Post post = form.toPost();
		postRepository.save(post);
		
		URI uri = uriBuilder.path("/posts/{id}").buildAndExpand(post.getId()).toUri();
		return ResponseEntity.created(uri).body(new PostDto(post));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<PostDto> update(@PathVariable Long id, @RequestBody @Valid PostUpdateForm form) {
		Optional<Post> postOptional = postRepository.findById(id);
    	if (postOptional.isPresent()) {
			Post post = form.update(id, postRepository);
			return ResponseEntity.ok(new PostDto(post));
		}
    	
    	return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<PostDto> delete(@PathVariable Long id){
    	Optional<Post> postOptional = postRepository.findById(id);
    	if (postOptional.isPresent()) {
    		postRepository.deleteById(id);			
    		return ResponseEntity.ok().build();
		}
    	
    	return ResponseEntity.notFound().build();
    }
}
