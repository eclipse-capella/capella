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
  protected EObject getDefaultContainer(EObject element_p, EObject result_p, IContext context_p) {
    Generalization element = (Generalization) element_p;
    GeneralizableElement target =
        (GeneralizableElement) TransformationHandlerHelper.getInstance(context_p).getBestTracedElement(element.getSub(), context_p,
            CapellacorePackage.Literals.GENERALIZABLE_ELEMENT, element_p, result_p);
    return target;
  }

  @Override
  public IStatus transformRequired(EObject source_p, IContext context_p) {
    IStatus result = super.transformRequired(source_p, context_p);

    if (result.isOK()) {
      Generalization element = (Generalization) source_p;
      EObject sourceElement = element.getSub();
      EObject targetElement = element.getSuper();

      result = TransformationHandlerHelper.getInstance(context_p).checkTransformRequired(element, context_p, sourceElement, targetElement);
    }
    return result;

  }

  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);

    Generalization element = (Generalization) source_p;
    EObject targetElement = element.getSuper();

    if (targetElement instanceof DataType) {
      result_p.add(targetElement);
    } else if (targetElement instanceof DataValue) {
      result_p.add(targetElement);
    } else if (targetElement instanceof GeneralClass) {
      result_p.add(targetElement);
    } else if (targetElement instanceof Collection) {
      result_p.add(targetElement);
    }
  }

  @Override
  protected void premicesRelated(EObject element_p, ArrayList<IPremise> needed_p) {
    super.premicesRelated(element_p, needed_p);
    needed_p.addAll(createDefaultPrecedencePremices(element_p, CapellacorePackage.Literals.GENERALIZATION__SUB));
    needed_p.addAll(createDefaultPrecedencePremices(element_p, CapellacorePackage.Literals.GENERALIZATION__SUPER));
  }

  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, CapellacorePackage.Literals.GENERALIZATION__SUB, context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, CapellacorePackage.Literals.GENERALIZATION__SUPER, context_p);
  }

  @Override
  protected void retrieveContainer(EObject element_p, List<EObject> result_p, IContext context_p) {
    // Nothing here
  }

}
