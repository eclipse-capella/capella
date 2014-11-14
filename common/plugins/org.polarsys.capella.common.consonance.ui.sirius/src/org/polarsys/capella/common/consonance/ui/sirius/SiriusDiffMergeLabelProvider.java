/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.consonance.ui.sirius;

import java.util.List;

import org.eclipse.emf.diffmerge.ui.gmf.GMFDiffMergeLabelProvider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.ContainerStyle;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.NodeStyle;
import org.eclipse.sirius.viewpoint.BasicLabelStyle;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentationContainer;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.description.Viewpoint;


/**
 * A custom label provider for comparisons encompassing Sirius elements.
 */
public class SiriusDiffMergeLabelProvider extends GMFDiffMergeLabelProvider {
  
  /** The instance of this class (singleton pattern) */
  private static SiriusDiffMergeLabelProvider __instance = null;
  
  /**
   * Return the instance of this class (singleton pattern)
   * @return a non-null object
   */
  public static SiriusDiffMergeLabelProvider getInstance() {
    if (__instance == null)
      __instance = new SiriusDiffMergeLabelProvider();
    return __instance;
  }
  
  
  /**
   * Constructor
   */
  public SiriusDiffMergeLabelProvider() {
    // Nothing needed
  }
  
  /**
   * Return a label for the given representation element
   * @param representationElement_p a non-null representation element
   * @return a non-null string
   */
  protected String getRepresentationElementText(
      DRepresentationElement representationElement_p) {
    String result = getExplicitlyTypedElementText(
        representationElement_p.getName(), representationElement_p.getMapping());
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.util.DiffMergeLabelProvider#getText(java.lang.Object)
   */
  @Override
  public String getText(Object element_p) {
    String result = null;
    // ****** Viewpoint
    if (element_p instanceof DAnalysis) {
      DAnalysis analysis = (DAnalysis)element_p;
      List<EObject> models = analysis.getModels();
      if (!models.isEmpty())
        result = getText(models.get(0));
      else
        result = super.getText(analysis);
    } else if (element_p instanceof DRepresentationContainer) {
      DRepresentationContainer representationContainer = (DRepresentationContainer)element_p;
      Viewpoint viewpoint = representationContainer.getViewpoint();
      if (viewpoint != null)
        result = viewpoint.getName();
    } else if (element_p instanceof DRepresentationElement) {
      result = getRepresentationElementText((DRepresentationElement)element_p);
    } else if (element_p instanceof NodeStyle) {
      result = ((EObject)element_p).eClass().getName() + " " + //$NON-NLS-1$
          formatTechnicalName(DiagramPackage.eINSTANCE.getNodeStyle().getName());
    } else if (element_p instanceof ContainerStyle || element_p instanceof EdgeStyle ||
        element_p instanceof BasicLabelStyle) {
      result = getManyQualifiedElementText((EObject)element_p);
    } else if (element_p instanceof RGBValues) {
      result = getContainmentText((EObject)element_p);
    }
    if (result == null)
      result = super.getText(element_p);
    return result;
  }
  
}
