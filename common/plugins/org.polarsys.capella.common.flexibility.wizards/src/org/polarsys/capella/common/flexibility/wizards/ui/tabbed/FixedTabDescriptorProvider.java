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
package org.polarsys.capella.common.flexibility.wizards.ui.tabbed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.internal.views.properties.tabbed.TabbedPropertyViewPlugin;
import org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyRegistry;
import org.eclipse.ui.views.properties.tabbed.ITabDescriptor;
import org.eclipse.ui.views.properties.tabbed.ITabDescriptorProvider;

/**
 * A ITabDescriptorProvider allowing to use both extension mechanism of org.eclipse.ui.views.properties.tabbed.propertySections
 * (java sections and section provided through extension points)
 * 
 * in default implementation, if you give a provider on propertySections extension point providing java-sections, 
 * you can't benefit of other sections provided through extension point.
 * 
 * This descriptorProvider allow to declare a provider, providing java-sections and sections from extension point.
 */
public abstract class FixedTabDescriptorProvider extends TabbedPropertyRegistry implements ITabDescriptorProvider {

  protected Collection<ITabDescriptorProvider> _descs = new ArrayList<ITabDescriptorProvider>();

  /**
   * @param id_p
   */
  public FixedTabDescriptorProvider(String propertiesId_p) {
    super(propertiesId_p);
  }

  /**
   * @param capellaTabDescriptorProvider_p
   */
  protected void addDescriptor(ITabDescriptorProvider provider_p) {
    _descs.add(provider_p);
  }

  protected boolean isExtensionsPrior() {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ITabDescriptor[] getTabDescriptors(IWorkbenchPart part_p, ISelection selection_p) {
    Collection<ITabDescriptor> descs = new ArrayList<ITabDescriptor>();

    if (isExtensionsPrior()) {
      descs.addAll(Arrays.asList(super.getTabDescriptors(part_p, selection_p)));
    }

    for (ITabDescriptorProvider provider : _descs) {
      descs.addAll(Arrays.asList(provider.getTabDescriptors(part_p, selection_p)));
    }

    if (!isExtensionsPrior()) {
      descs.addAll(Arrays.asList(super.getTabDescriptors(part_p, selection_p)));
    }

    return descs.toArray(new ITabDescriptor[0]);
  }

  @Override
  protected IConfigurationElement[] getConfigurationElements(String extensionPointId) {
    if (contributorId == null) {
      return new IConfigurationElement[0];
    }
    IExtensionPoint point =
        Platform.getExtensionRegistry().getExtensionPoint(TabbedPropertyViewPlugin.getPlugin().getBundle().getSymbolicName(), extensionPointId);
    IConfigurationElement[] extensions = point.getConfigurationElements();

    List unordered = new ArrayList(extensions.length);
    for (IConfigurationElement extension : extensions) {
      if (!extension.getName().equals(extensionPointId)) {
        continue;
      }
      String contributor = extension.getAttribute("contributorId");
      if (extension.getAttribute("tabDescriptorProvider") != null) {
        continue;
      }
      if (!contributorId.equals(contributor)) {
        continue;
      }
      unordered.add(extension);
    }

    return (IConfigurationElement[]) unordered.toArray(new IConfigurationElement[unordered.size()]);
  }
}
