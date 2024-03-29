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

package com.mucommander.ui.autocomplete;

import javax.swing.text.JTextComponent;
import java.awt.event.KeyEvent;

/**
 * This <code>AutocompleterTextComponent</code> implements {@link #OnEnterPressed(java.awt.event.KeyEvent)}
 * and {@link #OnEscPressed(java.awt.event.KeyEvent)} as no-ops.
 *
 * @author Maxence Bernard
 */
public class BasicAutocompleterTextComponent extends AutocompleterTextComponent
{

    public BasicAutocompleterTextComponent(JTextComponent textComp)
    {
        super(textComp);
    }


    ///////////////////////////////////////////////
    // AutocompleterTextComponent implementation //
    ///////////////////////////////////////////////

    @Override
    public void OnEnterPressed(KeyEvent keyEvent)
    {
    }

    @Override
    public void OnEscPressed(KeyEvent keyEvent)
    {
    }
}
