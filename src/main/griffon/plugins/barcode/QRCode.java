/*
 * Copyright 2010-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the 'License');
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an 'AS IS' BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package griffon.plugins.barcode;

import net.glxn.qrgen.image.ImageType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;

/**
 * @author Andres Almiray
 */
public class QRCode extends JComponent {
    private String msg;

    public void setMessage(String msg) {
        if (msg == null) {
            throw new NullPointerException("Parameter msg must not be null");
        }
        msg = EscapeHandler.parseEscapes(msg);
        if (!msg.equals(this.msg)) {
            this.msg = msg;
            repaint();
        }
    }

    public String getMessage() {
        return this.msg;
    }

    public void paint(Graphics g) {
        if (msg == null) {
            return;
        }
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
                RenderingHints.VALUE_FRACTIONALMETRICS_ON);

        try {
            AffineTransform baktrans = g2d.getTransform();
            try {
                //set up for painting
                g2d.setColor(Color.black);

                Image img = createImage();
                //now paint the barcode
                if (img != null) g2d.drawImage(img, 0, 0, null);
            } finally {
                g2d.setTransform(baktrans);
            }
        } catch (Exception e) {
            g.setColor(Color.red);
            g.drawLine(0, 0, getWidth(), getHeight());
            g.drawLine(0, getHeight(), getWidth(), 0);
        }
    }

    private Image createImage() {
        try {
            return ImageIO.read(net.glxn.qrgen.QRCode.from(msg).to(ImageType.JPG).withSize(getWidth(), getHeight()).file());
        } catch (IOException e) {
            return null;
        }
    }
}
