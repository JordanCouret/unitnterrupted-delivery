package fr.worldline.common.contract;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Post {
	public Long id;

	public String message;

	public List<Comment> comments;

	public Long userId;
}
