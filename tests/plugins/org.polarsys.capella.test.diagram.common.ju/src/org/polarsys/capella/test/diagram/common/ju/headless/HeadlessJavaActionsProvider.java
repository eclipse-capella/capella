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
import org.polarsys.capella.test.diagram.common.ju.TestDiagramCommonPlugin;

public class HeadlessJavaActionsProvider {
  
  public void init() {
    
    //Retrieve the headless actions registered within this plugin
    IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
    HashMap<String, String> headlessActions = new HashMap<String, String>();
    IExtension[] extensions = extensionRegistry.getExtensions(ContributorFactoryOSGi.createContributor(TestDiagramCommonPlugin.getDefault().getBundle()));
    for (IExtension extension : extensions) {
      if (ExternalJavaActionRegistryListener.EXTERNAL_JAVA_ACTION_EXTENSION_POINT.equals(extension.getExtensionPointUniqueIdentifier())) {
        for (IConfigurationElement element : extension.getConfigurationElements()) {
          ExternalJavaActionDescriptor descriptor = new ExternalJavaActionDescriptor(element);
          headlessActions.put(descriptor.getId(), descriptor.getActionClass());
        }
      }
    }
    
    //Remove all other actionClasses contributed to overridden actions
    Collection<String> toRemove = new ArrayList<String>();
    for (ExternalJavaActionDescriptor registeredAction : ExternalJavaActionProvider.INSTANCE.getJavaActionDescriptor()) {
      if (headlessActions.containsKey(registeredAction.getId())) {
        if (!headlessActions.get(registeredAction.getId()).equals(registeredAction.getActionClass())) {
          toRemove.add(registeredAction.getActionClass());
        }
      }
    }
    
    for (String action: toRemove) {
      ExternalJavaActionProvider.INSTANCE.removeAction(action);
    }
  }
  
}
