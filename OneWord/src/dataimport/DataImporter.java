package dataimport;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public final class DataImporter
{
    //initialisation
    private final ArrayList<DataImportCache> importCaches;
    private final String exportFilePath;

    //working
    private HashMap<String, HashMap<String, String>> cachedResults = null;
    private HashSet<String> colNames = null;

    public DataImporter(String exportFilePath, ArrayList<DataImportCache> importCaches)
    {
        this.exportFilePath = exportFilePath;
        this.importCaches = importCaches;
    }

    public void doImport() throws IOException
    {
        cacheFormattedData();
        writeToCsv();
    }

    private void cacheFormattedData()
    {
        HashSet<String> uids = new HashSet<String>();
        for (int i=0; i<importCaches.size(); i++)
        {
            uids.addAll(importCaches.get(i).getUids());
        }

        cachedResults =  new HashMap<String, HashMap<String,String>>();
        colNames = new HashSet<String>();

        Iterator<String> it = uids.iterator();
        while (it.hasNext())
        {
            String uid = it.next();
            HashMap<String, String> datasForUid = new HashMap<String, String>();
            for (int i=0; i<importCaches.size(); i++)
            {
                DataImportCache cache = importCaches.get(i);
                HashMap<String, String> datasForCache = cache.getDatasForUid(uid);
                datasForUid.putAll(datasForCache);

                Set<String> cacheColNames = datasForCache.keySet();
                colNames.addAll(cacheColNames);
            }
            cachedResults.put(uid, datasForUid);
        }
    }

    private void writeToCsv() throws IOException
    {
        List<String> colNamesOrdered = Arrays.stream((String[])this.colNames.toArray()).toList();

        File file = new File(exportFilePath);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        Iterator<String> it = cachedResults.keySet().iterator();
        while (it.hasNext())
        {
            String uid = it.next();
            String line = uid;
            for (int i=0; i<colNamesOrdered.size(); i++)
            {
                line += "," + cachedResults.get(uid).get(colNamesOrdered.get(i));
            }
            writer.write(line);
            writer.newLine();
        }
        writer.close();
    }
}
