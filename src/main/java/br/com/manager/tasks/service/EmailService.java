package br.com.manager.tasks.service;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import br.com.manager.tasks.model.Mail;
import freemarker.template.Configuration;
import freemarker.template.Template;

@Service
public class EmailService {
	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	@Qualifier("emailConfigBean")
	private Configuration emailConfig;

	public void sendEmail(Mail mailModel) throws Exception {
		Map<String, String> model = new HashMap<>();
		model.put("name", mailModel.getName());
		model.put("content", mailModel.getContent());

		mailModel.setModel(model);

		try {
			MimeMessage message = emailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message,
					MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

			Template template = emailConfig.getTemplate("user.ftl");
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, mailModel.getModel());

			mimeMessageHelper.setTo(mailModel.getTo());
			mimeMessageHelper.setText(html, true);
			mimeMessageHelper.setSubject(mailModel.getSubject());
			mimeMessageHelper.setFrom(mailModel.getFrom());

			emailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Houve um problema ao acessar o servidor de e-mail.");
		}
	}
}
