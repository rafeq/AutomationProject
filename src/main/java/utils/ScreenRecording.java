package utils;

import org.bytedeco.ffmpeg.avcodec.AVPacket;
import org.bytedeco.ffmpeg.avformat.AVFormatContext;
import org.bytedeco.ffmpeg.avutil.AVFrame;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.ffmpeg.global.avformat;
import org.bytedeco.ffmpeg.global.avutil;

import java.io.File;

public class ScreenRecording {
    private AVFormatContext formatContext;
    private boolean isRecording = false;
    private String outputFilePath;

    public ScreenRecording() {
        // Set default output file path
        this.outputFilePath = System.getProperty("user.dir") + "/videos/output.mp4";
    }

    public void startRecording() {
        // Ensure output directory exists
        File videoDir = new File(System.getProperty("user.dir") + "/videos");
        if (!videoDir.exists()) {
            videoDir.mkdirs();
        }

        try {
            // Initialize FFmpeg
            avutil.av_log_set_level(avutil.AV_LOG_INFO);
            avformat.avformat_network_init();

            // Prepare for screen capture
            formatContext = avformat.avformat_alloc_context();

            // Set up input format for screen capture
            AVFormatContext inputContext = new AVFormatContext();
            String inputDevice = "desktop";
            if (avformat.avformat_open_input(inputContext, inputDevice, null, null) < 0) {
                throw new RuntimeException("Could not open input device.");
            }

            // Configure output format
            AVFormatContext outputContext = avformat.avformat_alloc_context();
            if (avformat.avformat_open_input(outputContext, outputFilePath, null, null) < 0) {

                throw new RuntimeException("Could not open output file.");
            }

            isRecording = true;
            System.out.println("Screen recording started.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopRecording() {
        if (!isRecording) {
            System.out.println("No active recording found to stop.");
            return;
        }

        try {
            isRecording = false;
            avformat.av_write_trailer(formatContext);
            avformat.avformat_close_input(formatContext);
            System.out.println("Screen recording stopped.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ScreenRecording screenRecorder = new ScreenRecording();
        screenRecorder.startRecording();

        try {
            Thread.sleep(10000); // Record for 10 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        screenRecorder.stopRecording();
    }
    public File getVideoFile() {
        return new File(outputFilePath);
    }
}
