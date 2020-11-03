/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.common.ju.headless;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.eclipse.core.runtime.ContributorFactoryOSGi;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.sirius.tools.api.ui.ExternalJavaActionProvider;
import org.eclipse.sirius.tools.internal.ui.ExternalJavaActionDescriptor;
import org.eclipse.sirius.tools.internal.ui.ExternalJavaActionRegistryListener;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.test.diagram.common.ju.TestDiagramCommonPlugin;

public class HeadlessJavaActionsProvider {

  public void init() {

    // Retrieve the headless actions registered within this plugin
    IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
    HashMap<String, String> headlessActionIds = new HashMap<String, String>();
    IExtension[] extensions = extensionRegistry
        .getExtensions(ContributorFactoryOSGi.createContributor(TestDiagramCommonPlugin.getDefault().getBundle()));
    for (IExtension extension : extensions) {
      if (ExternalJavaActionRegistryListener.EXTERNAL_JAVA_ACTION_EXTENSION_POINT
          .equals(extension.getExtensionPointUniqueIdentifier())) {
        for (IConfigurationElement element : extension.getConfigurationElements()) {
          ExternalJavaActionDescriptor descriptor = new ExternalJavaActionDescriptor(element);
          headlessActionIds.put(descriptor.getId(), descriptor.getActionClass());
        }
      }
    }

    //Retrieve all registered actions into Sirius ExternalJavaActionProvider
    Collection<String> headlessActions = new ArrayList<String>();
    Collection<String> uiActions = new ArrayList<String>();
    for (ExternalJavaActionDescriptor registeredAction : ExternalJavaActionProvider.INSTANCE
        .getJavaActionDescriptor()) {
      if (headlessActionIds.containsKey(registeredAction.getId())) {
        if (headlessActionIds.get(registeredAction.getId()).equals(registeredAction.getActionClass())) {
          headlessActions.add(registeredAction.getActionClass());
        } else {
          uiActions.add(registeredAction.getActionClass());
        }
      }
    }

    // Remove uiActions when headless is required, or headless actions when outside junit
    boolean enableHeadless = (PlatformUI.getTestableObject().getTestHarness() != null);
    Collection<String> toRemove = enableHeadless ? uiActions : headlessActions;
    for (String action : toRemove) {
      ExternalJavaActionProvider.INSTANCE.removeAction(action);
    }
  }

}
