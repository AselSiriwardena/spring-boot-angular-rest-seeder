package com.demo.springbootmysqlrestdemo.util;

import com.demo.springbootmysqlrestdemo.models.User;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.util.List;

public class WriteCsvToResponse {

    private static final Logger LOGGER = LoggerFactory.getLogger(WriteCsvToResponse.class);

    public static void writeUsers(PrintWriter writer, List<User> cities) {

        try {

            ColumnPositionMappingStrategy<User> mapStrategy
                    = new ColumnPositionMappingStrategy<>();

            mapStrategy.setType(User.class);

            String[] columns = new String[]{"id", "name", "password"};
            mapStrategy.setColumnMapping(columns);

            StatefulBeanToCsv<User> btcsv = new StatefulBeanToCsvBuilder<User>(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withMappingStrategy(mapStrategy)
                    .withSeparator(',')
                    .build();

            btcsv.write(cities);

        } catch (CsvException ex) {

            LOGGER.error("Error mapping Bean to CSV", ex);
        }
    }

    public static void writeUser(PrintWriter writer, User city) {

        try {

            ColumnPositionMappingStrategy<User> mapStrategy
                    = new ColumnPositionMappingStrategy<>();

            mapStrategy.setType(User.class);

            String[] columns = new String[]{"id", "name", "password"};
            mapStrategy.setColumnMapping(columns);

            StatefulBeanToCsv<User> btcsv = new StatefulBeanToCsvBuilder<User>(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withMappingStrategy(mapStrategy)
                    .withSeparator(',')
                    .build();

            btcsv.write(city);

        } catch (CsvException ex) {

            LOGGER.error("Error mapping Bean to CSV", ex);
        }
    }
}