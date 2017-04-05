/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.vp.ms.provider;

import static com.google.common.base.Predicates.equalTo;
import static com.google.common.base.Predicates.not;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;
import org.polarsys.capella.common.model.IDelegatedListener;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalActor;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.vp.ms.CSConfiguration;
import org.polarsys.kitalpha.emde.model.ExtensibleElement;
import org.polarsys.kitalpha.emde.model.edit.provider.ExtensionItemPropertyDescriptor;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

public class ChildConfigurationsPropertyDescriptor extends ExtensionItemPropertyDescriptor {

  public ChildConfigurationsPropertyDescriptor(AdapterFactory adapterFactory, ResourceLocator resourceLocator,
      String displayName, String description, EStructuralFeature feature, boolean isSettable, boolean multiLine,
      boolean sortChoices, Object staticImage, String category, String[] filterFlags) {
    super(adapterFactory, resourceLocator, displayName, description, feature, isSettable, multiLine, sortChoices,
        staticImage, category, filterFlags);
  }

  // FIXME: canSetProperty is copied from ModelElementItemProvider.createPropertyDescriptor
  // Depends on https://bugs.polarsys.org/show_bug.cgi?id=739
  /**
   * @see org.polarsys.capella.common.mdsofa.emf.edit.provider.DslfactoryItemPropertyDescriptor#canSetProperty(java.lang.Object)
   */
  @Override
  public boolean canSetProperty(Object object_p) {
    boolean result = true;
    for (IConfigurationElement configurationElement : ExtensionPointHelper
        .getConfigurationElements("org.polarsys.capella.common.model", "DelegatedListener")) { //$NON-NLS-1$ //$NON-NLS-2$
      IDelegatedListener contributor = (IDelegatedListener) ExtensionPointHelper.createInstance(configurationElement,
          ExtensionPointHelper.ATT_CLASS);
      if (null != contributor) {
        result &= contributor.canSetProperty(object_p);
      }
    }
    if (result) {
      result &= super.canSetProperty(object_p);
    }
    return result;
    // TODO mettre en place un point d'extension qui sera implemente par le plugin collab capella.
    // En effet, ce dernier branchera le canSetProperty sur le lock cdo.
    // IPropertyEdition propertyEdition = null;
    // if (null == propertyEdition) {
    // return super.canSetProperty(object_p);
    // }
    // return propertyEdition.canSetProperty(object_p);
  }

  private Collection<CSConfiguration> getOwnedConfigurations(ExtensibleElement e,
      Predicate<CSConfiguration> predicate) {
    Collection<CSConfiguration> result = new ArrayList<CSConfiguration>();
    for (EObject ext : e.getOwnedExtensions()) {
      if (ext instanceof CSConfiguration) {
        if (predicate.apply((CSConfiguration) ext)) {
          result.add((CSConfiguration) ext);
        }
      }
    }
    return result;
  }

  private Collection<CSConfiguration> getOwnedConfigurations(ExtensibleElement e) {
    return getOwnedConfigurations(e, Predicates.<CSConfiguration> alwaysTrue());
  }

  private Collection<CSConfiguration> getSiblingConfigurations(ExtensibleElement parent,
      CSConfiguration configuration) {
    return getOwnedConfigurations(parent, not(equalTo(configuration)));
  }

  @Override
  protected Collection<?> getComboBoxObjects(Object object) {
    Component parent = (Component) ((CSConfiguration) object).eContainer();

    Collection<CSConfiguration> result = new ArrayList<CSConfiguration>();
    result.addAll(getSiblingConfigurations(parent, (CSConfiguration) object));

    if (parent instanceof LogicalComponent) {

      Deque<LogicalComponent> toVisit = new ArrayDeque<>(((LogicalComponent) parent).getOwnedLogicalComponents());
      while (toVisit.size() > 0) {
        LogicalComponent c = toVisit.pop();
        result.addAll(getOwnedConfigurations(c));
        for (LogicalComponent child : c.getOwnedLogicalComponents()) {
          toVisit.push(child);
        }
      }

    } else if (parent instanceof PhysicalComponent) {

      Deque<PhysicalComponent> toVisit = new ArrayDeque<>();
      toVisit.addAll(((PhysicalComponent) parent).getOwnedPhysicalComponents());
      toVisit.addAll(((PhysicalComponent) parent).getDeployedPhysicalComponents());
      Set<PhysicalComponent> seen = new HashSet<>();
      seen.add((PhysicalComponent) parent);
      walkPhysicalComponentHierarchy(result, toVisit, seen);

    } else if (parent instanceof PhysicalActor) {

      Deque<PhysicalComponent> toVisit = new ArrayDeque<>();
      toVisit.addAll(((PhysicalActor) parent).getDeployedPhysicalComponents());
      walkPhysicalComponentHierarchy(result, toVisit, new HashSet<PhysicalComponent>());

    }

    return result;
  }

  private void walkPhysicalComponentHierarchy(Collection<CSConfiguration> result, Deque<PhysicalComponent> toVisit,
      Set<PhysicalComponent> seen) {
    while (toVisit.size() > 0) {
      PhysicalComponent c = toVisit.pop();
      if (seen.add(c)) {
        result.addAll(getOwnedConfigurations(c));
        for (PhysicalComponent child : c.getOwnedPhysicalComponents()) {
          toVisit.push(child);
        }
        for (PhysicalComponent deployed : c.getDeployedPhysicalComponents()) {
          toVisit.push(deployed);
        }
      }
    }
  }

}
