package com.example.exportExcel.DTO;

import java.io.IOException;
import java.util.List;

import com.example.exportExcel.XlsxHelper.AbstractExportXlsx;

public class ExampleDTO extends AbstractExportXlsx {

    private Long id;
    private String name;
    private String country;

    public ExampleDTO() throws IOException {
        super(null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public List<String> generateXlsxTitles() {
        return List.of(
            "Id",
            this.properties.getProperty("name"),
            this.properties.getProperty("country")
        );
    }

    @Override
    public List<String> generateXlsxValues() {
        return List.of(
            this.id.toString(), 
            this.name, 
            this.country
        );
    }

}
