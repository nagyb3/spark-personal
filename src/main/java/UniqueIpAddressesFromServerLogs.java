import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import static org.apache.spark.sql.functions.*;

public class UniqueIpAddressesFromServerLogs {

    public static void main(String[] args) {
        SparkSession spark = SparkSession
                .builder()
                .appName("UniqueIpAddressesFromServerLogs")
                .master("local[*]")
                .getOrCreate();

        Dataset<Row> logs = spark.read().text("server_logs.txt");

        String ipRegexPattern = "(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})";

        Dataset<Row> uniqueIpAddresses = logs.select(
                regexp_extract(col("value"), ipRegexPattern, 1).as("unique_ip_address")
        ).distinct();

        uniqueIpAddresses.show();

        spark.stop();
    }
}
