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

import com.mucommander.ui.autocomplete.completers.Completer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.text.BadLocationException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * OtherTextComponentCompleter is a CompleterType which suite to text components
 * other than editable combobox and text-field (such as textArea for example).
 * <p>
 * This class was not tested.
 *
 * @author Arik Hadas, based on the code of Santhosh Kumar: http://www.jroller.com/santhosh/entry/file_path_autocompletion
 */

public class OtherTextComponentCompletion extends CompletionType
{
    private static final Logger LOGGER = LoggerFactory.getLogger(OtherTextComponentCompletion.class);

    private class ShowingThreadImp extends ShowingThread
    {
        public ShowingThreadImp(int delay)
        {
            super(delay);
        }

        @Override
        void showAutocompletionPopup()
        {
            if (autocompletedtextComp.isShowing() && autocompletedtextComp.isEnabled() && updateListData(list))
            {

                list.setVisibleRowCount(Math.min(list.getModel().getSize(), VISIBLE_ROW_COUNT));

                int x;
                try
                {
                    x = autocompletedtextComp.modelToView().x;
                }
                catch (BadLocationException e)
                {
                    // this should never happen!!!
                    LOGGER.debug("Caught exception", e);
                    return;
                }
                if (autocompletedtextComp.hasFocus())
                {
                    if (!isStopped)
                    {
                        list.ensureIndexIsVisible(0);
                        synchronized (popup)
                        {
                            popup.show(autocompletedtextComp.getTextComponent(), x, autocompletedtextComp.getHeight());

                            // probably because of swing's bug, sometimes the popup window looks
                            // as a gray rectangle - repainting solves it.
                            popup.repaint();
                        }
                        autocompletedtextComp.getDocument().addDocumentListener(documentListener);
                    }
                }
            }
        }
    }

    public OtherTextComponentCompletion(AutocompleterTextComponent comp, Completer completer)
    {
        super(comp, completer);

        autocompletedtextComp.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent keyEvent)
            {
                switch (keyEvent.getKeyCode())
                {
                    case KeyEvent.VK_ENTER:
                        boolean itemSelected = isItemSelectedAtPopupList();

                        // Notify listeners that the text field has been validated
                        if (itemSelected)
                        {
                            hideAutocompletionPopup();
                            acceptListItem((String) list.getSelectedValue());
                            keyEvent.consume();
                        }
                        else
                        {
                            autocompletedtextComp.OnEnterPressed(keyEvent);
                        }
                        break;
                    case KeyEvent.VK_ESCAPE:
                        if (isPopupListShowing())
                        {
                            if (autocompletedtextComp.isEnabled())
                            {
                                hideAutocompletionPopup();
                            }
                            keyEvent.consume();
                        }
                        else
                        {
                            autocompletedtextComp.OnEscPressed(keyEvent);
                        }
                        break;
                    case KeyEvent.VK_UP:
                        if (autocompletedtextComp.isEnabled() && popup.isVisible())
                        {
                            selectPreviousPossibleValue();
                            keyEvent.consume();
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if (autocompletedtextComp.isEnabled())
                        {
                            if (popup.isVisible())
                            {
                                selectNextPossibleValue();
                                keyEvent.consume();
                            }
                        }
                        break;
                    case KeyEvent.VK_SPACE:
                        if (keyEvent.isControlDown())
                        {
                            if (!popup.isVisible())
                            {
                                autocompletedtextComp.moveCarentToEndOfText();
                                createNewShowingThread(0);
                            }
                        }
                        break;
                    case KeyEvent.VK_PAGE_DOWN:
                        if (autocompletedtextComp.isEnabled() && isPopupListShowing())
                        {
                            selectNextPage();
                            keyEvent.consume();
                        }
                        break;
                    case KeyEvent.VK_PAGE_UP:
                        if (autocompletedtextComp.isEnabled() && isItemSelectedAtPopupList())
                        {
                            selectPreviousPage();
                            keyEvent.consume();
                        }
                        break;
                    case KeyEvent.VK_HOME:
                        if (autocompletedtextComp.isEnabled() && isPopupListShowing())
                        {
                            selectFirstValue();
                            keyEvent.consume();
                        }
                        break;
                    case KeyEvent.VK_END:
                        if (autocompletedtextComp.isEnabled() && isPopupListShowing())
                        {
                            selectLastValue();
                            keyEvent.consume();
                        }
                        break;
                    case KeyEvent.VK_LEFT:
                        hideAutocompletionPopup();
                        break;
                    default:
                }
            }
        });
    }

    @Override
    protected void hideAutocompletionPopup()
    {
        synchronized (popup)
        {
<<< This is the complex bug 'focused_fermi' (instance 1 of 9) >>>
<<< This is the complex bug 'focused_fermi' (instance 2 of 9) >>>
<<< This is the complex bug 'focused_fermi' (instance 3 of 9) >>>
            if (popup.isVisible())
            {
                popup.setVisible(false);
            }
            autocompletedtextComp.getDocument().removeDocumentListener(documentListener);
        }
    }

    @Override
    protected void startNewShowingThread(int delay)
    {
        (showingThread = new ShowingThreadImp(delay)).start();
    }
}
