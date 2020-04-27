/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.information.validation.class_;

import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.common.utils.graph.BasicDirectedGraph;
import org.polarsys.capella.common.utils.graph.IDirectedGraph;
import org.polarsys.capella.common.utils.graph.SCCSearch;
import org.polarsys.capella.common.mdsofa.common.misc.Couple;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.model.helpers.ClassExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 */
public class MDCHK_DWF_D16 extends AbstractValidationRule {

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("boxing")
  @Override
  public IStatus validate(IValidationContext ctx) {
    IStatus result = null;
    DataPkg pack = (DataPkg) ctx.getTarget();
    List<List<EObject>> sccs = findSCC(pack).getValue();
    if ((sccs != null) && (sccs.size() > 0)) {
      result = ctx.createFailureStatus(pack, sccs.size());
    } else {
      result = ctx.createSuccessStatus();
    }
    return result;
  }

  public Couple<IDirectedGraph<EObject>, List<List<EObject>>> findSCC(DataPkg pack) {
    BasicDirectedGraph<EObject> graph = new BasicDirectedGraph<EObject>();
    for (Class clz : pack.getOwnedClasses()) {
      Collection<EObject> dependencies = ClassExt.getClassDependencies2(clz).get(pack);
      if (dependencies != null) {
        for (EObject dependency : dependencies) {
          graph.addEdge(clz, dependency);
        }
      }
    }

    SCCSearch<EObject> search = new SCCSearch<EObject>();
    return new Couple<IDirectedGraph<EObject>, List<List<EObject>>>(graph, search.findSCC(graph, false));
  }

}
