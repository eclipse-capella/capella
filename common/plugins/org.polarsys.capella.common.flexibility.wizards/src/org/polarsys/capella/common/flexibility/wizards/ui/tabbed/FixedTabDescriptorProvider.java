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

package org.polarsys.capella.common.flexibility.wizards.ui.tabbed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.activities.IIdentifier;
import org.eclipse.ui.internal.views.properties.tabbed.view.TabDescriptor;
import org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyRegistry;
import org.eclipse.ui.views.properties.tabbed.AbstractTabDescriptor;
import org.eclipse.ui.views.properties.tabbed.ISectionDescriptor;
import org.eclipse.ui.views.properties.tabbed.ITabDescriptor;
import org.eclipse.ui.views.properties.tabbed.ITabDescriptorProvider;
import org.osgi.framework.FrameworkUtil;

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
   * @param propertiesId
   */
  public FixedTabDescriptorProvider(String propertiesId) {
    super(propertiesId);
  }

  /**
   * @param provider
   */
  protected void addDescriptor(ITabDescriptorProvider provider) {
    _descs.add(provider);
  }

  protected boolean isExtensionsPrior() {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ITabDescriptor[] getTabDescriptors(IWorkbenchPart part, ISelection selection) {
    Collection<ITabDescriptor> descs = new ArrayList<ITabDescriptor>();

    if (isExtensionsPrior()) {
      descs.addAll(Arrays.asList(super.getTabDescriptors(part, selection)));
    }

    for (ITabDescriptorProvider provider : _descs) {
      descs.addAll(Arrays.asList(provider.getTabDescriptors(part, selection)));
    }

    if (!isExtensionsPrior()) {
      descs.addAll(Arrays.asList(super.getTabDescriptors(part, selection)));
    }

    return descs.toArray(new ITabDescriptor[0]);
  }

  @Override
  protected ITabDescriptor adaptDescriptorFor(ITabDescriptor target, IWorkbenchPart part, ISelection selection) {
    AbstractTabDescriptor result = (AbstractTabDescriptor) ((AbstractTabDescriptor) target).clone();
    List filteredSectionDescriptors = new ArrayList();
    List descriptors = target.getSectionDescriptors();

    for (Iterator<?> iter = descriptors.iterator(); iter.hasNext();) {
      ISectionDescriptor descriptor = (ISectionDescriptor) iter.next();
      if (descriptor.appliesTo(part, selection)) {
        IIdentifier identifier = PlatformUI.getWorkbench().getActivitySupport().getActivityManager()
            .getIdentifier(descriptor.getId());
        if (!identifier.isEnabled()) {
          continue;
        }
        filteredSectionDescriptors.add(descriptor);
      }
    }
    result.setSectionDescriptors(filteredSectionDescriptors);
    return result;
  }

  @Override
  protected IConfigurationElement[] getConfigurationElements(String extensionPointId) {
    if (contributorId == null) {
      return new IConfigurationElement[0];
    }
    IExtensionPoint point =
        Platform.getExtensionRegistry().getExtensionPoint(FrameworkUtil.getBundle(TabDescriptor.class).getSymbolicName(), extensionPointId);
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
