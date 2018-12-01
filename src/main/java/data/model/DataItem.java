package data.model;

import com.opencsv.bean.CsvBindByPosition;

public class DataItem {

    @CsvBindByPosition(position = 0)
    private String date;

    @CsvBindByPosition(position = 1)
    private String apName;

    @CsvBindByPosition(position = 2)
    private int clientsNumber;

    @CsvBindByPosition(position = 19)
    private int channelUtilization;

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

    public int getClientsNumber() {
        return clientsNumber;
    }

    public void setClientsNumber(int clientsNumber) {
        this.clientsNumber = clientsNumber;
    }

    public int getChannelUtilization() {
        return channelUtilization;
    }

    public void setChannelUtilization(int channelUtilization) {
        this.channelUtilization = channelUtilization;
    }
}
