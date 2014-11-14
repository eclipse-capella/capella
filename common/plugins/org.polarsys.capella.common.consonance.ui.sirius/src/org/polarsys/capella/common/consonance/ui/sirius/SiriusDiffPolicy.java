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

import java.util.Arrays;
import java.util.Collection;

import org.eclipse.emf.diffmerge.gmf.GMFDiffPolicy;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.viewpoint.ViewpointPackage;


/**
 * A diff policy for Sirius models.
 */
public class SiriusDiffPolicy extends GMFDiffPolicy {
  
  /** The set of references whose order should be ignored
     (semantically unordered references) */
  private static final Collection<EReference> SEMANTICALLY_UNORDERED_REFERENCES =
    Arrays.asList(
        ViewpointPackage.eINSTANCE.getDDiagram_ActivatedLayers(),
        ViewpointPackage.eINSTANCE.getDDiagram_OwnedDiagramElements(),
        ViewpointPackage.eINSTANCE.getDAnalysis_Models(),
        ViewpointPackage.eINSTANCE.getDAnalysis_OwnedViews(),
        ViewpointPackage.eINSTANCE.getDRepresentationElement_SemanticElements(),
        ViewpointPackage.eINSTANCE.getEdgeTarget_IncomingEdges(),
        ViewpointPackage.eINSTANCE.getEdgeTarget_OutgoingEdges()
    );
  
  /** The set of String attributes for which the empty string value must not be
      distinguished from the null value */
  private static final Collection<EAttribute> IGNORING_EMPTY_STRING_ATTRIBUTES =
    Arrays.asList(
        ViewpointPackage.eINSTANCE.getDDiagramElement_TooltipText()
    );
  
  
  /**
   * @see org.polarsys.capella.common.compare.policies.DefaultDiffPolicy#considerOrdered(EStructuralFeature)
   */
  @Override
  public boolean considerOrdered(EStructuralFeature feature_p) {
    return super.considerOrdered(feature_p) &&
      !SEMANTICALLY_UNORDERED_REFERENCES.contains(feature_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.policies.DefaultDiffPolicy#coverValue(java.lang.Object, org.eclipse.emf.ecore.EAttribute)
   */
  @Override
  public boolean coverValue(Object value_p, EAttribute attribute_p) {
    boolean result;
    if (IGNORING_EMPTY_STRING_ATTRIBUTES.contains(attribute_p)
        && ((String)value_p).length() == 0)
      result = false;
    else
      result = super.coverValue(value_p, attribute_p);
    return result;
  }
  
}
