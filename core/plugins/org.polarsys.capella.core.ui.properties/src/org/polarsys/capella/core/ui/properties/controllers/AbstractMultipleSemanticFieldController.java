/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.properties.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;

/**
 * Base class to implement {@link IMultipleSemanticFieldController}.
 */
public abstract class AbstractMultipleSemanticFieldController extends AbstractSemanticFieldController implements IMultipleSemanticFieldController {
  /**
   * Do the add operation in {@link #writeOpenValues(EObject, EStructuralFeature, List)}
   * @param semanticElement
   * @param semanticFeature
   * @param object
   */
  @SuppressWarnings("unchecked")
  protected void doAddOperationInWriteOpenValues(EObject semanticElement, EStructuralFeature semanticFeature, EObject object) {
    ((List<EObject>) semanticElement.eGet(semanticFeature)).add(object);
  }

  /**
   * Do the remove operation in {@link #writeOpenValues(EObject, EStructuralFeature, List)}
   * @param semanticElement
   * @param semanticFeature
   * @param object
   */
  @SuppressWarnings("unchecked")
  protected void doRemoveOperationInWriteOpenValues(EObject semanticElement, EStructuralFeature semanticFeature, EObject object) {
    if ((semanticFeature instanceof EReference) && ((EReference) semanticFeature).isContainment()) {
      AbstractSemanticField.deleteContainmentValue(object);
    } else {
      ((List<EObject>) semanticElement.eGet(semanticFeature)).remove(object);
    }
  }

  /**
   * Get the business query used by {@link #readOpenValues(EObject, EStructuralFeature, boolean)}
   * @param semanticElement
   * @return could be <code>null</code> if no appropriate query for given element.
   */
  protected abstract IBusinessQuery getReadOpenValuesQuery(EObject semanticElement);

  /**
   * {@inheritDoc}
   */
  public List<EObject> loadValues(EObject semanticElement, EStructuralFeature semanticFeature) {
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
   * {@inheritDoc}
   */
  public List<EObject> readOpenValues(EObject semanticElement, EStructuralFeature semanticFeature, boolean availableElements) {
    IBusinessQuery query = getReadOpenValuesQuery(semanticElement);
    // Instantiate a new resulting list to avoid concurrent exceptions.
    return new ArrayList<>(availableElements ? doQueryAvailableElements(semanticElement, query)
        : doQueryCurrentElements(semanticElement, query));
  }

  protected List<EObject> doQueryAvailableElements(EObject semanticElement, IBusinessQuery query){
    return query != null ? query.getAvailableElements(semanticElement) : Collections.emptyList();
  }

  protected List<EObject> doQueryCurrentElements(EObject semanticElement, IBusinessQuery query){
    return query != null ? query.getCurrentElements(semanticElement, false) : Collections.emptyList();
  }

  /**
   * {@inheritDoc}
   */
  public List<EObject> writeOpenValues(EObject semanticElement, EStructuralFeature semanticFeature, List<EObject> values) {
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
