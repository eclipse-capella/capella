/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.eclipse.help.internal.server.WebappManager;
import org.eclipse.osgi.util.NLS;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * This test will parse the generated documentation pages and identify any broken links that might exist.
 * 
 */
public class BrokenLinksCheckTestCase extends BasicTestCase {

  @Override
  public void test() throws Exception {
    WebappManager.start("help");

    List<Bundle> docPlugins = new ArrayList<>();

    BundleContext ctx = FrameworkUtil.getBundle(StyleCheckTestCase.class).getBundleContext();
    Bundle[] bundles = ctx.getBundles();
    for (Bundle bundle : bundles) {
      if (isDocPlugin(bundle))
        docPlugins.add(bundle);
    }

    Collection<String> errorMessages = new ArrayList<>();

    for (Bundle docPlugin : docPlugins) {
      Enumeration<URL> entries = docPlugin.findEntries("html", "*.html", true);

      while (entries.hasMoreElements()) {
        URL htmlURL = entries.nextElement();
        String stringURL = getHelpServer() + docPlugin.getSymbolicName() + htmlURL.getFile().replace(" ", "%20");
        System.out.println("Analyzing..." + stringURL);

        URL rootURL = new URL(stringURL);

        if (!isExluded(htmlURL)) {
          Collection<String> messages = extractBrokenLinkMessages(rootURL);
          errorMessages.addAll(messages);
        }
      }

    }

    WebappManager.stop("help");

    assertTrue(errorMessages.stream().collect(Collectors.joining("\n")), errorMessages.isEmpty());
  }

  private String getHelpServer() {
    return "http://" //$NON-NLS-1$
        + WebappManager.getHost() + ":" //$NON-NLS-1$
        + WebappManager.getPort() + "/help/topic/";
  }

  protected boolean isDocPlugin(Bundle bundle) {
    return bundle.getSymbolicName().startsWith("org.polarsys.capella") && bundle.getSymbolicName().endsWith("doc");
  }

  public Collection<String> extractBrokenLinkMessages(URL rootUrl) throws IOException {

    List<String> errorMessages = new ArrayList<>();

    HttpURLConnection connection = (HttpURLConnection) rootUrl.openConnection();
    connection.setRequestMethod("POST");
    connection.setRequestProperty("ACCEPT", "application/xml");
    InputStream xmlContent = connection.getInputStream();

    NodeList linkNodes = null;

    try {
      // The parser might try to download the DTD in order to validate the parsed XML, we do not need to.
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      factory.setValidating(false);
      factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

      DocumentBuilder builder = factory.newDocumentBuilder();
      Document document = builder.parse(xmlContent);

      XPathFactory pathFactory = XPathFactory.newInstance();
      XPath path = pathFactory.newXPath();
      XPathExpression linkExpression = path.compile("//a/@href");
      linkNodes = (NodeList) linkExpression.evaluate(document, XPathConstants.NODESET);

    } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException e) {
      String errorMessage = NLS.bind("Could not process resource {0}. {1}", rootUrl.toString(), e.getMessage());
      errorMessages.add(errorMessage);
    }

    if (linkNodes != null) {

      for (int i = 0; i < linkNodes.getLength(); i++) {
        Node linkNode = linkNodes.item(i);
        String linkText = linkNode.getTextContent();

        URL absoluteUrl = new URL(rootUrl, linkText);
        System.out.println("Checking link: " + linkText);

        HttpURLConnection linkConnection = (HttpURLConnection) rootUrl.openConnection();

        int responseCode = linkConnection.getResponseCode();
        if (responseCode != 200) {
          String errorMessage = NLS.bind("Invalid url {0} in page {1} ", absoluteUrl, rootUrl);
          errorMessages.add(errorMessage);
        }

      }
    }

    return errorMessages;
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
