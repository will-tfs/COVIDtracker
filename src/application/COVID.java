package application;

public class COVID 
{
	private String Province;
	private Double Population;
	private Double Infections;
	private Double Mort;
	private Double Date;
	
	public COVID()
	{
		this(null,null);
	}
	
	public COVID(String Province, String Population)
	{
		this.Province="";
		this.Population=0.0;
		this.Infections=0.0;
		this.Mort=0.0;
		this.Date=0.0;	
	}
	
	public String getProvince() {
		return Province;
	}
	public void setProvince(String province) {
		Province = province;
	}
	public Double getPopulation() {
		return Population;
	}
	public void setPopulation(Double population) {
		Population = population;
	}
	public Double getInfections() {
		return Infections;
	}
	public void setInfections(Double infections) {
		Infections = infections;
	}
	public Double getMort() {
		return Mort;
	}
	public void setMort(Double mort) {
		Mort = mort;
	}
	public Double getDate() {
		return Date;
	}
	public void setDate(Double date) {
		Date = date;
	}
	
}