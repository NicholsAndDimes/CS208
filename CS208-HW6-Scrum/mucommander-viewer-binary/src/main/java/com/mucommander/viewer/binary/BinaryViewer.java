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
package com.mucommander.viewer.binary;

import com.mucommander.commons.file.AbstractFile;
import com.mucommander.commons.util.ui.helper.MenuToolkit;
import com.mucommander.commons.util.ui.helper.MnemonicHelper;
import com.mucommander.text.Translator;
import com.mucommander.ui.theme.ColorChangedEvent;
import com.mucommander.ui.theme.FontChangedEvent;
import com.mucommander.ui.theme.Theme;
import com.mucommander.ui.theme.ThemeListener;
import com.mucommander.ui.theme.ThemeManager;
import com.mucommander.ui.viewer.FileFrame;
import com.mucommander.ui.viewer.FileViewer;
import com.mucommander.viewer.FileViewerWrapper;
import org.exbin.deltahex.CodeType;
import org.exbin.deltahex.EditationAllowed;
import org.exbin.deltahex.HexCharactersCase;
import org.exbin.deltahex.swing.CodeArea;
import org.exbin.utils.binary_data.ByteArrayEditableData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * General viewer for binary files.
 *
 * @author Miroslav Hajda
 */
class BinaryViewer extends FileViewer implements ActionListener, FileViewerWrapper
{

    private Frame frame;

    /**
     * Menu bar
     */
    // Menus //
    private JMenu editMenu;
    private JMenu viewMenu;
    private JMenu codeTypeMenu;
    private JMenu hexCharacterCaseMenu;
    // Items //
    private javax.swing.ButtonGroup codeTypeButtonGroup;
    private javax.swing.ButtonGroup hexCharacterCaseButtonGroup;
    private JMenuItem copyItem;
    private JMenuItem selectAllItem;
    private javax.swing.JRadioButtonMenuItem binaryCodeTypeRadioButtonMenuItem;
    private javax.swing.JRadioButtonMenuItem octalCodeTypeRadioButtonMenuItem;
    private javax.swing.JRadioButtonMenuItem decimalCodeTypeRadioButtonMenuItem;
    private javax.swing.JRadioButtonMenuItem hexadecimalCodeTypeRadioButtonMenuItem;
    private javax.swing.JRadioButtonMenuItem lowerCaseRadioButtonMenuItem;
    private javax.swing.JRadioButtonMenuItem upperCaseRadioButtonMenuItem;

    private BinaryViewerImpl binaryViewerImpl;

    public BinaryViewer()
    {
        binaryViewerImpl = new BinaryViewerImpl();

        setComponentToPresent(binaryViewerImpl);

        initMenuBars();
    }

