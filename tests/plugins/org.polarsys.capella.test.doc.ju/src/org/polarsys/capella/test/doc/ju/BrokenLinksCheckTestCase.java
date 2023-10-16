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

import org.eclipse.emf.common.util.URI;
import org.eclipse.help.internal.base.BaseHelpSystem;
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
    BaseHelpSystem.ensureWebappRunning();

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
        System.out.println("Analyzing... " + stringURL);

        URL rootURL = new URL(stringURL);
        if (!isExluded(htmlURL)) {
          Collection<String> messages = extractBrokenLinkMessages(rootURL);
          errorMessages.addAll(messages);
        }
      }
    }

    assertTrue(errorMessages.stream().collect(Collectors.joining("\n")), errorMessages.isEmpty());
  }

  protected String getHelpServer() {
    return "http://" //$NON-NLS-1$
        + WebappManager.getHost() + ":" //$NON-NLS-1$
        + WebappManager.getPort() + "/help/topic/";
  }

  public Collection<String> extractBrokenLinkMessages(URL rootUrl) throws IOException {

    Collection<String> errorMessages = new ArrayList<>();

    Collection<Node> linkNodes = getAllNodes(rootUrl);

    // Exclude links that are only for online visualization
    linkNodes = linkNodes.stream().filter(BrokenLinksCheckTestCase::isOfflineLink).collect(Collectors.toList());

    for (Node linkNode : linkNodes) {
      String linkText = adaptToURL(linkNode);
      if (linkText == null) {
        String errorMessage = NLS.bind("Null link in page {0}", rootUrl.toString());
        errorMessages.add(errorMessage);
      }
    }

    Collection<String> urls = linkNodes.stream().map(x -> adaptToURL(x)).distinct().collect(Collectors.toList());
    for (String linkText : urls) {
      linkText = toValidURL(linkText);
      URL url = new URL(rootUrl, linkText);
      if (isFakeUrl(url)) {
        continue;
      }

      HttpURLConnection linkConnection = (HttpURLConnection) url.openConnection();
      try {
        int responseCode = linkConnection.getResponseCode();
        if (responseCode == 407) {
          String errorMessage = NLS.bind("Proxy issue for {0} ( {1} ) in page {2} : {3}",
              new String[] { linkText, url.toString(), rootUrl.toString(), "" + responseCode });
          errorMessages.add(errorMessage);

        } else if (responseCode != 200) {
          String errorMessage = NLS.bind("Invalid url {0} ( {1} ) in page {2} : {3}",
              new String[] { linkText, url.toString(), rootUrl.toString(), "" + responseCode });
          errorMessages.add(errorMessage);
        }

      } catch (Exception e) {
        String errorMessage = NLS.bind("Invalid url {0} ( {1} ) in page {2} : {3}",
            new String[] { linkText, url.toString(), rootUrl.toString(), e.getMessage() });
        errorMessages.add(errorMessage);

      } finally {
        linkConnection.disconnect();
      }
    }

    return errorMessages;
  }

  /**
   * From a node, retrieve its href
   */
  protected String adaptToURL(Node linkNode) {
    Node href = linkNode.getAttributes().getNamedItem("href");
    if (href != null) {
      return href.getTextContent();
    }
    Node src = linkNode.getAttributes().getNamedItem("src");
    if (src != null) {
      return src.getTextContent();
    }
    return null;
  }

  /**
   * From a href, return a valid url with correct encoding
   */
  protected String toValidURL(String href) {
    if (href.startsWith("/wiki/../")) {
      href = href.substring("/wiki/../".length() - 1);
    }
    href = URI.createURI(href, true).toString();
    return href;
  }

  /**
   * Returns whether we look links into this bundle
   */
  protected boolean isDocPlugin(Bundle bundle) {
    return bundle.getSymbolicName().startsWith("org.polarsys.capella") && bundle.getSymbolicName().endsWith("doc");
  }

  /**
   * Returns whether the URL is a real http. Some url are known to be fake.
   */
  protected boolean isFakeUrl(URL url) {
    String urlValue = url.toString();
    if (urlValue.startsWith("http://www.polarsys.org/capella/core/")
        || urlValue.startsWith("http://www.polarsys.org/capella/common/")
        || urlValue.startsWith("http://www.polarsys.org/capella/derived")
        || urlValue.startsWith("https://update-site.url")) {
      return true;
    }
    return false;
  }

  /**
   * Returns all <A> and <IMG>
   */
  private Collection<Node> getAllNodes(URL rootUrl) throws IOException {

    HttpURLConnection connection = (HttpURLConnection) rootUrl.openConnection();
    connection.setRequestMethod("POST");
    connection.setRequestProperty("ACCEPT", "application/xml");

    try (InputStream xmlContent = connection.getInputStream()) {
      // The parser might try to download the DTD in order to validate the parsed XML, we do not need to.
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      factory.setValidating(false);
      factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

      DocumentBuilder builder = factory.newDocumentBuilder();
      Document document = builder.parse(xmlContent);

      XPathFactory pathFactory = XPathFactory.newInstance();
      XPath path = pathFactory.newXPath();
      XPathExpression links = path.compile("//a | //img");
      return toList((NodeList) links.evaluate(document, XPathConstants.NODESET));

    } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException e) {
      String errorMessage = NLS.bind("Could not process resource {0}. {1}", rootUrl.toString(), e.getMessage());
      throw new IOException(errorMessage);
    }
  }

  protected Collection<Node> toList(NodeList list) {
    Collection<Node> result = new ArrayList<Node>();
    if (list != null) {
      for (int i = 0; i < list.getLength(); i++) {
        Node node = list.item(i);
        result.add(node);
      }
    }
    return result;
  }

  protected static boolean isOfflineLink(Node linkNode) {
    return !(linkNode.getParentNode() != null && linkNode.getParentNode().getNodeName() != null
        && linkNode.getParentNode().getNodeName().equals("span"));
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
