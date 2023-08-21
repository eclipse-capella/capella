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
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.core.data.capellacore.Trace;

public class TraceExtensionManager {

  public static TraceExtensionManager eINSTANCE = new TraceExtensionManager();
  static String EXTENSION_POINT_ID = "org.polarsys.capella.core.platform.eclipse.capella.ui.trace.extension";
  static String EXTENSION_POINT_CLASS = "traceExtension";

  Set<ITraceExtension> extensions;

  private TraceExtensionManager() {

  }

  public Set<ITraceExtension> getTraceExtensions() {
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

  public boolean canDelete(Trace obj) {
    Set<ITraceExtension> extensions = getTraceExtensions();
    for (ITraceExtension extension : extensions) {
      if (extension.canDelete(obj))
        return true;
    }
    return false;
  }

  public boolean canRemoveSource(Trace obj) {
    Set<ITraceExtension> extensions = getTraceExtensions();
    for (ITraceExtension extension : extensions) {
      if (extension.canRemoveSource(obj))
        return true;
    }
    return false;
  }

  public boolean canRemoveTarget(Trace obj) {
    Set<ITraceExtension> extensions = getTraceExtensions();
    for (ITraceExtension extension : extensions) {
      if (extension.canRemoveTarget(obj))
        return true;
    }
    return false;
  }

  public boolean isAssignableFrom(Class<?> clazz) {
    Set<ITraceExtension> extensions = getTraceExtensions();
    for (ITraceExtension extension : extensions) {
      if (extension.isAssignableFrom(clazz))
        return true;
    }
    return false;
  }

  public String getTraceName(Class<? extends AbstractTrace> clazz) {
    Set<ITraceExtension> extensions = getTraceExtensions();
    for (ITraceExtension extension : extensions) {
      if (extension.isAssignableFrom(clazz)) {
        return extension.getTraceName();
      }
    }
    return null;
  }

  public Trace getNewTraceInstanceFromTraceName(String traceName) {
    if (traceName == null || traceName.isEmpty())
      return null;
    Set<ITraceExtension> extensions = getTraceExtensions();
    for (ITraceExtension extension : extensions) {
      if (traceName.equals(extension.getTraceName())) {
        return extension.getNewTraceInstance();
      }
    }
    return null;
  }

  public List<String> getAllTraceTypes() {
    Set<ITraceExtension> extensions = getTraceExtensions();
    List<String> allTraceTypes = new ArrayList<String>();
    for (ITraceExtension extension : extensions) {
      allTraceTypes.add(extension.getTraceName());
    }
    return allTraceTypes;
  }

  public List<String> getAllManualTraceTypes() {
    Set<ITraceExtension> extensions = getTraceExtensions();
    List<String> allTraceTypes = new ArrayList<String>();
    for (ITraceExtension extension : extensions) {
      if (extension.isManualTrace())
        allTraceTypes.add(extension.getTraceName());
    }
    return allTraceTypes;
  }

  public boolean canAddRemoveItemsToTrace(Object element) {
    if (element == null)
      return false;
    Set<ITraceExtension> extensions = getTraceExtensions();
    for (ITraceExtension extension : extensions) {
      if (extension.canAddRemoveItemsToTrace(element)) {
        return true;
      }
    }
    return false;
  }

  public boolean canEdit(Object element) {
    if (element == null)
      return false;
    Set<ITraceExtension> extensions = getTraceExtensions();
    for (ITraceExtension extension : extensions) {
      if (extension.canEdit(element)) {
        return true;
      }
    }
    return false;
  }
}
