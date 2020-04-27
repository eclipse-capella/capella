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

package org.polarsys.capella.core.transition.system.rules.information.datatype;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.information.datatype.DataType;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.IContextScopeHandler;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.transition.system.rules.core.GeneralizableElementRule;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IPremise;

public class DataTypeRule extends GeneralizableElementRule {

  public DataTypeRule() {
    super();
    registerUpdatedAttribute(DatatypePackage.Literals.DATA_TYPE__DISCRETE);
    registerUpdatedAttribute(DatatypePackage.Literals.DATA_TYPE__MAX_INCLUSIVE);
    registerUpdatedAttribute(DatatypePackage.Literals.DATA_TYPE__MIN_INCLUSIVE);
    registerUpdatedAttribute(DatatypePackage.Literals.DATA_TYPE__PATTERN);
    registerUpdatedAttribute(DatatypePackage.Literals.DATA_TYPE__VISIBILITY);

    registerUpdatedAttribute(ModellingcorePackage.Literals.FINALIZABLE_ELEMENT__FINAL);
  }

  @Override
  protected boolean isOrderedContainment(EObject element) {
    return true;
  }

  @Override
  protected EClass getSourceType() {
    return DatatypePackage.Literals.DATA_TYPE;
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

    DataType element = (DataType) source;
    result.addAll(element.getOwnedDataValues());
    result.add(element.getDefaultValue());
    result.add(element.getNullValue());
    result.addAll(element.getSuperGeneralizations());

    IContextScopeHandler handler = ContextScopeHandlerHelper.getInstance(context);
    if (handler.contains(ITransitionConstants.SOURCE_SCOPE, source, context)) {
      handler.add(ITransitionConstants.SOURCE_SCOPE, element.getDefaultValue(), context);
      handler.addAll(ITransitionConstants.SOURCE_SCOPE, element.getOwnedDataValues(), context);
      handler.add(ITransitionConstants.SOURCE_SCOPE, element.getNullValue(), context);
      handler.addAll(ITransitionConstants.SOURCE_SCOPE, element.getSuperGeneralizations(), context);

    }

  }

  @Override
  protected void attachRelated(EObject element, EObject result, IContext context) {
    super.attachRelated(element, result, context);
    AttachmentHelper.getInstance(context).attachTracedElements(element, result, DatatypePackage.Literals.DATA_TYPE__DEFAULT_VALUE, context);
    AttachmentHelper.getInstance(context).attachTracedElements(element, result, DatatypePackage.Literals.DATA_TYPE__NULL_VALUE, context);
  }

  @Override
  protected void premicesRelated(EObject element, ArrayList<IPremise> needed) {
    super.premicesRelated(element, needed);
    needed.addAll(createDefaultPrecedencePremices(element, DatatypePackage.Literals.DATA_TYPE__DEFAULT_VALUE));
    needed.addAll(createDefaultPrecedencePremices(element, DatatypePackage.Literals.DATA_TYPE__NULL_VALUE));
  }

  @Override
  protected void retrieveContainer(EObject element, List<EObject> result, IContext context) {
    super.retrieveContainer(element, result, context);
  }

}
