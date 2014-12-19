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
package org.polarsys.capella.common.tools.report.appenders.reportlogview;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.ui.views.markers.MarkerViewUtil;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.helpers.validation.IValidationConstants;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

/**
 * This class provides a roll-our-own implementation of the Eclipse IMarker API,
 * lacking support for a few essential features of the original API:<br>
 * - Once a marker has been created, clients won't be notified about subsequent changes to the marker.<br>
 * - All marker attributes have to be set upon creation with the help of a callback argument (see createMarker() below).<br>
 * This class is thread safe.<br>
 * Listeners are notified on the thread that created/deleted a marker.
 */
public class LightMarkerRegistry implements IMarkerSource {

  /**
   * The capella validation marker type. This constant is deprecated and will soon be removed.
   * @deprecated use {@link org.polarsys.capella.core.model.handler.markers.ICapellaValidationConstants#CAPELLA_MARKER_ID} instead
   */
  public static final String VALIDATION_TYPE = "org.polarsys.capella.core.validation.markers"; //$NON-NLS-1$

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
   * @deprecated use addMarkerSourceListener instead
   */
  @Deprecated
  void addObserver(IContentProvider observer) {
    getObservers().add(observer);
  }

  /**
   * Add an observer which will be notified when the registry has changed
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
   * A shortcut for <pre><code>createMarker(fileResource_p, diagnostic_p, MarkerView.MARKER_ID);</code></pre>
   */
  public IMarker createMarker(IResource fileResource_p, Diagnostic diagnostic_p){
    return createMarker(fileResource_p, diagnostic_p, MarkerView.MARKER_ID);
  }

  /**
   * A shortcut for <pre><code>createMarker(fileResource_p, diagnostic_p, markerType_p, null);</code></pre>
   */
  public IMarker createMarker(IResource fileResource_p, Diagnostic diagnostic_p, String markerType_p){
    return createMarker(fileResource_p, diagnostic_p, markerType_p, null);
  }

  /**
   * Create a marker of a specific type and apply the given modification.
   * The returned marker should not be changed since listeners won't be notified about such changes.
   * @param fileResource_p the resource to which to attach the marker
   * @param diagnostic_p the diagnostic that backs the message, severity and elements of the marker
   * @param modification_p a callback that may be used to tune the marker before listeners are notified about its creation. may be null.
   */
  public IMarker createMarker(IResource fileResource_p, Diagnostic diagnostic_p, String markerType_p, IMarkerModification modification_p) {
    LightMarker marker = new LightMarker(fileResource_p, markerType_p, diagnostic_p);

    if (modification_p != null){
      modification_p.modify(marker);
    }

    _registry.add(marker);
    notifyRegistryChanged(null, marker);
    return marker;
  }


  /**
   * This method is deprecated and will be removed soon.
   * 
   * Create a new marker and apply the given modification to it.
   * @param resource_p
   * @param type_p
   * @param modification
   * @deprecated use any of the other createMarker methods
   */
  @Deprecated
  public void createMarker(IResource resource_p, String type_p, IMarkerModification modification_p) {
    createMarker(resource_p, new BasicDiagnostic(), type_p, modification_p);
  }

  /**
   * Returns an unmodifiable view of markers stored by this IMarkerSource. Deleting a marker in this view while iterating over its contents will throw a
   * ConcurrentModificationException.
   */
  public Collection<IMarker> getMarkers() {
    return Collections.unmodifiableCollection(_registry);
  }

  /**
   * A light version of IMarker - non persistent - avoid any resourceChangeEvents about markers (which cause performance decreased since Sirius made a ui-refresh
   * for each notification)
   * @see org.eclipse.sirius.common.tools.internal.resource.WorkspaceBackend
   */
  @SuppressWarnings("restriction")
  private class LightMarker implements IMarker {

    HashMap<String, Object> attributes;

    // Some classic attributes
    String type;
    long id;
    long creationTime;
    IResource resource;

    private Diagnostic diagnostic;

