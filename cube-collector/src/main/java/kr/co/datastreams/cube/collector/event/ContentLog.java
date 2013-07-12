package kr.co.datastreams.cube.collector.event;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shkim
 * Date: 13. 7. 10
 * Time: 오후 4:42
 * To change this template use File | Settings | File Templates.
 */
public class ContentLog implements ContentLogEvent, Serializable {

    protected String logLevel;

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    protected Long id;
    protected String link;
    protected String title;
    protected String text;
    protected String source;
    protected User author;
    protected Date createdAt;
    protected Date publishedAt;
    protected Integer replyCount;
    protected Integer quoteCount;

    @Override
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getSource() {
        return source;
    }

    @Override
    public String getLogLevel() {
        return logLevel;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    @Override
    public Integer getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }

    @Override
    public Integer getQuoteCount() {
        return quoteCount;
    }

    public void setQuoteCount(Integer quoteCount) {
        this.quoteCount = quoteCount;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof ContentLog)) return false;

        ContentLog other = (ContentLog)o;
        if (!author.equals(other.author)) return false;
        if (!title.equals(other.title)) return false;
        if (!publishedAt.equals(other.publishedAt)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int m = 31;
        int result = publishedAt.hashCode();
        result = m * result + (title != null ? title.hashCode() : 0);
        result = m * result + (author != null ? author.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        //int contentCut = 20;

        buf.append(getClass().getSimpleName()).append("{")
                .append("id:").append(id).append(", ")
                .append("title:").append(title).append(", ")
                .append("author:").append(author).append(", ")
                .append("published:").append(publishedAt).append(", ")
                .append("createdAt:").append(createdAt).append(", ")
                .append("replyCount:").append(replyCount).append(", ")
                .append("quoteCount:").append(quoteCount).append(", ")
                .append("source:").append(source).append(", ")
                .append("text:").append(text);

        return buf.toString();

    }
}
