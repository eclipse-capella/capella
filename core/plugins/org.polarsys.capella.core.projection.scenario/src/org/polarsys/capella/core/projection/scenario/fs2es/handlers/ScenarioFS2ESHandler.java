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
package org.polarsys.capella.core.projection.scenario.fs2es.handlers;

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
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.information.PartitionableElement;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.oa.ActivityAllocation;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.core.data.oa.RoleAllocation;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.resolver.ResolverFinalizer;
import org.polarsys.capella.core.projection.scenario.CommonScenarioHelper;
import org.polarsys.capella.core.projection.scenario.Messages;
import org.polarsys.capella.core.projection.scenario.fs2es.rules.FS2CESFinalizer;
import org.polarsys.capella.core.projection.scenario.handlers.ScenarioHorizontalHandler;
import org.polarsys.capella.core.tiger.IResolver;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.TransfoException;

public class ScenarioFS2ESHandler extends ScenarioHorizontalHandler {

  private Collection<PartitionableElement> getAllocatingBlocks(AbstractFunction function_p) {
    Collection<PartitionableElement> allocatingBlocks = new HashSet<PartitionableElement>();
    // Retrieve all related parts
    for (ComponentFunctionalAllocation allocation : function_p.getComponentFunctionalAllocations()) {
      AbstractFunctionalBlock block = allocation.getBlock();
      if ((block != null) && (block instanceof PartitionableElement)) {
        PartitionableElement element = (PartitionableElement) block;
        allocatingBlocks.add(element);
      }
    }

    if (function_p instanceof OperationalActivity) {
      for (ActivityAllocation allocation : ((OperationalActivity) function_p).getActivityAllocations()) {
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
   * @param function_p
   * @return
   * @throws TransfoException
   */
  private AbstractInstance getRelatedPart(InstanceRole role, AbstractFunction function_p, ITransfo transfo_p) {
    List<EObject> elements = new ArrayList<EObject>();
    boolean isMultiAllocation = false;
    int nbAllocation = 0;

    // Retrieve allocating blocks for the given function
    Collection<PartitionableElement> allocatingBlocks = getAllocatingBlocks(function_p);

    // if all leaf functions are allocated to the same component, we use it
    Iterator<AbstractFunction> leafs = FunctionExt.getAllLeafAbstractFunctions(function_p).iterator();
    if (leafs.hasNext()) {
      AbstractFunction leaf = leafs.next();
      Collection<PartitionableElement> leafAllocatingBlocks = getAllocatingBlocks(leaf);
      while (leafs.hasNext() && (leafAllocatingBlocks.size() > 0)) {
        leaf = leafs.next();
        leafAllocatingBlocks.retainAll(getAllocatingBlocks(leaf));
      }
      allocatingBlocks.addAll(leafAllocatingBlocks);
    }

    // Retrieve all related parts
    for (PartitionableElement element : allocatingBlocks) {
      if (element.getRepresentingPartitions().size() > 0) {
        nbAllocation++;
        elements.addAll(element.getRepresentingPartitions());
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
    IResolver resolver = ResolverFinalizer.getResolver(transfo_p);
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
        resolver.resolve(function_p, elements, CommonScenarioHelper.getTitle(transfo_p), message, false, transfo_p, new EObject[] { function_p });

    if (result.size() > 0) {
      return (AbstractInstance) result.get(0);
    }

    return null;
  }

  @Override
  public List<AbstractInstance> getRelatedInstances(InstanceRole role_p, IContext context_p) {
    AbstractFunction function = (AbstractFunction) role_p.getRepresentedInstance();
    AbstractInstance part = getRelatedPart(role_p, function, context_p.getTransfo());
    if (part != null) {
      return Collections.singletonList(part);
    }
    return Collections.emptyList();
  }

  @Override
  public InstanceRole getInstanceRole(AbstractInstance tracedInstance_p, IContext context_p) {
    return FS2CESFinalizer.getInstanceRole(tracedInstance_p);
  }

}
