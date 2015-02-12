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
package org.polarsys.capella.core.ui.metric.utils;

import java.util.Collection;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyLiteral;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.PropertyPropagator;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.ui.properties.annotations.IRepresentationAnnotationConstants;

/**
 * Utility class for Progress Monitoring
 */
public class ProgressMonitoringPropagator extends PropertyPropagator {

  /**
   * 
   */
  private static ProgressMonitoringPropagator _instance;

  /**
   * 
   */
  static private Collection<EClass> _directTypes = null;
  static private Collection<EClass> _withSpecializationTypes = null;

  /**
   * @return a unique instance of this class
   */
  public static ProgressMonitoringPropagator getInstance() {
    if (null == _instance) {
      _instance = new ProgressMonitoringPropagator();
    }
    return _instance;
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
    if ( null == _directTypes ) {
      _directTypes = super.getDirectTypes();
    }
    return _directTypes;
  }

  /**
   * @return
   */
  @Override
  protected Collection<EClass> getWithSpecializationType() {
    if (null == _withSpecializationTypes) {
      _withSpecializationTypes = super.getWithSpecializationType();
    }
    return _withSpecializationTypes;
  }

  /**
   * @return
   */
  @Override
  protected String getKeyword() {
    return CapellaProjectHelper.PROGRESS_STATUS_KEYWORD;
  }

  /**
   * @param literal_p
   * @param eObject_p
   * @return
   */
  @Override
  protected boolean tagElement(EnumerationPropertyLiteral literal_p, EObject eObject_p) {
    if (eObject_p instanceof CapellaElement) {
      ((CapellaElement) eObject_p).setStatus(literal_p);
      return true;
    }
    return false;
  }

  /**
   * @param eObject_p
   * @return
   */
  @Override
  protected boolean isTagged(EObject eObject_p) {
    return ((eObject_p instanceof CapellaElement) && (null != ((CapellaElement) eObject_p).getStatus()));
  }
  
  /**
   * @param eObject_p
   * @return
   */
  @Override
  protected boolean isTaggedDiagram(EObject eObject_p) {
	String eAnnot= IRepresentationAnnotationConstants.ProgressStatus;
	DAnnotation dAnnotation= RepresentationHelper.getAnnotation(eAnnot, (DRepresentation) eObject_p);
    return ((eObject_p instanceof DDiagram) && (null != dAnnotation.getDetails().get("value")));
  }
}
