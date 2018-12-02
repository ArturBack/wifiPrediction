package data.metadata;

import java.util.Collections;
import java.util.List;

public class MetaData2015Week1 implements MetaDataInfo {
    @Override
    public String getDataDirectorName() {
        return "2015-03-(2-8)";
    }

    @Override
    public List<String> getNoSchoolDates() {
        return Collections.emptyList();
    }
}
