import org.apache.spark.sql.SparkSession;

/**
 * This class starts a Spark application that does nothing but keeps the Spark Web UI alive for an hour.
 * This can be useful for testing/viewing the Web UI without running any actual Spark jobs.
 */
public class EmptyWebUI {
    public static void main(String[] args) {
        SparkSession spark = SparkSession
                .builder()
                .appName("EmptyWebUI")
                .master("local[*]")
                .getOrCreate();

        try {
            Thread.sleep(3600000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        spark.stop();
    }
}
