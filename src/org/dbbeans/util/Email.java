package org.dbbeans.util;

import java.io.File;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

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

    public static class Attachment {
        private final String filepath;
        private final String filename;

        public Attachment(final File file) {
            this(file, file.getName());
        }

        public Attachment(final File file, final String filename) {
            this(file.getPath(), filename);
        }

        public Attachment(final String filepath, final String filename) {
            this.filepath = filepath;
            this.filename = filename;
        }

        private EmailAttachment getEmailAttachment() {
            final EmailAttachment emailAttachment = new EmailAttachment();
            emailAttachment.setPath(filepath);
            emailAttachment.setName(filename);
            return emailAttachment;
        }
    }

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

    private boolean textOnly = false;

    private final List<Attachment> attachments = new ArrayList<Attachment>();

    private boolean debug = false;

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
    public void addTo(final Collection<String> addresses) {
        for (String address: addresses) {
            if (!EmailValidator.validate(address, true, true))
                throw new IllegalArgumentException("Invalid e-mail address: " + address);
            to.add(address);
        }
    }

    /**
     * Add one or more recipient for the message.
     * @param addresses one or more recipient for the message.
     */
    public void addTo(final String... addresses) {
        addTo(Arrays.asList(addresses));
    }

    /**
     * Add one or more CC recipient for the message.
     * @param addresses one or more CC recipient for the message.
     */
    public void addCc(final Collection<String> addresses) {
        for (String address: addresses) {
            if (!EmailValidator.validate(address, true, true))
                throw new IllegalArgumentException("Invalid e-mail address: " + address);
            cc.add(address);
        }
    }

    /**
     * Add one or more CC recipient for the message.
     * @param addresses one or more CC recipient for the message.
     */
    public void addCc(final String... addresses) {
        addCc(Arrays.asList(addresses));
    }

    /**
     * Add one or more BCC recipient for the message.
     * @param addresses one or more BCC recipient for the message.
     */
    public void addBcc(final Collection<String> addresses) {
        for (String address: addresses) {
            if (!EmailValidator.validate(address, true, true))
                throw new IllegalArgumentException("Invalid e-mail address: " + address);
            bcc.add(address);
        }
    }

    /**
     * Add one or more BCC recipient for the message.
     * @param addresses one or more BCC recipient for the message.
     */
    public void addBcc(final String... addresses) {
        addBcc(Arrays.asList(addresses));
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
     * Set this to true if you want to send a text only message (no HTML). Useful for SMS gateway and other
     * administrative uses.
     * @param textOnly flag that indicates if the HTML part of the message should be ignored.
     */
    public void setTextOnly(final boolean textOnly) {
        this.textOnly = textOnly;
    }

    /**
     * Sends the message.<br/>
     * It is the caller responsibility to ensure that all parameters previously passed to this object (SMTP server,
     * email addresses, etc.) are correct before calling send().
     * @throws java.lang.RuntimeException if the org.apache.commons.mail.HtmlEmail backend throws any exception. All
     * details of the originally thrown exception will be available in the RuntimeException.
     */
    public void send() {
        final org.apache.commons.mail.Email apacheEmail = createEmail();

        if (Strings.isEmpty(smtpServer))
            throw new IllegalArgumentException("No SMTP server specified.");
        if (Strings.isEmpty(from))
            throw new IllegalArgumentException("No sender (from address) specified.");
        if (to.size() == 0)
            throw new IllegalArgumentException("No recipient (to address) specified.");
        if (!textOnly && Strings.isEmpty(subject))
            throw new IllegalArgumentException("Subject is missing. Empty subject not allowed for HTML messages.");
        if (!textOnly && Strings.isEmpty(htmlMainText))
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

            apacheEmail.setDebug(debug);

            apacheEmail.send();
        } catch (final EmailException eex) {
            throw new RuntimeException(eex.getMessage());
        }
    }

    private org.apache.commons.mail.Email createEmail() {
        if (textOnly) {
            if (attachments.isEmpty()) {
                final SimpleEmail simpleEmail = new SimpleEmail();

                try {
                    simpleEmail.setMsg(textMessage);
                } catch (final EmailException eex) {
                    throw new RuntimeException(eex.getMessage());
                }

                return simpleEmail;
            }

            final MultiPartEmail multiPartEmail = new MultiPartEmail();

            try {
                multiPartEmail.setMsg(textMessage);
                for (Attachment attachment: attachments)
                    multiPartEmail.attach(attachment.getEmailAttachment());
            } catch (final EmailException eex) {
                throw new RuntimeException(eex.getMessage());
            }

            return multiPartEmail;
        }

        final HtmlEmail htmlEmail = new HtmlEmail();

        try {
            if (escapeNonAsciiCharacters)
                htmlEmail.setHtmlMsg(HTMLText.accentsToEscape(htmlPrologue + htmlMainText + htmlEpilogue));
            else
                htmlEmail.setHtmlMsg(htmlPrologue + htmlMainText + htmlEpilogue);
            if (!Strings.isEmpty(textMessage))
                htmlEmail.setTextMsg(textMessage);
            for (Attachment attachment: attachments)
                htmlEmail.attach(attachment.getEmailAttachment());
        } catch (final EmailException eex) {
            throw new RuntimeException(eex.getMessage());
        }

        return htmlEmail;
    }

    /**
     * Removes all email recipients added via the constuctor or the addTo() function.
     */
    public void clearRecipients() {
        to.clear();
    }

    /**
     * Removes all email recipients added via the addCc() function.
     */
    public void clearCCs() {
        cc.clear();
    }

    /**
     * Removes all email recipients added via the addBcc() function.
     */
    public void clearBCCs() {
        bcc.clear();
    }

    public void addAttachment(final File file) {
        attachments.add(new Attachment(file));
    }

    public void addAttachment(final File file, final String filename) {
        attachments.add(new Attachment(file, filename));
    }

    public void addAttachment(final String filepath, final String filename) {
        attachments.add(new Attachment(filepath, filename));
    }

    public void addAttachment(final Attachment attachment) {
        attachments.add(attachment);
    }

    public void clearAttachments() {
        attachments.clear();
    }

    public String getToRecipients() {
        return flatten(to);
    }

    public String getCcRecipients() {
        return flatten(cc);
    }

    public String getBCcRecipients() {
        return flatten(bcc);
    }

    private String flatten(Set<String> addresses) {
        if (addresses.isEmpty())
            return "";

        final StringBuilder buf = new StringBuilder();
        for (String address: addresses)
            buf.append(address).append(", ");
        buf.delete(buf.length() - 2, buf.length());

        return buf.toString();
    }

    public String getSubject() {
        return subject;
    }

    public String getHtmlText() {
        return htmlPrologue + htmlMainText + htmlEpilogue;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }
}
