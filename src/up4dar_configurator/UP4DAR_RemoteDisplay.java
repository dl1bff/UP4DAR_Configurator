/*
 * Copyright (C) 2013 Michael Dirska <dl1bff@mdx.de>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package up4dar_configurator;


import java.awt.image.MemoryImageSource;

/**
 *
 * @author Michael Dirska <dl1bff@mdx.de>
 */
public class UP4DAR_RemoteDisplay
{
    int pixels[];
    MemoryImageSource source;

    int size;
    int width;
    int height;
    static final int DISPLAY_WIDTH = 128;
    static final int DISPLAY_HEIGHT = 64;

    int onPixel = 0xFF202080;
    int offPixel = 0xFFE0E0FF;
    int spacerPixel = 0xFFE0E0E0;
    
    int pixelSize;

    UP4DAR_RemoteDisplay(int pixelSize)
    {
        this. pixelSize = pixelSize;
        
        width = pixelSize * DISPLAY_WIDTH;
        height = pixelSize * DISPLAY_HEIGHT;
        size = width * height;
        pixels = new int[size];

        for (int i = 0; i < size; i++) {
            pixels[i] = spacerPixel;
        }

        source = new MemoryImageSource(width, height, pixels, 0, width);
        source.setAnimated(true);
    }
    
    MemoryImageSource getImageSource()
    {
        return source;
    }
	
    void updatePixel (byte p[])
    {
        if (p.length != 1024)
            return;
        
        
        int x;
        int y;

        for (x = 0; x < DISPLAY_WIDTH; x++)
        {
            for (y=0; y < DISPLAY_HEIGHT; y++)
            {
                int b = ((int) p[(x >> 3) | (y << 4)]) & 0xff;

                int color =  ( (b & (1 << ( 7 - (x & 0x07)))) != 0 ) ? onPixel : offPixel;

                int i;
                int j;

                for (i=0; i < pixelSize; i++)
                {
                    for (j=0; j < pixelSize; j++)
                    {
                            pixels[ (y*pixelSize + i) * (DISPLAY_WIDTH * pixelSize) + (x*pixelSize + j) ] = color;
                    }
                }
            }
        }

        source.newPixels(0,0, width, height, true);
    }
	

}
