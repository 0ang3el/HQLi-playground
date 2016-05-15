package hqli.web;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PostDto {

	@XmlElement
	private int id;

	@XmlElement
	private String name;
	
	public PostDto(){
		
	}

	public PostDto(int id, String name) {
		this.id = id;
		this.name = name;
	}
}
