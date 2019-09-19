package fr.worldline.post;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import fr.worldline.common.contract.Post;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
@Slf4j
public class PostController {

	@Value("${eureka.instance.metadata-map.version}")
	public String currentVersion;

	@Autowired
	private CommentApi api;

	private List<Post> postList = new ArrayList<>();

	@PostConstruct
	public void init() {
		for (long id = 0; id < 20; id++) {
			postList.add(Post.builder()
					.id(id)
					.message(String.format("Post message for id = %d", id))
					.userId(1L)
					.build());
		}
	}

	@GetMapping
	public List<Post> findByUserId(@RequestParam Long userId) {
		log.info("Version : {}", currentVersion);
		return this.postList
				.stream()
				.filter(post -> userId.equals(post.getUserId()))
				.peek(post -> post.setComments(api.findAll(post.getId())))
				.collect(Collectors.toList());
	}
}
