/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.layout.ju.layout.compare;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.diffdata.EComparison;
import org.eclipse.emf.diffmerge.diffdata.impl.EComparisonImpl;
import org.eclipse.emf.diffmerge.generic.api.IDiffPolicy;
import org.eclipse.emf.diffmerge.generic.api.IMatchPolicy;
import org.eclipse.emf.diffmerge.generic.api.config.IComparisonConfiguration;
import org.eclipse.emf.diffmerge.generic.api.scopes.IEditableTreeDataScope;
import org.eclipse.emf.diffmerge.ui.specification.IComparisonMethod;
import org.eclipse.emf.diffmerge.ui.specification.IComparisonMethodFactory;
import org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition;
import org.eclipse.emf.diffmerge.ui.specification.ext.DefaultComparisonMethod;
import org.eclipse.emf.diffmerge.ui.specification.ext.DefaultComparisonMethodFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.test.diagram.layout.ju.layout.LayoutPackage;

public class LayoutComparisonMethodFactory implements IComparisonMethodFactory<EObject> {

  @Override
  public String getLabel() {
    return NLS.bind("Junit: {0} comparison", LayoutPackage.eNS_PREFIX);
  }

  @Override
  public Collection<? extends Class<?>> getOverridenClasses() {
    return Collections.emptyList();
  }

  @Override
  public IComparisonMethod<EObject> createComparisonMethod(IModelScopeDefinition leftScopeSpec,
      IModelScopeDefinition rightScopeSpec, IModelScopeDefinition ancestorScopeSpec) {
    IComparisonMethod<EObject> method = new DefaultComparisonMethod<EObject>(leftScopeSpec, rightScopeSpec,
        ancestorScopeSpec, this) {

      @Override
      protected IDiffPolicy<EObject> createDiffPolicy() {
        return new LayoutDiffPolicy();
      }

      @Override
      protected IMatchPolicy<EObject> createMatchPolicy() {
        return new LayoutMatchPolicy();
      }

      @Override
      public EComparison createComparison(IEditableTreeDataScope<EObject> targetScope,
          IEditableTreeDataScope<EObject> referenceScope, IEditableTreeDataScope<EObject> ancestorScope) {
        return new EComparisonImpl(targetScope, referenceScope, ancestorScope);
      }

    };
    return method;
  }

  @Override
  public boolean isApplicableTo(IModelScopeDefinition leftScopeSpec, IModelScopeDefinition rightScopeSpec,
      IModelScopeDefinition ancestorScopeSpec) {
    if (leftScopeSpec != null && leftScopeSpec.getEntrypoint() instanceof URI) {
      if (!LayoutPackage.eNS_PREFIX.equals(((URI) leftScopeSpec.getEntrypoint()).fileExtension())) {
        return false;
      }
    }

    if (rightScopeSpec != null && rightScopeSpec.getEntrypoint() instanceof URI) {
      if (!LayoutPackage.eNS_PREFIX.equals(((URI) rightScopeSpec.getEntrypoint()).fileExtension())) {
        return false;
      }
    }
    return true;
  }

  @Override
  public IComparisonConfiguration<EObject> createComparisonConfiguration() {
    return createComparisonMethod(DefaultComparisonMethodFactory.EMPTY_MODEL_SCOPE_DEFINITION,
        DefaultComparisonMethodFactory.EMPTY_MODEL_SCOPE_DEFINITION,
        DefaultComparisonMethodFactory.EMPTY_MODEL_SCOPE_DEFINITION);
  }

  @Override
  public String getID() {
    return getClass().getName();
  }
}
