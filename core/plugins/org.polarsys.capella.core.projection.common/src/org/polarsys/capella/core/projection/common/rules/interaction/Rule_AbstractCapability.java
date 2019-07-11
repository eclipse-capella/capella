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
package org.polarsys.capella.core.projection.common.rules.interaction;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalCapability;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.projection.common.CapellaEngine;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.projection.common.rules.core.Rule_CapellaElement;
import org.polarsys.capella.core.tiger.ITransfo;

/**
 */
public class Rule_AbstractCapability extends Rule_CapellaElement {

  public Rule_AbstractCapability() {
    super(InteractionPackage.Literals.ABSTRACT_CAPABILITY, InteractionPackage.Literals.ABSTRACT_CAPABILITY,
          InteractionPackage.Literals.ABSTRACT_CAPABILITY_REALIZATION);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);

    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, InteractionPackage.Literals.ABSTRACT_CAPABILITY__AVAILABLE_IN_STATES,
        context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, InteractionPackage.Literals.ABSTRACT_CAPABILITY__EXTENDING, context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, InteractionPackage.Literals.ABSTRACT_CAPABILITY__EXTENDS, context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, InteractionPackage.Literals.ABSTRACT_CAPABILITY__INCLUDES, context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, InteractionPackage.Literals.ABSTRACT_CAPABILITY__INCLUDING, context_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EObject transformDirectElement(EObject element_p, IContext context_p) {
    if (element_p instanceof OperationalCapability) {
      return CtxFactory.eINSTANCE.createCapability();
    }
    return LaFactory.eINSTANCE.createCapabilityRealization();
  }

  @Override
  public boolean when(EObject element_p, ITransfo transfo_p) {
    return !(element_p instanceof OperationalCapability);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EObject getDefaultContainer(EObject element_p, EObject result_p, IContext context_p) {
    BlockArchitecture architecture = (BlockArchitecture) context_p.get(CapellaEngine.TRANSFO_TARGET_CONTAINER);
    return BlockArchitectureExt.getAbstractCapabilityPkg(architecture);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element_p, EObject result_p, EObject container_p, IContext context_p) {
    if (result_p instanceof OperationalCapability) {
      return OaPackage.Literals.OPERATIONAL_CAPABILITY_PKG__OWNED_OPERATIONAL_CAPABILITIES;
    } else if (result_p instanceof Capability) {
      return CtxPackage.Literals.CAPABILITY_PKG__OWNED_CAPABILITIES;
    }
    return LaPackage.Literals.CAPABILITY_REALIZATION_PKG__OWNED_CAPABILITY_REALIZATIONS;
  }
}
