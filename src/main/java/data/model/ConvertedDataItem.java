package data.model;

import com.opencsv.bean.CsvBindByPosition;

public class ConvertedDataItem {

    public static final int DAY_POSITION = 0;
    public static final int HOUR_POSITION = 1;
    public static final int MINUTE_POSITION = 2;
    public static final int IS_SCHOOL_POSITION = 3;
    public static final int CHANNEL_UTILIZATION_POSITION = 4;
    public static final int CLIENTS_NUMBER_POSITION = 5;

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

    public ConvertedDataItem() {
    }

    public ConvertedDataItem(Integer day, Integer hour, Integer minute, Integer isSchool, Integer channelUtilization, Integer clientsNumber) {
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.isSchool = isSchool;
        this.channelUtilization = channelUtilization;
        this.clientsNumber = clientsNumber;
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
}
