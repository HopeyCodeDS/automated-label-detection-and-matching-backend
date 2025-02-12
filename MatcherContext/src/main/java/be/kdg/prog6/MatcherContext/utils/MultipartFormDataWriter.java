package be.kdg.prog6.MatcherContext.utils;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;

@Component
public class MultipartFormDataWriter {
    private static final String BOUNDARY = "----WebKitFormBoundary7MA4YWxkTrZu0gW";
    private static final String LINE_END = "\r\n";
    private static final String TWO_HYPHENS = "--";

    public void writeMultipartFormData(OutputStream os, byte[] imageBytes) throws IOException {
        os.write((TWO_HYPHENS + BOUNDARY + LINE_END).getBytes());
        os.write(("Content-Disposition: form-data; name=\"file\"; filename=\"image.jpg\"" + LINE_END).getBytes());
        os.write(("Content-Type: image/jpeg" + LINE_END).getBytes());
        os.write(LINE_END.getBytes());
        os.write(imageBytes);
        os.write(LINE_END.getBytes());
        os.write((TWO_HYPHENS + BOUNDARY + TWO_HYPHENS + LINE_END).getBytes());
    }
}