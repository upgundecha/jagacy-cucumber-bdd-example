package com.example.session;

import com.example.Fields.EntryField;
import com.example.Fields.LabelField;
import com.jagacy.Session3270;
import com.jagacy.util.JagacyException;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;

/**
 * Created by upgundecha on 15/10/16.
 */
public class Session extends Session3270 {

    private final HashMap<RenderingHints.Key, Object> renderingProperties
            = new HashMap<>();


    public Session(final String session)
            throws JagacyException {

        super(session);
   }

    public final boolean waitForTextLabel(final LabelField textLabel)
            throws JagacyException {

        return waitForPosition(textLabel.getRow(),
                textLabel.getColumn(), textLabel.getText(), 10000);
    }

    public final void setEntryFieldValue(final EntryField entryField,
                                         final String value)
            throws JagacyException {

        writePosition(entryField.getRow(),
                entryField.getColumn(), value);
    }

    public final byte[] getScreenshot() throws Exception {

        String screenText = StringUtils.join(readScreen(), "\n");

        renderingProperties.put(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        renderingProperties.put(RenderingHints.KEY_STROKE_CONTROL,
                RenderingHints.VALUE_STROKE_PURE);
        renderingProperties.put(RenderingHints.KEY_FRACTIONALMETRICS,
                RenderingHints.VALUE_FRACTIONALMETRICS_ON);

        Font font = new Font("Consolas", Font.PLAIN, 12);
        FontRenderContext fontRenderContext =
                new FontRenderContext(null, true, true);
        BufferedImage bufferedImage =
                new BufferedImage(600, 300, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setRenderingHints(renderingProperties);
        graphics2D.setBackground(Color.black);
        graphics2D.setColor(Color.GREEN);
        graphics2D.setFont(font);
        graphics2D.clearRect(0, 0, bufferedImage.getWidth(),
                bufferedImage.getHeight());

        TextLayout textLayout =
                new TextLayout(screenText, font, fontRenderContext);

        int count = 0;
        for (String line : screenText.split("\n")) {
            graphics2D.drawString(line, 15,
                    (int) (15 + count * textLayout.getBounds()
                            .getHeight() + 0.5));
            count++;
        }
        graphics2D.dispose();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "PNG", out);
        return out.toByteArray();
    }
}
