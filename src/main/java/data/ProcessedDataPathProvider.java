package data;

import data.metadata.MetaDataInfo;

public class ProcessedDataPathProvider {

    private static String DATA_DIR_PROCESSED = "_PROCESSED/";

    public static String getProcessedDataDirectoryPath(MetaDataInfo metaDataInfo) {
        return metaDataInfo.getDataDirectorName().concat(DATA_DIR_PROCESSED);
    }
}
