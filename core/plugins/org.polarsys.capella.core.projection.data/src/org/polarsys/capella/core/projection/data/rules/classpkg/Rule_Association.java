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
package org.polarsys.capella.core.projection.data.rules.classpkg;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.projection.common.rules.core.Rule_CapellaElement;
import org.polarsys.capella.core.tiger.helpers.Query;

public class Rule_Association extends Rule_CapellaElement {

  public Rule_Association() {
    super(InformationPackage.Literals.ASSOCIATION, InformationPackage.Literals.ASSOCIATION);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    Association source = (Association) source_p;
    result_p.addAll(source.getOwnedMembers());
    super.retrieveGoDeep(source_p, result_p, context_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element_p, EObject result_p, EObject container_p, IContext context_p) {
    return InformationPackage.Literals.ASSOCIATION_PKG__OWNED_ASSOCIATIONS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);

    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, InformationPackage.Literals.ASSOCIATION__OWNED_MEMBERS, context_p);

    // Update the navigability field members
    Association sourceElt = (Association) element_p;
    Association targetElt = (Association) Query.retrieveFirstTransformedElement(sourceElt, context_p.getTransfo());
    if (null != targetElt && targetElt.getNavigableMembers().size() > 0)
      targetElt.getNavigableMembers().removeAll(targetElt.getNavigableMembers());
    for (Property property : sourceElt.getNavigableMembers()) {
      Property targetProp = (Property) Query.retrieveFirstTransformedElement(property, context_p.getTransfo());
      if (null != targetProp) {
        targetElt.getNavigableMembers().add(targetProp);
      }
    }
  }

}
