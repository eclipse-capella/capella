package org.polarsys.capella.core.model.helpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.data.ctx.SystemComponentPkg;
import org.polarsys.capella.core.data.epbs.ConfigurationItemPkg;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.data.oa.EntityPkg;
import org.polarsys.capella.core.data.pa.PhysicalComponentPkg;

public class ComponentPkgExt {

  /**
   * 
   * @param componentPkg
   * @return the first-level Components in this package and its sub-packages
   */
  public static List<Component> getOwnedComponents(ComponentPkg componentPkg) {
    List<Component> components = new ArrayList<>();
    if (componentPkg == null) {
      return Collections.emptyList();
    }
    if (componentPkg instanceof EntityPkg) {
      EntityPkg entityPkg = (EntityPkg) componentPkg;
      components.addAll(entityPkg.getOwnedEntities());
      for (EntityPkg pkg : entityPkg.getOwnedEntityPkgs()) {
        components.addAll(getOwnedComponents(pkg));
      }
    } else if (componentPkg instanceof SystemComponentPkg) {
      SystemComponentPkg systemComponentPkg = (SystemComponentPkg) componentPkg;
      components.addAll(systemComponentPkg.getOwnedSystemComponents());
      for (SystemComponentPkg pkg : systemComponentPkg.getOwnedSystemComponentPkgs()) {
        components.addAll(getOwnedComponents(pkg));
      }
    } else if (componentPkg instanceof LogicalComponentPkg) {
      LogicalComponentPkg logicalComponentPkg = (LogicalComponentPkg) componentPkg;
      components.addAll(logicalComponentPkg.getOwnedLogicalComponents());
      for (LogicalComponentPkg pkg : logicalComponentPkg.getOwnedLogicalComponentPkgs()) {
        components.addAll(getOwnedComponents(pkg));
      }
    } else if (componentPkg instanceof PhysicalComponentPkg) {
      PhysicalComponentPkg physicalComponentPkg = (PhysicalComponentPkg) componentPkg;
      components.addAll(physicalComponentPkg.getOwnedPhysicalComponents());
      for (PhysicalComponentPkg pkg : physicalComponentPkg.getOwnedPhysicalComponentPkgs()) {
        components.addAll(getOwnedComponents(pkg));
      }
    } else if (componentPkg instanceof ConfigurationItemPkg) {
      ConfigurationItemPkg configurationItemComponentPkg = (ConfigurationItemPkg) componentPkg;
      components.addAll(configurationItemComponentPkg.getOwnedConfigurationItems());
      for (ConfigurationItemPkg pkg : configurationItemComponentPkg.getOwnedConfigurationItemPkgs()) {
        components.addAll(getOwnedComponents(pkg));
      }
    }
    return components;
  }

  public static List<Component> getAllActors(ComponentPkg componentPkg) {
    return getOwnedComponents(componentPkg).stream().filter(comp -> ComponentExt.isActor(comp))
        .collect(Collectors.toList());
  }

}
