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

package org.polarsys.capella.core.transition.system.rules.core;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.information.Collection;
import org.polarsys.capella.core.data.information.datatype.DataType;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.capellacore.GeneralClass;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.Generalization;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IPremise;

/**
 */
public class GeneralizationRule extends AbstractCapellaElementRule {

  @Override
  protected EClass getSourceType() {
    return CapellacorePackage.Literals.GENERALIZATION;
  }

  @Override
  protected EObject getDefaultContainer(EObject element, EObject result, IContext context) {
    Generalization generalization = (Generalization) element;
    GeneralizableElement target =
        (GeneralizableElement) TransformationHandlerHelper.getInstance(context).getBestTracedElement(generalization.getSub(), context,
            CapellacorePackage.Literals.GENERALIZABLE_ELEMENT, element, result);
    return target;
  }

  @Override
  public IStatus transformRequired(EObject source, IContext context) {
    IStatus result = super.transformRequired(source, context);

    if (result.isOK()) {
      Generalization element = (Generalization) source;
      EObject sourceElement = element.getSub();
      EObject targetElement = element.getSuper();

      result = TransformationHandlerHelper.getInstance(context).checkTransformRequired(element, context, sourceElement, targetElement);
    }
    return result;

  }

  @Override
  protected void retrieveGoDeep(EObject source, List<EObject> result, IContext context) {
    super.retrieveGoDeep(source, result, context);

    Generalization element = (Generalization) source;
    EObject targetElement = element.getSuper();

    if (targetElement instanceof DataType) {
      result.add(targetElement);
    } else if (targetElement instanceof DataValue) {
      result.add(targetElement);
    } else if (targetElement instanceof GeneralClass) {
      result.add(targetElement);
    } else if (targetElement instanceof Collection) {
      result.add(targetElement);
    }
  }

  @Override
  protected void premicesRelated(EObject element, ArrayList<IPremise> needed) {
    super.premicesRelated(element, needed);
    needed.addAll(createDefaultPrecedencePremices(element, CapellacorePackage.Literals.GENERALIZATION__SUB));
    needed.addAll(createDefaultPrecedencePremices(element, CapellacorePackage.Literals.GENERALIZATION__SUPER));
  }

  @Override
  protected void attachRelated(EObject element, EObject result, IContext context) {
    super.attachRelated(element, result, context);
    AttachmentHelper.getInstance(context).attachTracedElements(element, result, CapellacorePackage.Literals.GENERALIZATION__SUB, context);
    AttachmentHelper.getInstance(context).attachTracedElements(element, result, CapellacorePackage.Literals.GENERALIZATION__SUPER, context);
  }

  @Override
  protected void retrieveContainer(EObject element, List<EObject> result, IContext context) {
    // Nothing here
  }

}
