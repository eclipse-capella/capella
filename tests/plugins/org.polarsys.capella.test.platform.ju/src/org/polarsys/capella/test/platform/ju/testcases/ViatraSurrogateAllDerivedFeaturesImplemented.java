/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.platform.ju.testcases;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EReference;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.Version;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.helpers.TestHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ViatraSurrogateAllDerivedFeaturesImplemented extends BasicTestCase {
  public String WORKSPACE;

  protected void parseArgs() {
    String[] args = Platform.getApplicationArgs();
    for (int i = 0; i < args.length; i++) {
      String currentArg = args[i];
      if ("-workspacePath".equalsIgnoreCase(currentArg))
        WORKSPACE = args[++i];
    }
  }

  protected String getPluginXmlFilePath(String workspacePath, String qualifier, String pluginName) {
    return "jar:file:/" + workspacePath + "/releng/plugins/org.polarsys.capella.rcp.site/target/repository/plugins/"
        + pluginName + "_" + qualifier + ".jar!/plugin.xml";
  }

  @Override
  public void test() throws Exception {
    parseArgs();
    Version version = FrameworkUtil.getBundle(getClass()).getVersion();
    String commonDataPluginXmlContent = PlatformFilesHelper.loadFileContentFromURL(getPluginXmlFilePath(
        WORKSPACE, version.toString(), "org.polarsys.capella.viatra.common.data.gen"));
    String commonRePluginXmlContent = PlatformFilesHelper.loadFileContentFromURL(getPluginXmlFilePath(
        WORKSPACE, version.toString(), "org.polarsys.capella.viatra.common.re.gen"));
    String coreDataPluginXmlContent = PlatformFilesHelper.loadFileContentFromURL(getPluginXmlFilePath(
        WORKSPACE, version.toString(), "org.polarsys.capella.viatra.core.data.gen"));
    List<String> pluginXmlContents = Arrays.asList(commonDataPluginXmlContent, commonRePluginXmlContent,
        coreDataPluginXmlContent);
    List<SurrogateQuery> contributedSurrogateQueries = getContributedSurrogateQueries(pluginXmlContents);

    List<EReference> uncoveredReferences = new ArrayList<>();
    for (EReference eReference : TestHelper.getAllCapellaDerivedReferences()) {
      if (!PlatformFilesHelper.isIgnored(eReference) && !isReferenceCovered(eReference, contributedSurrogateQueries)) {
        uncoveredReferences.add(eReference);
      }
    }

    List<SurrogateQuery> redundantReferences = new ArrayList<>();
    for (SurrogateQuery query : contributedSurrogateQueries) {
      boolean usefulQuery = TestHelper.getAllCapellaDerivedReferences().stream()
          .anyMatch(r -> !PlatformFilesHelper.isIgnored(r) && query.corresponds(r));
      if (!usefulQuery) {
        redundantReferences.add(query);
      }
    }

    if (!uncoveredReferences.isEmpty() || !redundantReferences.isEmpty()) {
      StringBuilder builder = new StringBuilder();
      if (!uncoveredReferences.isEmpty()) {
        builder.append("The following derived features do not have a corresponding Viatra surrogate query: \n");
        for (EReference eRef : uncoveredReferences) {
          builder.append(eRef.getEContainingClass().getEPackage().getNsURI() + "/"
              + eRef.getEContainingClass().getName() + "/" + eRef.getName() + "\n");
        }
      }
      builder.append("\n");
      if (!redundantReferences.isEmpty()) {
        builder.append("The following contributed Viatra queries are redundant: \n");
        for (SurrogateQuery query : redundantReferences) {
          builder.append(query.packageUri + "/" + query.eClassName + "/" + query.featureName + "\n");
        }
      }
      fail(builder.toString());
    }
  }

  /**
   * 
   * @param pluginXmlContents
   * @return the list of surrogate queries contributed via extensions
   */
  protected List<SurrogateQuery> getContributedSurrogateQueries(List<String> pluginXmlContents) {
    List<SurrogateQuery> surrogateQueries = new ArrayList<>();

    for (String pluginXmlContent : pluginXmlContents) {
      try {
        DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(pluginXmlContent));
        Document doc = docBuilder.parse(is);

        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xpath = xPathFactory.newXPath();
        XPathExpression extensionExpr = xpath
            .compile("/plugin/extension[@point='org.eclipse.viatra.query.runtime.surrogatequeryemf']");
        NodeList extensionNodes = (NodeList) extensionExpr.evaluate(doc, XPathConstants.NODESET);
        for (int i = 0; i < extensionNodes.getLength(); i++) {
          Node extensionNode = extensionNodes.item(i);
          XPathExpression eClassNameExpr = xpath.compile("surrogate-query-emf/@class-name");
          XPathExpression featureNameExpr = xpath.compile("surrogate-query-emf/@feature-name");
          XPathExpression packageUriExpr = xpath.compile("surrogate-query-emf/@package-nsUri");
          String eClassName = (String) eClassNameExpr.evaluate(extensionNode, XPathConstants.STRING);
          String featureName = (String) featureNameExpr.evaluate(extensionNode, XPathConstants.STRING);
          String packageUri = (String) packageUriExpr.evaluate(extensionNode, XPathConstants.STRING);
          surrogateQueries.add(new SurrogateQuery(packageUri, eClassName, featureName));
        }
      } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException e) {
        // Silent catch
      }
    }
    return surrogateQueries;
  }

  /**
   * 
   * @param reference
   * @param contributedSurrogateQueries
   * @return whether the reference is covered by a surrogate query
   */
  protected boolean isReferenceCovered(EReference reference, List<SurrogateQuery> contributedSurrogateQueries) {
    for (SurrogateQuery query : contributedSurrogateQueries) {
      if (query.corresponds(reference))
        return true;
    }
    return false;
  }

  class SurrogateQuery {
    public SurrogateQuery(String packageUri, String eClassName, String featureName) {
      this.packageUri = packageUri;
      this.eClassName = eClassName;
      this.featureName = featureName;
    }

    String packageUri;
    String eClassName;
    String featureName;

    public boolean corresponds(EReference reference) {
      return (reference.getEContainingClass().getEPackage().getNsURI().equals(packageUri)
          && reference.getEContainingClass().getName().equals(eClassName) && reference.getName().equals(featureName));
    }
  }
}
