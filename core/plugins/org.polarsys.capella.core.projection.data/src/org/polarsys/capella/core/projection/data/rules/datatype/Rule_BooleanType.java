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
package org.polarsys.capella.core.projection.data.rules.datatype;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.information.datatype.BooleanType;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.projection.common.context.IContext;

public class Rule_BooleanType extends Rule_DataType {

  public Rule_BooleanType() {
    super(DatatypePackage.Literals.BOOLEAN_TYPE, DatatypePackage.Literals.BOOLEAN_TYPE);
  }

  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    BooleanType booleanType = (BooleanType) source_p;

    result_p.addAll(booleanType.getOwnedLiterals());
    if (null != booleanType.getOwnedDefaultValue())
      result_p.add(booleanType.getOwnedDefaultValue());

    super.retrieveGoDeep(source_p, result_p, context_p);
  }

}
