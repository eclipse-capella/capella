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
package org.polarsys.capella.core.ui.metric.utils;

import java.util.Collection;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyLiteral;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.PropertyPropagator;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.ui.properties.annotations.IRepresentationAnnotationConstants;
import org.polarsys.capella.core.ui.properties.annotations.RepresentationAnnotationHelper;

/**
 * Utility class for Progress Monitoring
 */
public class ProgressMonitoringPropagator extends PropertyPropagator {

	/**
   * 
   */
	private static ProgressMonitoringPropagator instance;

	/**
   * 
   */
	static private Collection<EClass> directTypes = null;
	static private Collection<EClass> withSpecializationTypes = null;

	/**
	 * @return a unique instance of this class
	 */
	public static ProgressMonitoringPropagator getInstance() {
		if (null == instance) {
			instance = new ProgressMonitoringPropagator();
		}
		return instance;
	}

	/**
	 * Constructor is private because it's a singleton
	 */
	private ProgressMonitoringPropagator() {
		// do nothing
	}

	/**
	 * @return
	 */
	@Override
	protected Collection<EClass> getDirectTypes() {
		if (null == directTypes) {
			directTypes = super.getDirectTypes();
		}
		return directTypes;
	}

	/**
	 * @return
	 */
	@Override
	protected Collection<EClass> getWithSpecializationType() {
		if (null == withSpecializationTypes) {
			withSpecializationTypes = super.getWithSpecializationType();
		}
		return withSpecializationTypes;
	}

	/**
	 * @return
	 */
	@Override
	protected String getKeyword() {
		return CapellaProjectHelper.PROGRESS_STATUS_KEYWORD;
	}

	public boolean isTaggableElement(EObject element) {
		return element instanceof DRepresentation
				|| super.isTaggableElement(element);
	}

	/**
	 * @param literal
	 * @param eObject
	 * @return
	 */
	@Override
	protected boolean tagElement(EnumerationPropertyLiteral literal,
			EObject eObject) {
		if (eObject instanceof CapellaElement) {
			if (literal == null) {
				((CapellaElement) eObject).eUnset(CapellacorePackage.eINSTANCE
						.getCapellaElement_Status());
			} else {
				((CapellaElement) eObject).setStatus(literal);
			}
			return true;
		} else if (eObject instanceof DRepresentation) {
			String value = literal == null ? null : literal.getLabel();
			RepresentationAnnotationHelper.setProgressStatus(
					((DRepresentation) eObject), value);
			return true;
		}
		return false;
	}

	/**
	 * @param eObject
	 * @return
	 */
	@Override
	protected boolean isTagged(EObject eObject) {
		return ((eObject instanceof CapellaElement) && ((null != ((CapellaElement) eObject)
				.getStatus()) || (null != ((CapellaElement) eObject)
				.getReview())));
	}

	/**
	 * @param eObject
	 * @return
	 */
	@Override
	protected boolean isTaggedRepresentation(EObject eObject) {
		if (eObject instanceof DRepresentation) {
			String eAnnotStatus = IRepresentationAnnotationConstants.ProgressStatus;
			DAnnotation dAnnotationStatus = RepresentationHelper.getAnnotation(
					eAnnotStatus, (DRepresentation) eObject);

			String eAnnotReview = IRepresentationAnnotationConstants.StatusReview;
			DAnnotation dAnnotationReview = RepresentationHelper.getAnnotation(
					eAnnotReview, (DRepresentation) eObject);

			return ((null != dAnnotationStatus)
					&& (null != dAnnotationStatus.getDetails().get("value")) || (null != dAnnotationReview)
					&& (null != dAnnotationReview.getDetails().get("value")));
		}
		return false;
	}
}
