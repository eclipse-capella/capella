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

package org.polarsys.capella.common.tools.report.appenders.reportlogview;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;

/**
 * A marker view content provider that groups markers by their preferences(epf file) validation epf file.
 */
public class PreferenceRuleSetLabelProvider extends AbstractMarkerViewContentProvider implements ITreeContentProvider {

  private Map<Object, List<IMarker>> markers; // markers that live under a validation rule id
  private List<IMarker> messages; // markers that live directly under the root (e.g logs from transition)

  public PreferenceRuleSetLabelProvider(TreeViewer viewer, MarkerViewHelper helper, IViewerRefresh refresh) {
    super(viewer, helper, refresh);
    refillCache();
  }

  private void refillCache() {
    markers = new LinkedHashMap<Object, List<IMarker>>();
    messages = new ArrayList<IMarker>();

    for (IMarker marker : findMarkers()) {
      markerAddedIntern(marker);
    }
  }

  private Object findKey(IMarker marker) {
    Object key = MarkerViewHelper.getConstraintDescriptor(marker);
    if (key == null) {
      Diagnostic d = MarkerViewHelper.getDiagnostic(marker);
      if (d != null) {
        key = MarkerViewHelper.OTHER_RULES;
      }
    }
    return key;
  }

  /**
   * {@inheritDoc}
   */
  public Object getParent(Object element) {
    Object parent = null;
    if (element instanceof IMarker) {
      parent = MarkerViewHelper.getConstraintDescriptor((IMarker) element);
      if (parent == null) {
        Diagnostic d = MarkerViewHelper.getDiagnostic((IMarker) element);
        if (d != null) {
          parent = MarkerViewHelper.OTHER_RULES;
        }
      }
    }
    return parent;
  }

  /**
   * {@inheritDoc}
   */
  public synchronized Object[] getElements(Object inputElement) {
    Object[] elems = new Object[markers.size() + messages.size()];
    int i = 0;
    Set<?> keys = markers.keySet();
    for (Object o : keys) {
      elems[i++] = o;
    }
    for (Object o : messages) {
      elems[i++] = o;
    }
    return elems;
  }

  /**
   * {@inheritDoc}
   */
  public synchronized Object[] getChildren(Object parentElement) {
    List<IMarker> children = markers.get(parentElement);
    if (children != null) {
      return children.toArray();
    }
    return noChildren;
  }

  /**
   * {@inheritDoc}
   */
  public synchronized boolean hasChildren(Object element) {
    if (element == viewer.getInput()) {
      for (Map.Entry<Object, List<IMarker>> entry : markers.entrySet()) {
        List<IMarker> children = entry.getValue();
        if ((children != null) && (children.size() > 0)) {
          return true;
        }
      }
      return !messages.isEmpty();
    }

    List<IMarker> children = markers.get(element);
    if ((children != null) && (children.size() > 0)) {
      return true;
    }
    return false;
  }

  private void markerAddedIntern(IMarker marker) {
    Object key = findKey(marker);
    if (key != null) {
      List<IMarker> markersForKey = markers.get(key);
      if (markersForKey == null) {
        markersForKey = new ArrayList<IMarker>();
        markers.put(key, markersForKey);
      }
      markersForKey.add(marker);
    } else {
      messages.add(marker);
    }
    viewerRefresh.refresh();

  }

  /**
   * {@inheritDoc}
   */
  public synchronized void markerAdded(IMarker marker) {
    markerAddedIntern(marker);
  }

  /**
   * {@inheritDoc}
   */
  public synchronized void markerDeleted(IMarker marker) {
    boolean removed = false;
    for (Map.Entry<Object, List<IMarker>> entry : markers.entrySet()) {
      List<IMarker> children = entry.getValue();
      if (children != null) {
        removed = children.remove(marker);
        if (removed) {
          break;
        }
      }
    }
    if (!removed) {
      messages.remove(marker);
    }
    viewerRefresh.refresh();
  }

}
