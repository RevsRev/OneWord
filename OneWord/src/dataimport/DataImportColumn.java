package dataimport;

public class DataImportColumn
{
    private final String colName;
    private final int colIndex;

    public DataImportColumn(String colName, int colIndex)
    {
        this.colName = colName;
        this.colIndex = colIndex;
    }

    public String getColName() { return colName ; }
    public int getColIndex() {return colIndex; }
}
