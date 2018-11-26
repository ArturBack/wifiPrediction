package data.model;

public class ConvertedDataItem {

    private Integer day;
    private String time;
    private String clientsNumber;
    private String channelUtilization;

    public ConvertedDataItem(Integer day, String time, String clientsNumber, String channelUtilization) {
        this.day = day;
        this.time = time;this.clientsNumber = clientsNumber;
        this.channelUtilization = channelUtilization;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getClientsNumber() {
        return clientsNumber;
    }

    public void setClientsNumber(String clientsNumber) {
        this.clientsNumber = clientsNumber;
    }

    public String getChannelUtilization() {
        return channelUtilization;
    }

    public void setChannelUtilization(String channelUtilization) {
        this.channelUtilization = channelUtilization;
    }
}
