package webmail.entities;

import java.util.Date;

/**
 * Created by YuexingYin on 11/10/14.
 */
public class Email {
    private String id;
    private String date;
    private String subject;
    private String from;
    private String to;
    private String text;
    private String html;
    private String content;
    private boolean read;
    private String folder;

    public void setId(String id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setRead(boolean read){this.read=read;}

    public void setFolder(String folder){this.folder=folder;}

    public String getId(){return id;}

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }

    public String getSubject() {
        return subject;
    }

    public String getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    public String getText() {
        return text;
    }

    public String getHtml() {
        return html;
    }

    public boolean getRead(){ return read;}

    public String getFolder() {
        return folder;
    }
}
