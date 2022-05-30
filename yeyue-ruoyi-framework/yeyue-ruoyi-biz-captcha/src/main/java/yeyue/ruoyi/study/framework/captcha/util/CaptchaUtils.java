package yeyue.ruoyi.study.framework.captcha.util;

import yeyue.ruoyi.study.framework.common.exception.ServiceException;
import yeyue.ruoyi.study.framework.common.exception.common.GlobalErrorCode;

import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author yeyue
 * @date 2022-05-30 01:38:32
 */
public abstract class CaptchaUtils {

    private static final Color DEFAULT_BACKGROUND = Color.WHITE;

    private static final int RGB_COLOR_BOUND = 256;

    private static final String IMAGE_TYPE_PNG = "png";

    public static String getCaptcha(int width, int height, String code, int interfereCount) {
        return Base64.getEncoder().encodeToString(write(make(width, height, code, interfereCount)));
    }

    private static byte[] write(BufferedImage image) {
        ImageWriter writer = ImageIO.getImageWriters(ImageTypeSpecifier.createFromRenderedImage(image), IMAGE_TYPE_PNG).next();
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             ImageOutputStream output = ImageIO.createImageOutputStream(out)) {
            writer.setOutput(output);
            writer.write(image);
            output.flush();
            return out.toByteArray();
        } catch (IOException e) {
            throw new ServiceException(GlobalErrorCode.IO_EXCEPTION, e);
        } finally {
            if (writer != null) {
                writer.dispose();
            }
        }
    }

    // 拷贝 From hutool-captcha

    private static BufferedImage make(int width, int height, String code, int interfereCount) {

        ThreadLocalRandom random = ThreadLocalRandom.current();

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();

        // 填充背景
        g.setColor(DEFAULT_BACKGROUND);
        g.fillRect(0, 0, image.getWidth(), image.getHeight());

        // 设置随机干扰圈圈
        for (int i = 0; i < interfereCount; i++) {
            g.setColor(randomColor(random));
            g.drawOval(random.nextInt(width), random.nextInt(height), random.nextInt(height >> 1), random.nextInt(height >> 1));
        }
        // 设置字符串

        // 抗锯齿
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // 字体高度设为验证码高度-2，留边距
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, (int) (height * 0.75)));
        // 文字高度（必须在设置字体后调用）
        int midY = getCenterY(g, height);
        int len = code.length();
        int charWidth = width / len;
        for (int i = 0; i < len; i++) {
            // 产生随机的颜色值，让输出的每个字符的颜色值都将不同。
            g.setColor(randomColor(random));
            g.drawString(String.valueOf(code.charAt(i)), i * charWidth, midY);
        }
        return image;
    }

    /**
     * 获取随机颜色
     *
     * @param random 随机因子
     * @return 结果
     */
    private static Color randomColor(ThreadLocalRandom random) {
        if (null == random) {
            random = ThreadLocalRandom.current();
        }
        return new Color(random.nextInt(RGB_COLOR_BOUND), random.nextInt(RGB_COLOR_BOUND), random.nextInt(RGB_COLOR_BOUND));
    }

    /**
     * 获取文字居中高度的Y坐标（距离上边距距离）<br>
     * 此方法依赖FontMetrics，如果获取失败，默认为背景高度的1/3
     *
     * @param g                {@link Graphics2D}画笔
     * @param backgroundHeight 背景高度
     * @return 最小高度，-1表示无法获取
     * @since 4.5.17
     */
    private static int getCenterY(Graphics g, int backgroundHeight) {
        // 获取允许文字最小高度
        FontMetrics metrics = null;
        try {
            metrics = g.getFontMetrics();
        } catch (Exception e) {
            // 此处报告bug某些情况下会抛出IndexOutOfBoundsException，在此做容错处理
        }
        int y;
        if (null != metrics) {
            y = (backgroundHeight - metrics.getHeight()) / 2 + metrics.getAscent();
        } else {
            y = backgroundHeight / 3;
        }
        return y;
    }
}
