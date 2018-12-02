package data.metadata;

import java.util.Arrays;
import java.util.List;

public class MetaData2015 implements MetaDataInfo {


    @Override
    public String getDataDirectorName() {
        return "2015-03";
    }

    @Override
    public List<String> getNoSchoolDates() {
        return Arrays.asList("2015-03-14", "2015-03-15", "2015-03-28", "2015-03-29");
    }
}
