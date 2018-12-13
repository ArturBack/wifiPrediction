package data.model;

import com.opencsv.bean.CsvBindByPosition;

public class DataItem {

    public static final int DATE_POSITION = 0;
    public static final int AP_NAME_POSITION = 1;
    public static final int CLIENTS_NUMBER_POSITION = 2;
    public static final int CHANNEL_UTILIZATION_POSITION = 19;

    @CsvBindByPosition(position = DATE_POSITION)
    private String date;

    @CsvBindByPosition(position = AP_NAME_POSITION)
    private String apName;

    @CsvBindByPosition(position = CLIENTS_NUMBER_POSITION)
    private int clientsNumber;

    @CsvBindByPosition(position = CHANNEL_UTILIZATION_POSITION)
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
