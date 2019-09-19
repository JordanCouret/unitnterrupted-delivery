package fr.worldline.user;

import java.util.List;

import fr.worldline.common.contract.Post;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "post", path = "/api/post")
public interface PostApi {
	@GetMapping
	List<Post> findForUser(@RequestParam("userId") Long userId);
}
