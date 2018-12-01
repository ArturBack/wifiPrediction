package data.metadata;

import java.util.Arrays;
import java.util.List;

public class MetaData2016 implements MetaDataInfo {

    @Override
    public String getDataDirectorName() {
        return "2016-03";
    }

    @Override
    public List<String> getNoSchoolDates() {
        return Arrays.asList("2016-03-12", "2016-03-13", "2016-03-26", "2016-03-27");
    }
}
