package couchdb_bdd_project;

public class Feedback {
    private String id;
    private String ref;
    private String comment;

    public Feedback(String id, String ref, String comment) {
        this.id = id;
        this.ref = ref;
        this.comment = comment;
    }

    public String get_id() {
        return id;
    }

    public void set_id(String id) {
        this.id = id;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
