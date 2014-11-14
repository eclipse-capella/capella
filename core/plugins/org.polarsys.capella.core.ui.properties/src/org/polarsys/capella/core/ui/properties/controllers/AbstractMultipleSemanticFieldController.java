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
package org.polarsys.capella.core.ui.properties.controllers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;

/**
 * Base class to implement {@link IMultipleSemanticFieldController}.
 */
public abstract class AbstractMultipleSemanticFieldController extends AbstractSemanticFieldController implements IMultipleSemanticFieldController {
  /**
   * Do the add operation in {@link #writeOpenValues(CapellaElement, EStructuralFeature, List)}
   * @param semanticElement_p
   * @param semanticFeature_p
   * @param object_p
   */
  @SuppressWarnings("unchecked")
  protected void doAddOperationInWriteOpenValues(CapellaElement semanticElement_p, EStructuralFeature semanticFeature_p, EObject object_p) {
    ((List<EObject>) semanticElement_p.eGet(semanticFeature_p)).add(object_p);
  }

  /**
   * Do the remove operation in {@link #writeOpenValues(CapellaElement, EStructuralFeature, List)}
   * @param semanticElement_p
   * @param semanticFeature_p
   * @param object_p
   */
  @SuppressWarnings("unchecked")
  protected void doRemoveOperationInWriteOpenValues(CapellaElement semanticElement_p, EStructuralFeature semanticFeature_p, EObject object_p) {
    if ((semanticFeature_p instanceof EReference) && ((EReference) semanticFeature_p).isContainment()) {
      AbstractSemanticField.deleteContainmentValue(object_p);
    } else {
      ((List<EObject>) semanticElement_p.eGet(semanticFeature_p)).remove(object_p);
    }
  }

  /**
   * Get the business query used by {@link #readOpenValues(CapellaElement, EStructuralFeature, boolean)}
   * @param semanticElement_p
   * @return could be <code>null</code> if no appropriate query for given element.
   */
  protected abstract IBusinessQuery getReadOpenValuesQuery(CapellaElement semanticElement_p);

  /**
   * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.widgets.SimpleEditableSemanticField#loadData(org.polarsys.capella.core.data.information.datatype.BooleanType,
   *      org.eclipse.emf.ecore.EReference)
   */
  public List<EObject> loadValues(CapellaElement semanticElement_p, EStructuralFeature semanticFeature_p) {
    List<EObject> result = new ArrayList<EObject>();
    List<?> values = (List<?>) semanticElement_p.eGet(semanticFeature_p);
    if (null != values) {
      for (Object value : values) {
        result.add((EObject) value);
      }
    }
    return result;

  }

  /**
   * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.widgets.SimpleEditableSemanticField#readOpenValues()
   */
  public List<EObject> readOpenValues(CapellaElement semanticElement_p, EStructuralFeature semanticFeature_p, boolean availableElements_p) {
    // Instantiate a new resulting list to avoid concurrent exceptions.
    List<EObject> result = new ArrayList<EObject>(0);
    // query for 'super'
    IBusinessQuery query = getReadOpenValuesQuery(semanticElement_p);
    if (null != query) {
      List<CapellaElement> capellaElements = null;
      if (availableElements_p) {
        capellaElements = query.getAvailableElements(semanticElement_p);
      } else {
        capellaElements = query.getCurrentElements(semanticElement_p, false);
      }
      result.addAll(capellaElements);
    }
    return result;
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.controllers.custom.properties.controllers.IMultipleSemanticFieldController#writeOpenValues(org.polarsys.capella.core.data.capellacore.CapellaElement,
   *      org.eclipse.emf.ecore.EStructuralFeature, java.util.List)
   */
  public List<EObject> writeOpenValues(CapellaElement semanticElement_p, EStructuralFeature semanticFeature_p, List<EObject> values_p) {
    List<EObject> result = new ArrayList<EObject>();
    if (null != values_p) {
      List<EObject> modelCurrentList = readOpenValues(semanticElement_p, semanticFeature_p, false);
      for (EObject currentModelObject : modelCurrentList) {
        if (!values_p.contains(currentModelObject)) {
          doRemoveOperationInWriteOpenValues(semanticElement_p, semanticFeature_p, currentModelObject);
        }
      }
      for (EObject currentObject : values_p) {
        if (!modelCurrentList.contains(currentObject)) {
          doAddOperationInWriteOpenValues(semanticElement_p, semanticFeature_p, currentObject);
        }
        result.add(currentObject);
      }
    }
    return result;
  }
}
