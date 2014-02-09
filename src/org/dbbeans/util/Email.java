package org.dbbeans.util;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 * ...
 */
public class Email {

    private String smtpServer;

    private String from;

    private Set<String> to = new HashSet<String>();
    private Set<String> cc = new HashSet<String>();
    private Set<String> bcc = new HashSet<String>();

    private String subject;

    private String htmlProlog = "<html>";
    private String htmlMainText = "";
    private String htmlEpilog = "</html>";

    private String textMessage;

    private boolean escapeNonAsciiCharacters = false;

    private HtmlEmail apacheEmail = new HtmlEmail();


    public void setSmtpServer(final String smtpServer) {
        this.smtpServer = smtpServer;
    }

    public void setFrom(final String from) {
        if (!EmailValidator.validate(from, true, true))
            throw new IllegalArgumentException("Invalid e-mail address: " + from);

        this.from = from;
    }

    public void addTo(final String... addresses) {
        for (String address: addresses) {
            if (!EmailValidator.validate(address, true, true))
                throw new IllegalArgumentException("Invalid e-mail address: " + address);
            to.add(address);
        }
    }

    public void addCc(final String... addresses) {
        for (String address: addresses) {
            if (!EmailValidator.validate(address, true, true))
                throw new IllegalArgumentException("Invalid e-mail address: " + address);
            cc.add(address);
        }
    }

    public void addBcc(final String... addresses) {
        for (String address: addresses) {
            if (!EmailValidator.validate(address, true, true))
                throw new IllegalArgumentException("Invalid e-mail address: " + address);
            bcc.add(address);
        }
    }

    public void setSubject(final String subject) {
        this.subject = subject;
    }

    public void setHtmlProlog(final String htmlProlog) {
        this.htmlProlog = htmlProlog;
    }

    public void setHtmlMainText(final String htmlMainText) {
        this.htmlMainText = htmlMainText;
    }

    public void setHtmlEpilog(final String htmlEpilog) {
        this.htmlEpilog = htmlEpilog;
    }

    public void setTextMessage(final String textMessage) {
        this.textMessage = textMessage;
    }

    public void setEscapeNonAsciiCharacters(final boolean escapeNonAsciiCharacters) {
        this.escapeNonAsciiCharacters = escapeNonAsciiCharacters;
    }


    public void send() {
        if (Strings.isEmpty(smtpServer))
            throw new IllegalArgumentException("No SMTP server specified.");
        if (Strings.isEmpty(from))
            throw new IllegalArgumentException("No sender (from address) specified.");
        if (to.size() == 0)
            throw new IllegalArgumentException("No recipient (to address) specified.");
        if (Strings.isEmpty(subject))
            throw new IllegalArgumentException("Subject is missing.");
        if (Strings.isEmpty(htmlMainText))
            throw new IllegalArgumentException("Main message text is missing.");

        try {
            apacheEmail.setHostName(smtpServer);
            apacheEmail.setFrom(from);
            for (String address: to)
                apacheEmail.addTo(address);
            for (String address: cc)
                apacheEmail.addCc(address);
            for (String address: bcc)
                apacheEmail.addBcc(address);
            apacheEmail.setSubject(subject);
            if (escapeNonAsciiCharacters)
                apacheEmail.setHtmlMsg(HTMLText.accentsToEscape(htmlProlog + htmlMainText + htmlEpilog));
            else
                apacheEmail.setHtmlMsg(htmlProlog + htmlMainText + htmlEpilog);
            if (!Strings.isEmpty(textMessage))
                apacheEmail.setTextMsg(textMessage);

            apacheEmail.send();
        } catch (final EmailException eex) {
            throw new RuntimeException(eex.getMessage());
        }
    }
}
