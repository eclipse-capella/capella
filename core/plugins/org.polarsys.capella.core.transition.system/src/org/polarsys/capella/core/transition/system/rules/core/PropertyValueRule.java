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
  protected boolean isOrderedContainment(EObject element) {
    return true;
  }

  @Override
  protected EClass getSourceType() {
    return CapellacorePackage.Literals.ABSTRACT_PROPERTY_VALUE;
  }

  @Override
  protected EObject getDefaultContainer(EObject element, EObject result, IContext context) {
    EObject root = TransformationHandlerHelper.getInstance(context).getLevelElement(element, context);
    BlockArchitecture target =
        (BlockArchitecture) TransformationHandlerHelper.getInstance(context).getBestTracedElement(root, context, CsPackage.Literals.BLOCK_ARCHITECTURE,
            element, result);
    return target;
  }

  @Override
  protected void retrieveGoDeep(EObject source, List<EObject> result, IContext context) {
    super.retrieveGoDeep(source, result, context);
    AbstractPropertyValue element = (AbstractPropertyValue) source;

    if (element instanceof EnumerationPropertyValue) {
      result.add(((EnumerationPropertyValue) element).getType());
      result.add(((EnumerationPropertyValue) element).getValue());
    }
  }

  @Override
  protected void attachRelated(EObject element, EObject result, IContext context) {
    super.attachRelated(element, result, context);
    if (element instanceof EnumerationPropertyValue) {

      AttachmentHelper.getInstance(context).attachTracedElements(element, result, CapellacorePackage.Literals.ENUMERATION_PROPERTY_VALUE__TYPE, context);
      AttachmentHelper.getInstance(context)
          .attachTracedElements(element, result, CapellacorePackage.Literals.ENUMERATION_PROPERTY_VALUE__VALUE, context);
    }
  }

  @Override
  protected void premicesRelated(EObject element, ArrayList<IPremise> needed) {
    super.premicesRelated(element, needed);
    if (element instanceof EnumerationPropertyValue) {
      needed.addAll(createDefaultPrecedencePremices(element, CapellacorePackage.Literals.ENUMERATION_PROPERTY_VALUE__TYPE));
      needed.addAll(createDefaultPrecedencePremices(element, CapellacorePackage.Literals.ENUMERATION_PROPERTY_VALUE__VALUE));
    }
  }

  @Override
  protected void retrieveContainer(EObject element, List<EObject> result, IContext context) {
    if ((element.eContainer() instanceof PropertyValueGroup) || (PackageHelper.isPackage(element.eContainer(), context))) {
      super.retrieveContainer(element, result, context);
    }
  }

}
