/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.layout.ju.layout.compare;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.api.IDiffPolicy;
import org.eclipse.emf.diffmerge.api.IMatchPolicy;
import org.eclipse.emf.diffmerge.ui.specification.IComparisonMethod;
import org.eclipse.emf.diffmerge.ui.specification.IComparisonMethodFactory;
import org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition;
import org.eclipse.emf.diffmerge.ui.specification.ext.DefaultComparisonMethod;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.test.diagram.layout.ju.layout.LayoutPackage;

public class LayoutComparisonMethodFactory implements IComparisonMethodFactory {

  @Override
  public String getLabel() {
    return NLS.bind("Junit: {0} comparison", LayoutPackage.eNS_PREFIX);
  }

  @Override
  public Collection<? extends Class<?>> getOverridenClasses() {
    return Collections.emptyList();
  }

  @Override
  public IComparisonMethod createComparisonMethod(IModelScopeDefinition leftScopeSpec_p,
      IModelScopeDefinition rightScopeSpec_p, IModelScopeDefinition ancestorScopeSpec_p) {
    IComparisonMethod method =  new DefaultComparisonMethod(leftScopeSpec_p, rightScopeSpec_p, ancestorScopeSpec_p) {

      @Override
      protected IDiffPolicy createDiffPolicy() {
        return new LayoutDiffPolicy();
      }
      
      @Override
      protected IMatchPolicy createMatchPolicy() {
        return new LayoutMatchPolicy();
      }
      
    };
    return method;
  }

  @Override
  public boolean isApplicableTo(IModelScopeDefinition leftScopeSpec_p, IModelScopeDefinition rightScopeSpec_p,
      IModelScopeDefinition ancestorScopeSpec_p) {
    if (leftScopeSpec_p != null && leftScopeSpec_p.getEntrypoint() instanceof URI) {
      if (!LayoutPackage.eNS_PREFIX.equals(((URI)leftScopeSpec_p.getEntrypoint()).fileExtension())) {
        return false;
      }
    }

    if (rightScopeSpec_p != null && rightScopeSpec_p.getEntrypoint() instanceof URI) {
      if (!LayoutPackage.eNS_PREFIX.equals(((URI)rightScopeSpec_p.getEntrypoint()).fileExtension())) {
        return false;
      }
    }
    return true;
  }

}
