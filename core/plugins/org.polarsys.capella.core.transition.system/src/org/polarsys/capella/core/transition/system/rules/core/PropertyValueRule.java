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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.capellacore.AbstractPropertyValue;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyValue;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.PropertyValueGroup;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.transition.system.helpers.PackageHelper;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IPremise;

public class PropertyValueRule extends AbstractCapellaElementRule {

  public PropertyValueRule() {
    super();
    registerUpdatedAttribute(CapellacorePackage.Literals.BOOLEAN_PROPERTY_VALUE__VALUE);
    registerUpdatedAttribute(CapellacorePackage.Literals.FLOAT_PROPERTY_VALUE__VALUE);
    registerUpdatedAttribute(CapellacorePackage.Literals.INTEGER_PROPERTY_VALUE__VALUE);
    registerUpdatedAttribute(CapellacorePackage.Literals.STRING_PROPERTY_VALUE__VALUE);
  }

  @Override
  protected boolean isOrderedContainment(EObject element_p) {
    return true;
  }

  @Override
  protected EClass getSourceType() {
    return CapellacorePackage.Literals.ABSTRACT_PROPERTY_VALUE;
  }

  @Override
  protected EObject getDefaultContainer(EObject element_p, EObject result_p, IContext context_p) {
    EObject root = TransformationHandlerHelper.getInstance(context_p).getLevelElement(element_p, context_p);
    BlockArchitecture target =
        (BlockArchitecture) TransformationHandlerHelper.getInstance(context_p).getBestTracedElement(root, context_p, CsPackage.Literals.BLOCK_ARCHITECTURE,
            element_p, result_p);
    return target;
  }

  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);
    AbstractPropertyValue element = (AbstractPropertyValue) source_p;

    if (element instanceof EnumerationPropertyValue) {
      result_p.add(((EnumerationPropertyValue) element).getType());
      result_p.add(((EnumerationPropertyValue) element).getValue());
    }
  }

  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);
    if (element_p instanceof EnumerationPropertyValue) {

      AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, CapellacorePackage.Literals.ENUMERATION_PROPERTY_VALUE__TYPE, context_p);
      AttachmentHelper.getInstance(context_p)
          .attachTracedElements(element_p, result_p, CapellacorePackage.Literals.ENUMERATION_PROPERTY_VALUE__VALUE, context_p);
    }
  }

  @Override
  protected void premicesRelated(EObject element_p, ArrayList<IPremise> needed_p) {
    super.premicesRelated(element_p, needed_p);
    if (element_p instanceof EnumerationPropertyValue) {
      needed_p.addAll(createDefaultPrecedencePremices(element_p, CapellacorePackage.Literals.ENUMERATION_PROPERTY_VALUE__TYPE));
      needed_p.addAll(createDefaultPrecedencePremices(element_p, CapellacorePackage.Literals.ENUMERATION_PROPERTY_VALUE__VALUE));
    }
  }

  @Override
  protected void retrieveContainer(EObject element_p, List<EObject> result_p, IContext context_p) {
    if ((element_p.eContainer() instanceof PropertyValueGroup) || (PackageHelper.isPackage(element_p.eContainer(), context_p))) {
      super.retrieveContainer(element_p, result_p, context_p);
    }
  }

}
