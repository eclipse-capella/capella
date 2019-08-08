package org.polarsys.capella.core.model.helpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.data.cs.Part;
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
  public static List<Component> getSubDefinedComponents(ComponentPkg componentPkg) {
    List<Component> components = new ArrayList<>();
    if (componentPkg == null) {
      return Collections.emptyList();
    }
    if (componentPkg instanceof EntityPkg) {
      EntityPkg entityPkg = (EntityPkg) componentPkg;
      components.addAll(entityPkg.getOwnedEntities());
      for (EntityPkg pkg : entityPkg.getOwnedEntityPkgs()) {
        components.addAll(getSubDefinedComponents(pkg));
      }
    } else if (componentPkg instanceof SystemComponentPkg) {
      SystemComponentPkg systemComponentPkg = (SystemComponentPkg) componentPkg;
      components.addAll(systemComponentPkg.getOwnedSystemComponents());
      for (SystemComponentPkg pkg : systemComponentPkg.getOwnedSystemComponentPkgs()) {
        components.addAll(getSubDefinedComponents(pkg));
      }
    } else if (componentPkg instanceof LogicalComponentPkg) {
      LogicalComponentPkg logicalComponentPkg = (LogicalComponentPkg) componentPkg;
      components.addAll(logicalComponentPkg.getOwnedLogicalComponents());
      for (LogicalComponentPkg pkg : logicalComponentPkg.getOwnedLogicalComponentPkgs()) {
        components.addAll(getSubDefinedComponents(pkg));
      }
    } else if (componentPkg instanceof PhysicalComponentPkg) {
      PhysicalComponentPkg physicalComponentPkg = (PhysicalComponentPkg) componentPkg;
      components.addAll(physicalComponentPkg.getOwnedPhysicalComponents());
      for (PhysicalComponentPkg pkg : physicalComponentPkg.getOwnedPhysicalComponentPkgs()) {
        components.addAll(getSubDefinedComponents(pkg));
      }
    } else if (componentPkg instanceof ConfigurationItemPkg) {
      ConfigurationItemPkg configurationItemComponentPkg = (ConfigurationItemPkg) componentPkg;
      components.addAll(configurationItemComponentPkg.getOwnedConfigurationItems());
      for (ConfigurationItemPkg pkg : configurationItemComponentPkg.getOwnedConfigurationItemPkgs()) {
        components.addAll(getSubDefinedComponents(pkg));
      }
    }
    
    return components;
  }

  /**
   * Returns components defined into the package and its children components.
   */
  public static List<Component> getAllSubDefinedComponents(ComponentPkg componentPkg) {
    List<Component> components = new ArrayList<>();
    List<Component> subDefinedComponents = getSubDefinedComponents(componentPkg);
    components.addAll(subDefinedComponents);
    subDefinedComponents.stream().forEach(c -> components.addAll(ComponentExt.getAllSubDefinedComponents(c)));
    return components;
  }
  
  public static List<Component> getAllActors(ComponentPkg componentPkg) {
    return getAllSubDefinedComponents(componentPkg).stream().filter(comp -> ComponentExt.isActor(comp))
        .collect(Collectors.toList());
  }

  public static List<Component> getSubUsedComponents(ComponentPkg componentPkg) {
    return PartExt.getComponentsOfParts(getSubParts(componentPkg));
  }

  /**
   * Returns sub parts of a component package
   */
  public static List<Part> getSubParts(ComponentPkg componentPkg) {
    List<Part> result = new ArrayList<Part>();
    result.addAll(componentPkg.getOwnedParts());
    getContainedComponentPkgs(componentPkg).stream().forEach(pkg -> result.addAll(ComponentPkgExt.getSubParts(pkg)));
    return result;
  }

  /**
   * 
   * @param componentPkg
   * @return component packages contained in this component
   */
  public static List<ComponentPkg> getContainedComponentPkgs(ComponentPkg componentPkg) {
    if (componentPkg == null) {
      return Collections.emptyList();
    }

    List<ComponentPkg> componentPkgs = new ArrayList<>();
    if (componentPkg instanceof EntityPkg) {
      componentPkgs.addAll(((EntityPkg) componentPkg).getOwnedEntityPkgs());
    } else if (componentPkg instanceof SystemComponentPkg) {
      componentPkgs.addAll(((SystemComponentPkg) componentPkg).getOwnedSystemComponentPkgs());
    } else if (componentPkg instanceof LogicalComponentPkg) {
      componentPkgs.addAll(((LogicalComponentPkg) componentPkg).getOwnedLogicalComponentPkgs());
    } else if (componentPkg instanceof PhysicalComponentPkg) {
      componentPkgs.addAll(((PhysicalComponentPkg) componentPkg).getOwnedPhysicalComponentPkgs());
    } else if (componentPkg instanceof ConfigurationItemPkg) {
      componentPkgs.addAll(((ConfigurationItemPkg) componentPkg).getOwnedConfigurationItemPkgs());
    }
    return componentPkgs;
  }

  /**
   * 
   * @param componentPkg
   * @return the nearest Component containing this package, null if it's not contained in a component
   */
  public static Component getParentComponent(ComponentPkg componentPkg) {
    for (EObject object = componentPkg.eContainer(); object != null; object = object.eContainer()) {
      if (object instanceof BlockArchitecture) {
        return null;
      } else if (object instanceof Component) {
        return (Component) object;
      }
    }
    return null;
  }

  public static boolean isRootComponentPkg(EObject object) {
    return (object instanceof ComponentPkg && object.eContainer() instanceof BlockArchitecture);
  }

  public static ComponentPkg getRootComponentPkg(ComponentPkg componentPkg) {

    ComponentPkg rootComponentPkg = componentPkg;

    for (EObject parent = componentPkg.eContainer(); parent != null; parent = parent.eContainer()) {
      if (parent instanceof ComponentPkg) {
        rootComponentPkg = (ComponentPkg) parent;
      } else if (parent instanceof BlockArchitecture) {
        return rootComponentPkg;
      }
    }

    return rootComponentPkg;
  }

  public static List<Component> getContainedComponents(ComponentPkg componentPkg) {

    if (componentPkg == null) {
      return Collections.emptyList();
    }

    List<Component> components = new ArrayList<>();

    if (componentPkg instanceof EntityPkg) {
      components.addAll(((EntityPkg) componentPkg).getOwnedEntities());
    } else if (componentPkg instanceof SystemComponentPkg) {
      components.addAll(((SystemComponentPkg) componentPkg).getOwnedSystemComponents());
    } else if (componentPkg instanceof LogicalComponentPkg) {
      components.addAll(((LogicalComponentPkg) componentPkg).getOwnedLogicalComponents());
    } else if (componentPkg instanceof PhysicalComponentPkg) {
      components.addAll(((PhysicalComponentPkg) componentPkg).getOwnedPhysicalComponents());
    }

    return components;
  }

}
