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

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.eclipse.emf.ecore.EAnnotation;
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
    File capella = findRootFolder(testPluginFolder);
    File ext = getSubFolder(capella, "ext");
    File viatra = getSubFolder(ext, "viatra");
    File plugins = getSubFolder(viatra, "plugins");
    List<File> pluginXmlFiles = getPluginXmlFiles(plugins);
    List<SurrogateQuery> contributedSurrogateQueries = getContributedSurrogateQueries(pluginXmlFiles);
    for (EReference eReference : TestHelper.getAllCapellaDerivedReferences()) {
      if (!isIgnored(eReference)) {
        assertTrue(
            "The derived feature " + eReference.getEContainingClass().getEPackage().getNsURI() + "/"
                + eReference.getEContainingClass().getName() + "/" + eReference.getName()
                + " does not have a corresponding Viatra surrogate query",
            isReferenceCovered(eReference, contributedSurrogateQueries));
      }
    }
  }

  /**
   * 
   * @param testPluginFolder
   * @return the root folder (which contains .git folder)
   */
  private File findRootFolder(File testPluginFolder) {
    if (testPluginFolder.listFiles(new FileFilter() {
      @Override
      public boolean accept(File subFolder) {
        return subFolder.isDirectory() && subFolder.getName().equals(".git");
      }
    }).length == 1) {
      return testPluginFolder;
    }
    return findRootFolder(testPluginFolder.getParentFile());
  }

  /**
   * 
   * @param eReference
   * @return whether the reference is ignored during the check of surrogate query
   */
  private boolean isIgnored(EReference eReference) {
    // A reference is ignored if it has the viagra.variant=unimplemented annotation
    Optional<EAnnotation> eAnnotation = eReference.getEAnnotations().stream().filter(new Predicate<EAnnotation>() {
      @Override
      public boolean test(EAnnotation eAnnotation) {
        return eAnnotation.getSource().equals("http://www.polarsys.org/capella/derived");
      }
    }).findFirst();
    return eAnnotation.get().getDetails().stream().anyMatch(new Predicate<Map.Entry<String, String>>() {
      @Override
      public boolean test(Map.Entry<String, String> entry) {
        return entry.getKey().equals("viatra.variant") && entry.getValue().equals("unimplemented");
      }
    });
  }

  /**
   * 
   * @param folder
   * @param folderName
   * @return the subfolder with the given name
   */
  private File getSubFolder(File folder, String folderName) {
    File[] listFiles = folder.listFiles(new FileFilter() {
      @Override
      public boolean accept(File subFolder) {
        return subFolder.isDirectory() && subFolder.getName().equals(folderName);
      }
    });
    if (listFiles.length > 0)
      return listFiles[0];
    return null;
  }

  /**
   * 
   * @param pluginsFolder
   * @return the list of plugin.xml files from the plugins folder
   */
  private List<File> getPluginXmlFiles(File pluginsFolder) {
    List<File> pluginXmlFiles = new ArrayList<>();
    for (File file : pluginsFolder.listFiles()) {
      if (file.isFile() && file.getName().equals("plugin.xml"))
        pluginXmlFiles.add(file);
      else if (file.isDirectory()) {
        pluginXmlFiles.addAll(getPluginXmlFiles(file));
      }
    }
    return pluginXmlFiles;
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
