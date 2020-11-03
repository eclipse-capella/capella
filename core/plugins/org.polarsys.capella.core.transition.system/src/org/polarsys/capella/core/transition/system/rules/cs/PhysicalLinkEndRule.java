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

package org.polarsys.capella.core.transition.system.rules.cs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.PhysicalLinkEnd;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IPremise;

public class PhysicalLinkEndRule extends AbstractCapellaElementRule {

  @Override
  protected EClass getSourceType() {
    return CsPackage.Literals.PHYSICAL_LINK_END;
  }

  @Override
  protected void retrieveGoDeep(EObject source, List<EObject> result, IContext context) {
    super.retrieveGoDeep(source, result, context);
    PhysicalLinkEnd element = (PhysicalLinkEnd) source;
    result.add(element.getPart());
    result.add(element.getPort());
  }

  @Override
  protected void attachRelated(EObject element, EObject result, IContext context) {
    super.attachRelated(element, result, context);
    AttachmentHelper.getInstance(context).attachTracedElements(element, result, CsPackage.Literals.PHYSICAL_LINK_END__PART, context);
    AttachmentHelper.getInstance(context).attachTracedElements(element, result, CsPackage.Literals.PHYSICAL_LINK_END__PORT, context);
  }

  @Override
  protected void premicesRelated(EObject eObject1, ArrayList<IPremise> needed) {
    super.premicesRelated(eObject1, needed);
    PhysicalLinkEnd element = (PhysicalLinkEnd) eObject1;
    needed.addAll(createDefaultPrecedencePremices(element, CsPackage.Literals.PHYSICAL_LINK_END__PART));
    needed.addAll(createDefaultPrecedencePremices(element, CsPackage.Literals.PHYSICAL_LINK_END__PORT));
  }
}
