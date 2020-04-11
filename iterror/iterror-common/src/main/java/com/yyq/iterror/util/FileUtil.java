package com.yyq.iterror.util;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
public class FileUtil {

    public static String[] CONTACT_ALLOW_TYPES = { "gif", "jpg", "jpeg", "bmp", "png", "x-png", "x-bmp", "x-ms-bmp" };

    public static String[] SOUND_FILE_TYPES    = { "amr", "mp3", "wma", "wmv", "midi", "mp4", "mod", "cda", "fla", "flac", "mid" };

    /**
     * 文件名
     * 
     * @return
     */
    public static String getUniqueId() {
        String uuidram = "" + UUID.randomUUID().getLeastSignificantBits();
        return uuidram.replace("-", "");
    }

    public static String createImgFileNewName(String extname, int width, int height) {
        return FileUtil.getUniqueId() + "_" + width + "x" + height + "." + extname;
    }

    public static String createFileNewName(String extname) {
        return FileUtil.getUniqueId() + "." + extname;
    }

    public static String createFileNewName(String extname, long time) {
        if (time <= 0) {
            return createFileNewName(extname);
        }
        return FileUtil.getUniqueId() + "_" + time + "." + extname;
    }


    public static String getImg350(String url){
        return url+"?x-oss-process=i350";
    }

    public static String getImg500(String url){
        return url+"?x-oss-process=i500";
    }


    /**
     * 根据byte数组，生成文件
     */
    public static boolean saveFile(byte[] bfile, String filePath) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if(!dir.exists()&&dir.isDirectory()){//判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(filePath);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return true;
    }

    /**
     * 总数
     * @param in
     * @return
     * @throws IOException
     */
    public static String inputStream2String(InputStream in) throws IOException {
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1;) {
            out.append(new String(b, 0, n));
        }
        return out.toString();
    }

    /**
     * 验证图片格式
     * 
     * @param fileName
     * @return
     */
    public static boolean checkImgFileName(String fileName) {
        boolean checkResult = false;
        String fileType = getFileExtName(fileName);
        for (String allowType : CONTACT_ALLOW_TYPES) {
            if (allowType.equalsIgnoreCase(fileType)) {
                checkResult = true;
                break;
            }
        }
        return checkResult;
    }

    /**
     * 验证声音文件
     * 
     * @param fileName
     * @return
     */
    public static boolean checkSoundFileName(String fileName) {
        boolean checkResult = false;
        String fileType = getFileExtName(fileName);
        for (String allowType : SOUND_FILE_TYPES) {
            if (allowType.equals(fileType)) {
                checkResult = true;
                break;
            }
        }
        return checkResult;
    }

    /**
     * 得到amr的时长
     * 
     * @param file
     * @return
     * @throws IOException
     */
    public static long getAmrDuration(File file) throws IOException {
        long duration = -1;
        int[] packedSize = { 12, 13, 15, 17, 19, 20, 26, 31, 5, 0, 0, 0, 0, 0, 0, 0 };
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile(file, "rw");
            long length = file.length();// 文件的长度
            int pos = 6;// 设置初始位置
            int frameCount = 0;// 初始帧数
            int packedPos = -1;
            // ///////////////////////////////////////////////////
            byte[] datas = new byte[1];// 初始数据值
            while (pos <= length) {
                randomAccessFile.seek(pos);
                if (randomAccessFile.read(datas, 0, 1) != 1) {
                    duration = length > 0 ? ((length - 6) / 650) : 0;
                    break;
                }
                packedPos = (datas[0] >> 3) & 0x0F;
                pos += packedSize[packedPos] + 1;
                frameCount++;
            }
            // ///////////////////////////////////////////////////
            duration += frameCount * 20;// 帧数*20
        } finally {
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
        }
        return duration;
    }


    /**
     * 读取音频文件长度
     * @param file
     * @return
     */
    public static long getAudioTime(File file){
        long time = 0;
        try {
            Encoder encoder = new Encoder();
            MultimediaInfo m = encoder.getInfo(file);
            time = m.getDuration();
        } catch (Exception e) {
            log.error("get audio time error",e);
        }
        return time;
    }

    public static String getMd5ByFile(File file) throws FileNotFoundException {
        String value = null;
        FileInputStream in = new FileInputStream(file);
        try {
            MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            BigInteger bi = new BigInteger(1, md5.digest());
            value = bi.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }

    public static String getMd5ByFile(FileInputStream inputStream) {
        String value = null;
        try {
            MappedByteBuffer byteBuffer = inputStream.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, inputStream.available());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            BigInteger bi = new BigInteger(1, md5.digest());
            value = bi.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public static String getFileExtName(String fileName) {
        String ext = "jpg";
        if (StringUtils.isBlank(fileName)) {
            return ext;
        }
        int i = fileName.lastIndexOf(".");
        if (i == -1) {
            return ext;
        }
        // --扩展名
        ext = fileName.substring(i + 1);
        return ext;
    }

    /**
     * @param dirPrefix
     * @return
     */
    public static String getBaseDir(String dirPrefix) {
        StringBuilder sb = new StringBuilder();
        sb.append("/");
        sb.append(dirPrefix).append("/");
        LocalDateTime now = LocalDateTime.now();
        sb.append(now.getYear()).append("/");
        sb.append(now.getMonthValue()).append("/");
        sb.append(now.getDayOfMonth()).append("/");
        return sb.toString();
    }

    /**
     * 生成 path
     *
     * @return
     */
    public static String generatePath(String baseDir, String fileName) {
        return getBaseDir(baseDir) + fileName;
    }

    public static String generateDefaultPath(String fileName) {
        return getBaseDir("store") + fileName;
    }

    /**
     *
     * @param fileNewName
     * @return
     */
    public static String createLocalFilePath(String fileNewName) {
        File createFile6 = new File("/tmp/");
        if (!createFile6.exists()) {
            createFile6.mkdirs();
        }
        return "/tmp/" + fileNewName;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file  = new File("d:/Hilight+Tribe-Free+Tibet-单曲-收录在专辑《Plastik+Galaxy+Rebels+Album+(1).mp3");
        String md5 = FileUtil.getMd5ByFile(file);
        System.out.println(md5);
    }
}
