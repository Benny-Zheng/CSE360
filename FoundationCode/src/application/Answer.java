package application;

/**
 * The Answer class represents an answer provided by a user for a question.
 * 答案类表示用户为问题提供的答案。
 */
public class Answer {
    private int id;
    private int questionId;
    private String content;
    private String author;

    // 构造函数
    public Answer(int id, int questionId, String content, String author) {
        this.id = id;
        this.questionId = questionId;
        this.content = content;
        this.author = author;
    }

    // Getter 和 Setter 方法
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getQuestionId() { return questionId; }
    public void setQuestionId(int questionId) { this.questionId = questionId; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
}
