package webmail.entities;

public class User {
    private String userid;
    private String username;
    private String password;
    private String gender;
	private String age;

	public User(String username,String password, String gender,String age) {

		this.username = username;
        this.password=password;
        this.gender=gender;
		this.age = age;
	}

    public User(String username,String password ){
        this.username=username;
        this.password=password;
    }

    public String getUserid(){return userid;}
	public String getName() { return username; }
    public String getPassword(){return password;}
    public String getGender(){return gender;}
	public String getAge() { return age; }
	public String toString() { return username+":"+age; }
}
