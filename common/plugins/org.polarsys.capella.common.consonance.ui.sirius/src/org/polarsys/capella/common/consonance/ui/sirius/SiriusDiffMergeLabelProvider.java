/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
   * @param representationElement a non-null representation element
   * @return a non-null string
   */
  protected String getRepresentationElementText(
      DRepresentationElement representationElement) {
    String result = getExplicitlyTypedElementText(
        representationElement.getName(), representationElement.getMapping());
    return result;
  }
  
  /**
   * Return a label for the given RGBValues element
   * @param element a non-null RGBValues element
   * @return a non-null string
   */
  protected String getRGBValuesText(RGBValues element) {
    StringBuilder builder = new StringBuilder();
    builder.append('(');
    builder.append(element.getRed());
    builder.append(',');
    builder.append(element.getGreen());
    builder.append(',');
    builder.append(element.getBlue());
    builder.append(')');
    return builder.toString();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.util.DiffMergeLabelProvider#getText(java.lang.Object)
   */
  @Override
  public String getText(Object element) {
    String result = null;
    // ****** Viewpoint
    if (element instanceof DAnalysis) {
      DAnalysis analysis = (DAnalysis)element;
      List<EObject> models = analysis.getModels();
      if (!models.isEmpty())
        result = getText(models.get(0));
      else
        result = super.getText(analysis);
    } else if (element instanceof DRepresentationContainer) {
      DRepresentationContainer representationContainer = (DRepresentationContainer)element;
      Viewpoint viewpoint = representationContainer.getViewpoint();
      if (viewpoint != null) {
        result = viewpoint.getLabel();
        if (result == null)
          result = viewpoint.getName();
      }
    } else if (element instanceof DRepresentationElement) {
      result = getRepresentationElementText((DRepresentationElement)element);
    } else if (element instanceof NodeStyle) {
      result = ((EObject)element).eClass().getName() + " " + //$NON-NLS-1$
          formatTechnicalName(DiagramPackage.eINSTANCE.getNodeStyle().getName());
    } else if (element instanceof ContainerStyle || element instanceof EdgeStyle ||
        element instanceof BasicLabelStyle) {
      result = getManyQualifiedElementText((EObject)element);
    } else if (element instanceof RGBValues) {
      result = getRGBValuesText((RGBValues)element);
    }
    if (result == null)
      result = super.getText(element);
    return result;
  }
  
}
