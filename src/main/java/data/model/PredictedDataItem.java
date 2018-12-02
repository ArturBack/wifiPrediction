package data.model;

import com.opencsv.bean.CsvBindByPosition;

public class PredictedDataItem {

    @CsvBindByPosition(position = 0)
    private Integer day;

    @CsvBindByPosition(position = 1)
    private Integer hour;

    @CsvBindByPosition(position = 2)
    private Integer minute;

    @CsvBindByPosition(position = 3)
    private Integer isSchool;

    @CsvBindByPosition(position = 4)
    private Integer channelUtilization;

    @CsvBindByPosition(position = 5)
    private Integer clientsNumber;

    @CsvBindByPosition(position = 6)
    private Integer prediction;

    public PredictedDataItem(Integer day, Integer hour, Integer minute, Integer isSchool, Integer channelUtilization, Integer clientsNumber, Integer prediction) {
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.isSchool = isSchool;
        this.channelUtilization = channelUtilization;
        this.clientsNumber = clientsNumber;
        this.prediction = prediction;
    }

    public PredictedDataItem() {
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public Integer getIsSchool() {
        return isSchool;
    }

    public void setIsSchool(Integer isSchool) {
        this.isSchool = isSchool;
    }

    public Integer getChannelUtilization() {
        return channelUtilization;
    }

    public void setChannelUtilization(Integer channelUtilization) {
        this.channelUtilization = channelUtilization;
    }

    public Integer getClientsNumber() {
        return clientsNumber;
    }

    public void setClientsNumber(Integer clientsNumber) {
        this.clientsNumber = clientsNumber;
    }

    public Integer getPrediction() {
        return prediction;
    }

    public void setPrediction(Integer prediction) {
        this.prediction = prediction;
    }
}
