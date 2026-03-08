import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import static org.apache.spark.sql.functions.*;

/**
 * This class reads a CSV file of transactions and identifies VIP customers.
 * (Those who have spent more than $500 in total).
 */
public class VipFilter {
    public static void main(String[] args) {
        SparkSession spark = SparkSession
                .builder()
                .appName("VipFilter")
                .master("local[*]")
                .getOrCreate();

        Dataset<Row> df = spark.read()
                .option("header", "true")
                .option("inferSchema", "true")
                .csv("transactions.csv");

        Dataset<Row> vips = df.groupBy("user_id")
                .agg(sum("amount").as("total_spent"))
                .filter("total_spent > 500");

        vips.show();

        spark.stop();
    }
}
