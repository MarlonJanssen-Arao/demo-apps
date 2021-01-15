package tech.arao.exchangerate.data.xml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Envelope", namespace = "http://www.gesmes.org/xml/2002-08-01")
@XmlAccessorType(XmlAccessType.NONE)
public class Envelope {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	private TimeCubeList timeCubeList;

	@XmlElement(name = "Cube")
	public TimeCubeList getTimeCubeList() {

		return timeCubeList;
	}

	public void setTimeCubeList(TimeCubeList timeCubeList) {
		this.timeCubeList = timeCubeList;
	}

}
