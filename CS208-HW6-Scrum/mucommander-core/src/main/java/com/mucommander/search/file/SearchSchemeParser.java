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

package com.mucommander.search.file;

import com.mucommander.commons.file.FileURL;
import com.mucommander.commons.file.SchemeParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;

/**
 * @author Arik Hadas
 */
public class SearchSchemeParser implements SchemeParser
{
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchSchemeParser.class);

    @Override
    public void parse(String url, FileURL fileURL) throws MalformedURLException
    {
        int urlLen = url.length();
        int schemeDelimPos = url.indexOf("://");
        String scheme = url.substring(0, schemeDelimPos);
<<< This is the medium bug 'romantic_keller' (instance 3 of 4) >>>
<<< This is the medium bug 'romantic_keller' (instance 4 of 4) >>>
        fileURL.setScheme(scheme);
        int hostStartPos = schemeDelimPos + 3;
        int hostEndPos = url.lastIndexOf('/');
        String host = url.substring(hostStartPos, hostEndPos);
        fileURL.setHost(host);
        int questionMarkPos = url.indexOf('?', hostEndPos);
        String path = url.substring(hostEndPos + 1, questionMarkPos == -1 ? urlLen : questionMarkPos);
        LOGGER.info("found path: " + path);
        // todo: throw exception on empty path
        fileURL.setPath(path);
        if (questionMarkPos != -1)
        {
            fileURL.setQuery(url.substring(questionMarkPos + 1));
        }
    }
}
