package edu.wctc.distjavazodiac.service;

import edu.wctc.distjavazodiac.entity.Month;

import java.util.List;

public interface MonthListService {
    Month getMonths(int monthId);
    List<Month> getMonthsList();
}
