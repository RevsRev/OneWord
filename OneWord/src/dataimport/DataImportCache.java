package dataimport;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class DataImportCache
{
    //map of uid to column name & data
    private final HashMap<String, HashMap<String, String>> hmUidToColumns = new HashMap<String, HashMap<String, String>>();

    private final String importFilePath;
    private final DataImportColumn uidColumn;
    private final ArrayList<DataImportColumn> matchingColumns;

    public DataImportCache(String importFilePath, DataImportColumn uidColumm, ArrayList<DataImportColumn> matchingColumns) throws IOException
    {
        this.importFilePath = importFilePath;
        this.uidColumn = uidColumm;
        this.matchingColumns = matchingColumns;

        initCache();
    }

    private void initCache() throws IOException
    {
        File file = new File(getImportFilePath());
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String cols = reader.readLine();
        String data = null;
        while ((data = reader.readLine() )!= null)
        {
            String[] datas = data.split(",");
            cacheDatas(datas);
        }

        reader.close();
    }

    private void cacheDatas(String[] datas)
    {
        String uidColName = getUidColumn().getColName();
        HashMap<String, String> hmCachedData = new HashMap<String, String>();
        for (int i=0; i<getMatchingColumns().size(); i++)
        {
            DataImportColumn col = getMatchingColumns().get(i);
            hmCachedData.put(col.getColName(), datas[col.getColIndex()-1]);
        }
        hmUidToColumns.put(uidColName, hmCachedData);
    }

    public Set<String> getUids()
    {
        return hmUidToColumns.keySet();
    }

    public HashMap<String, String> getDatasForUid(String uid)
    {
        return hmUidToColumns.get(uid);
    }

    public String getImportFilePath() { return importFilePath;}
    public DataImportColumn getUidColumn() {return uidColumn;}
    public ArrayList<DataImportColumn> getMatchingColumns() {return matchingColumns;}
}
