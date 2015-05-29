package net.x_talker.web.controller;

/**
 * 
 * Properties props = new Properties();
    props.put("mail.smtp.host", "my-mail-server");
    Session session = Session.getInstance(props, null);

    try {
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom("me@example.com");
        msg.setRecipients(Message.RecipientType.TO,
                          "you@example.com");
        msg.setSubject("JavaMail hello world example");
        msg.setSentDate(new Date());
        msg.setText("Hello, world!\n");
        Transport.send(msg, "me@example.com", "my-password");
    } catch (MessagingException mex) {
        System.out.println("send failed, exception: " + mex);
    }
    
    
Transport tr = session.getTransport("smtp");
tr.connect(smtphost, username, password);
msg.saveChanges();      // don't forget this
tr.sendMessage(msg, msg.getAllRecipients());
tr.close();


mail.debug	boolean	
mail.from	String	
mail.mime.address.strict	boolean
mail.host	String	
mail.store.protocol	String	
mail.transport.protocol	String
mail.user	String
mail.protocol.class	String
mail.protocol.host	String
mail.protocol.port	int
mail.protocol.user
 * */

public class SmtpSender 
{
	private final static String version = "0.0.1";
}
