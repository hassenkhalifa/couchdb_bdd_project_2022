package couchdb_bdd_project;

public class BrandProduct {
    private String type;
    private String ref;


    public BrandProduct(String type, String ref) {
        this.type = type;
        this.ref = ref;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }
}
