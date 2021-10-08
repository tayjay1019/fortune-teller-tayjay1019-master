package edu.wctc.distjavazodiac.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.wctc.distjavazodiac.entity.Month;
import edu.wctc.distjavazodiac.repo.MonthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BasicMonthListService implements MonthListService {
    private MonthRepository monthRepository;

    @Autowired
    public BasicMonthListService(MonthRepository mr){this.monthRepository = mr; }

    @Override
    public Month getMonths() {
        Optional<Month> m = monthRepository.findById(monthId);
        if (m.isPresent()) {
            return m.get();
        }

        return null;
    }

    @Override
    public List<Month> getMonthList() {
        List<Month> list = new ArrayList<>();
        monthRepository.findAll().forEach(list::add);
        return list;
    }

    /*@PostConstruct
    public void initMonths() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Month[] monthArray = mapper.readValue(Paths.get("months.json").toFile(), Month[].class);
            monthList = Arrays.asList(monthArray);
        } catch (IOException e) {
            e.printStackTrace();
            monthList = new ArrayList<>(0);
        }
    }*/
}
