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

package com.mucommander.commons.util.ui.layout;

import javax.swing.*;
import java.awt.*;

/**
 * This panel should be used instead of creating temporary panels only for layout purpose.
 * Using Fluent Interface technique, the add component calls can be chained and the resulting
 * code becomes more readable.
 * <p>
 * Note: The caller to {@link #add(Component)} should be very careful not to assume
 * the returned object is the object that was just added (as in {@link JPanel#add(Component)})
 *
 * @author Arik Hadas
 */
public class FluentPanel extends JPanel
{

    public FluentPanel(LayoutManager layoutManager)
    {
        super(layoutManager);
    }

    @Override
    public FluentPanel add(Component comp)
    {
        super.add(comp);

        return this;
    }

    public FluentPanel add(Component comp, String constraints)
    {
        super.add(comp, constraints);

        return this;
    }
}
