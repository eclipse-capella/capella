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
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.ui.views.markers.MarkerViewUtil;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.helpers.validation.IValidationConstants;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

/**
 * A lot of default-marker cause bad performances.<br>
 * This class provides a roll-our-own implementation of the Eclipse IMarker API, lacking support for a few essential
 * features of the original API:<br>
 * - Once a marker has been created, clients won't be notified about subsequent changes to the marker.<br>
 * - All marker attributes have to be set upon creation with the help of a callback argument (see createMarker() below).
 * <br>
 * This class is thread safe.<br>
 * Listeners are notified on the thread that created/deleted a marker.
 */
public class LightMarkerRegistry implements IMarkerSource {

  private static final LightMarkerRegistry _instance = new LightMarkerRegistry();

  private List<IMarker> _registry = Collections.synchronizedList(new ArrayList<IMarker>());

  /**
   * Legacy content provider style observers
   **/
  List<IContentProvider> legacyObservers = Collections.synchronizedList(new ArrayList<IContentProvider>());

  /**
   * Listeners
   */
  List<IMarkerSourceListener> listeners = Collections.synchronizedList(new ArrayList<IMarkerSourceListener>());

  /**
   * Get the global instance. This method is thread safe.
   */
  public static LightMarkerRegistry getInstance() {
    return _instance;
  }

  /**
   * Returns the observers list
   */
  protected List<IContentProvider> getObservers() {
    return legacyObservers;
  }

  /**
   * Add an observer which will be notified when the registry has changed
   * 
   * @deprecated use addMarkerSourceListener instead
   */
  @Deprecated
  void addObserver(IContentProvider observer) {
    getObservers().add(observer);
  }

  /**
   * Add an observer which will be notified when the registry has changed
   * 
   * @deprecated use removeMarkerSourceListener instead
   */
  @Deprecated
  void removeObserver(IContentProvider observer) {
    getObservers().remove(observer);
  }

  protected void notifyRegistryChanged(IMarker oldValue, IMarker newValue) {
    List<IContentProvider> oldObservers = getObservers();
    synchronized (oldObservers) {
      for (IContentProvider observer : oldObservers) {
        observer.inputChanged(null, oldValue, newValue);
      }
    }
    synchronized (listeners) {
      if ((oldValue == null) && (newValue != null)) {
        for (IMarkerSourceListener listener : listeners) {
          listener.markerAdded(newValue);
        }
      } else if ((oldValue != null) && (newValue == null)) {
        for (IMarkerSourceListener listener : listeners) {
          listener.markerDeleted(oldValue);
        }
      }
    }
  }

  /**
   * A shortcut for
   * 
   * <pre>
   * <code>createMarker(fileResource, diagnostic, MarkerView.MARKER_ID);</code>
   * </pre>
   */
  public IMarker createMarker(IResource fileResource, Diagnostic diagnostic) {
    return createMarker(fileResource, diagnostic, MarkerView.MARKER_ID);
  }

  /**
   * A shortcut for
   * 
   * <pre>
   * <code>createMarker(fileResource, diagnostic, markerType, null);</code>
   * </pre>
   */
  public IMarker createMarker(IResource fileResource, Diagnostic diagnostic, String markerType) {
    return createMarker(fileResource, diagnostic, markerType, null);
  }

  /**
   * Create a marker of a specific type and apply the given modification. The returned marker should not be changed
   * since listeners won't be notified about such changes.
   * 
   * @param fileResource
   *          the resource to which to attach the marker
   * @param diagnostic
   *          the diagnostic that backs the message, severity and elements of the marker
   * @param modification
   *          a callback that may be used to tune the marker before listeners are notified about its creation. may be
   *          null.
   */
  public IMarker createMarker(IResource fileResource, Diagnostic diagnostic, String markerType,
      IMarkerModification modification) {
    LightMarker marker = new LightMarker(fileResource, markerType, diagnostic);

    if (modification != null) {
      modification.modify(marker);
    }

    _registry.add(marker);
    notifyRegistryChanged(null, marker);
    return marker;
  }

  public boolean hasMarkers() {
    return !_registry.isEmpty();
  }

  /**
   * Returns an unmodifiable view of markers stored by this IMarkerSource. Deleting a marker in this view while
   * iterating over its contents will throw a ConcurrentModificationException.
   */
  public Collection<IMarker> getMarkers() {
    return Collections.unmodifiableCollection(_registry);
  }

  public void purgeMarkers() {
    Iterator<IMarker> markers = _registry.iterator();
    while (markers.hasNext()) {
      IMarker marker = markers.next();
      if (isPurgeable(marker)) {
        try {
          markers.remove();
          marker.delete();
        } catch (Exception e) {
          // Nothing here
        }
      }
    }
  }

  protected boolean isPurgeable(IMarker marker) {
    return marker instanceof LightMarker && ((LightMarker) marker).isPurgeable();
  }

