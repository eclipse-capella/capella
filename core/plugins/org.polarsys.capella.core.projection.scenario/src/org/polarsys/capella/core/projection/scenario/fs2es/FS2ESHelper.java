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
package org.polarsys.capella.core.projection.scenario.fs2es;

import static org.polarsys.capella.common.helpers.cache.ModelCache.getCache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.oa.ActivityAllocation;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.core.data.oa.RoleAllocation;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.resolver.ResolverFinalizer;
import org.polarsys.capella.core.projection.scenario.Messages;
import org.polarsys.capella.core.projection.scenario.ScenarioTransfo;
import org.polarsys.capella.core.projection.scenario.helpers.ScenarioHorizontalHelper;
import org.polarsys.capella.core.tiger.IResolver;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.TransfoException;

public class FS2ESHelper extends ScenarioHorizontalHelper {

  private Collection<Component> getAllocatingBlocks(AbstractFunction function) {
    Collection<Component> allocatingBlocks = new HashSet<>();
    // Retrieve all related parts
    for (ComponentFunctionalAllocation allocation : function.getComponentFunctionalAllocations()) {
      AbstractFunctionalBlock block = allocation.getBlock();
      if (block instanceof Component) {
        Component element = (Component) block;
        allocatingBlocks.add(element);
      }
    }

    if (function instanceof OperationalActivity) {
      for (ActivityAllocation allocation : ((OperationalActivity) function).getActivityAllocations()) {
        Role allocatingRole = allocation.getRole();
        if (allocatingRole != null) {
          for (RoleAllocation roleAllocation : allocatingRole.getRoleAllocations()) {
            if (roleAllocation.getEntity() != null) {
              allocatingBlocks.add(roleAllocation.getEntity());
            }
          }
        }
      }
    }

    return allocatingBlocks;
  }

  /**
   * @param function
   * @return
   * @throws TransfoException
   */
  private AbstractInstance getRelatedPart(InstanceRole role, AbstractFunction function, ITransfo transfo) {
    List<EObject> elements = new ArrayList<>();
    boolean isMultiAllocation = false;
    int nbAllocation = 0;

    // Retrieve allocating blocks for the given function
    Collection<Component> allocatingBlocks = getAllocatingBlocks(function);

    // if all leaf functions are allocated to the same component, we use it
    Iterator<AbstractFunction> leafs = getCache(FunctionExt::getAllLeafAbstractFunctions, function).iterator();
    if (leafs.hasNext()) {
      AbstractFunction leaf = leafs.next();
      Collection<Component> leafAllocatingBlocks = getAllocatingBlocks(leaf);
      while (leafs.hasNext() && (leafAllocatingBlocks.size() > 0)) {
        leaf = leafs.next();
        leafAllocatingBlocks.retainAll(getAllocatingBlocks(leaf));
      }
      allocatingBlocks.addAll(leafAllocatingBlocks);
    }

    // Retrieve all related parts
    for (Component element : allocatingBlocks) {
      if (element.getRepresentingParts().size() > 0) {
        nbAllocation++;
        elements.addAll(element.getRepresentingParts());
      }
    }

    isMultiAllocation = nbAllocation > 1;

    if (elements.size() == 0) {
      return null;
    }
    // If only one part is related, select it
    if (elements.size() == 1) {
      return (AbstractInstance) elements.get(0);
    }

    // Retrieve a resolver
    IResolver resolver = ResolverFinalizer.getResolver(transfo);
    if (resolver == null) {
      // If no resolver, return the first
      return (AbstractInstance) elements.get(0);
    }

    String message = ICommonConstants.EMPTY_STRING;
    if (isMultiAllocation) {
      message = Messages.Rule_InstanceRole_AllocatedMoreThanOnce;
    } else {
      message = Messages.Rule_InstanceRole_AllocatingMultipart;
    }
    message += Messages.Rule_InstanceRole_Selection;
    message = NLS.bind(message, EObjectLabelProviderHelper.getText(role), EObjectLabelProviderHelper.getText(role));

    List<EObject> result =
        resolver.resolve(function, elements, ScenarioTransfo.getTitle(transfo), message, false, transfo, new EObject[] { function });

    if (result.size() > 0) {
      return (AbstractInstance) result.get(0);
    }

    return null;
  }

  @Override
  public List<AbstractInstance> getTargetInstances(InstanceRole role_p, IContext context_p) {
    AbstractFunction function = (AbstractFunction) role_p.getRepresentedInstance();
    AbstractInstance part = getRelatedPart(role_p, function, context_p.getTransfo());
    if (part != null) {
      return Collections.singletonList(part);
    }
    return Collections.emptyList();
  }

}
