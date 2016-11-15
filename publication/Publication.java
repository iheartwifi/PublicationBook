/**
 * COMP 249:U - assignment 2 - Publication
 * Wan Lan He - 29496419
 */
package assignment2;

public class Publication {
	//instances variables of class publication
	private long publication_code;
	private String publication_name;
	private int publication_year;
	private String publication_authorname;
	private double publication_cost;
	private int publication_nbpages;

	//default constructors
	public Publication(){
		publication_code=0;
		publication_name="";
		publication_year=0;
		publication_authorname="";
		publication_cost=0.0;
		publication_nbpages=0;
	}
	
	public Publication(long publication_code){
		this.publication_code=publication_code;
	}
	
	//default constructor
	public Publication(long publication_code, String publication_name, int publication_year, String publication_authorname,
			double publication_cost, int publication_nbpages) {
		this.publication_code=publication_code;
		this.publication_name=publication_name;
		this.publication_year=publication_year;
		this.publication_authorname=publication_authorname;
		this.publication_cost=publication_cost;
		this.publication_nbpages=publication_nbpages;
	}
	
	
	
	//getters
	public long getPublicationCode(){
		return publication_code;
	}
	
	public String getPublicationName(){
		return publication_name;
	}
	
	public int getPublicationYear(){
		return publication_year;
	}
	
	public String getPublicationAuthorName(){
		return publication_authorname;
	}
	
	public double getPublicationCost(){
		return publication_cost;
	}
	
	public int getPublicationPages(){
		return publication_nbpages;
	}
	
	//setters
	public void setPublicationCode(long publication_code){
		this.publication_code=publication_code;
	}
	
	public void setPublicationName(String publication_name){
		this.publication_name=publication_name;
	}
	
	public void setPublicationYear(int publication_year){
		this.publication_year=publication_year;
	}
	
	public void setPublicationAuthorName(String publication_authorname){
		this.publication_authorname=publication_authorname;
	}
	
	public void setPublicationCost(double publication_cost){
		this.publication_cost=publication_cost;
	}
	
	public void setPublicationPages(int publication_nbpages){
		this.publication_nbpages=publication_nbpages;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Publication other = (Publication) obj;
		if (publication_code!= other.publication_code)
			return false;
		if (publication_authorname == null) {
			if (other.publication_name != null)
				return false;
		} else if (!publication_authorname.equals(other.publication_authorname))
			return false;
		if (publication_year != other.publication_year)
			return false;
		if (publication_nbpages != other.publication_nbpages)
			return false;
		if (Double.doubleToLongBits(publication_cost) != Double
				.doubleToLongBits(other.publication_cost))
			return false;
		if (publication_name == null) {
			if (other.publication_name != null)
				return false;
		} else if (!publication_name.equals(other.publication_name))
			return false;
		return true;
	}
	
	public String toString(){
		return publication_code + " " + publication_name + " "
				+ publication_year + " " + publication_authorname + " "
				+ publication_cost + " " + publication_nbpages;
	}
	
	
}