  /**
   * A light version of IMarker - non persistent - avoid any resourceChangeEvents about markers (which cause performance
   * decreased since Sirius made a ui-refresh for each notification)
   * 
   * @see org.eclipse.sirius.common.tools.internal.resource.WorkspaceBackend
   */
  @SuppressWarnings("restriction")
  public class LightMarker implements IMarker {

    HashMap<String, Object> attributes;

    // Some classic attributes
    String type;
    long id;
    long creationTime;
    IResource resource;
    private Diagnostic diagnostic;

    public LightMarker(IResource resource, String markerType, Diagnostic diagnostic) {
      attributes = new HashMap<String, Object>();
      this.resource = resource;
      this.diagnostic = diagnostic;

      id = System.nanoTime();
      this.type = markerType;
      creationTime = System.currentTimeMillis();
    }

    public boolean isPurgeable() {
      
      // A marker no-related to any data is kept
      if (diagnostic.getData().isEmpty()) {
        return false;
      }
      boolean purgeable = false;

      // Delete the EMF object only if the internal object it's referencing has been deleted
      if (diagnostic.getSource() != null && diagnostic.getSource().equals("org.eclipse.emf.ecore")) {
        for (Object o : diagnostic.getData()) {
          if (o instanceof EObject && ((EObject) o).eResource() == null && !((EObject) o).eIsProxy()) {
            purgeable = true;
            break;
          }
        }
      } else {

        // If one element is invalid, marker is deleted
        for (Object o : diagnostic.getData()) {
          if (o instanceof EObject && ((EObject) o).eResource() == null) {
            purgeable = true;
            break;
          }
        }
      }
      return purgeable;
    }

    /**
     * @see org.eclipse.core.resources.IMarker#delete()
     */
    @SuppressWarnings("synthetic-access")
    public void delete() throws CoreException {
      _registry.remove(this);
      notifyRegistryChanged(this, null);
    }

    /**
     * @see org.eclipse.core.resources.IMarker#exists()
     */
    @SuppressWarnings("synthetic-access")
    public boolean exists() {
      return _registry.contains(this);
    }

    /**
     * @see org.eclipse.core.resources.IMarker#getAttribute(java.lang.String)
     */
    public Object getAttribute(String attributeName) throws CoreException {

      // values in the attribute map take precedence for backwards compatibility
      Object attribute = attributes.get(attributeName);
      if (attribute != null) {
        return attribute;
      }

      // otherwise attribute values are read from the embedded diagnostic
      if (IMarker.MESSAGE.equals(attributeName)) {
        return diagnostic.getMessage();
      }
      if (IMarker.SEVERITY.equals(attributeName)) {
        if (diagnostic.getSeverity() < Diagnostic.WARNING) {
          return IMarker.SEVERITY_INFO;
        } else if (diagnostic.getSeverity() < Diagnostic.ERROR) {
          return IMarker.SEVERITY_WARNING;
        } else {
          return IMarker.SEVERITY_ERROR;
        }
      }

      if (IValidationConstants.TAG_RULE_ID.equals(attributeName)) {
        return diagnostic.getMessage();
      }

      if (MarkerViewUtil.PATH_ATTRIBUTE.equals(attributeName)) {
        String pathAttributes = ICommonConstants.EMPTY_STRING;
        for (Object data : diagnostic.getData()) {
          if (data instanceof ModelElement) {
            ModelElement element = (ModelElement) data;
            Session session = SessionManager.INSTANCE.getSession(element);
            if (session != null) {
              pathAttributes += element.getFullLabel() + ICommonConstants.LINE_SEPARATOR;
            }
          }
        }
        return pathAttributes;
      }

      return attributes.get(attributeName);
    }

    /**
     * @see org.eclipse.core.resources.IMarker#getAttribute(java.lang.String, int)
     */
    public int getAttribute(String attributeName, int defaultValue) {
      Object result = null;
      try {
        result = getAttribute(attributeName);
      } catch (CoreException e) {
        MarkerViewPlugin.getDefault().getLog()
            .log(new Status(e.getStatus().getSeverity(), MarkerViewPlugin.PLUGIN_ID, e.getMessage(), e));
      }
      if (result instanceof Integer) {
        return ((Integer) result).intValue();
      }
      return defaultValue;
    }

    /**
     * @see org.eclipse.core.resources.IMarker#getAttribute(java.lang.String, java.lang.String)
     */
    public String getAttribute(String attributeName, String defaultValue) {
      Object result = null;
      try {
        result = getAttribute(attributeName);
      } catch (CoreException e) {
        MarkerViewPlugin.getDefault().getLog()
            .log(new Status(e.getStatus().getSeverity(), MarkerViewPlugin.PLUGIN_ID, e.getMessage(), e));
      }
      if (result instanceof String) {
        return (String) result;
      }
      return defaultValue;
    }

