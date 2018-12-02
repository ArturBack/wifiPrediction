package data.metadata;

import java.util.Collections;
import java.util.List;

public class MetaData2015Week3 implements MetaDataInfo {
    @Override
    public String getDataDirectorName() {
        return "2015-03-(16-22)";
    }

    @Override
    public List<String> getNoSchoolDates() {
        return Collections.emptyList();
    }
}
