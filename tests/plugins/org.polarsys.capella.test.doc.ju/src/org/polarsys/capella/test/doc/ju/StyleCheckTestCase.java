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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.test.framework.api.BasicTestCase;

public class StyleCheckTestCase extends BasicTestCase {

  @Override
  public void test() throws Exception {
    List<Bundle> docPlugins = new ArrayList<>();
    List<URL> invalidHtmls = new ArrayList<>();

    BundleContext ctx = FrameworkUtil.getBundle(StyleCheckTestCase.class).getBundleContext();
    Bundle[] bundles = ctx.getBundles();
    for (Bundle bundle : bundles) {
      if (isDocPlugin(bundle))
        docPlugins.add(bundle);
    }

    String errMsg = "The following documents do not include CSS style: ";
    
    for (Bundle docPlugin : docPlugins) {
      String bundleErrMsg = "In plugin " + docPlugin.getSymbolicName() + ": ";
      Enumeration<URL> entries = docPlugin.findEntries("html", "*.html", true);
      boolean hasInvalidHtml = false;
      while (entries.hasMoreElements()) {
        URL htmlURL = entries.nextElement();
        if (!isExluded(htmlURL) && !doesHtmlDocIncludeStyle(htmlURL)) {
          invalidHtmls.add(htmlURL);
          bundleErrMsg += (htmlURL.getFile() + ", ");
          hasInvalidHtml = true;
        }
      }
      if (hasInvalidHtml)
        errMsg += bundleErrMsg.substring(0, bundleErrMsg.length() - 2) + ". ";
    }

    assertTrue(errMsg, invalidHtmls.size() == 0);
  }

  protected boolean isDocPlugin(Bundle bundle) {
    return bundle.getSymbolicName().startsWith("org.polarsys.capella") && bundle.getSymbolicName().endsWith("doc");
  }

  public boolean doesHtmlDocIncludeStyle(URL htmlURL) throws IOException {
    boolean result = false;
    BufferedReader in = new BufferedReader(new InputStreamReader(htmlURL.openStream()));
    String inputLine;
    while ((inputLine = in.readLine()) != null)
      if (inputLine.contains(getCSSFile()))
        result = true;
    in.close();
    return result;
  }

  protected String getCSSFile() {
    return "PLUGINS_ROOT/org.polarsys.capella.doc/html/styles.css";
  }
  
  protected boolean isExluded(URL htmlURL) {
    // We should not verify generated javadoc 
    if (htmlURL.getFile().startsWith("/html/api-docs/javadoc"))
      return true;
    return false;
  }

}
