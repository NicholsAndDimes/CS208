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

package com.mucommander.desktop.windows;

import com.mucommander.desktop.DesktopAdapter;
import com.mucommander.osgi.OperatingSystemService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import java.util.Arrays;
import java.util.List;

public class Activator implements BundleActivator
{

    private ServiceRegistration<OperatingSystemService> osRegistration;

    @Override
    public void start(BundleContext context) throws Exception
    {
        OperatingSystemService service = new OperatingSystemService()
        {
            @Override
            public List<DesktopAdapter> getDesktopAdapters()
            {
                return Arrays.asList(
                        new Win9xDesktopAdapter(),
                        new WinNtDesktopAdapter());
            }
        };
        osRegistration = context.registerService(OperatingSystemService.class, service, null);
    }

    @Override
    public void stop(BundleContext context) throws Exception
    {
        osRegistration.unregister();
    }
}
