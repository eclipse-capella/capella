/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
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
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyLiteral;
import org.polarsys.capella.core.diagram.helpers.RepresentationAnnotationHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.PropertyPropagator;

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
		  
	  } else if (eObject instanceof DRepresentationDescriptor) {
	    EnumerationPropertyLiteral previousLiteral = RepresentationAnnotationHelper.getProgressStatus(((DRepresentationDescriptor) eObject));
		  if ((null == previousLiteral && null == literal) || (previousLiteral != null && literal != null && previousLiteral.equals(literal))) {
			  // Don't update if value unchanged.
			  return false;
		  }
		  RepresentationAnnotationHelper.setProgressStatus(((DRepresentationDescriptor) eObject), literal);
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
    if (eObject instanceof DRepresentationDescriptor) {
      if (RepresentationAnnotationHelper.hasStatusReview((DRepresentationDescriptor) eObject)) {
        return true;
      }
      if (RepresentationAnnotationHelper.hasProgressStatus((DRepresentationDescriptor) eObject)) {
        return true;
      }
    }
    return false;
  }

  @Override
  protected String getElementTag(EObject eObject) {
    if (eObject instanceof CapellaElement) {
      EnumerationPropertyLiteral status = ((CapellaElement) eObject).getStatus();
      return status != null ? status.getLabel() : null;
    } else if (eObject instanceof DRepresentationDescriptor) {
      EnumerationPropertyLiteral status = RepresentationAnnotationHelper.getProgressStatus((((DRepresentationDescriptor) eObject)));
      return status != null ? status.getLabel() : null;
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
    } else if (eobj instanceof DRepresentationDescriptor) {
    	if (RepresentationAnnotationHelper.getStatusReview((DRepresentationDescriptor) eobj) != null) {
        RepresentationAnnotationHelper.setStatusReview((DRepresentationDescriptor) eobj, null);
        return true;
    	}
    }
    return false;
  }
}
