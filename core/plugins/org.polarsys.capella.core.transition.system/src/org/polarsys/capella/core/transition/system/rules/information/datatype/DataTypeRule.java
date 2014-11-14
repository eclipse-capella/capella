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
  protected boolean isOrderedContainment(EObject element_p) {
    return true;
  }

  @Override
  protected EClass getSourceType() {
    return DatatypePackage.Literals.DATA_TYPE;
  }

  @Override
  protected EObject getDefaultContainer(EObject element_p, EObject result_p, IContext context_p) {
    EObject root = TransformationHandlerHelper.getInstance(context_p).getLevelElement(element_p, context_p);
    BlockArchitecture target =
        (BlockArchitecture) TransformationHandlerHelper.getInstance(context_p).getBestTracedElement(root, context_p, CsPackage.Literals.BLOCK_ARCHITECTURE,
            element_p, result_p);
    return BlockArchitectureExt.getDataPkg(target);
  }

  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);

    DataType element = (DataType) source_p;
    result_p.addAll(element.getOwnedDataValues());
    result_p.add(element.getDefaultValue());
    result_p.add(element.getNullValue());
    result_p.addAll(element.getSuperGeneralizations());

    IContextScopeHandler handler = ContextScopeHandlerHelper.getInstance(context_p);
    if (handler.contains(ITransitionConstants.SOURCE_SCOPE, source_p, context_p)) {
      handler.add(ITransitionConstants.SOURCE_SCOPE, element.getDefaultValue(), context_p);
      handler.addAll(ITransitionConstants.SOURCE_SCOPE, element.getOwnedDataValues(), context_p);
      handler.add(ITransitionConstants.SOURCE_SCOPE, element.getNullValue(), context_p);
      handler.addAll(ITransitionConstants.SOURCE_SCOPE, element.getSuperGeneralizations(), context_p);

    }

  }

  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, DatatypePackage.Literals.DATA_TYPE__DEFAULT_VALUE, context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, DatatypePackage.Literals.DATA_TYPE__NULL_VALUE, context_p);
  }

  @Override
  protected void premicesRelated(EObject element_p, ArrayList<IPremise> needed_p) {
    super.premicesRelated(element_p, needed_p);
    needed_p.addAll(createDefaultPrecedencePremices(element_p, DatatypePackage.Literals.DATA_TYPE__DEFAULT_VALUE));
    needed_p.addAll(createDefaultPrecedencePremices(element_p, DatatypePackage.Literals.DATA_TYPE__NULL_VALUE));
  }

  @Override
  protected void retrieveContainer(EObject element_p, List<EObject> result_p, IContext context_p) {
    super.retrieveContainer(element_p, result_p, context_p);
  }

}
