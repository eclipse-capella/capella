/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.doc.ju;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.eclipse.help.internal.server.WebappManager;
import org.jsoup.Connection.Response;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.test.framework.api.BasicTestCase;

public class BrokenLinksCheckTestCase extends BasicTestCase {

  @Override
  public void test() throws Exception {
    WebappManager.start("help");

    List<Bundle> docPlugins = new ArrayList<>();
    List<URL> invalidHtmls = new ArrayList<>();

    BundleContext ctx = FrameworkUtil.getBundle(StyleCheckTestCase.class).getBundleContext();
    Bundle[] bundles = ctx.getBundles();
    for (Bundle bundle : bundles) {
      if (isDocPlugin(bundle))
        docPlugins.add(bundle);
    }

    String errMsg = "The following documents contain broken links: ";

    for (Bundle docPlugin : docPlugins) {
      String bundleErrMsg = "In plugin " + docPlugin.getSymbolicName() + ": ";
      Enumeration<URL> entries = docPlugin.findEntries("html", "*.html", true);
      boolean hasInvalidHtml = false;
      while (entries.hasMoreElements()) {
        URL htmlURL = entries.nextElement();
        if (!isExluded(htmlURL) && containBrokenLinks(docPlugin, htmlURL)) {
          invalidHtmls.add(htmlURL);
          bundleErrMsg += (htmlURL.getFile() + ", ");
          hasInvalidHtml = true;
        }
      }
      if (hasInvalidHtml)
        errMsg += bundleErrMsg.substring(0, bundleErrMsg.length() - 2) + ". ";
    }

    assertTrue(errMsg, invalidHtmls.size() == 0);

    WebappManager.stop("help");
  }

  private String getHelpServer() {
    return "http://" //$NON-NLS-1$
        + WebappManager.getHost() + ":" //$NON-NLS-1$
        + WebappManager.getPort() + "/help/topic/";
  }

  protected boolean isDocPlugin(Bundle bundle) {
    return bundle.getSymbolicName().startsWith("org.polarsys.capella") && bundle.getSymbolicName().endsWith("doc");
  }

  public boolean containBrokenLinks(Bundle docPlugin, URL htmlURL) throws IOException {
    boolean result = false;

    String url = getHelpServer() + docPlugin.getSymbolicName() + htmlURL.getFile().replace(" ", "%20");
    System.out.println("Fetching..." + url);

    try {
      Document doc = Jsoup.connect(url).get();
      Elements links = doc.select("a[href]");

      for (Element link : links) {
        String linkURL = link.absUrl("href").replace(" ", "%20");
        try {
          System.out.println("Checking link " + link.text());
          Response response = Jsoup.connect(linkURL).ignoreContentType(true).execute();
          System.out.println("	OK with status code:" + response.statusCode());
        } catch (HttpStatusException e1) {
          System.out.println("Found broken link: " + link.absUrl("href") + " with status code " + e1.getStatusCode());
          result = true;
        } catch (UnknownHostException e2) {
          System.out.println("Found broken link: " + link.absUrl("href") + " with unknown host");
          result = true;
        }
      }
    } catch (HttpStatusException e) {
      System.out.println("Error while fetching " + url);
      result = true;
    }

    return result;
  }

  protected boolean isExluded(URL htmlURL) {
    // We should not verify generated javadoc
    if (htmlURL.getFile().startsWith("/html/api-docs/javadoc"))
      return true;
    return false;
  }

  protected boolean isInternalLink(String linkURL) {
    if (linkURL.contains(WebappManager.getHost()))
      return true;
    return false;
  }
}
