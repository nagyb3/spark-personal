import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import static org.apache.spark.sql.functions.*;

public class AverageTransactionByPerson {

    public static void main(String[] args) {
        SparkSession spark = SparkSession
                .builder()
                .appName("AverageTransactionByPerson")
                .master("local[*]")
                .getOrCreate();

        Dataset<Row> df = spark.read()
                .option("header", "true")
                .option("inferSchema", "true")
                .csv("transactions.csv");

        Dataset<Row> avgByPerson = df.groupBy("user_id")
                .agg(avg("amount").as("avg_amount"));

        avgByPerson.show();

        spark.stop();
    }
}
