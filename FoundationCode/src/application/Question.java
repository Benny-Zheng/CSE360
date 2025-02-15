package application;

/**
 * The Question class represents a user-submitted question.
 * 问题类表示用户提交的问题。
 */
public class Question {
    private int id;
    private String content;
    private String author;

    // 构造函数
    public Question(int id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }

    // Getter 和 Setter 方法
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
}