    /**
     * @see org.eclipse.core.resources.IMarker#getAttribute(java.lang.String, boolean)
     */
    public boolean getAttribute(String attributeName, boolean defaultValue) {
      Object result = null;
      try {
        result = getAttribute(attributeName);
      } catch (CoreException e) {
        MarkerViewPlugin.getDefault().getLog()
            .log(new Status(e.getStatus().getSeverity(), MarkerViewPlugin.PLUGIN_ID, e.getMessage(), e));
      }
      if (result instanceof Boolean) {
        return ((Boolean) result).booleanValue();
      }
      return defaultValue;
    }

    /**
     * @see org.eclipse.core.resources.IMarker#getAttributes()
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Map getAttributes() throws CoreException {
      Map result = new HashMap(attributes);
      result.put(IMarker.MESSAGE, getAttribute(IMarker.MESSAGE));
      result.put(IMarker.SEVERITY, getAttribute(IMarker.SEVERITY));
      result.put(MarkerViewUtil.PATH_ATTRIBUTE, getAttribute(MarkerViewUtil.PATH_ATTRIBUTE));
      return result;
    }

    /**
     * @see org.eclipse.core.resources.IMarker#getAttributes(java.lang.String[])
     */
    public Object[] getAttributes(String[] attributeNames) throws CoreException {
      Object[] res = new Object[attributeNames.length];
      for (int i = 0; i < attributeNames.length; i++) {
        res[i] = getAttribute(attributeNames[i]);
      }
      return res;
    }

    /**
     * @see org.eclipse.core.resources.IMarker#getCreationTime()
     */
    public long getCreationTime() throws CoreException {
      return creationTime;
    }

    /**
     * @see org.eclipse.core.resources.IMarker#getId()
     */
    public long getId() {
      return id;
    }

    /**
     * @see org.eclipse.core.resources.IMarker#getResource()
     */
    public IResource getResource() {
      return resource;
    }

    /**
     * @see org.eclipse.core.resources.IMarker#getType()
     */
    public String getType() throws CoreException {
      return type;
    }

    /**
     * @see org.eclipse.core.resources.IMarker#isSubtypeOf(java.lang.String)
     */
    public boolean isSubtypeOf(String superType) throws CoreException {
      return false;
    }

    /**
     * @see org.eclipse.core.resources.IMarker#setAttribute(java.lang.String, int)
     */
    public void setAttribute(String attributeName, int value) throws CoreException {
      attributes.put(attributeName, Integer.valueOf(value));
    }

    /**
     * @see org.eclipse.core.resources.IMarker#setAttribute(java.lang.String, java.lang.Object)
     */
    public void setAttribute(String attributeName, Object value) throws CoreException {
      attributes.put(attributeName, value);
    }

    /**
     * @see org.eclipse.core.resources.IMarker#setAttribute(java.lang.String, boolean)
     */
    public void setAttribute(String attributeName, boolean value) throws CoreException {
      attributes.put(attributeName, Boolean.valueOf(value));
    }

    /**
     * @see org.eclipse.core.resources.IMarker#setAttributes(java.util.Map)
     */
    @SuppressWarnings("rawtypes")
    public void setAttributes(Map attributes) throws CoreException {
      for (Object key : attributes.keySet()) {
        if ((key != null) && (key instanceof String)) {
          this.attributes.put((String) key, attributes.get(key));
        }
      }
    }

    /**
     * @see org.eclipse.core.resources.IMarker#setAttributes(java.lang.String[], java.lang.Object[])
     */
    public void setAttributes(String[] attributeNames, Object[] values) throws CoreException {
      for (int i = 0; i < attributeNames.length; i++) {
        String key = attributeNames[i];
        Object value = values[i];
        attributes.put(key, value);
      }
    }

    /**
     * LightMarkers can be adapted into EMF Diagnostic objects
     * 
     * @see org.eclipse.emf.common.util.Diagnostic
     */
    @SuppressWarnings("rawtypes")
    public Object getAdapter(Class adapter) {
      if (adapter == Diagnostic.class) {
        return diagnostic;
      }
      return null;
    }
  }

  /**
   * Light markers do fire notifications after they were added to this registry. You must therefore set all marker
   * attributes upon creation via this callback class, by passing an instance to createMarker();
   * 
   * @see createMarker();
   */
  public interface IMarkerModification {
    public void modify(IMarker marker);
  }

  // A helper to support the deprecated createMarker(IResource, String)
  class MarkerModificationState implements IMarkerModification {
    private IMarker theMarker;

    /**
     * {@inheritDoc}
     */
    public void modify(IMarker marker) {
      theMarker = marker;
    }

    public IMarker getMarker() {
      return theMarker;
    }
  }

  /**
   * {@inheritDoc}
   */
  public void addListener(IMarkerSourceListener listener) {
    listeners.add(listener);
  }

  /**
   * {@inheritDoc}
   */
  public void removeListener(IMarkerSourceListener listener) {
    listeners.remove(listener);
  }

}
