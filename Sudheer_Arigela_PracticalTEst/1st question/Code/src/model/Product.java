package model;

public class Product {
	private int pid;
	private String pname;
	private double price;
	private String hsnCode;
	private int pcatid;
	private String image;

	// Constructors
	public Product(int i, String pname, double price, String hsnCode, int pcatid, String image) {
		// Default constructor
		this.pid = i;
		this.pname = pname;
		this.price = price;
		this.hsnCode = hsnCode;
		this.pcatid = pcatid;
		this.image = image;
	}

	public Product(int i, String pname, double price, String image) {
		// Default constructor
		this.pid = i;
		this.pname = pname;
		this.price = price;
		this.image = image;
	}

	public Product() {
		// TODO Auto-generated constructor stub
	}

	// Getters and setters
	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getHsnCode() {
		return hsnCode;
	}

	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}

	public int getPcatid() {
		return pcatid;
	}

	public void setPcatid(int pcatid) {
		this.pcatid = pcatid;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	// toString method (optional)
	@Override
	public String toString() {
		return "Product{" + "pid=" + pid + ", pname='" + pname + '\'' + ", price=" + price + ", hsnCode='" + hsnCode
				+ '\'' + ", pcatid=" + pcatid + ", image='" + image + '\'' + '}';
	}
}