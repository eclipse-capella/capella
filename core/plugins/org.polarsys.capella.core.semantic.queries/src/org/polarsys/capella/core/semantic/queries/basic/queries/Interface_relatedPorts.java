/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;

/**
 * This query allows to get the interface related ports.
 */
public class Interface_relatedPorts implements IQuery {

  /**
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    Interface itf = (Interface) object;
    // gets the Semantic Editing Domain
    SemanticEditingDomain semEditDomain = (SemanticEditingDomain) TransactionHelper.getEditingDomain(itf);
    // Gets the Cross Referencer
    ECrossReferenceAdapter crossReferencer = semEditDomain.getCrossReferencer();
    //Indeed, a Port may reference an interface while this interface will never reference any port
    Collection<Setting> inverseReferences = crossReferencer.getInverseReferences(itf);
    for (Setting setting : inverseReferences) {
      EObject eObject = setting.getEObject();
      if (eObject instanceof Port) {
        //The object referencing the current interface is a <code>Port</code> instance.
        result.add(eObject);
      }
    }
    return result;
  }
}