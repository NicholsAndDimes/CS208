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

package com.mucommander.ui.dialog.pref.component;

import com.mucommander.ui.autocomplete.BasicAutocompleterTextComponent;
import com.mucommander.ui.autocomplete.CompleterFactory;
import com.mucommander.ui.autocomplete.TextFieldCompletion;

/**
 * @author Arik Hadas
 */
public abstract class PrefFilePathField extends PrefTextField
{

    public PrefFilePathField(String text)
    {
        super(text);
        enableAutoCompletion();
    }

    /**
     * Adds auto-completion capabilities to this text field.
     */
    private void enableAutoCompletion()
    {
        new TextFieldCompletion(new BasicAutocompleterTextComponent(this), CompleterFactory.getPathCompleter());
    }
}
