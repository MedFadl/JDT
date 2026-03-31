package com.medz;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CustomTray {
    private static final int ICON_SIZE = 16;
    private static final String FONT_NAME = "Arial Narrow";

    private final TrayIcon trayIcon;
    private final BufferedImage image;
    private final int maxValue;

    public CustomTray(int initialValue, int maxValue, String tooltip) throws AWTException {
        if (!SystemTray.isSupported()) {
            throw new UnsupportedOperationException("SystemTray is not supported on this platform.");
        }

        this.maxValue = maxValue;
        this.image = new BufferedImage(ICON_SIZE, ICON_SIZE, BufferedImage.TYPE_INT_ARGB);
        this.trayIcon = new TrayIcon(image, tooltip);
        this.trayIcon.setImageAutoSize(true);

        SystemTray.getSystemTray().add(this.trayIcon);
        update(initialValue);
    }

    public void update(int value) {
        Graphics2D g = image.createGraphics();

        try {
            prepareCanvas(g);
            setupRenderingHints(g);

            g.setColor(calculateColor(value, maxValue));
            String text = String.valueOf(value);
            g.setFont(getAdaptiveFont(text));

            FontMetrics fm = g.getFontMetrics();
            int x = (ICON_SIZE - fm.stringWidth(text)) / 2;
            int y = 13;

            g.drawString(text, x, y);
        } finally {
            g.dispose();
        }

        trayIcon.setImage(image);
    }

    private void prepareCanvas(Graphics2D g) {
        g.setComposite(AlphaComposite.Clear);
        g.fillRect(0, 0, ICON_SIZE, ICON_SIZE);
        g.setComposite(AlphaComposite.SrcOver);
    }

    private void setupRenderingHints(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    }

    private Font getAdaptiveFont(String text) {
        int size = (text.length() > 2) ? 10 : 14;
        return new Font(FONT_NAME, Font.BOLD, size);
    }

    private Color calculateColor(int val, int max) {
        float percentage = Math.min(1.0f, Math.max(0.0f, (float) val / max));

        int red = (int) (255 * Math.min(1, 2 * percentage));
        int green = (int) (255 * Math.min(1, 2 * (1 - percentage)));

        return new Color(red, green, 0);
    }

    public void setTooltip(String tooltip) {
        this.trayIcon.setToolTip(tooltip);
    }
}