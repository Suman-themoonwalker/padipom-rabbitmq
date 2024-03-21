package dwaki.rabbitmq.consumer.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import dwaki.rabbitmq.consumer.model.Due;

@Service
public class MailService {

	private static final Logger LOG = LoggerFactory.getLogger(MailService.class);

	public void sendEmail(Due due) {

		/*
		 * Create Email Session
		 */

		final String username = "infosciondwarak@gmail.com";
		final String password = "uowuaajhsnbjwaly";

		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true"); // TLS

		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		LOG.info("SMTP Session Connected");

		/*
		 * Send Email
		 */

		String from = "test@gmail.com";
		String to = "dwarakeshwaran.m@infosys.com";
		String subject = "RabbitMQ POC - Company "+ due.getCompanyName() + " Due";
		String msg = "Pay Due Amount " + due.getDueAmount() + " by " + due.getDueDate();

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(msg);

			Transport.send(message);

			System.out.println("Done");
			LOG.info("Email Sent");

		} catch (Exception e) {
			e.printStackTrace();
			LOG.info("Email Failed to Send");
		}

	}

}
