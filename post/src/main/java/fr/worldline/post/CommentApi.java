package fr.worldline.post;

import java.util.List;

import fr.worldline.common.contract.Comment;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "comment", contextId = "commentApi", path = "/api/comment")
public interface CommentApi {

	@GetMapping
	List<Comment> findAll(@RequestParam Long postId);
}
