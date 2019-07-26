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
package org.polarsys.capella.test.odesign.directeditlabel;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.polarsys.capella.test.odesign.directeditlabel.DirectEditLabelConstants.CURRENT_ODESIGN_PATH;
import static org.polarsys.capella.test.odesign.directeditlabel.DirectEditLabelConstants.ODESIGN_EXT;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.tool.DirectEditLabel;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.polarsys.capella.core.sirius.analysis.FaServices;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ODesignHelper;
import org.polarsys.capella.test.framework.helpers.IResourceHelpers;

public class DirectEditLabelHelper {

  public static DirectEditLabel getLabelByNameAndPath(List<DirectEditLabel> directEditLabels,
      DirectEditLabel editLabelToFind) {
    for (DirectEditLabel label : directEditLabels) {
      if (label.getName().equals(editLabelToFind.getName()) && hasSameModelPath(label, editLabelToFind)) {
        return label;
      }
    }
    return null;
  }

  /**
   * @param label_p
   * @param editLabelToFind_p
   * @return
   */
  public static boolean hasSameModelPath(DirectEditLabel editLabel_p, DirectEditLabel otherEditLabel_p) {
    return computeModelPath(editLabel_p).equals(computeModelPath(otherEditLabel_p));
  }

  /**
   * @param editLabel_p
   * @return
   */
  public static String computeModelPath(DirectEditLabel editLabel_p) {
    StringBuilder sb = new StringBuilder(editLabel_p.getName());
    EObject container = editLabel_p.eContainer();
    while ((null != container) && (container instanceof IdentifiedElement)) {
      sb.insert(0, ((IdentifiedElement) container).getName() + "/"); //$NON-NLS-1$
      container = container.eContainer();
    }

    return sb.toString();
  }

  @SuppressWarnings("unchecked")
  public static <T extends EObject> List<T> allInstanceOf(Class<T> clazz, URI modelURI) throws IOException {
    ResourceSet resSet = new ResourceSetImpl();

    Resource res = resSet.createResource(modelURI);
    res.load(Collections.emptyMap());
    res.eAdapters().add(new ECrossReferenceAdapter());
    TreeIterator<EObject> iterator = res.getAllContents();

    List<T> result = new ArrayList<T>();
    while (iterator.hasNext()) {
      EObject elt = iterator.next();
      String Tname = clazz.getSimpleName();
      if (elt.eClass().getName().equals(Tname)) {
        result.add((T) elt);
      }
    }

    return result;
  }

  public static void compareEditLabelsForLayer(String layerName,
      Map<String, List<DirectEditLabel>> oldDirectEditLabelMap,
      Map<String, List<DirectEditLabel>> currentDirectEditLabelMap) {

    for (DirectEditLabel oldEditLabel : oldDirectEditLabelMap.get(layerName)) {
      DirectEditLabel newEditLabel = DirectEditLabelHelper
          .getLabelByNameAndPath(currentDirectEditLabelMap.get(layerName), oldEditLabel);
      if (!getModifiedEditLabel().contains(oldEditLabel.getName())) {
        assertNotNull("Direct edit label:" + DirectEditLabelHelper.computeModelPath(oldEditLabel) + " not found !", //$NON-NLS-1$//$NON-NLS-2$
            newEditLabel);

        EList<DiagramElementMapping> oldMappings = oldEditLabel.getMapping();
        EList<DiagramElementMapping> newMappings = newEditLabel.getMapping();
        assertTrue("Mapping list of direct edit label:" + DirectEditLabelHelper.computeModelPath(oldEditLabel) //$NON-NLS-1$
            + " has changed !", compareDirectEditLabelMapping(oldMappings, newMappings));
      }
    }
  }

  private static boolean compareDirectEditLabelMapping(EList<DiagramElementMapping> oldMapping_p,
      EList<DiagramElementMapping> newMapping_p) {
    // If there are less mappings in the new version it can lead to a regression
    if (newMapping_p.size() < oldMapping_p.size()) {
      return false;
    }
    ArrayList<String> newMappingNames = new ArrayList<String>();
    for (DiagramElementMapping m : newMapping_p) {
      newMappingNames.add(m.getName());
    }
    for (DiagramElementMapping m2 : oldMapping_p) {
      if (!newMappingNames.contains(m2.getName())) {
        return false;
      }

    }
    return true;
  }

  /**
   * 
   * @return the list of EditLabel tool that has been removed/modifed in the new version
   */
  private static List<String> getModifiedEditLabel() {
    // The tool has been moved to Common section (see Bug 2024 https://bugs.polarsys.org/show_bug.cgi?id=2024)
    return Arrays.asList("Constraint Content");
  }

  public static void checkEditLabelsHasMappings(String layerName,
      Map<String, List<DirectEditLabel>> currentDirectEditLabelMap) {

    for (DirectEditLabel editLabel : currentDirectEditLabelMap.get(layerName)) {
      EList<DiagramElementMapping> mappings = editLabel.getMapping();
      if (mappings.isEmpty()) {
        System.err.println("Layer: " + layerName + ", mapping list empty of direct edit label:"
            + DirectEditLabelHelper.computeModelPath(editLabel));
      }
    }
  }

  public static void checkMappingsHasEditLabels(String layerName,
      Map<String, List<DirectEditLabel>> currentDirectEditLabelMap) {

    List<Resource> odesigns = new ArrayList<Resource>();
    odesigns.add(getResource(layerName));
    List<DiagramDescription> diagramDescriptions = ODesignHelper.getAllDiagramDescription(odesigns);

    for (DiagramDescription dDescription : diagramDescriptions) {
      checkHasEditLabel(dDescription.getAllEdgeMappings());
      checkHasEditLabel(dDescription.getContainerMappings());
      checkHasEditLabel(dDescription.getNodeMappings());
      checkHasEditLabel(dDescription.getReusedMappings());
    }
  }

  public static void checkHasEditLabel(EList<? extends DiagramElementMapping> mappingsToCheck) {
    if (mappingsToCheck != null) {
      for (DiagramElementMapping mapping : mappingsToCheck) {
        if (mapping.getLabelDirectEdit() == null) {
          System.err.println("No DirectEditLabel for: " + mapping);
        }
      }
    }
  }

  public static List<DiagramElementMapping> getAllEditLabelMappings(String layerName,
      Map<String, List<DirectEditLabel>> currentDirectEditLabelMap) {
    List<DiagramElementMapping> allmappings = new ArrayList<DiagramElementMapping>();
    for (DirectEditLabel editLabel : currentDirectEditLabelMap.get(layerName)) {
      EList<DiagramElementMapping> mappings = editLabel.getMapping();
      allmappings.addAll(mappings);
    }
    return allmappings;
  }

  public static Resource getResource(String layerName) {
    ResourceSet resSet = new ResourceSetImpl();
    File folder = IResourceHelpers.getPluginFolder(FaServices.class);
    String path = folder.getAbsolutePath() + CURRENT_ODESIGN_PATH + layerName + ODESIGN_EXT;
    URI modelURI = URI.createFileURI(path);
    Resource res = resSet.createResource(modelURI);
    try {
      res.load(Collections.emptyMap());
    } catch (IOException e) {
      e.printStackTrace();
    }
    res.eAdapters().add(new ECrossReferenceAdapter());
    return res;
  }
}