    @SuppressWarnings("deprecation")
    LightMarker(IResource resource_p, String markerType_p, Diagnostic diagnostic_p) {
      attributes = new HashMap<String, Object>();
      resource = resource_p;
      diagnostic = diagnostic_p;
      id = System.nanoTime();
      type = markerType_p;
      creationTime = System.currentTimeMillis();
      
      // for backwards compatibility
      attributes.put(IValidationConstants.TAG_DIAGNOSTIC, diagnostic_p);
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
    public Object getAttribute(String attributeName_p) throws CoreException {

      // values in the attribute map take precedence for backwards compatibility
      Object attribute = attributes.get(attributeName_p);
      if (attribute != null){
        return attribute;
      }

      // otherwise attribute values are read from the embedded diagnostic
      if (IMarker.MESSAGE.equals(attributeName_p)){
        return diagnostic.getMessage();
      }
      if (IMarker.SEVERITY.equals(attributeName_p)){
        if (diagnostic.getSeverity() < Diagnostic.WARNING) {
          return IMarker.SEVERITY_INFO;
        } else if (diagnostic.getSeverity() < Diagnostic.ERROR) {
          return IMarker.SEVERITY_WARNING;
        } else {
          return IMarker.SEVERITY_ERROR;
        }
      }
      
      if (MarkerViewUtil.PATH_ATTRIBUTE.equals(attributeName_p)){
        String pathAttributes = ICommonConstants.EMPTY_STRING;
        for (Object data : diagnostic.getData()) {
          if (data instanceof ModelElement) {
            ModelElement element = (ModelElement) data;
            pathAttributes += element.getFullLabel() + ICommonConstants.LINE_SEPARATOR;
          }
        }
        return pathAttributes;
      }

      return attributes.get(attributeName_p);
    }

    /**
     * @see org.eclipse.core.resources.IMarker#getAttribute(java.lang.String, int)
     */
    public int getAttribute(String attributeName_p, int defaultValue_p) {
      Object result = null;
      try {
        result = getAttribute(attributeName_p);
      } catch (CoreException e) {
        MarkerViewPlugin.getDefault().getLog().log(new Status(e.getStatus().getSeverity(), MarkerViewPlugin.PLUGIN_ID, e.getMessage(), e));
      }
      if (result instanceof Integer){
        return ((Integer) result).intValue();
      }
      return defaultValue_p;
    }

    /**
     * @see org.eclipse.core.resources.IMarker#getAttribute(java.lang.String, java.lang.String)
     */
    public String getAttribute(String attributeName_p, String defaultValue_p) {
      Object result = null;
      try {
        result = getAttribute(attributeName_p);
      } catch (CoreException e) {
        MarkerViewPlugin.getDefault().getLog().log(new Status(e.getStatus().getSeverity(), MarkerViewPlugin.PLUGIN_ID, e.getMessage(), e));
      }
      if (result instanceof String){
        return (String) result;
      }
      return defaultValue_p;
    }

    /**
     * @see org.eclipse.core.resources.IMarker#getAttribute(java.lang.String, boolean)
     */
    public boolean getAttribute(String attributeName_p, boolean defaultValue_p) {
      Object result = null;
      try {
        result = getAttribute(attributeName_p);
      } catch (CoreException e) {
        MarkerViewPlugin.getDefault().getLog().log(new Status(e.getStatus().getSeverity(), MarkerViewPlugin.PLUGIN_ID, e.getMessage(), e));
      }
      if (result instanceof Boolean){
        return ((Boolean) result).booleanValue();
      }
      return defaultValue_p;
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
    public Object[] getAttributes(String[] attributeNames_p) throws CoreException {
      Object[] res = new Object[attributeNames_p.length];
      for (int i = 0; i < attributeNames_p.length; i++) {
        res[i] = getAttribute(attributeNames_p[i]);
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
    public boolean isSubtypeOf(String superType_p) throws CoreException {
      return false;
    }

    /**
     * @see org.eclipse.core.resources.IMarker#setAttribute(java.lang.String, int)
     */
    public void setAttribute(String attributeName_p, int value_p) throws CoreException {
      attributes.put(attributeName_p, Integer.valueOf(value_p));
    }

    /**
     * @see org.eclipse.core.resources.IMarker#setAttribute(java.lang.String, java.lang.Object)
     */
    public void setAttribute(String attributeName_p, Object value_p) throws CoreException {
      attributes.put(attributeName_p, value_p);
    }

    /**
     * @see org.eclipse.core.resources.IMarker#setAttribute(java.lang.String, boolean)
     */
    public void setAttribute(String attributeName_p, boolean value_p) throws CoreException {
      attributes.put(attributeName_p, Boolean.valueOf(value_p));
    }

    /**
     * @see org.eclipse.core.resources.IMarker#setAttributes(java.util.Map)
     */
    @SuppressWarnings("rawtypes")
    public void setAttributes(Map attributes_p) throws CoreException {
      for (Object key : attributes_p.keySet()) {
        if ((key != null) && (key instanceof String)) {
          attributes.put((String) key, attributes_p.get(key));
        }
      }
    }

    /**
     * @see org.eclipse.core.resources.IMarker#setAttributes(java.lang.String[], java.lang.Object[])
     */
    public void setAttributes(String[] attributeNames_p, Object[] values_p) throws CoreException {
      for (int i = 0; i < attributeNames_p.length; i++) {
        String key = attributeNames_p[i];
        Object value = values_p[i];
        attributes.put(key, value);
      }
    }

    /**
     * LightMarkers can be adapted into EMF Diagnostic objects
     * @see org.eclipse.emf.common.util.Diagnostic
     */
    @SuppressWarnings("rawtypes")
    public Object getAdapter(Class adapter_p) {
      if (adapter_p == Diagnostic.class){
        return diagnostic;
      }
      return null;
    }
  }

  /**
   * Light markers do fire notifications after they were added to this registry. You must therefore set all marker attributes upon creation via this callback
   * class, by passing an instance to createMarker();
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
    public void modify(IMarker marker_p) {
      theMarker = marker_p;
    }

    public IMarker getMarker() {
      return theMarker;
    }
  }

  /**
   * {@inheritDoc}
   */
  public void addListener(IMarkerSourceListener listener_p) {
    listeners.add(listener_p);
  }

  /**
   * {@inheritDoc}
   */
  public void removeListener(IMarkerSourceListener listener_p) {
    listeners.remove(listener_p);
  }

}
