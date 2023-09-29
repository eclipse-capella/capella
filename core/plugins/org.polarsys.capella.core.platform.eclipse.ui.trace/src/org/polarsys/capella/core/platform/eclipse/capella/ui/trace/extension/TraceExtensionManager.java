/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.eclipse.capella.ui.trace.extension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.core.data.capellacore.Trace;
import org.polarsys.kitalpha.ad.services.manager.ViewpointManager;

public class TraceExtensionManager {

  public static TraceExtensionManager eINSTANCE = new TraceExtensionManager();
  String EXTENSION_POINT_ID = "org.polarsys.capella.core.platform.eclipse.capella.ui.trace.extension";
  String EXTENSION_POINT_CLASS = "traceExtension";

  Set<ITraceExtension> extensions;

  private TraceExtensionManager() {
    initializeTraceExtensions();
  }

  private Set<ITraceExtension> initializeTraceExtensions() {
    if (extensions == null) {
      extensions = new HashSet<ITraceExtension>();
      IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();

      for (IConfigurationElement configurationElement : extensionRegistry
          .getConfigurationElementsFor(EXTENSION_POINT_ID)) {
        try {
          ITraceExtension extension = (ITraceExtension) configurationElement
              .createExecutableExtension(EXTENSION_POINT_CLASS);
          extensions.add(extension);
        } catch (CoreException e) {
          e.printStackTrace();
        }
      }

    }
    return extensions;
  }

  public boolean isActive(ITraceExtension extension, ResourceSet rSet) {
    ViewpointManager vpManager = ViewpointManager.getInstance(rSet);
    return vpManager.isActive(extension.getViewpointID());
  }

  public boolean canDelete(Trace obj) {
    for (ITraceExtension extension : extensions) {
      if (isActive(extension, obj.eResource().getResourceSet())) {
        if (extension.canDelete(obj)) {
          return true;
        }
      }
    }
    return false;
  }

  public boolean canRemoveSource(Trace obj) {
    for (ITraceExtension extension : extensions) {
      if (isActive(extension, obj.eResource().getResourceSet())) {
        if (extension.canRemoveSource(obj)) {
          return true;
        }
      }
    }
    return false;
  }

  public boolean canRemoveTarget(Trace obj) {
    for (ITraceExtension extension : extensions) {
      if (isActive(extension, obj.eResource().getResourceSet())) {
        if (extension.canRemoveTarget(obj)) {
          return true;
        }
      }
    }
    return false;
  }

  public boolean isAssignableFrom(Class<?> clazz, ResourceSet context) {
    for (ITraceExtension extension : extensions) {
      if (isActive(extension, context)) {
        if (extension.isAssignableFrom(clazz)) {
          return true;
        }
      }
    }
    return false;
  }

  public String getTraceName(Class<? extends AbstractTrace> clazz, ResourceSet context) {
    for (ITraceExtension extension : extensions) {
      if (isActive(extension, context)) {
        if (extension.isAssignableFrom(clazz)) {
          return extension.getTraceName();
        }
      }
    }
    return null;
  }

  public Trace getNewTraceInstanceFromTraceName(String traceName, ResourceSet context) {
    if (traceName == null || traceName.isEmpty()) {
      return null;
    }
    for (ITraceExtension extension : extensions) {
      if (isActive(extension, context)) {
        if (traceName.equals(extension.getTraceName())) {
          return extension.getNewTraceInstance();
        }
      }
    }
    return null;
  }

  public List<String> getAllTraceTypes(ResourceSet context) {
    List<String> allTraceTypes = new ArrayList<String>();
    for (ITraceExtension extension : extensions) {
      if (isActive(extension, context)) {
        allTraceTypes.add(extension.getTraceName());
      }
    }
    return allTraceTypes;
  }

  public List<String> getAllManualTraceTypes(ResourceSet context) {
    List<String> allTraceTypes = new ArrayList<String>();
    for (ITraceExtension extension : extensions) {
      if (isActive(extension, context)) {
        if (extension.isManualTrace()) {
          allTraceTypes.add(extension.getTraceName());
        }
      }
    }
    return allTraceTypes;
  }

  public boolean canAddRemoveItemsToTrace(EObject element) {
    if (element == null) {
      return false;
    }
    for (ITraceExtension extension : extensions) {
      if (isActive(extension, element.eResource().getResourceSet())) {
        if (extension.canAddRemoveItemsToTrace(element)) {
          return true;
        }
      }
    }
    return false;
  }

  public boolean canEdit(EObject element) {
    if (element == null) {
      return false;
    }
    for (ITraceExtension extension : extensions) {
      if (isActive(extension, element.eResource().getResourceSet())) {
        if (extension.canEdit(element)) {
          return true;
        }
      }
    }
    return false;
  }
}
