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
package org.polarsys.capella.core.projection.data.rules.classpkg;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.capellacore.GeneralClass;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.rules.core.Rule_FinalizableElement;

public abstract class Rule_GeneralClass extends Rule_FinalizableElement {

  public Rule_GeneralClass(EClass sourceType_p, EClass targetType_p) {
    super(sourceType_p, targetType_p);
  }

  public Rule_GeneralClass(EClass sourceType_p, EClass targetType_p, EClass specificLinkKind_p) {
    super(sourceType_p, targetType_p, specificLinkKind_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);

    GeneralClass sourceElement = (GeneralClass) source_p;

    if (transformRequired(source_p, context_p).isOK()) {
      // Temporary retrieve element restriction for waiting
      // rules correction in according MM changements
      result_p.addAll(sourceElement.getOwnedFeatures());
      result_p.addAll(sourceElement.getNestedGeneralClasses());
      result_p.addAll(sourceElement.getSuperGeneralizations());
      result_p.addAll(sourceElement.getOwnedConstraints());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element_p, EObject result_p, EObject container_p, IContext context_p) {
    if (element_p.eContainer() instanceof DataPkg) {
      return InformationPackage.Literals.DATA_PKG__OWNED_CLASSES;
    }
    return CapellacorePackage.Literals.GENERAL_CLASS__NESTED_GENERAL_CLASSES;
  }

}
