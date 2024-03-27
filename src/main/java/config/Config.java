package config;

import lombok.Getter;
import lombok.Setter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

@Setter
@Getter
public class Config {

    private String url;

    private String resumeFrom;

    private BufferedWriter output;

    public Config(String[] args) throws IOException {
        parseParams(args);
        setDefaults();
    }

    private void parseParams(String[] args) throws IOException {
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-u", "--url" -> setUrl(args[i + 1]);
                case "-rf", "--resume-from" -> setResumeFrom(args[i + 1]);
                case "-o", "--output" -> setOutput(args[i+1]);
            }
        }
    }

    private void setDefaults() throws IOException {
        if (url == null) {
            Calendar calendar = Calendar.getInstance();
            url = "https://aiesec.org/search?earliest_start_date=%d-%d-%d&programmes=8".formatted(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH) + 1,
                    calendar.get(Calendar.DAY_OF_MONTH)
            );
        }

        if (output == null) {
            setOutput("output.csv");
        }
    }

    private void setOutput(String filename) throws IOException {
        output = new BufferedWriter(new FileWriter(filename));
    }
}
