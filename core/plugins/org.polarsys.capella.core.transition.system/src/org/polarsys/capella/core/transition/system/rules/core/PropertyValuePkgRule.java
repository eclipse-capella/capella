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

package org.polarsys.capella.core.transition.system.rules.core;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.transition.system.helpers.PackageHelper;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class PropertyValuePkgRule extends AbstractCapellaElementRule {

  public PropertyValuePkgRule() {
    super();
  }

  @Override
  protected boolean isOrderedContainment(EObject element) {
    return true;
  }

  @Override
  public void apply(EObject element, IContext context) throws Exception {
    super.apply(element, context);
  }

  @Override
  protected EClass getSourceType() {
    return CapellacorePackage.Literals.PROPERTY_VALUE_PKG;
  }

  @Override
  protected EObject getDefaultContainer(EObject element, EObject result, IContext context) {
    EObject root = TransformationHandlerHelper.getInstance(context).getLevelElement(element, context);
    BlockArchitecture target =
        (BlockArchitecture) TransformationHandlerHelper.getInstance(context).getBestTracedElement(root, context, CsPackage.Literals.BLOCK_ARCHITECTURE,
            element, result);
    return target;
  }

  @Override
  protected void retrieveGoDeep(EObject source, List<EObject> result, IContext context) {
    super.retrieveGoDeep(source, result, context);
  }

  @Override
  protected void attachRelated(EObject element, EObject result, IContext context) {
    super.attachRelated(element, result, context);
  }

  @Override
  protected void retrieveContainer(EObject element, List<EObject> result, IContext context) {
    if (PackageHelper.isPackage(element.eContainer(), context)) {
      super.retrieveContainer(element, result, context);
    }
  }

}
