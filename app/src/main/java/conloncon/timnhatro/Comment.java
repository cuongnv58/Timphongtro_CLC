package conloncon.timnhatro;

public class Comment {
    String pk;
    String comment;
    String username;
    String time;

    public String getPk() {
        return pk;
    }
    public String getComment() {
        return comment;
    }
    public String getUsername() {
        return username;
    }
    public String getTime() {
        return time;
    }
    public void setPk(String pk) {
        this.pk = pk;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setTime(String time) {
        this.time = time;
    }
}
