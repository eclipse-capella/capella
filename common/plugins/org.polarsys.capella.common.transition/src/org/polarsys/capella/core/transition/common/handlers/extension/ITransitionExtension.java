/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.common.handlers.extension;

import java.util.Collection;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.transition.common.handlers.scope.IScopeFilter;
import org.polarsys.capella.core.transition.common.handlers.scope.IScopeRetriever;

import com.google.common.collect.SetMultimap;

public interface ITransitionExtension {

  String getViewpointID();

  Collection<? extends IScopeFilter> getScopeFilters();

  Collection<? extends IScopeRetriever> getScopeRetrievers();

  Set<Entry<EObject, EObject>> initializeBlockArchitecture(BlockArchitecture source, BlockArchitecture target,
      boolean createTarget);

  void addAdditionnalTraceabilityMappings(SetMultimap<EObject, EObject> map, PhysicalArchitecture sourcePA,
      BlockArchitecture targetBlockArchitecture);

  String getAdditionalMatchKey(EObject element_p, ITreeDataScope<EObject> scope_p);

}
