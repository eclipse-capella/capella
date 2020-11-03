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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.capellacore.Allocation;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IPremise;

/**
 */
public class PortAllocationRule extends AbstractCapellaElementRule {

  /**
   * {@inheritDoc}
   */
  @Override
  protected EClass getSourceType() {
    return InformationPackage.Literals.PORT_ALLOCATION;
  }

  @Override
  protected void retrieveGoDeep(EObject source, List<EObject> result, IContext context) {
    super.retrieveGoDeep(source, result, context);
    // Nothing here
  }

  @Override
  public IStatus transformRequired(EObject source, IContext context) {
    IStatus result = super.transformRequired(source, context);

    if (result.isOK()) {
      Allocation element = (Allocation) source;
      EObject sourceElement = element.getSourceElement();
      EObject targetElement = element.getTargetElement();

      result = TransformationHandlerHelper.getInstance(context).checkTransformRequired(element, context, sourceElement, targetElement);
    }
    return result;
  }

  @Override
  protected void premicesRelated(EObject eObject1, ArrayList<IPremise> needed) {
    super.premicesRelated(eObject1, needed);
    Allocation element = (Allocation) eObject1;
    needed.addAll(createDefaultPrecedencePremices(element, ModellingcorePackage.Literals.ABSTRACT_TRACE__SOURCE_ELEMENT));
    needed.addAll(createDefaultPrecedencePremices(element, ModellingcorePackage.Literals.ABSTRACT_TRACE__TARGET_ELEMENT));
  }

  @Override
  protected void attachRelated(EObject element, EObject result, IContext context) {
    super.attachRelated(element, result, context);
    AttachmentHelper.getInstance(context).attachTracedElements(element, result, ModellingcorePackage.Literals.ABSTRACT_TRACE__SOURCE_ELEMENT, context);
    AttachmentHelper.getInstance(context).attachTracedElements(element, result, ModellingcorePackage.Literals.ABSTRACT_TRACE__TARGET_ELEMENT, context);
  }

}
