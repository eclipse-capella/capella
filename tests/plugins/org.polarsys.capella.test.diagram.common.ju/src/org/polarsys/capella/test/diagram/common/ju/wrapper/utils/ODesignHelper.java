/*******************************************************************************
 * Copyright (c) 2006, 2016, 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.common.ju.wrapper.utils;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.polarsys.capella.test.framework.helpers.EObjectHelper;

/**
 * Helper class for odesign
 */
public class ODesignHelper {

  public final static String ODESIGN_EXT = "odesign"; //$NON-NLS-1$

  /**
   * Return all odesign {@link Resource} from a given {@link ResourceSet}
   * 
   * @param resourceSet
   * @return a {@link List} of resources if found, an empty one otherwise.
   */
  public static List<Resource> getAvailableODesignFile(ResourceSet resourceSet) {

    List<Resource> resources = new ArrayList<Resource>();

    for (Resource resource : resourceSet.getResources()) {

      if (resource.getURI().toString().endsWith(ODESIGN_EXT)) {
        resources.add(resource);
      }
    }

    return resources;
  }

  /**
   * Get all the {@link DiagramDescription} defined into a given odesign {@link Resource}
   * 
   * @param resources
   * @return
   */
  public static List<DiagramDescription> getAllDiagramDescription(List<Resource> resources) {

    List<DiagramDescription> result = new ArrayList<DiagramDescription>();

    for (Resource current : resources) {
      result.addAll(getAllDiagramDescription(current));
    }

    return result;
  }

  /**
   * Get all the {@link DiagramDescription} defined into a given {@link List} of odesign {@link Resource}.
   * 
   * @param resource
   * @return
   */
  public static List<DiagramDescription> getAllDiagramDescription(Resource resource) {

    List<DiagramDescription> result = new ArrayList<DiagramDescription>();

    EObject root = resource.getContents().get(0);
    EClass eClass = DescriptionPackage.Literals.DIAGRAM_DESCRIPTION;

    for (EObject current : EObjectHelper.getAllEObjectOfType(root, eClass, true)) {
      result.add((DiagramDescription) current);
    }

    return result;
  }

  /**
   * 
   * @param mappingToolDescription
   * @return path of the element in odesign
   */
  public static String computeModelPath(DiagramElementMapping mappingToolDescription) {
    StringBuilder sb = new StringBuilder(mappingToolDescription.getName());
    EObject container = mappingToolDescription.eContainer();
    while ((null != container) && (container instanceof IdentifiedElement)) {
      sb.insert(0, ((IdentifiedElement) container).getName() + "/"); //$NON-NLS-1$
      container = container.eContainer();
    }
    return sb.toString();
  }

}
