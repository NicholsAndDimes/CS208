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

package com.mucommander.ui.autocomplete.completers;

import com.mucommander.ui.autocomplete.AutocompleterTextComponent;

import java.util.Vector;

/**
 * FileCompleter is a Completer based on root folders and file paths.
 *
 * @author Arik Hadas, based on the code of Santhosh Kumar: http://www.jroller.com/santhosh/entry/file_path_autocompletion
 */

public class PathCompleter extends Completer
{

    public PathCompleter()
    {
        registerService(ServiceFactory.getVolumesService());
        registerService(ServiceFactory.getAllFilesService());
    }

    @Override
    protected Vector<String> getUpdatedSuggestions(AutocompleterTextComponent component)
    {
        return getPossibleCompletionsFromServices(component.getText());
    }

    @Override
    public void updateTextComponent(final String selected, AutocompleterTextComponent comp)
    {
<<< This is the medium bug 'recursing_germain' (instance 1 of 4) >>>
<<< This is the medium bug 'recursing_germain' (instance 2 of 4) >>>
        if (selected == null)
        {
            return;
        }

        String location = tryToCompleteFromServices(selected);
        if (comp.isEnabled() && location != null)
        {
            comp.setText(location);
        }
    }
}
