package com.example.im01.psmemory.Gmail;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Security;
import java.util.Properties;

public class GMailSender extends javax.mail.Authenticator
{
    private String mailhost = "smtp.gmail.com";
    private String user;
    private String password;
    private Session session;

    static
    {
        Security.addProvider(new JSSEProvider());
    }

    public GMailSender(String user, String password)
    {
        this.user = user;
        this.password = password;

        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", mailhost);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.quitwait", "false");

        session = Session.getDefaultInstance(props, this);
    }

    protected PasswordAuthentication getPasswordAuthentication()
    {
        return new PasswordAuthentication(user, password);
    }

    public synchronized void sendMail(String subject, String body, String sender, String recipients) throws Exception
    {
        MimeMessage message = new MimeMessage(session);

        // Add trivial information
        message.setSender(new InternetAddress(sender));
        message.setSubject(subject);
        if (recipients.indexOf(',') > 0) {
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
        } else {
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipients));
        }

        // Create multipart content.
        Multipart multipart = new MimeMultipart("mixed");

        // Add the body part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setDataHandler(new DataHandler(new ByteArrayDataSource(body.getBytes(), "text/html")));
        multipart.addBodyPart(messageBodyPart);

        // Put parts in message
        message.setContent(multipart);

        // Send the message.
        Transport.send(message);

        //pictrue send
        // create multipart
        Multipart multipartP = new MimeMultipart();

        // create bodypart with image and set content-id
        MimeBodyPart messageBodyPartP = new MimeBodyPart();
        File testImage = new File("/storage/emulated/0/Download/", "test.png"); //手機檔案位置,檔案名稱
        DataSource source = new FileDataSource(testImage);
        messageBodyPartP.setDataHandler(new DataHandler(source));
        messageBodyPartP.setFileName("image.png");
        messageBodyPartP.setDisposition(MimeBodyPart.INLINE);
        messageBodyPartP.setHeader("Content-ID","<vogue>");
        multipartP.addBodyPart(messageBodyPart);
        // create bodypart with html content and reference to the content-id
        messageBodyPartP = new MimeBodyPart();
        String htmlText = "<img src=\"cid:vogue\">";
        messageBodyPartP.setContent(htmlText, "text/html");
        multipartP.addBodyPart(messageBodyPart);

        // add multipart to message
        message.setContent(multipartP);



    }

    public class ByteArrayDataSource implements DataSource
    {
        private byte[] data;
        private String type;

        public ByteArrayDataSource(byte[] data, String type)
        {
            super();
            this.data = data;
            this.type = type;
        }

        public ByteArrayDataSource(byte[] data)
        {
            super();
            this.data = data;
        }

        public void setType(String type)
        {
            this.type = type;
        }

        public String getContentType()
        {
            if (type == null) return "application/octet-stream";
            else return type;
        }

        public InputStream getInputStream() throws IOException
        {
            return new ByteArrayInputStream(data);
        }

        public String getName()
        {
            return "ByteArrayDataSource";
        }

        public OutputStream getOutputStream() throws IOException
        {
            throw new IOException("Not Supported");
        }

    }
}