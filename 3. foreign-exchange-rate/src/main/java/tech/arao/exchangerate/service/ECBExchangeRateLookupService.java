package tech.arao.exchangerate.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tech.arao.exchangerate.data.domain.ExchangeRate;
import tech.arao.exchangerate.data.xml.Cube;
import tech.arao.exchangerate.data.xml.Envelope;
import tech.arao.exchangerate.data.xml.TimeCube;
import tech.arao.exchangerate.data.xml.TimeCubeList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ECBExchangeRateLookupService {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Value("${ecb.webservice.url}")
	private String url;
	private final RestTemplate template;


	public ECBExchangeRateLookupService(RestTemplateBuilder builder) {
		this.template = builder.build();
	}


	@Async
	public Collection<ExchangeRate> updateRates() throws Exception {
		LOGGER.info("Fetching European Central Bank foreign exchange reference rates");

		Envelope envelope = getEnvelope();

		if (envelope != null) {
			LOGGER.debug("Transform Envelope XML object into collection of ExchangeRate");
			Collection<ExchangeRate> rates = transformEnvelopeToExchangeRates(envelope);
			return rates;
		} else {
			return Collections.EMPTY_LIST;
		}
	}

	private Collection<ExchangeRate> transformEnvelopeToExchangeRates(Envelope envelope) throws Exception {
		if (envelope == null) {
			return Collections.EMPTY_LIST;
		}

		LinkedList<ExchangeRate> rates = new LinkedList<>();
		TimeCubeList dates = envelope.getTimeCubeList();

		LOGGER.debug("----------->>> transformEnvelopeToExchangeRates getTimeCubeList().getTimeCube()");

		List<TimeCube> timeCubes = envelope.getTimeCubeList().getTimeCube();

		LOGGER.debug("----------->>> transformEnvelopeToExchangeRates transforming time cubes to rates");
		timeCubes.stream().forEach(timeCube -> transformTimeCube(timeCube, rates));

		return rates;
	}

	private void transformTimeCube(TimeCube timeCube, LinkedList<ExchangeRate> rates) {
		Date date = parseDate(timeCube);
		timeCube.getCubes().stream().forEach(cube -> transformCube(cube, date, rates));
	}

	private Date parseDate(TimeCube timeCube) {
		String timeString = timeCube.getTime();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = simpleDateFormat.parse(timeString);
		} catch (ParseException e) {
			LOGGER.error("Cannot parse date: " + timeString, e);
			return null;
		}
		return date;
	}

	private void transformCube(Cube cube, Date date, LinkedList<ExchangeRate> rates) {
		ExchangeRate exchangeRate = new ExchangeRate(cube.getCurrency(), cube.getRate(), date);
		rates.add(exchangeRate);
	}

	private Envelope getEnvelope() {
		return template.getForObject(url, Envelope.class);
	}
}
