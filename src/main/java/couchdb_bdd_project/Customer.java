package couchdb_bdd_project;

public class Customer {

	private String _id;
	private String _rev;
	private String id;
	private String firstName;
	private String lastName;
	private String gender;
	private String birthday;
	private String creationDate;
	private String locationIP;
	private String browserUsed;
	private String place;

	public Customer(String _id, String id, String firstName, String lastName, String gender, String birthday,
			String creationDate, String locationIP, String browserUsed, String place) {
		this._id = _id;
		this.setId(id);
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthday = birthday;
		this.creationDate = creationDate;
		this.locationIP = locationIP;
		this.browserUsed = browserUsed;
		this.place = place;
	}

	public Customer(String id, String firstName, String lastName, String gender, String birthday, String creationDate,
			String locationIP, String browserUsed, String place) {
		this.setId(id);
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthday = birthday;
		this.creationDate = creationDate;
		this.locationIP = locationIP;
		this.browserUsed = browserUsed;
		this.place = place;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getLocationIP() {
		return locationIP;
	}

	public void setLocationIP(String locationIP) {
		this.locationIP = locationIP;
	}

	public String getBrowserUsed() {
		return browserUsed;
	}

	public void setBrowserUsed(String browserUsed) {
		this.browserUsed = browserUsed;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String get_rev() {
		return _rev;
	}

	public void set_rev(String _rev) {
		this._rev = _rev;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