    private void initMenuBars()
    {
        editMenu = new JMenu(Translator.get("text_viewer.edit"));
        MnemonicHelper menuItemMnemonicHelper = new MnemonicHelper();

        copyItem = MenuToolkit.addMenuItem(editMenu, Translator.get("text_viewer.copy"), menuItemMnemonicHelper, null, this);
        copyItem.addActionListener(e -> binaryViewerImpl.copy());

        selectAllItem = MenuToolkit.addMenuItem(editMenu, Translator.get("text_viewer.select_all"), menuItemMnemonicHelper, null, this);
        selectAllItem.addActionListener(e -> binaryViewerImpl.selectAll());

        // View menu
        viewMenu = new JMenu(Translator.get("text_viewer.view"));

        codeTypeMenu = new JMenu("Code Type");

        codeTypeButtonGroup = new ButtonGroup();
        binaryCodeTypeRadioButtonMenuItem = new JRadioButtonMenuItem();
        codeTypeButtonGroup.add(binaryCodeTypeRadioButtonMenuItem);
        binaryCodeTypeRadioButtonMenuItem.setText("Binary");
        binaryCodeTypeRadioButtonMenuItem.addActionListener(e -> binaryViewerImpl.setCodeType(CodeType.BINARY));
        codeTypeMenu.add(binaryCodeTypeRadioButtonMenuItem);

        octalCodeTypeRadioButtonMenuItem = new JRadioButtonMenuItem();
        codeTypeButtonGroup.add(octalCodeTypeRadioButtonMenuItem);
        octalCodeTypeRadioButtonMenuItem.setText("Octal");
        octalCodeTypeRadioButtonMenuItem.addActionListener(e -> binaryViewerImpl.setCodeType(CodeType.OCTAL));
        codeTypeMenu.add(octalCodeTypeRadioButtonMenuItem);

        decimalCodeTypeRadioButtonMenuItem = new JRadioButtonMenuItem();
        codeTypeButtonGroup.add(decimalCodeTypeRadioButtonMenuItem);
        decimalCodeTypeRadioButtonMenuItem.setText("Decimal");
        decimalCodeTypeRadioButtonMenuItem.addActionListener(e -> binaryViewerImpl.setCodeType(CodeType.DECIMAL));
        codeTypeMenu.add(decimalCodeTypeRadioButtonMenuItem);

        hexadecimalCodeTypeRadioButtonMenuItem = new JRadioButtonMenuItem();
        codeTypeButtonGroup.add(hexadecimalCodeTypeRadioButtonMenuItem);
        hexadecimalCodeTypeRadioButtonMenuItem.setSelected(true);
        hexadecimalCodeTypeRadioButtonMenuItem.setText("Hexadecimal");
        hexadecimalCodeTypeRadioButtonMenuItem.addActionListener(e -> binaryViewerImpl.setCodeType(CodeType.HEXADECIMAL));
        codeTypeMenu.add(hexadecimalCodeTypeRadioButtonMenuItem);
        viewMenu.add(codeTypeMenu);

        hexCharacterCaseMenu = new JMenu("Hex Character Case");
        hexCharacterCaseButtonGroup = new ButtonGroup();

        upperCaseRadioButtonMenuItem = new JRadioButtonMenuItem();
        hexCharacterCaseButtonGroup.add(upperCaseRadioButtonMenuItem);
        upperCaseRadioButtonMenuItem.setSelected(true);
        upperCaseRadioButtonMenuItem.setText("Upper Case");
        upperCaseRadioButtonMenuItem.addActionListener(e -> binaryViewerImpl.setHexCharactersCase(HexCharactersCase.UPPER));
        hexCharacterCaseMenu.add(upperCaseRadioButtonMenuItem);

        lowerCaseRadioButtonMenuItem = new JRadioButtonMenuItem();
        hexCharacterCaseButtonGroup.add(lowerCaseRadioButtonMenuItem);
        lowerCaseRadioButtonMenuItem.setText("Lower Case");
        lowerCaseRadioButtonMenuItem.addActionListener(e -> binaryViewerImpl.setHexCharactersCase(HexCharactersCase.LOWER));
        hexCharacterCaseMenu.add(lowerCaseRadioButtonMenuItem);
        viewMenu.add(hexCharacterCaseMenu);
    }

    @Override
    public JMenuBar getMenuBar()
    {
        JMenuBar menuBar = super.getMenuBar();

        menuBar.add(editMenu);
        menuBar.add(viewMenu);

        return menuBar;
    }

    private synchronized void loadFile(AbstractFile file) throws IOException
    {
        FileFrame frame = getFrame();
        frame.setCursor(new Cursor(Cursor.WAIT_CURSOR));

        ByteArrayEditableData data = new ByteArrayEditableData();
        try
        {
            data.loadFromStream(file.getInputStream());
        }
        catch (IOException ex)
        {
            Logger.getLogger(BinaryViewer.class.getName()).log(Level.SEVERE, null, ex);
        }

        binaryViewerImpl.setData(data);

        frame.setCursor(Cursor.getDefaultCursor());
    }

    @Override
    public void show(AbstractFile file) throws IOException
    {
        loadFile(file);
    }

    /**
     * Returns panel for viewer.
     *
     * @return component instance
     */
    @Override
    public JComponent getViewerComponent()
    {
        return this;
    }

    @Override
    public void setFrame(Frame frame)
    {
        this.frame = frame;
    }

    private class BinaryViewerImpl extends CodeArea implements ThemeListener
    {

        private Color backgroundColor;

        BinaryViewerImpl()
        {
            backgroundColor = ThemeManager.getCurrentColor(Theme.EDITOR_BACKGROUND_COLOR);
            super.setBackground(backgroundColor);
            ThemeManager.addCurrentThemeListener(this);
            setEditationAllowed(EditationAllowed.READ_ONLY);
        }

        @Override
        public synchronized Dimension getPreferredSize()
        {
            return new Dimension(750, 500);
        }

        /**
         * Receives theme color changes notifications.
         */
        @Override
        public void colorChanged(ColorChangedEvent event)
        {
            if (event.getColorId() == Theme.EDITOR_BACKGROUND_COLOR)
            {
                backgroundColor = event.getColor();
                super.setBackground(backgroundColor);
                repaint();
            }
        }

        /**
         * Not used, implemented as a no-op.
         */
        @Override
        public void fontChanged(FontChangedEvent event)
        {
        }
    }
}
