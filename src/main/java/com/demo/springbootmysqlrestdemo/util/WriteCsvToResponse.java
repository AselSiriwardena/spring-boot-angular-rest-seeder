package com.demo.springbootmysqlrestdemo.util;

import com.demo.springbootmysqlrestdemo.models.Book;
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

    public static void writeCities(PrintWriter writer, List<Book> cities) {

        try {

            ColumnPositionMappingStrategy<Book> mapStrategy
                    = new ColumnPositionMappingStrategy<>();

            mapStrategy.setType(Book.class);

            String[] columns = new String[]{"id", "book_name", "isbn"};
            mapStrategy.setColumnMapping(columns);

            StatefulBeanToCsv<Book> btcsv = new StatefulBeanToCsvBuilder<Book>(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withMappingStrategy(mapStrategy)
                    .withSeparator(',')
                    .build();

            btcsv.write(cities);

        } catch (CsvException ex) {

            LOGGER.error("Error mapping Bean to CSV", ex);
        }
    }

    public static void writeCity(PrintWriter writer, Book city) {

        try {

            ColumnPositionMappingStrategy<Book> mapStrategy
                    = new ColumnPositionMappingStrategy<>();

            mapStrategy.setType(Book.class);

            String[] columns = new String[]{"id", "book_name", "isbn"};
            mapStrategy.setColumnMapping(columns);

            StatefulBeanToCsv<Book> btcsv = new StatefulBeanToCsvBuilder<Book>(writer)
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