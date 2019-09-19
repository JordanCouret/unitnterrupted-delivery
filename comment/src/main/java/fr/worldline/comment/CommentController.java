package fr.worldline.comment;

import java.util.ArrayList;
import java.util.List;

import fr.worldline.common.contract.Comment;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static java.util.stream.Collectors.toList;


@RestController
@RequestMapping("/api/comment")
@Slf4j
public class CommentController {

	@Value("${eureka.instance.metadata-map.version}")
	public String currentVersion;


	public CommentController() {
		for (long id = 0; id < 20; id++) {
			commentList.add(Comment.builder()
					.id(id)
					.postId(id % 2 == 0 ? id / 2 : id)
					.message(String.format("Comment generated with id = %d", id))
					.build());
		}
	}

	private final List<Comment> commentList = new ArrayList<>();

	@GetMapping
	public List<Comment> findAll(@RequestParam Long postId) {
		log.info("Version : {}", currentVersion);
		return commentList.stream()
				.filter(user -> postId.equals(user.getId()))
				.collect(toList());
	}
}
