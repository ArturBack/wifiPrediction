package data.model;

import com.opencsv.bean.CsvBindByPosition;

import static data.model.ConvertedDataItem.*;

public class PredictedDataItem {

    public static final int PREDICTION_POSITION = 6;

    @CsvBindByPosition(position = DAY_POSITION)
    private Integer day;

    @CsvBindByPosition(position = HOUR_POSITION)
    private Integer hour;

    @CsvBindByPosition(position = MINUTE_POSITION)
    private Integer minute;

    @CsvBindByPosition(position = IS_SCHOOL_POSITION)
    private Integer isSchool;

    @CsvBindByPosition(position = CHANNEL_UTILIZATION_POSITION)
    private Integer channelUtilization;

    @CsvBindByPosition(position = CLIENTS_NUMBER_POSITION)
    private Integer clientsNumber;

    @CsvBindByPosition(position = PREDICTION_POSITION)
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
