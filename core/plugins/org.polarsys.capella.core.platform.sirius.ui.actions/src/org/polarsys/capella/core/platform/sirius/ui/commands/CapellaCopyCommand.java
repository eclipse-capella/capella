/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.ui.commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.CopyCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Property;

/**
 * The standard EMF Copy command creates copies of the argument's containment hierarchy. 
 * For Capella, this is not sufficient, as we might need to copy additional objects
 * that are outside the containment hierarchy.
 * 
 * Additionally, we need to counter-act the fact that newly created model objects
 * are automatically added to the famous 'holding resource'. For a copy that's
 * not yet attached to a container, this is probably not desired,
 * so we remove the object from the 'holding resource' resource immediately
 * before the command completes. (Hopefully this is not too late)
 */
public class CapellaCopyCommand extends CommandWrapper {
  
  /*
   * Please use the static create method to obtain instances.
   */
  private CapellaCopyCommand(Command wrapped){
    super(wrapped);
  }
  
  /**
   * Creates a command which copies all containment trees in the specified
   * collection PLUS several other objects that might be external to the
   * containment tree but must be copied anyway to satisfy the business
   * constraints of a capella model.
   * 
   * @see findCopyClosure
   * @param domain
   * @param collection
   * @return
   */
  public static Command create(EditingDomain domain, Collection<?> collection){
    return new CapellaCopyCommand(CopyCommand.create(domain, findCopyClosure(collection)));
  }
  
  /**
   * Find the copy hull of a set of model elements. The copy hull is the 
   * join between the argument collection and a set of objects that require
   * to be copied in addition to the arguments.
   * 
   * Currently, the only case where the copy hull is a strict superset
   * of the argument occurs when the argument contains a class which
   * is part of an association. In this case, the association is
   * also copied.
   * 
   * @param sources a non-null collection
   * @return a non-null collection
   */
  public static Collection<?> findCopyClosure(Collection<?> sources) {
    Collection<Object> result = new ArrayList<Object>();
    // Separate model elements from other objects
    Collection<EObject> elements = new ArrayList<EObject>();
    for (Object o : sources) {
      if (o instanceof EObject)
        elements.add((EObject)o);
      else
        result.add(o);
    }
    // Look for properties (requiring associations) within the direct
    // children of the selection only. This is because:
    // - as a directly selected element, a property goes without an association
    // - deeper than the direct children, it would be within a class within a
    //    package, so the association would be covered anyway
    // Consequently, the impact on performance remains small.
    for (EObject root : new ArrayList<EObject>(elements)) {
      if (!(root instanceof Association)) {
        for (EObject child : root.eContents()) {
          if (child instanceof Property) {
            // If a property is referenced by an association, include the association
            Property property = (Property)child;
            List<EObject> associations = EObjectExt.getReferencers(property,
                InformationPackage.eINSTANCE.getAssociation_NavigableMembers());
            if (!associations.isEmpty()) {
              // Size can only be 1
              Association association = (Association)associations.get(0);
              if (!EcoreUtil.isAncestor(elements, association))
                elements.add(association);
            }
          }
        }
      }
    }
    result.addAll(elements);
    return result;
  }
  
  /**
   * Execute our copy command and remove all resulting copies from their
   * resource.
   * 
   * Every model object is added to a dummy resource immediately on creation
   * (see ModelObjectImpl()). Here we counter this, for example to
   * avoid retrieving copied objects in queries before they are
   * attached to a container.
   * {@inheritDoc}
   */ 
  @Override
  public void execute(){
    super.execute();
    removeResultObjectsFromResource(getResult());
  }
  
  protected void removeResultObjectsFromResource(Collection<?> result){
    for (Object r : result) {
      EObject eObject = (EObject) r;
      Resource resource = eObject.eResource();
      if (null != resource) {
        resource.getContents().remove(eObject);
      }
    }
  }
}
