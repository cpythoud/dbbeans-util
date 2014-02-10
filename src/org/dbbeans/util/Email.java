package org.dbbeans.util;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 * This class is a basic wrapper around org.apache.commons.mail.HtmlEmail.
 * <br/>
 * This class adds an HTML prologue and an HTML epilogue, between which the actual message is inserted. The default
 * prologue is:<br/>
 * {@literal <html>}<br/>
 * The default epilogue is:<br/>
 * {@literal </html>}<br/>
 * The prologue and epilogue can be independently modified or kept as is.
 */
public class Email {

    private String smtpServer;

    private String from;

    private Set<String> to = new HashSet<String>();
    private Set<String> cc = new HashSet<String>();
    private Set<String> bcc = new HashSet<String>();

    private String subject;

    private String htmlPrologue = "<html>";
    private String htmlMainText = "";
    private String htmlEpilogue = "</html>";

    private String textMessage;

    private boolean escapeNonAsciiCharacters = false;

    private HtmlEmail apacheEmail = new HtmlEmail();


    /**
     * Set the SMTP server to be used for sending e-mail. SMTP authentication is not supported yet.
     * @param smtpServer the SMTP server.
     */
    public void setSmtpServer(final String smtpServer) {
        this.smtpServer = smtpServer;
    }

    /**
     * Set the e-mail address of the (supposed) message sender.
     * @param from sender e-mail address.
     */
    public void setFrom(final String from) {
        if (!EmailValidator.validate(from, true, true))
            throw new IllegalArgumentException("Invalid e-mail address: " + from);

        this.from = from;
    }

    /**
     * Add one or more recipient for the message.
     * @param addresses one or more recipient for the message.
     */
    public void addTo(final String... addresses) {
        for (String address: addresses) {
            if (!EmailValidator.validate(address, true, true))
                throw new IllegalArgumentException("Invalid e-mail address: " + address);
            to.add(address);
        }
    }

    /**
     * Add one or more CC recipient for the message.
     * @param addresses one or more CC recipient for the message.
     */
    public void addCc(final String... addresses) {
        for (String address: addresses) {
            if (!EmailValidator.validate(address, true, true))
                throw new IllegalArgumentException("Invalid e-mail address: " + address);
            cc.add(address);
        }
    }

    /**
     * Add one or more BCC recipient for the message.
     * @param addresses one or more BCC recipient for the message.
     */
    public void addBcc(final String... addresses) {
        for (String address: addresses) {
            if (!EmailValidator.validate(address, true, true))
                throw new IllegalArgumentException("Invalid e-mail address: " + address);
            bcc.add(address);
        }
    }

    /**
     * Set the subject of the message.
     * @param subject of the message.
     */
    public void setSubject(final String subject) {
        this.subject = subject;
    }

    /**
     * Change the HTML prologue of the message.
     * @param htmlPrologue HTML prologue of the message.
     */
    public void setHtmlPrologue(final String htmlPrologue) {
        this.htmlPrologue = htmlPrologue;
    }

    /**
     * Set the text of the message.
     * @param htmlMainText text of the message.
     */
    public void setHtmlMainText(final String htmlMainText) {
        this.htmlMainText = htmlMainText;
    }

    /**
     * Change the HTML epilogue of the message.
     * @param htmlEpilogue HTML epilogue of the message.
     */
    public void setHtmlEpilogue(final String htmlEpilogue) {
        this.htmlEpilogue = htmlEpilogue;
    }

    /**
     * Set alternate plain text message for users with e-mail clients that cannot process HTML e-mail messages.
     * @param textMessage plain text message.
     */
    public void setTextMessage(final String textMessage) {
        this.textMessage = textMessage;
    }

    /**
     * Set or unset a flag that indicates that any accented characters should be replaced by the appropriate HTML
     * entity when the message is sent.
     * @param escapeNonAsciiCharacters flag that indicates  if accented characters should be escaped.
     */
    public void setEscapeNonAsciiCharacters(final boolean escapeNonAsciiCharacters) {
        this.escapeNonAsciiCharacters = escapeNonAsciiCharacters;
    }


    /**
     * Sends the message.<br/>
     * It is the caller responsibility to ensure that all parameters previously passed to this object (SMTP server,
     * email addresses, etc.) are correct before calling send().
     * @throws java.lang.RuntimeException if the org.apache.commons.mail.HtmlEmail backend throws any exception. All
     * details of the originally thrown exception will be available in the RuntimeException.
     */
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
                apacheEmail.setHtmlMsg(HTMLText.accentsToEscape(htmlPrologue + htmlMainText + htmlEpilogue));
            else
                apacheEmail.setHtmlMsg(htmlPrologue + htmlMainText + htmlEpilogue);
            if (!Strings.isEmpty(textMessage))
                apacheEmail.setTextMsg(textMessage);

            apacheEmail.send();
        } catch (final EmailException eex) {
            throw new RuntimeException(eex.getMessage());
        }
    }
}
