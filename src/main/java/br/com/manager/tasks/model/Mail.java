package br.com.manager.tasks.model;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Mail {
	private String from = "nao.responda.gerenciador@gmail.com";
	private String to;
	private String name;
	private String subject;
	private String content;
	private Map<String, String> model = new HashMap<>();

	public Mail(String to, String name, String subject, String content) {
		this.from = "nao.responda.gerenciador@gmail.com";
		this.to = to;
		this.name = name;
		this.subject = subject;
		this.content = content;
		this.model = new HashMap<>();
	}
}