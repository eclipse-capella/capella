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

import java.util.Arrays;
import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.gmf.GMFDiffPolicy;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.business.api.resource.ResourceDescriptor;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.sequence.ordering.OrderingPackage;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * A diff policy for Sirius models.
 */
public class SiriusDiffPolicy extends GMFDiffPolicy {
	
	/**
	 * Separator
	 */
	private static final char SEGMENT_SEPARATOR = '/';

	/**
	 * The set of references whose order should be ignored (semantically
	 * unordered references)
	 */
	private static final Collection<EReference> SEMANTICALLY_UNORDERED_REFERENCES = Arrays
			.asList(DiagramPackage.eINSTANCE.getDDiagram_ActivatedLayers(),
					DiagramPackage.eINSTANCE.getDDiagram_OwnedDiagramElements(),
					ViewpointPackage.eINSTANCE.getDAnalysis_Models(),
					ViewpointPackage.eINSTANCE.getDAnalysis_OwnedViews(),
					ViewpointPackage.eINSTANCE
							.getDRepresentationElement_SemanticElements(),
					DiagramPackage.eINSTANCE.getEdgeTarget_IncomingEdges(),
					DiagramPackage.eINSTANCE.getEdgeTarget_OutgoingEdges());

	/** The set of references that can be ignored */
	private static final Collection<EReference> UNSIGNIFICANT_REFERENCES = Arrays
			.asList(ViewpointPackage.eINSTANCE.getDRepresentation_UiState(),
					DiagramPackage.eINSTANCE.getDDiagram_HiddenElements());

	/** The set of types that can be ignored */
	private static final Collection<EClass> UNSIGNIFICANT_TYPES = Arrays
			.asList(OrderingPackage.eINSTANCE.getEventEndsOrdering(),
					OrderingPackage.eINSTANCE.getInstanceRolesOrdering(),
					ViewpointPackage.eINSTANCE.getUIState());

	/**
	 * The set of String attributes for which the empty string value must not be
	 * distinguished from the null value
	 */
	private static final Collection<EAttribute> IGNORING_EMPTY_STRING_ATTRIBUTES = Arrays
			.asList(DiagramPackage.eINSTANCE.getDDiagramElement_TooltipText());

	/**
	 * @see org.eclipse.emf.diffmerge.impl.policies.DefaultDiffPolicy#coverFeature(org.eclipse.emf.ecore.EStructuralFeature)
	 */
	@Override
	public boolean coverFeature(EStructuralFeature feature) {
		return !UNSIGNIFICANT_REFERENCES.contains(feature)
				&& super.coverFeature(feature);
	}

	/**
	 * @see org.eclipse.emf.diffmerge.impl.policies.DefaultDiffPolicy#coverMatch(org.eclipse.emf.diffmerge.api.IMatch)
	 */
	@Override
	public boolean coverMatch(IMatch match) {
		boolean result = super.coverMatch(match);
		if (result && match.isPartial()) {
			// Ignore certain transient elements (OK because no cross-ref)
			EObject element = match
					.get(match.getUncoveredRole().opposite());
			if (element != null)
				result = !UNSIGNIFICANT_TYPES.contains(element.eClass());
		}
		return result;
	}

	/**
	 * @see org.eclipse.emf.diffmerge.impl.policies.DefaultDiffPolicy#coverValue(java.lang.Object,
	 *      org.eclipse.emf.ecore.EAttribute)
	 */
	@Override
	public boolean coverValue(Object value, EAttribute attribute) {
		boolean result;
		if (IGNORING_EMPTY_STRING_ATTRIBUTES.contains(attribute)
				&& ((String) value).length() == 0)
			result = false;
		else
			result = super.coverValue(value, attribute);
		return result;
	}

	/**
	 * @see org.eclipse.emf.diffmerge.impl.policies.ConfigurableDiffPolicy#doConsiderOrdered(org.eclipse.emf.ecore.EStructuralFeature)
	 */
	@Override
	protected boolean doConsiderOrdered(EStructuralFeature feature) {
		return super.doConsiderOrdered(feature)
				&& !SEMANTICALLY_UNORDERED_REFERENCES.contains(feature);
	}

	/**
   * 
   */
	@Override
	public boolean considerEqual(Object value1, Object value2,
			EAttribute attribute) {
		boolean result = super.considerEqual(value1, value2, attribute);
		if (!result
				&& ViewpointPackage.eINSTANCE.getDAnalysis_SemanticResources() == attribute) {
			
			// Get URI(s)
			URI refURI = ((ResourceDescriptor) value1).getResourceURI();
			URI trgtURI = ((ResourceDescriptor) value2).getResourceURI();
			
			// Get the position of project name in URI segments
			int positionInRefURI = getProjectPositionInURI(refURI);
			int positionInTrgtURI = getProjectPositionInURI(trgtURI);
			
			// Either make it relative or convert to string
			String refURIStr = positionInRefURI != -1 ?  makeRelativeToProject(refURI, positionInRefURI) : refURI.toString();			
			String trgtURIStr = positionInTrgtURI != -1 ? makeRelativeToProject(trgtURI, positionInTrgtURI) : trgtURI.toString();
			return refURIStr.equals(trgtURIStr);
		}
		return result;
	}

	/**
	 * 
	 * @param uri
	 *            The uri to make relative
	 * @param projectPositionInURI
	 *            The position of the segment which correspond to the project
	 *            name in the given uri
	 * 
	 * @return the relative uri as String
	 */
	protected String makeRelativeToProject(URI uri, int projectPositionInURI) {
		StringBuilder result = new StringBuilder();
		for (int i = projectPositionInURI; i < uri.segments().length; i++) {
			result.append(SEGMENT_SEPARATOR);
			result.append(uri.segment(i));
		}
		return result.toString();
	}

	/**
	 * 
	 * @return The position of the segment which correspond to the project name.
	 */
	protected int getProjectPositionInURI(URI uri) {
		if(uri.isPlatformResource()){
			return 2;			
		}
		return -1;
	}
}
