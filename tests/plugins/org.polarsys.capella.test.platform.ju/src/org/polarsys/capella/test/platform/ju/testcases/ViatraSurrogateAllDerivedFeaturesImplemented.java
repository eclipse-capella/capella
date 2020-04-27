/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.platform.ju.testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.helpers.TestHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ViatraSurrogateAllDerivedFeaturesImplemented extends BasicTestCase {

  @Override
  public void test() throws Exception {
    File testPluginFolder = getPluginFolder();
    File capella = PlatformFilesHelper.findRootFolder(testPluginFolder);
    File ext = PlatformFilesHelper.getSubFolder(capella, "ext");
    File viatra = PlatformFilesHelper.getSubFolder(ext, "viatra");
    File plugins = PlatformFilesHelper.getSubFolder(viatra, "plugins");
    List<File> pluginXmlFiles = PlatformFilesHelper.getPluginXmlFiles(plugins);
    List<SurrogateQuery> contributedSurrogateQueries = getContributedSurrogateQueries(pluginXmlFiles);

		List<EReference> uncoveredReferences = new ArrayList<>();
		for (EReference eReference : TestHelper.getAllCapellaDerivedReferences()) {
			if (!PlatformFilesHelper.isIgnored(eReference)
					&& !isReferenceCovered(eReference, contributedSurrogateQueries)) {
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
   * @param pluginXmlFiles
   * @return the list of surrogate queries contributed via extensions
   */
  private List<SurrogateQuery> getContributedSurrogateQueries(List<File> pluginXmlFiles) {
    List<SurrogateQuery> surrogateQueries = new ArrayList<>();

    for (File pluginXml : pluginXmlFiles) {
      try {
        DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        FileInputStream fis = new FileInputStream(pluginXml);
        Document doc = docBuilder.parse(fis);

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
  private boolean isReferenceCovered(EReference reference, List<SurrogateQuery> contributedSurrogateQueries) {
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
