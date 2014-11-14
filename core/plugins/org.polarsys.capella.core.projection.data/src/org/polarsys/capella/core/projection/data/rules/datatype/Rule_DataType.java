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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.datatype.DataType;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.rules.core.Rule_FinalizableElement;

public abstract class Rule_DataType extends Rule_FinalizableElement {

  public Rule_DataType(EClass eSrc, EClass eTgt) {
    super(eSrc, eTgt, InformationPackage.Literals.INFORMATION_REALIZATION);

    setNeedsContext(true);
    _updatedAttributes.add(DatatypePackage.Literals.DATA_TYPE__DISCRETE.getName());
    _updatedAttributes.add(DatatypePackage.Literals.DATA_TYPE__VISIBILITY.getName());
    _updatedAttributes.add(DatatypePackage.Literals.DATA_TYPE__PATTERN.getName());
    _updatedAttributes.add(CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__ABSTRACT.getName());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);

    DataType dataType = (DataType) source_p;
    if (transformRequired(source_p, context_p).isOK()) {
      result_p.addAll(dataType.getOwnedPropertyValues());
      result_p.addAll(dataType.getOwnedConstraints());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element_p, EObject result_p, EObject container_p, IContext context_p) {
    return InformationPackage.Literals.DATA_PKG__OWNED_DATA_TYPES;
  }

}
