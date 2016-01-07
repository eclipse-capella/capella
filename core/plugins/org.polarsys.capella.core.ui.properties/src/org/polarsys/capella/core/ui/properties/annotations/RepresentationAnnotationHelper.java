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
package org.polarsys.capella.core.ui.properties.annotations;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;

/**
 * Define DRepresentation annotation helpers.
 * 
 */
public class RepresentationAnnotationHelper {

	/**
	 * @param representation
	 */
	public static boolean isVisibleInDoc(DRepresentation representation) {
		return RepresentationHelper.getAnnotation(
				IRepresentationAnnotationConstants.NotVisibleInDoc,
				representation) == null ? true : false;
	}

	/**
	 * @param representation
	 */
	public static boolean isVisibleInLM(DRepresentation representation) {
		return RepresentationHelper.getAnnotation(
				IRepresentationAnnotationConstants.NotVisibleInLM,
				representation) == null ? true : false;
	}

	/**
	 * @param representation
	 */
	public static String getProgressStatus(DRepresentation representation) {
		DAnnotation annotation = RepresentationHelper.getAnnotation(
				IRepresentationAnnotationConstants.ProgressStatus,
				representation);
		if (null != annotation) {
			String value = annotation.getDetails().get(
					IRepresentationAnnotationConstants.PROGRESS_VALUE_KEYVALUE);
			if (null != value) {
				return value;
			}
		}
		return ICommonConstants.EMPTY_STRING;
	}
  
	public static void setProgressStatus(DRepresentation representation,
			String value) {
		if (representation != null) {
			setAnnotation(representation,
					IRepresentationAnnotationConstants.ProgressStatus,
					IRepresentationAnnotationConstants.PROGRESS_VALUE_KEYVALUE,
					value);
		}
	}

	/**
	 * @param representation
	 */
	public static String getStatusReview(DRepresentation representation) {
		DAnnotation annotation = RepresentationHelper
				.getAnnotation(IRepresentationAnnotationConstants.StatusReview,
						representation);
		if (null != annotation) {
			String value = annotation.getDetails().get(
					IRepresentationAnnotationConstants.REVIEW_VALUE_KEYVALUE);
			if (null != value) {
				return value;
			}
		}
		return ICommonConstants.EMPTY_STRING;
	}

	/**
	 * @param representation
	 * @param source
	 * @param value
	 */
	public static void setAnnotation(DRepresentation representation,
			String source, Boolean value) {
		if (!value.booleanValue()) {
			DAnnotation annotation = RepresentationHelper.getAnnotation(source,
					representation);
			if (null == annotation) {
				RepresentationHelper.createAnnotation(source, representation);
			}
		} else {
			RepresentationHelper.removeAnnotation(source, representation);
		}
	}

	/**
	 * @param representation
	 * @param source
	 * @param value
	 */
	public static void setAnnotation(DRepresentation representation,
			String source, String key, String value) {
		DAnnotation annotation = RepresentationHelper.getAnnotation(source,
				representation);
		if (null != value) {
			if (null == annotation) {
				annotation = RepresentationHelper.createAnnotation(source,
						representation);
			}
			String oldValue = annotation.getDetails().get(key);
			annotation.getDetails().put(key, value);
			// Send just a fake notification to enable UI update
			representation.eNotify(new ENotificationImpl(
					(InternalEObject) representation, Notification.SET,
					ViewpointPackage.DREPRESENTATION__NAME, oldValue, value));
		} else {
			RepresentationHelper.removeAnnotation(source, representation);
			// Send just a fake notification to enable UI update
			representation.eNotify(new ENotificationImpl(
					(InternalEObject) representation, Notification.UNSET,
					ViewpointPackage.DREPRESENTATION__NAME, null, null));
		}
	}
}
