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
package org.polarsys.capella.core.semantic.queries.basic.queries;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;

import org.polarsys.capella.core.data.information.Collection;
import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.data.information.datatype.DataType;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * Return ExchangeItemElements of current Type(Class,Collection,DataType) via ExchangeItemElement
 *
 */
public class AbstractTypeExchangeItemElements implements IQuery {

  /**
   * Constructor
   */
  public AbstractTypeExchangeItemElements() {
    // does nothing
  }

  /**
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    if (object instanceof org.polarsys.capella.core.data.information.Class
        ||object instanceof Collection || object instanceof DataType) {
      if(!(object instanceof Type)) return result;
      Type type = (Type) object;
      EList<AbstractTypedElement> abstractTypedElements = type.getAbstractTypedElements();
      for (AbstractTypedElement abstractTypedElement : abstractTypedElements) {
        if (abstractTypedElement instanceof ExchangeItemElement) {
          result.add(abstractTypedElement);
        }
      }
    }
    return result;
  }
}
