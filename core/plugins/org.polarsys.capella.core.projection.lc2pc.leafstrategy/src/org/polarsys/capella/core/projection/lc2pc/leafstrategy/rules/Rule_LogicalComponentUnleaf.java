/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.projection.lc2pc.leafstrategy.rules;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.projection.common.ProjectionMessages;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.lc2pc.breakdownstrategy.rules.Rule_LogicalComponent;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.core.tiger.impl.TransfoEngine;

/**
 */
public class Rule_LogicalComponentUnleaf extends Rule_LogicalComponent {

  public Rule_LogicalComponentUnleaf() {
    super(LaPackage.Literals.LOGICAL_COMPONENT, PaPackage.Literals.PHYSICAL_COMPONENT_PKG);
    setNeedsContext(true);
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#when(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.tiger.impl.Transfo)
   */
  @Override
  public boolean when(EObject element_p, ITransfo transfo_p) {
    return ComponentExt.isComposite((LogicalComponent) element_p) && !(element_p.eContainer() instanceof BlockArchitecture);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus transformRequired(EObject element_p, IContext context_p) {

    EObject transfoSource = (EObject) context_p.get(TransfoEngine.TRANSFO_SOURCE);
    if (transfoSource instanceof Component) {
      if (transfoSource == element_p || ComponentExt.isComponentAncestor((Component) transfoSource, (Component) element_p)) {
        return Status.OK_STATUS;
      }
    }

    Object targetIp2 = Query.retrieveTransformedElement(element_p, context_p.getTransfo(), CsPackage.Literals.COMPONENT);
    if (targetIp2 != null) {
      return new Status(IStatus.WARNING, ProjectionMessages.Activity_Transformation, NLS.bind(ProjectionMessages.ComponentAlreadyTransitionedToComponent,
          EObjectLabelProviderHelper.getText(element_p), EObjectLabelProviderHelper.getText((EObject) targetIp2)));
    }

    return super.transformRequired(element_p, context_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveComponentGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {

    LogicalComponent component = (LogicalComponent) source_p;

    if (isRelatedToSource(component, context_p)) {
      // Retrieve sub LogicalComponentPkg from the current LC
      for (LogicalComponentPkg pkg : component.getOwnedLogicalComponentPkgs()) {
        result_p.add(pkg);
      }

      // Retrieve sub LogicalComponent from the current LC
      for (LogicalComponent subLc : component.getOwnedLogicalComponents()) {
        result_p.add(subLc);
      }
    }

  }

}
