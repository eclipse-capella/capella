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
package org.polarsys.capella.core.transition.system.rules.information.datavalue;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.information.datavalue.StringReference;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IPremise;

public class StringReferenceRule extends DataValueRule {

  public StringReferenceRule() {
    super();
  }

  @Override
  protected EClass getSourceType() {
    return DatavaluePackage.Literals.STRING_REFERENCE;
  }

  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);
    StringReference element = (StringReference) source_p;
    result_p.add(element.getReferencedProperty());
    result_p.add(element.getReferencedValue());
  }

  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, DatavaluePackage.Literals.STRING_REFERENCE__REFERENCED_PROPERTY,
        context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, DatavaluePackage.Literals.STRING_REFERENCE__REFERENCED_VALUE, context_p);
  }

  @Override
  protected void premicesRelated(EObject element_p, ArrayList<IPremise> needed_p) {
    super.premicesRelated(element_p, needed_p);
    needed_p.addAll(createDefaultPrecedencePremices(element_p, DatavaluePackage.Literals.STRING_REFERENCE__REFERENCED_PROPERTY));
    needed_p.addAll(createDefaultPrecedencePremices(element_p, DatavaluePackage.Literals.STRING_REFERENCE__REFERENCED_VALUE));
  }
}
