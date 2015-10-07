/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.gmf.GMFDiffPolicy;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.sequence.ordering.OrderingPackage;
import org.eclipse.sirius.viewpoint.ViewpointPackage;


/**
 * A diff policy for Sirius models.
 */
public class SiriusDiffPolicy extends GMFDiffPolicy {
  
  /** The set of references whose order should be ignored
     (semantically unordered references) */
  private static final Collection<EReference> SEMANTICALLY_UNORDERED_REFERENCES =
    Arrays.asList(
        DiagramPackage.eINSTANCE.getDDiagram_ActivatedLayers(),
        DiagramPackage.eINSTANCE.getDDiagram_OwnedDiagramElements(),
        ViewpointPackage.eINSTANCE.getDAnalysis_Models(),
        ViewpointPackage.eINSTANCE.getDAnalysis_OwnedViews(),
        ViewpointPackage.eINSTANCE.getDRepresentationElement_SemanticElements(),
        DiagramPackage.eINSTANCE.getEdgeTarget_IncomingEdges(),
        DiagramPackage.eINSTANCE.getEdgeTarget_OutgoingEdges()
    );

  /** The set of references that can be ignored */
  private static final Collection<EReference> UNSIGNIFICANT_REFERENCES =
      Arrays.asList(
          ViewpointPackage.eINSTANCE.getDRepresentation_UiState()
      );
  
  /** The set of types that can be ignored */
  private static final Collection<EClass> UNSIGNIFICANT_TYPES =
      Arrays.asList(
          OrderingPackage.eINSTANCE.getEventEndsOrdering(),
          OrderingPackage.eINSTANCE.getInstanceRolesOrdering(),
          ViewpointPackage.eINSTANCE.getUIState()
      );
  
  /** The set of String attributes for which the empty string value must not be
      distinguished from the null value */
  private static final Collection<EAttribute> IGNORING_EMPTY_STRING_ATTRIBUTES =
    Arrays.asList(
        DiagramPackage.eINSTANCE.getDDiagramElement_TooltipText()
    );
  
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.policies.DefaultDiffPolicy#coverFeature(org.eclipse.emf.ecore.EStructuralFeature)
   */
  @Override
  public boolean coverFeature(EStructuralFeature feature_p) {
    return !UNSIGNIFICANT_REFERENCES.contains(feature_p) && super.coverFeature(feature_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.policies.DefaultDiffPolicy#coverMatch(org.eclipse.emf.diffmerge.api.IMatch)
   */
  @Override
  public boolean coverMatch(IMatch match_p) {
    boolean result = super.coverMatch(match_p);
    if (result && match_p.isPartial()) {
      // Ignore certain transient elements (OK because no cross-ref)
      EObject element = match_p.get(match_p.getUncoveredRole().opposite());
      if (element != null)
        result = !UNSIGNIFICANT_TYPES.contains(element.eClass());
    }
    return result;
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
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.policies.ConfigurableDiffPolicy#doConsiderOrdered(org.eclipse.emf.ecore.EStructuralFeature)
   */
  @Override
  protected boolean doConsiderOrdered(EStructuralFeature feature_p) {
    return super.doConsiderOrdered(feature_p) &&
        !SEMANTICALLY_UNORDERED_REFERENCES.contains(feature_p);
  }
  
}
