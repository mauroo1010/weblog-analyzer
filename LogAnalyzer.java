/**
 * Read web server data and analyse
 * hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version 2011.07.31
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer()
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader();
    }

    public LogAnalyzer(String fileName)
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader(fileName);
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    public int numberOfAccesses() {
        int i = 0;
        int numberOfAccesses = 0;
        while (i < hourCounts.length) {
            numberOfAccesses += hourCounts[i]; 
            i++;
        }
        return numberOfAccesses;
    }

    public int busiestHour() {
        int i = 0;
        int horaMasOcupada = 0;
        int logMax = 0;
        while (i < hourCounts.length) {
            if (logMax < hourCounts[i]) { 
                logMax = hourCounts[i];
                horaMasOcupada = i;
            }
            i++;
        }
        return horaMasOcupada;
        }

    public int quietesHour(){
        int i = 0;
        int horaMenosOcupada = 0;
        int logMin = hourCounts[0];
        while (i < hourCounts.length) {
            if (logMin > hourCounts[i]) {
                logMin = hourCounts[i];
                horaMenosOcupada = i;
            }
            i++;
        }
        return horaMenosOcupada;
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        int hour = 0;
        while (hour < hourCounts.length) {
            System.out.println(hour + ": " + hourCounts[hour]);
            hour++;
        }
    }

    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
}
