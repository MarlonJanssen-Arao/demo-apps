package tech.arao.exchangerate.data.repository;

import org.springframework.data.repository.CrudRepository;
import tech.arao.exchangerate.data.domain.ExchangeRate;

import java.util.Date;
import java.util.List;

public interface ExchangeRateRepository extends CrudRepository<ExchangeRate, Long> {

	List<ExchangeRate> findAll();

	void delete(ExchangeRate exchangeRate);


	ExchangeRate getByCurrency(String currency);

	ExchangeRate getByDateAndCurrency(Date date, String currency);

	List<ExchangeRate> findAllByDate(Date date);

	List<ExchangeRate> findAllByCurrency(String currency);

}
