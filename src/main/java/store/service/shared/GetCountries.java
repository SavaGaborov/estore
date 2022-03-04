package store.service.shared;

import com.google.common.collect.Sets;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static store.util.ConstantsUtil.COUNTRIES;

@Service
@RequiredArgsConstructor
public class GetCountries {

    public Set<String> execute() {
        final String countriesArray[] = COUNTRIES.split(",");
        final List<String> countryList = new ArrayList<>(Arrays.asList(countriesArray));
        final Set<String> allCountries = Sets.newHashSet(countryList);
        return allCountries;
    }
}
