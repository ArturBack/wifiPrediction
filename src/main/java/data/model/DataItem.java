package data.model;

import com.opencsv.bean.CsvBindByPosition;

public class DataItem {

    @CsvBindByPosition(position = 0)
    private String date;

    @CsvBindByPosition(position = 1)
    private String apName;

    @CsvBindByPosition(position = 2)
    private String clientsNumber;

    @CsvBindByPosition(position = 19)
    private String channelUtilization;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getApName() {
        return apName;
    }

    public void setApName(String apName) {
        this.apName = apName;
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
