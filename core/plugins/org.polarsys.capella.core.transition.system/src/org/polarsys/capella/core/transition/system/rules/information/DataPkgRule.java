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

package org.polarsys.capella.core.transition.system.rules.information;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.cs.Block;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class DataPkgRule extends AbstractCapellaElementRule {

  @Override
  protected EClass getSourceType() {
    return InformationPackage.Literals.DATA_PKG;
  }

  @Override
  protected EObject getDefaultContainer(EObject element, EObject result, IContext context) {
    EObject root = TransformationHandlerHelper.getInstance(context).getLevelElement(element, context);
    BlockArchitecture target =
        (BlockArchitecture) TransformationHandlerHelper.getInstance(context).getBestTracedElement(root, context, CsPackage.Literals.BLOCK_ARCHITECTURE,
            element, result);
    return BlockArchitectureExt.getDataPkg(target);
  }

  @Override
  protected void retrieveContainer(EObject element, List<EObject> result, IContext context) {
    if (!(element.eContainer() instanceof Component)) {
      if (!(element.eContainer() instanceof BlockArchitecture)) {
        super.retrieveContainer(element, result, context);
      }
    }
  }

  @Override
  protected EObject transformDirectElement(EObject element, IContext context) {
    if (element.eContainer() instanceof BlockArchitecture) {
      EObject root = TransformationHandlerHelper.getInstance(context).getLevelElement(element, context);
      BlockArchitecture target = (BlockArchitecture) TransformationHandlerHelper.getInstance(context)
          .getBestTracedElement(root, context, CsPackage.Literals.BLOCK_ARCHITECTURE);
      
      Structure result = BlockArchitectureExt.getDataPkg(target, true);
      if (result != null) {
        if (!BlockArchitectureExt.isDefaultNameDataPkg((AbstractNamedElement) element)) {
          ((AbstractNamedElement) result).setName(((AbstractNamedElement) element).getName());
        }
        return result;
      }
      
    }
    return super.transformDirectElement(element, context);
  }
  
  
  @Override
  protected boolean isOrderedContainment(EObject element) {
    return true;
  }

  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element, EObject result, EObject container, IContext context) {
    if (container instanceof Block) {
      return CsPackage.Literals.BLOCK__OWNED_DATA_PKG;

    } else if (container instanceof DataPkg) {
      return InformationPackage.Literals.DATA_PKG__OWNED_DATA_PKGS;

    } else if (container instanceof BlockArchitecture) {
      return CsPackage.Literals.BLOCK_ARCHITECTURE__OWNED_DATA_PKG;
    }

    return super.getTargetContainementFeature(element, result, container, context);
  }

}
