package Utils;

import java.awt.*;

public class GraphicsExtensions {
    public static void drawCenteredString(Graphics g, String text, Rectangle rect, Font font, Color color) {
        FontMetrics metrics = g.getFontMetrics(font);

        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();

        g.setFont(font);
        g.setColor(color);
        g.drawString(text, x, y);
    }
}
