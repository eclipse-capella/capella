/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.projection.interfaces.rules;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.cs.Block;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.StructureExt;
import org.polarsys.capella.core.projection.common.ProjectionMessages;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.handlers.log.LogHelper;
import org.polarsys.capella.core.projection.common.handlers.traceability.TraceabilityHandlerHelper;
import org.polarsys.capella.core.projection.common.rules.core.Rule_CapellaElement;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.core.transfo.misc.CapellaEngine;

/**
 */
public class Rule_InterfacePkg extends Rule_CapellaElement {

  public Rule_InterfacePkg() {
    super(CsPackage.Literals.INTERFACE_PKG, CsPackage.Literals.INTERFACE_PKG);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveContainer(EObject element_p, List<EObject> result_p, IContext context_p) {

    if (!(element_p.eContainer() instanceof Component)) {
      if (!(element_p.eContainer() instanceof BlockArchitecture)) {
        super.retrieveContainer(element_p, result_p, context_p);
      }
    }
  }

  @Override
  protected EObject transformDirectElement(EObject element_p, IContext context_p) {
    //Return the root interface pkg
    if ((element_p.eContainer() instanceof BlockArchitecture)) {
      BlockArchitecture architecture = (BlockArchitecture) context_p.get(CapellaEngine.TRANSFO_TARGET_CONTAINER);
      if (architecture != null && !(architecture instanceof PhysicalArchitecture)) {
        InterfacePkg result = BlockArchitectureExt.getInterfacePkg(architecture);
        if (result != null) {
          LogHelper.getInstance().info(
              NLS.bind(ProjectionMessages.ElementTransitionedToExistingElement, EObjectLabelProviderHelper.getText(element_p),
                  EObjectLabelProviderHelper.getText(result)), new Object[] { element_p, result }, ProjectionMessages.Activity_Transformation);
          return result;
        }
      }
    }

    return super.transformDirectElement(element_p, context_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);

    InterfacePkg sourceElement = (InterfacePkg) source_p;

    if (isRelatedToSource(sourceElement, context_p)) {
      result_p.addAll(sourceElement.getOwnedInterfaces());
      result_p.addAll(sourceElement.getOwnedInterfacePkgs());
      result_p.addAll(sourceElement.getOwnedExchangeItems());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element_p, EObject result_p, EObject container_p, IContext context_p) {
    if (container_p instanceof Block) {
      return CsPackage.Literals.BLOCK__OWNED_INTERFACE_PKG;
    } else if (container_p instanceof InterfacePkg) {
      return CsPackage.Literals.INTERFACE_PKG__OWNED_INTERFACE_PKGS;
    } else if (container_p instanceof BlockArchitecture) {
      return CsPackage.Literals.BLOCK_ARCHITECTURE__OWNED_INTERFACE_PKG;
    }

    return super.getTargetContainementFeature(element_p, result_p, container_p, context_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EObject getBestContainer(EObject element_p, EObject result_p, IContext context_p) {
    EObject bestContainer = super.getBestContainer(element_p, result_p, context_p);

    if (bestContainer != null) {
      EObject sourceContainer = element_p.eContainer();
      if (sourceContainer instanceof Component) {
        EObject targetComponent = Query.retrieveFirstTransformedElement(sourceContainer, context_p.getTransfo(), CsPackage.Literals.COMPONENT);
        if (targetComponent == null) {
          targetComponent = Query.retrieveFirstTransformedElement(sourceContainer, context_p.getTransfo());
          targetComponent = EcoreUtil2.getFirstContainer(targetComponent, CsPackage.Literals.COMPONENT);
        }
        if (targetComponent != null) {
          InterfacePkg dataPkg = ((Component) targetComponent).getOwnedInterfacePkg();

          //If the owned interfacePkg is not the transitioned package, put it inside
          if (dataPkg != null && dataPkg != result_p) {
            return dataPkg;
          }
        }
        return targetComponent;
      }
    }
    return bestContainer;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void attachContainement(EObject element_p, EObject result_p, IContext context_p) {
    super.attachContainement(element_p, result_p, context_p);

    if (result_p instanceof Structure) {
      Structure named = (Structure) result_p;
      if (!TraceabilityHandlerHelper.getInstance(context_p).retrieveTracedElements(element_p.eContainer(), context_p).contains(result_p.eContainer())) {
        named.setName(StructureExt.getNewStructureName(named, element_p.eContainer()));
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EObject getDefaultContainer(EObject element_p, EObject result_p, IContext context_p) {
    BlockArchitecture architecture = (BlockArchitecture) context_p.get(CapellaEngine.TRANSFO_TARGET_CONTAINER);
    if (architecture != null) {
      if (element_p.eContainer() instanceof BlockArchitecture && !(architecture instanceof PhysicalArchitecture)) {
        return architecture;
      }
      return BlockArchitectureExt.getInterfacePkg(architecture);
    }

    return super.getDefaultContainer(element_p, result_p, context_p);
  }

}
