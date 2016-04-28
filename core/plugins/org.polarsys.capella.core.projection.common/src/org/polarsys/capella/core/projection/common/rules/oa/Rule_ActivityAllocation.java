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
package org.polarsys.capella.core.projection.common.rules.oa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.oa.ActivityAllocation;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.core.data.oa.RoleAllocation;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.projection.common.rules.cs.Rule_ComponentFunctionalAllocation;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.TransfoException;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.core.tiger.helpers.TigerRelationshipHelper;
import org.polarsys.capella.core.tiger.impl.TransfoEngine;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

/**
 */
public class Rule_ActivityAllocation extends Rule_ComponentFunctionalAllocation {

  /**
   * @param sourceType
   * @param targetType
   */
  public Rule_ActivityAllocation() {
    super(OaPackage.Literals.ACTIVITY_ALLOCATION, FaPackage.Literals.COMPONENT_FUNCTIONAL_ALLOCATION);
  }

  public Rule_ActivityAllocation(EClass source, EClass target) {
    super(source, target);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected Collection<EObject> transformElement(EObject element, IContext context) {
    return createComponentFunctionalAllocations(element, context);
  }

  /**
   * @param element
   */
  private Collection<EObject> createComponentFunctionalAllocations(EObject element, IContext context) {

    ActivityAllocation allocation = (ActivityAllocation) element;
    List<ComponentFunctionalAllocation> result = new ArrayList<ComponentFunctionalAllocation>();

    //Retrieve previously created transitioned allocations (in cases of update, some allocations can be created but not yet used)
    int nbAllocations = 0;
    for (EObject tAllocation : Query.retrieveTransformedElements(allocation, context.getTransfo(), getTargetType())) {
      if (tAllocation.eContainer() == null) {
        nbAllocations++;
      }
    }

    Set<AbstractFunctionalBlock> entities = retrieveComponentsToAllocate(allocation, context);
    int nbEntities = entities.size();

    //Create the correct number of links
    if (nbEntities - nbAllocations > 0) {
      for (int i = 0; i < nbEntities - nbAllocations; i++) {
        ComponentFunctionalAllocation allocationTransformated = (ComponentFunctionalAllocation) super.transformDirectElement(allocation, context);
        result.add(allocationTransformated);
      }
    }

    return (Collection) result;
  }

  @Override
  protected boolean shouldUpdate() {
    return false;
  }

  @Override
  public void update_(EObject element_p, ITransfo transfo_p) {
    super.update_(element_p, transfo_p);
    IContext context_p = IContext.getContext(transfo_p);

    createLinks(element_p, createComponentFunctionalAllocations(element_p, context_p));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);

    ActivityAllocation allocation = (ActivityAllocation) element_p;
    TraceableElement t = (TraceableElement) Query.retrieveTransformedElement(allocation.getTargetElement(), context_p.getTransfo());
    List<?> elements = Query.retrieveTransformedElements(element_p, context_p.getTransfo(), getTargetType());

    if (elements.size() > 0 && elements.get(0).equals(result_p)) {

      //Retrieve all settable allocations
      LinkedList<ComponentFunctionalAllocation> allocs = new LinkedList<ComponentFunctionalAllocation>();
      for (Object element : elements) {
        if (element instanceof ComponentFunctionalAllocation) {
          ComponentFunctionalAllocation a = (ComponentFunctionalAllocation) element;
          if (a.eContainer() == null) {
            allocs.add(a);
          }
        }
      }


      //For all components in which the ActivityAllocation needs to be allocated
      for (AbstractFunctionalBlock block : retrieveComponentsToAllocate(allocation, context_p)) {

        if (allocs.size() > 0) {
          ComponentFunctionalAllocation a = allocs.remove();
          block.getOwnedFunctionalAllocation().add(a);
          a.setTargetElement(t);
          a.setSourceElement(block);
        }
      }
    }

  }

  /**
   * Retrieve all components in which the ActivityAllocation needs to be allocated
   */
  @SuppressWarnings("unchecked")
  private Set<AbstractFunctionalBlock> retrieveComponentsToAllocate(ActivityAllocation allocation, IContext context_p) {

    TraceableElement t = (TraceableElement) Query.retrieveFirstTransformedElement(allocation.getTargetElement(), context_p.getTransfo());
    HashSet<AbstractFunctionalBlock> entities = new HashSet<AbstractFunctionalBlock>();
    Role role = (Role) allocation.eContainer();

    if (role != null) {
      for (RoleAllocation roleAllocation : role.getRoleAllocations()) {

        Entity source = roleAllocation.getEntity();
        List<Component> transitioneds = new ArrayList<Component>();
        if (TransformationHandlerHelper.getInstance(context_p).isOrWillBeTransformed(source, context_p).isOK()) {
          transitioneds = (List) Query.retrieveTransformedElements(source, context_p.getTransfo(), CsPackage.Literals.COMPONENT);
          for (EObject transitioned : transitioneds) {
            if (transitioned instanceof AbstractFunctionalBlock) {
              entities.add((AbstractFunctionalBlock) transitioned);
            }
          }
        }

        if (transitioneds.size() > 0) {

          for (EObject transitionedElement : Query.retrieveTransformedElements(allocation, context_p.getTransfo(), getTargetType())) {
            if (transitionedElement instanceof ComponentFunctionalAllocation) {
              ComponentFunctionalAllocation transitionedAllocation = (ComponentFunctionalAllocation) transitionedElement;
              AbstractFunctionalBlock block = transitionedAllocation.getBlock();
              EObject target = transitionedAllocation.getTargetElement();
              if (transitioneds.contains(block) && target.equals(t)) {
                entities.remove(block);
              }
            }
          }
        }
      }
    }
    return entities;
  }

  /**
   * 
   */
  @SuppressWarnings("unchecked")
  private void createLinks(EObject source, Collection<EObject> elements) {

    for (EObject target : elements) {
      List<EObject> transformed = (List<EObject>) _transfo.get(TransfoEngine.TRANSFORMED_ELEMENTS);
      if (!transformed.contains(target)) {
        transformed.add(target);
      }

      if (Query.retrieveSourceElements(target, _transfo, getSourceType()).size() == 0) {

        // Links
        AbstractTrace newLink;
        try {
          newLink = TigerRelationshipHelper.createTransfoLink(source, target, _transfo);

          if (newLink != null) {
            List<AbstractTrace> links = (List<AbstractTrace>) _transfo.get(TransfoEngine.NEW_LINKS);

            links.add(newLink);
          }
        } catch (TransfoException exception_p) {
          //Nothing. If no link, no care
        }
      }
    }
  }

}
