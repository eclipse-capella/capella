/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.model.ju.sortContent;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.test.framework.api.BasicTestCase;

/**
 * This test case tests if the Sort Content works well when the selected element has only 2 children
 */
public class SortContentTestCase extends BasicTestCase {

  /**
   * 
   * @param obj
   * @param containmentReference
   *          the containment reference of obj to check
   * @param idOrder
   * @return if children are sorted according to order defined in idOrder
   */
  protected boolean checkChildrenOrder(EObject obj, EReference containmentReference, List<String> idOrder) {
    EList<EObject> containedElements = (EList<EObject>) obj.eGet(containmentReference);
    for (int i = 0; i < containedElements.size(); i++) {
      ModelElement modelElem = (ModelElement) obj.eContents().get(i);
      if (!modelElem.getId().equals(idOrder.get(i)))
        return false;
    }
    return true;
  }

  @Override
  public void test() throws Exception {
    // TODO Auto-generated method stub
  }
}
