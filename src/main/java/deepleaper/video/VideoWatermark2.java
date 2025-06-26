//package deepleaper.video;
//
//import org.bytedeco.javacpp.Loader;
//import org.bytedeco.javacpp.avcodec;
//import org.bytedeco.javacpp.avformat;
//import org.bytedeco.javacpp.avutil;
//import org.bytedeco.javacpp.opencv_core;
//import org.bytedeco.javacpp.opencv_imgcodecs;
//import org.bytedeco.javacv.*;
//
//import java.io.File;
//
//public class VideoWatermark2 {
//    public static void main(String[] args) {
//        // 视频文件路径
//        String videoPath = "input.mp4";
//        // 图片水印路径
//        String watermarkPath = "watermark.png";
//        // 输出视频路径
//        String outputPath = "output.mp4";
//
//        // 加载视频文件
//        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(videoPath);
//        try {
//            grabber.start();
//
//            // 获取视频信息
//            int width = grabber.getImageWidth();
//            int height = grabber.getImageHeight();
//            int frameRate = (int) grabber.getFrameRate();
//            int bitRate = grabber.getVideoBitrate();
//            int numFrames = grabber.getLengthInFrames();
//
//            // 创建视频编码器
//            FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(outputPath, width, height);
//            recorder.setFrameRate(frameRate);
//            recorder.setVideoBitrate(bitRate);
//            recorder.setFormat("mp4");
//            recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
//            recorder.setVideoOption("preset", "ultrafast");
//            recorder.setVideoOption("crf", "28");
//            recorder.setVideoOption("tune", "zerolatency");
//            recorder.setVideoOption("movflags", "faststart");
//            recorder.start();
//
//            // 加载水印图片
//            opencv_core.Mat watermark = opencv_imgcodecs.imread(watermarkPath);
//
//            // 添加水印并写入输出视频
//            Frame frame;
//            while ((frame = grabber.grabFrame()) != null) {
//                // 将水印图片缩放到与视频帧相同大小
//                opencv_core.Mat resizedWatermark = new opencv_core.Mat();
//                opencv_core.Size size = new opencv_core.Size(width, height);
//                opencv_core.resize(watermark, resizedWatermark, size);
//
//                // 将水印图片叠加到视频帧上
//                opencv_core.Mat videoFrame = new OpenCVFrameConverter.ToMat().convert(frame);
//                opencv_core.addWeighted(videoFrame, 1.0, resizedWatermark, 0.5, 0.0, videoFrame);
//
//                // 将视频帧写入输出视频
//                Frame outputFrame = new OpenCVFrameConverter.ToMat().convert(videoFrame);
//                recorder.record(outputFrame);
//            }
//
//            // 释放资源
//            recorder.stop();
//            grabber.stop();
//            grabber.release();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}