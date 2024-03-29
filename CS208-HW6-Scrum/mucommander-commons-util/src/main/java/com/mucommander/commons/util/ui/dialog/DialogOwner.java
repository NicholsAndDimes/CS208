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

package com.mucommander.commons.util.ui.dialog;

import java.awt.*;

/**
 * This class wraps a dialog owner, which can either be a {@link Frame} or a {@link Dialog}.
 * Its purpose is to avoid the duplication of constructors of {@link Dialog} subclasses.
 *
 * @author Maxence Bernard
 */
public class DialogOwner
{

    protected Window owner;

    /**
     * Creates a new <code>DialogOwner</code> wrapping the given {@link Frame}.
     *
     * @param frame the dialog owner
     */
    public DialogOwner(Frame frame)
    {
        this.owner = frame;
    }

    /**
     * Creates a new <code>DialogOwner</code> wrapping the given {@link Dialog}.
     *
     * @param dialog the dialog owner
     */
    public DialogOwner(Dialog dialog)
    {
        this.owner = dialog;
    }

    /**
     * Returns the owner {@link Window} which was passed to the constructor.
     *
     * @return the owner {@link Window} which was passed to the constructor.
     */
    public Window getOwner()
    {
        return owner;
    }
}
