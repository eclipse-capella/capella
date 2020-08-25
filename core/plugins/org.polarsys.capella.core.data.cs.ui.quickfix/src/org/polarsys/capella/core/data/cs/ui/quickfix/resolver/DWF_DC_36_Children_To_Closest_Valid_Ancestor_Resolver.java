/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.cs.ui.quickfix.resolver;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.ef.command.ICommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewHelper;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.EntityPkg;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

/**
 * The DWF_DC_36 rule identifies Operational Human Actors that are decomposed (they contain child Entities). This quick
 * fix moves all the invalid children to the closest valid ancestor.
 *
 */
public class DWF_DC_36_Children_To_Closest_Valid_Ancestor_Resolver extends AbstractCapellaMarkerResolution {

  private static final String RULE_ID = "org.polarsys.capella.core.data.cs.validation.DWF_DC_36";

  public DWF_DC_36_Children_To_Closest_Valid_Ancestor_Resolver() {
    setLabel("Move children to their closest valid ancestor");
  }

  @Override
  public void run(IMarker marker) {
    List<EObject> elements = getModelElements(marker);

    for (EObject element : elements) {
      if (element instanceof Component) {
        Component component = (Component) element;
        Set<Component> allSubComponents = getAllSubComponents(component);

        Set<Entity> allSubEntities = new HashSet<>();
        Set<Part> allSubParts = new HashSet<>();

        for (Component subComponent : allSubComponents) {
          if (subComponent instanceof Entity) {
            Entity entity = (Entity) subComponent;
            List<Part> parts = entity.getRepresentingParts();

            allSubEntities.add(entity);
            allSubParts.addAll(parts);
          }
        }

        EObject closestValidAncestor = getclosestValidAncestor(component);
        ExecutionManager executionManager = TransactionHelper.getExecutionManager(component);
        ICommand command = new MoveChildrenToClosestValidAncestorCommand(closestValidAncestor, allSubEntities,
            allSubParts);

        executionManager.execute(command);
      }
    }

    deleteMarker(marker);

  }

  /**
   * Since Actors that are decomposed might contain only a children part or a children entity without the part (due to
   * the nature of the bug), we must extract both sub defined components and sub used components.
   * 
   * @param component
   *          the component
   * @return both sub defined components and sub used components
   */
  private static Set<Component> getAllSubComponents(Component component) {
    Set<Component> allSubComponents = new HashSet<>();

    Collection<Component> allSubDefinedComponents = ComponentExt.getAllSubDefinedComponents(component);
    Collection<Component> allSubUsedComponents = ComponentExt.getAllSubUsedComponents(component);

    allSubComponents.addAll(allSubDefinedComponents);
    allSubComponents.addAll(allSubUsedComponents);

    allSubComponents.remove(component);

    return allSubComponents;
  }

  private static EObject getclosestValidAncestor(EObject root) {

    for (EObject parent = root.eContainer(); parent != null; parent = parent.eContainer()) {
      if (ComponentExt.canCreateABActor(parent)) {
        return parent;
      }
    }

    return null;
  }

  private static class MoveChildrenToClosestValidAncestorCommand extends AbstractReadWriteCommand {

    private EObject closestValidAncestor;
    private Set<Entity> allSubEntities;
    private Set<Part> allSubParts;

    public MoveChildrenToClosestValidAncestorCommand(EObject closestValidAncestor, Set<Entity> allSubEntities,
        Set<Part> allSubParts) {
      this.closestValidAncestor = closestValidAncestor;
      this.allSubEntities = allSubEntities;
      this.allSubParts = allSubParts;
    }

    @Override
    public void run() {
      if (closestValidAncestor instanceof Entity) {
        Entity ancestor = (Entity) closestValidAncestor;
        ancestor.getOwnedEntities().addAll(allSubEntities);
        ancestor.getOwnedFeatures().addAll(allSubParts);

      } else if (closestValidAncestor instanceof EntityPkg) {
        EntityPkg ancestor = (EntityPkg) closestValidAncestor;
        ancestor.getOwnedEntities().addAll(allSubEntities);
        ancestor.getOwnedParts().addAll(allSubParts);

      }
    }
  }

  @Override
  protected boolean canResolve(IMarker marker) {
    String markerRuleId = MarkerViewHelper.getRuleID(marker, true);
    return RULE_ID.equals(markerRuleId);
  }

}
