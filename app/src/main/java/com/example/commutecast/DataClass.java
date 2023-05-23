package com.example.commutecast;

public class DataClass {

    private String dataTitle;
    private String dataDesc;
    private String dataLang;
    private String dataImage;


    public DataClass(String dataTitle, String dataDesc, String dataLang, String dataImage) {
        this.dataTitle = dataTitle;
        this.dataDesc = dataDesc;
        this.dataLang = dataLang;
        this.dataImage = dataImage;
    }

    public String getDataImage() {
        return dataImage;
    }

    public String getDataLang() {
        return dataLang;
    }

    public String getDataDesc() {
        return dataDesc;
    }

    public String getDataTitle() {
        return dataTitle;
    }
}

