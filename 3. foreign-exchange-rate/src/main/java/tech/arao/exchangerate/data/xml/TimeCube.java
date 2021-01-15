package tech.arao.exchangerate.data.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class TimeCube {

	private List<Cube> cubes;
	private String time;

	@XmlElement(name = "Cube")
	public List<Cube> getCubes() {
		return cubes;
	}

	public void setCubes(List<Cube> cubes) {
		this.cubes = cubes;
	}

	@XmlAttribute(name = "time")
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
