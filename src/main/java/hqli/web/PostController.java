package hqli.web;

import hqli.persistent.Post;
import hqli.persistent.PostRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
@Path("/")
public class PostController {

	@Inject
	private PostRepository postRepository;

	@GET
	@Path("/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PostDto> doName_Insecure(@PathParam("name") String name) {

		List<Post> posts = postRepository.getByName_Insecure(name);

		List<PostDto> dtos = new ArrayList<>();

		for (Post post : posts) {
			dtos.add(new PostDto(post.getId(), post.getName()));
		}

		return dtos;
	}
	
	@GET
	@Path("/secure/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PostDto> doName_Secure(@PathParam("name") String name) {

		List<Post> posts = postRepository.getByName_Secure(name);

		List<PostDto> dtos = new ArrayList<>();

		for (Post post : posts) {
			dtos.add(new PostDto(post.getId(), post.getName()));
		}

		return dtos;
	}

	@POST
	@Path("/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, String> doAdd(@PathParam("name") String name) {

		postRepository.save(new Post(name));

		return new HashMap<String, String>() {{
			put("message", "ok");
		}};
	}
}
