package application;

import java.awt.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;@

XmlRootElement(name="COVID")
public class COVIDListWrapper 
{
	private List COVID;
	@XmlElement(name = "COVID")
	public List getCOVID()
	{
		return COVID;
	}
	
	public void setCOVID(List covid)
	
	{
	this.COVID=covid;
	
	}
}