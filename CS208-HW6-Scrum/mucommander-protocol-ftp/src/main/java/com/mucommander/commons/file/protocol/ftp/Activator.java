/**
 * This file is part of muCommander, http://www.mucommander.com
 * <p>
 * muCommander is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * muCommander is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mucommander.commons.file.protocol.ftp;

import com.mucommander.commons.file.AuthenticationType;
import com.mucommander.commons.file.Credentials;
import com.mucommander.commons.file.DefaultSchemeHandler;
import com.mucommander.commons.file.DefaultSchemeParser;
import com.mucommander.commons.file.SchemeHandler;
import com.mucommander.commons.file.osgi.FileProtocolService;
import com.mucommander.commons.file.protocol.ProtocolProvider;
import com.mucommander.protocol.ui.ProtocolPanelProvider;
import com.mucommander.protocol.ui.ServerPanel;
import com.mucommander.protocol.ui.ServerPanelListener;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import javax.swing.*;

/**
 * @author Arik Hadas
 */
public class Activator implements BundleActivator
{

    private ServiceRegistration<FileProtocolService> serviceRegistration;
    private ServiceRegistration<ProtocolPanelProvider> uiServiceRegistration;

    @Override
    public void start(BundleContext context) throws Exception
    {
        FileProtocolService service = new FileProtocolService()
        {
            @Override
            public String getSchema()
            {
                return "ftp";
            }

            @Override
            public ProtocolProvider getProtocolProvider()
            {
                return new FTPProtocolProvider();
            }

            @Override
            public SchemeHandler getSchemeHandler()
            {
                return new DefaultSchemeHandler(new DefaultSchemeParser(), 21, "/", AuthenticationType.AUTHENTICATION_REQUIRED, new Credentials("anonymous", "anonymous_coward@mucommander.com"));
            }
        };
        ProtocolPanelProvider panelProvider = new ProtocolPanelProvider()
        {
            @Override
            public String getSchema()
            {
                return "ftp";
            }

            @Override
            public ServerPanel get(ServerPanelListener listener, JFrame mainFrame)
            {
                return new FTPPanel(listener, mainFrame);
            }

            @Override
            public int priority()
            {
                return 5000;
            }

            @Override
            public Class<? extends ServerPanel> getPanelClass()
            {
                return FTPPanel.class;
            }
        };
        serviceRegistration = context.registerService(FileProtocolService.class, service, null);
        uiServiceRegistration = context.registerService(ProtocolPanelProvider.class, panelProvider, null);
    }

    @Override
    public void stop(BundleContext context) throws Exception
    {
        serviceRegistration.unregister();
        uiServiceRegistration.unregister();
    }

}
