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
   * @param semanticElement
   * @param semanticFeature
   * @param object
   */
  @SuppressWarnings("unchecked")
  protected void doAddOperationInWriteOpenValues(CapellaElement semanticElement, EStructuralFeature semanticFeature, EObject object) {
    ((List<EObject>) semanticElement.eGet(semanticFeature)).add(object);
  }

  /**
   * Do the remove operation in {@link #writeOpenValues(CapellaElement, EStructuralFeature, List)}
   * @param semanticElement
   * @param semanticFeature
   * @param object
   */
  @SuppressWarnings("unchecked")
  protected void doRemoveOperationInWriteOpenValues(CapellaElement semanticElement, EStructuralFeature semanticFeature, EObject object) {
    if ((semanticFeature instanceof EReference) && ((EReference) semanticFeature).isContainment()) {
      AbstractSemanticField.deleteContainmentValue(object);
    } else {
      ((List<EObject>) semanticElement.eGet(semanticFeature)).remove(object);
    }
  }

  /**
   * Get the business query used by {@link #readOpenValues(CapellaElement, EStructuralFeature, boolean)}
   * @param semanticElement
   * @return could be <code>null</code> if no appropriate query for given element.
   */
  protected abstract IBusinessQuery getReadOpenValuesQuery(CapellaElement semanticElement);

  /**
   * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.widgets.SimpleEditableSemanticField#loadData(org.polarsys.capella.core.data.information.datatype.BooleanType,
   *      org.eclipse.emf.ecore.EReference)
   */
  public List<EObject> loadValues(CapellaElement semanticElement, EStructuralFeature semanticFeature) {
    List<EObject> result = new ArrayList<EObject>();
    List<?> values = (List<?>) semanticElement.eGet(semanticFeature);
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
  public List<EObject> readOpenValues(CapellaElement semanticElement, EStructuralFeature semanticFeature, boolean availableElements) {
    // Instantiate a new resulting list to avoid concurrent exceptions.
    List<EObject> result = new ArrayList<EObject>(0);
    // query for 'super'
    IBusinessQuery query = getReadOpenValuesQuery(semanticElement);
    if (null != query) {
      List<EObject> capellaElements = null;
      if (availableElements) {
        capellaElements = query.getAvailableElements(semanticElement);
      } else {
        capellaElements = query.getCurrentElements(semanticElement, false);
      }
      result.addAll(capellaElements);
    }
    return result;
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.controllers.custom.properties.controllers.IMultipleSemanticFieldController#writeOpenValues(org.polarsys.capella.core.data.capellacore.CapellaElement,
   *      org.eclipse.emf.ecore.EStructuralFeature, java.util.List)
   */
  public List<EObject> writeOpenValues(CapellaElement semanticElement, EStructuralFeature semanticFeature, List<EObject> values) {
    List<EObject> result = new ArrayList<EObject>();
    if (null != values) {
      List<EObject> modelCurrentList = readOpenValues(semanticElement, semanticFeature, false);
      for (EObject currentModelObject : modelCurrentList) {
        if (!values.contains(currentModelObject)) {
          doRemoveOperationInWriteOpenValues(semanticElement, semanticFeature, currentModelObject);
        }
      }
      for (EObject currentObject : values) {
        if (!modelCurrentList.contains(currentObject)) {
          doAddOperationInWriteOpenValues(semanticElement, semanticFeature, currentObject);
        }
        result.add(currentObject);
      }
    }
    return result;
  }
}
