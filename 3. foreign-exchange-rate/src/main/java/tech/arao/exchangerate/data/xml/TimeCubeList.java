package tech.arao.exchangerate.data.xml;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class TimeCubeList {

	private List<TimeCube> timeCube;

	@XmlElement(name = "Cube")
	public List<TimeCube> getTimeCube() {
		return timeCube;
	}

	public void setTimeCube(List<TimeCube> timeCube) {
		this.timeCube = timeCube;
	}
}
