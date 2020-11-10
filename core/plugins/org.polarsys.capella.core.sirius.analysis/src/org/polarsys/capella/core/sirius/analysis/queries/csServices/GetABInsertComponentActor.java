/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.analysis.queries.csServices;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;

public abstract class GetABInsertComponentActor extends AbstractQuery {
  /**
   * 
   * @param target
   * @return whether a component can be moved into a target element
   */
  Predicate<Component> getCanBeMovedIntoPredicate(Object target) {
    if (target instanceof Component) {
      return component -> ComponentExt.canMoveInto(component, (Component) target);
    } else if (target instanceof ComponentPkg) {
      return component -> ComponentExt.canMoveInto(component, (ComponentPkg) target);
    }
    return component -> false;
  }

  protected List<Component> getComponentsToInsertFromArchitecture(Object input, BlockArchitecture architecture) {
    return BlockArchitectureExt.getAllComponents(architecture).stream()
        .filter(component -> !ComponentExt.isActor(component)).filter(getCanBeMovedIntoPredicate(input))
        .collect(Collectors.toList());
  }

  protected List<Component> getActorsToInsertFromArchitecture(Object input, BlockArchitecture architecture) {
    return BlockArchitectureExt.getAllComponents(architecture).stream().filter(ComponentExt::isActor)
        .filter(getCanBeMovedIntoPredicate(input)).collect(Collectors.toList());
  }
}
