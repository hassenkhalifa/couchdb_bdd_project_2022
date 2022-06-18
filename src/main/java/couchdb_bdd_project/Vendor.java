package couchdb_bdd_project;

public class Vendor {
    private String Vendor;
    private String Country;
    private String Industry;


    public Vendor(String vendor, String country, String industry) {
        Vendor = vendor;
        Country = country;
        Industry = industry;
    }

    public String getVendor() {
        return Vendor;
    }

    public void setVendor(String vendor) {
        Vendor = vendor;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getIndustry() {
        return Industry;
    }

    public void setIndustry(String industry) {
        Industry = industry;
    }
}
