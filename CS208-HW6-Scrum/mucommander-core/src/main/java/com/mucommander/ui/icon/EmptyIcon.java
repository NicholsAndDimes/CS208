/*
 * This file is part of muCommander, http://www.mucommander.com
 *
 * muCommander is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * muCommander is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.mucommander.ui.icon;

import javax.swing.*;
import java.awt.*;

/**
 * Empty {@link Icon} in the specified size that can be used as a place holder
 *
 * @author Arik Hadas
 */
public class EmptyIcon implements Icon
{

    private int width;
    private int height;

    public EmptyIcon(int size)
    {
        this.width = size;
        this.height = size;
    }

    public EmptyIcon(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    public int getIconHeight()
    {
        return height;
    }

    public int getIconWidth()
    {
        return width;
    }

    public void paintIcon(Component c, Graphics g, int x, int y)
    {
    }
}
