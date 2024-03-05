package org.icepdf.core.pobjects.graphics.RasterOps;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.RasterOp;
import java.awt.image.WritableRaster;

/**
 * The basic idea is that we do a fuzzy colour conversion from YCCK to
 * CMYK.  The conversion is not perfect but when converted again from
 * CMYK to RGB the result is much better then going directly from YCCK to
 * RGB.
 * NOTE: no masking here, as it is done later in the call to
 * {@see alterRasterCMYK2BGRA}
 *
 * @sine 5.1
 */
public class YCCKRasterOp implements RasterOp
{

    private RenderingHints hints = null;

    public YCCKRasterOp(RenderingHints hints)
    {
        this.hints = hints;
    }

    public WritableRaster filter(Raster src, WritableRaster dest)
    {

        if (dest == null)
        {
            dest = src.createCompatibleWritableRaster();
        }

        // may have to add some instance of checks
        byte[] srcPixels = ((DataBufferByte) src.getDataBuffer()).getData();
        byte[] destPixels = ((DataBufferByte) dest.getDataBuffer()).getData();

        double Y, Cb, Cr, K;
        double lastY = -1, lastCb = -1, lastCr = -1, lastK = -1;
        int c = 0, m = 0, y2 = 0, k = 0;

        int bands = src.getNumBands();
        for (int pixel = 0; pixel < srcPixels.length; pixel += bands)
        {

            Y = (srcPixels[pixel] & 0xff);
            Cb = (srcPixels[pixel + 1] & 0xff);
            Cr = (srcPixels[pixel + 2] & 0xff);
            K = (srcPixels[pixel + 3] & 0xff);

            if (!(lastY == Y && lastCb == Cb && lastCr == Cr && lastK == K))
            {

                // intel codecs, http://software.intel.com/sites/products/documentation/hpc/ipp/ippi/ippi_ch6/ch6_color_models.html
                // Intel IPP conversion for JPEG codec.
                c = 255 - (int) (Y + (1.402 * Cr) - 179.456);
                m = 255 - (int) (Y - (0.34414 * Cb) - (0.71413636 * Cr) + 135.45984);
                y2 = 255 - (int) (Y + (1.7718 * Cb) - 226.816);
                k = (int) K;

                c = clip(0, 255, c);
                m = clip(0, 255, m);
                y2 = clip(0, 255, y2);
            }

            lastY = Y;
            lastCb = Cb;
            lastCr = Cr;
            lastK = K;
            destPixels[pixel] = (byte) (c & 0xff);
            destPixels[pixel + 1] = (byte) (m & 0xff);
            destPixels[pixel + 2] = (byte) (y2 & 0xff);
            destPixels[pixel + 3] = (byte) (k & 0xff);
        }
        return dest;
    }

    public Rectangle2D getBounds2D(Raster src)
    {
        return null;
    }

    public WritableRaster createCompatibleDestRaster(Raster src)
    {
        return src.createCompatibleWritableRaster();
    }

    public Point2D getPoint2D(Point2D srcPt, Point2D dstPt)
    {
        if (dstPt == null)
        {
            dstPt = (Point2D) srcPt.clone();
        }
        else
        {
            dstPt.setLocation(srcPt);
        }
        return dstPt;
    }

    public RenderingHints getRenderingHints()
    {
        return hints;
    }

    /**
     * Clips the value according to the specified floor and ceiling.
     *
     * @param floor   floor value of clip
     * @param ceiling ceiling value of clip
     * @param value   value to clip.
     * @return clipped value.
     */
    private static int clip(int floor, int ceiling, int value)
    {
        if (value < floor)
        {
            value = floor;
        }
        if (value > ceiling)
        {
            value = ceiling;
        }
        return value;
    }
}
