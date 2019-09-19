package fr.worldline.user;

import java.util.Collections;
import java.util.List;

import fr.worldline.common.ResourceNotFoundException;
import fr.worldline.common.contract.Post;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/user")
public class UserController {

	@Value("${eureka.instance.metadata-map.version}")
	public String currentVersion;

	@Autowired
	private PostApi api;

	private final List<User> userList = Collections.singletonList(User.builder()
			.id(1L)
			.name("User name")
			.build());

	@GetMapping("/{id}")
	public User findOne(@PathVariable Long id) {
		log.info("Version : {}", currentVersion);
		User user = this.userList.stream()
				.filter(u -> id.equals(u.getId()))
				.findFirst()
				.orElseThrow(ResourceNotFoundException::new);
		List<Post> postList = this.api.findForUser(id);
		user.setPosts(postList);
		return user;
	}


	@Data
	@Builder
	public static class User {
		public Long id;

		public String name;

		public List<Post> posts;
	}

}
