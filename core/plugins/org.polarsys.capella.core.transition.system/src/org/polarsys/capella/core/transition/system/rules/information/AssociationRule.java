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

package org.polarsys.capella.core.transition.system.rules.information;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.IContextScopeHandler;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IPremise;

/**
 *
 */
public class AssociationRule extends AbstractCapellaElementRule {

  @Override
  protected EClass getSourceType() {
    return InformationPackage.Literals.ASSOCIATION;
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
  protected void retrieveGoDeep(EObject source, List<EObject> result, IContext context) {
    super.retrieveGoDeep(source, result, context);

    Association element = (Association) source;
    result.addAll(element.getOwnedMembers());
    result.addAll(element.getNavigableMembers());

    IContextScopeHandler handler = ContextScopeHandlerHelper.getInstance(context);
    if (handler.contains(ITransitionConstants.SOURCE_SCOPE, source, context)) {
      handler.addAll(ITransitionConstants.SOURCE_SCOPE, element.getOwnedMembers(), context);
      handler.addAll(ITransitionConstants.SOURCE_SCOPE, element.getNavigableMembers(), context);

    }

  }

  @Override
  protected void attachRelated(EObject element, EObject result, IContext context) {
    super.attachRelated(element, result, context);
    AttachmentHelper.getInstance(context).attachTracedElements(element, result, InformationPackage.Literals.ASSOCIATION__NAVIGABLE_MEMBERS, context);
  }

  @Override
  protected void retrieveContainer(EObject element, List<EObject> result, IContext context) {
    super.retrieveContainer(element, result, context);
  }

  @Override
  protected void premicesRelated(EObject element, ArrayList<IPremise> needed) {
    super.premicesRelated(element, needed);
    needed.addAll(createDefaultPrecedencePremices(element, InformationPackage.Literals.ASSOCIATION__NAVIGABLE_MEMBERS));
  }
}
