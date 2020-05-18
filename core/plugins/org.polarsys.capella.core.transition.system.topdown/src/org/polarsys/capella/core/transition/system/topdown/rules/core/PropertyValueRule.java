/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.system.topdown.rules.core;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.capellacore.AbstractPropertyValue;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.common.handlers.options.OptionsHandlerHelper;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IPremise;

public class PropertyValueRule extends org.polarsys.capella.core.transition.system.rules.core.PropertyValueRule {

  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);
    AbstractPropertyValue element = (AbstractPropertyValue) source_p;

    if (OptionsHandlerHelper.getInstance(context_p).getBooleanValue(context_p, ITopDownConstants.OPTIONS_SCOPE,
        ITopDownConstants.OPTIONS__PROPERTY_VALUE__INVOLVED_ELEMENTS, ITopDownConstants.OPTIONS__PROPERTY_VALUE__INVOLVED_ELEMENTS__DEFAULT.booleanValue())) {
      result_p.addAll(element.getInvolvedElements());
    }
  }

  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);

    if (OptionsHandlerHelper.getInstance(context_p).getBooleanValue(context_p, ITopDownConstants.OPTIONS_SCOPE,
        ITopDownConstants.OPTIONS__PROPERTY_VALUE__INVOLVED_ELEMENTS, ITopDownConstants.OPTIONS__PROPERTY_VALUE__INVOLVED_ELEMENTS__DEFAULT.booleanValue())) {
      AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, CapellacorePackage.Literals.ABSTRACT_PROPERTY_VALUE__INVOLVED_ELEMENTS,
          context_p);
    }
  }

  @Override
  protected void premicesRelated(EObject element_p, ArrayList<IPremise> needed_p) {
    super.premicesRelated(element_p, needed_p);
    needed_p.addAll(createDefaultPrecedencePremices(element_p, CapellacorePackage.Literals.ABSTRACT_PROPERTY_VALUE__INVOLVED_ELEMENTS));
  }

}
