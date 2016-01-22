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
package org.polarsys.capella.core.ui.metric.utils;

import java.util.Collection;

import org.eclipse.emf.ecore.EAttribute;
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
  
  @Override
  public boolean isTaggableElement(EObject element) {
    return element instanceof DRepresentation || super.isTaggableElement(element);
  }

  /**
   * @param literal
   * @param eObject
   * @return
   */
  @Override
  protected boolean tagElement(EnumerationPropertyLiteral literal, EObject eObject) {
	  if (eObject instanceof CapellaElement) {
		  EnumerationPropertyLiteral previousLiteral = ((CapellaElement) eObject).getStatus();
		  if ((null == previousLiteral && null == literal) || (previousLiteral!= null && previousLiteral.equals(literal))) {
			  // Don't update if value unchanged.
			  return false;
		  }

		  if (literal == null) {
			  ((CapellaElement) eObject).eUnset(CapellacorePackage.eINSTANCE.getCapellaElement_Status());
		  } else {
			  ((CapellaElement) eObject).setStatus(literal);
		  }
		  return true;
	  } else if (eObject instanceof DRepresentation) {
		  String previousLiteral = RepresentationAnnotationHelper.getProgressStatus(((DRepresentation) eObject));
		  if ((null == previousLiteral && null == literal) || (previousLiteral != null && literal != null && previousLiteral.equals(literal.getLabel()))) {
			  // Don't update if value unchanged.
			  return false;
		  }
		  String value = literal == null ? null : literal.getLabel();
		  RepresentationAnnotationHelper.setProgressStatus(((DRepresentation) eObject), value);
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
    return ((eObject instanceof CapellaElement) && ((null != ((CapellaElement) eObject).getStatus()) || ((null != ((CapellaElement) eObject)
        .getReview()) && (!((CapellaElement) eObject).getReview().isEmpty()))));
  }

  /**
   * @param eObject
   * @return
   */
  @Override
  protected boolean isTaggedRepresentation(EObject eObject) {
    if (eObject instanceof DRepresentation) {
      String eAnnotStatus = IRepresentationAnnotationConstants.ProgressStatus;
      DAnnotation dAnnotationStatus = RepresentationHelper.getAnnotation(eAnnotStatus, (DRepresentation) eObject);

      String eAnnotReview = IRepresentationAnnotationConstants.StatusReview;
      DAnnotation dAnnotationReview = RepresentationHelper.getAnnotation(eAnnotReview, (DRepresentation) eObject);

      return ((null != dAnnotationStatus) && (null != dAnnotationStatus.getDetails().get("value")) || (null != dAnnotationReview)
          && (null != dAnnotationReview.getDetails().get("value")));
    }
    return false;
  }

  @Override
  protected String getElementTag(EObject eObject) {
    if (eObject instanceof CapellaElement) {
      EnumerationPropertyLiteral status = ((CapellaElement) eObject).getStatus();
      return status != null ? status.getLabel() : null;
    } else if (eObject instanceof DRepresentation) {
      String value = RepresentationAnnotationHelper.getProgressStatus((((DRepresentation) eObject)));
      return value == "" ? null : value;
    }
    return null;
  }
  
  @Override
  protected boolean cleanReview(EObject eobj) {
    if (eobj instanceof CapellaElement) {
    	EAttribute reviewFeature = CapellacorePackage.eINSTANCE.getCapellaElement_Review();
    	if (eobj.eIsSet(reviewFeature)) {
    		eobj.eUnset(reviewFeature);
    		return true;
    	}
    } else if (eobj instanceof DRepresentation) {
    	String statusReview = IRepresentationAnnotationConstants.StatusReview;
    	if (null!=RepresentationHelper.getAnnotation(statusReview, (DRepresentation) eobj)) {
    		RepresentationHelper.removeAnnotation(statusReview, (DRepresentation) eobj);
    		return true;
    	}
    }
    return false;
  }
}
