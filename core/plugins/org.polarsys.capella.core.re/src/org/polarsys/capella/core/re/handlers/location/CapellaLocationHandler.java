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
package org.polarsys.capella.core.re.handlers.location;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.handlers.location.DefaultLocationHandler;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.model.helpers.move.CapellaMoveHelper;
import org.polarsys.capella.core.model.helpers.move.MoveHelper;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.options.IPropertyHandler;
import org.polarsys.capella.core.transition.common.handlers.options.OptionsHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class CapellaLocationHandler extends DefaultLocationHandler {

  final Collection<SpecificPackageLocationAdapter> adapters = new ArrayList<SpecificPackageLocationAdapter>();

  @Override
  protected Supplier<EObject> getSpecificPackage(CatalogElementLink link, CatalogElementLink oppositeLink,
      IContext context) {

    CatalogElement rpl = link.getSource();
    SpecificPackageLocationAdapter adapter = (SpecificPackageLocationAdapter) EcoreUtil.getExistingAdapter(rpl,
        SpecificPackageLocationAdapter.class);

    if (adapter == null) {
      String scope = (String) context.get(ITransitionConstants.OPTIONS_SCOPE);
      IPropertyContext propertyContext = ((IPropertyHandler) OptionsHandlerHelper.getInstance(context))
          .getPropertyContext(context, scope);
      Resource rplResource = EcoreUtil.getRootContainer(rpl).eResource();
      adapter = new SpecificPackageLocationAdapter(propertyContext, rplResource);
      rpl.eAdapters().add(adapter);
      adapters.add(adapter);
    }

    return adapter.getSpecificPackage(link.getTarget());

  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected boolean isValidContainement(EObject targetContainer_p, EObject target_p) {
    List<EObject> elements = Collections.singletonList(target_p);

    MoveHelper helper = new CapellaMoveHelper();
    boolean isSemanticallyCorrect = helper.checkSemanticRules(elements, targetContainer_p).isOK();
    if (!isSemanticallyCorrect) {
      return false;
    }

    // Checks all target eReferences compatibility with all selected model elements eClass.
    if (!helper.checkEMFRules(elements, targetContainer_p).isOK()) {
      return false;
    }

    return super.isValidContainement(targetContainer_p, target_p);
  }

  @Override
  protected CatalogElementLink getLinkRelatedElement(CatalogElementLink link_p, IContext context_p) {
    return null;
  }

  @Override
  protected Collection<EObject> getRelatedElements(EObject element_p) {
    if (element_p instanceof Part) {
      return Arrays.asList(new EObject[] { element_p, ((Part) element_p).getAbstractType() });
    }
    return Collections.singleton(element_p);
  }

  @Override
  protected boolean isInitialSelectionValidContainer(EObject selection_p, CatalogElementLink link_p,
      IContext context_p) {

    if ((link_p.getTarget() instanceof Component) && (selection_p instanceof Part)) {
      return false;
    }
    return super.isInitialSelectionValidContainer(selection_p, link_p, context_p);
  }

  @Override
  public IStatus dispose(IContext context) {
    for (SpecificPackageLocationAdapter a : adapters) {
      a.dispose();
    }
    return Status.OK_STATUS;
  }

}
