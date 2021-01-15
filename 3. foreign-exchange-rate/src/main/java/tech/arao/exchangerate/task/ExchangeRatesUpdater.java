package tech.arao.exchangerate.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tech.arao.exchangerate.data.domain.ExchangeRate;
import tech.arao.exchangerate.service.ECBExchangeRateLookupService;
import tech.arao.exchangerate.service.ExchangeRatePersistenceService;

import java.util.Collection;

@Component
public class ExchangeRatesUpdater {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ECBExchangeRateLookupService lookupService;
	@Autowired
	private ExchangeRatePersistenceService exchangeRatePersistenceService;


	@Scheduled(fixedRateString = "${update.task.rate}")
	public void updateRates() {
		LOGGER.info("Initiating exchange rates update");

		try {
			Collection<ExchangeRate> rates = lookupService.updateRates();

			exchangeRatePersistenceService.updateRates(rates);

			LOGGER.info("Successful updating of exchange rates");
		} catch (Exception e) {
			LOGGER.error("Unsuccessful updating of exchange rates", e);
		}
	}

}
