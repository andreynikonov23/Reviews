package nick.pack.service;

import nick.pack.model.Country;
import nick.pack.repository.CountryRepository;
import nick.pack.service.dao.DAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService implements DAO<Country, Integer> {
    private final CountryRepository repository;

    @Autowired
    public CountryService(CountryRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<Country> findByAll() {
        return repository.findAll();
    }

    @Override
    public Country findById(Integer integer) {
        return repository.findCountryById(integer);
    }

    @Override
    public void saveAndFlush(Country country) {
        repository.saveAndFlush(country);
    }

    @Override
    public void delete(Country country) {
        repository.delete(country);
    }
}
