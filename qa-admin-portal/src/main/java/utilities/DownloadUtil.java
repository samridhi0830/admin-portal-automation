package utilities;

import java.io.File;
import java.time.Duration;
import java.util.Arrays;
import java.util.Comparator;

public class DownloadUtil {

    public static File waitForLatestCSV(
            String downloadPath, int timeoutSeconds) {

        File folder = new File(downloadPath);

        long endTime = System.currentTimeMillis()
                + Duration.ofSeconds(timeoutSeconds).toMillis();

        while (System.currentTimeMillis() < endTime) {

            File[] files = folder.listFiles();

            if (files != null) {

                boolean downloading = Arrays.stream(files)
                        .anyMatch(f -> f.getName()
                                .endsWith(".crdownload"));

                File latestCSV = Arrays.stream(files)
                        .filter(File::isFile)
                        .filter(f -> f.getName()
                                .startsWith("sale_"))
                        .filter(f -> f.getName()
                                .endsWith(".csv"))
                        .max(Comparator.comparingLong(
                                File::lastModified))
                        .orElse(null);

                if (!downloading && latestCSV != null) {
                    return latestCSV;
                }
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(
                        "Interrupted while waiting for CSV download", e);
            }
        }

        throw new RuntimeException(
                "Sale CSV file was not downloaded within "
                        + timeoutSeconds + " seconds");
    }
}
