/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.transition.system.rules.interaction;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalCapability;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IPremise;


/**
 */
public class AbstractCapabilityRule extends AbstractCapellaElementRule {

  public AbstractCapabilityRule() {
    super();
    registerUpdatedReference(InteractionPackage.Literals.ABSTRACT_CAPABILITY__PRE_CONDITION);
    registerUpdatedReference(InteractionPackage.Literals.ABSTRACT_CAPABILITY__POST_CONDITION);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void attachContainement(EObject element, EObject result, IContext context) {
    super.attachContainement(element, result, context);
  }

  @Override
  protected EClass getSourceType() {
    return InteractionPackage.Literals.ABSTRACT_CAPABILITY;
  }

  @Override
  protected EObject getDefaultContainer(EObject element, EObject result, IContext context) {
    EObject root = TransformationHandlerHelper.getInstance(context).getLevelElement(element, context);
    BlockArchitecture target =
        (BlockArchitecture) TransformationHandlerHelper.getInstance(context).getBestTracedElement(root, context, CsPackage.Literals.BLOCK_ARCHITECTURE,
            element, result);
    return BlockArchitectureExt.getAbstractCapabilityPkg(target);
  }

  @Override
  protected void premicesRelated(EObject element, ArrayList<IPremise> needed) {
    super.premicesRelated(element, needed);
    needed.addAll(createDefaultPrecedencePremices(element, InteractionPackage.Literals.ABSTRACT_CAPABILITY__AVAILABLE_IN_STATES));
    needed.addAll(createDefaultPrecedencePremices(element, InteractionPackage.Literals.ABSTRACT_CAPABILITY__EXTENDING));
    needed.addAll(createDefaultPrecedencePremices(element, InteractionPackage.Literals.ABSTRACT_CAPABILITY__EXTENDS));
    needed.addAll(createDefaultPrecedencePremices(element, InteractionPackage.Literals.ABSTRACT_CAPABILITY__INCLUDING));
    needed.addAll(createDefaultPrecedencePremices(element, InteractionPackage.Literals.ABSTRACT_CAPABILITY__INCLUDES));
    needed.addAll(createDefaultPrecedencePremices(element, InteractionPackage.Literals.ABSTRACT_CAPABILITY__PRE_CONDITION));
    needed.addAll(createDefaultPrecedencePremices(element, InteractionPackage.Literals.ABSTRACT_CAPABILITY__POST_CONDITION));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void attachRelated(EObject element, EObject result, IContext context) {
    super.attachRelated(element, result, context);

    AttachmentHelper.getInstance(context).attachTracedElements(element, result, InteractionPackage.Literals.ABSTRACT_CAPABILITY__AVAILABLE_IN_STATES,
        context);
    AttachmentHelper.getInstance(context).attachTracedElements(element, result, InteractionPackage.Literals.ABSTRACT_CAPABILITY__EXTENDING, context);
    AttachmentHelper.getInstance(context).attachTracedElements(element, result, InteractionPackage.Literals.ABSTRACT_CAPABILITY__EXTENDS, context);
    AttachmentHelper.getInstance(context).attachTracedElements(element, result, InteractionPackage.Literals.ABSTRACT_CAPABILITY__INCLUDING, context);
    AttachmentHelper.getInstance(context).attachTracedElements(element, result, InteractionPackage.Literals.ABSTRACT_CAPABILITY__INCLUDES, context);
    AttachmentHelper.getInstance(context).attachTracedElements(element, result, InteractionPackage.Literals.ABSTRACT_CAPABILITY__PRE_CONDITION, context);
    AttachmentHelper.getInstance(context).attachTracedElements(element, result, InteractionPackage.Literals.ABSTRACT_CAPABILITY__POST_CONDITION, context);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveGoDeep(EObject source, List<EObject> result, IContext context) {
    super.retrieveGoDeep(source, result, context);

    result.add(((AbstractCapability) source).getPreCondition());
    result.add(((AbstractCapability) source).getPostCondition());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element, EObject result, EObject container, IContext context) {
    if (result instanceof OperationalCapability) {
      return OaPackage.Literals.OPERATIONAL_CAPABILITY_PKG__OWNED_OPERATIONAL_CAPABILITIES;

    } else if (result instanceof Capability) {
      return CtxPackage.Literals.CAPABILITY_PKG__OWNED_CAPABILITIES;
    }
    return LaPackage.Literals.CAPABILITY_REALIZATION_PKG__OWNED_CAPABILITY_REALIZATIONS;
  }

}
