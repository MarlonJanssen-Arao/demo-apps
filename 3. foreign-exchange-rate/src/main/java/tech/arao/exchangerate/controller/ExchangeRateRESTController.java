package tech.arao.exchangerate.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.arao.exchangerate.data.domain.ExchangeRate;
import tech.arao.exchangerate.service.ExchangeRatePersistenceService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/exchangeRate")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ExchangeRateRESTController {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ExchangeRatePersistenceService persisntence;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<List<ExchangeRate>> listAllRates() {
		LOGGER.debug("Listing all rates");
		List<ExchangeRate> rates = persisntence.getAllRates();

		if (rates == null || rates.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<ExchangeRate>>(rates, HttpStatus.OK);
	}

	@RequestMapping(value = "/all/{date}", method = RequestMethod.GET)
	public ResponseEntity<List<ExchangeRate>> getAllByDate(
			@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
		LOGGER.debug("Get all by date" + date);
		List<ExchangeRate> rates = persisntence.getAllRatesByDate(date);

		if (rates == null || rates.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<ExchangeRate>>(rates, HttpStatus.OK);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<ExchangeRate>> getAllByCurrency(@RequestParam("cur") String currency) {
		LOGGER.debug("Get all by currency" + currency);
		List<ExchangeRate> rates = persisntence.getAllRatesByCurrency(currency);

		if (rates == null || rates.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<ExchangeRate>>(rates, HttpStatus.OK);
	}

	@RequestMapping(value = "/{date}", method = RequestMethod.GET)
	public ResponseEntity<ExchangeRate> getByDateAndCurrency(
			@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
			@RequestParam("cur") String currency) {
		LOGGER.debug("Get by date: " + date + " and currency: " + currency);
		ExchangeRate rate = persisntence.getByDateAndCurrency(date, currency);

		if (rate == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<ExchangeRate>(rate, HttpStatus.OK);
	}

	@RequestMapping(value = "/convert", method = RequestMethod.GET)
	public ResponseEntity<Double> convertByDateAndCurrency(
			@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
			@RequestParam("fromCurrency") String fromCurrency,
			@RequestParam("toCurrency") String toCurrency,
			@RequestParam("amount") Double amount) {
		LOGGER.debug("Convert by date: {}, from {}, to {}, amount:{}", date, fromCurrency, toCurrency, amount);

		Double value = persisntence.convert(date, fromCurrency, toCurrency, amount);
		
		if (value == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Double>(value, HttpStatus.OK);
	}

}
