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
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.ui.views.markers.MarkerViewUtil;
import org.polarsys.capella.common.helpers.validation.ConstraintStatusDiagnostic;
import org.polarsys.capella.common.helpers.validation.IValidationConstants;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;

/**
 * A lot of default-marker cause bad performances.<br>
 * This class provides a roll-our-own implementation of the Eclipse IMarker API,
 * lacking support for a few essential features of the original API:<br>
 * - Once a marker has been created, clients won't be notified about subsequent changes to the marker.<br>
 * - All marker attributes have to be set upon creation with the help of a callback argument (see createMarker() below).<br>
 * This class is thread safe.<br>
 * Listeners are notified on the thread that created/deleted a marker.
 */
public class LightMarkerRegistry implements IMarkerSource {

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

  public void createMarker(IResource resource_p, Diagnostic diagnostic_p, Resource emfResource_p) {
    createMarker(resource_p, emfResource_p, diagnostic_p);
  }

  /**
   * Create a light marker. The returned marker should not be changed. See also the comments on the class definition above.
   */
  public IMarker createMarker(IResource fileResource_p, Resource emfResource_p, Diagnostic diagnostic_p) {
    LightMarker marker = new LightMarker(fileResource_p, diagnostic_p);

    int severity = diagnostic_p.getSeverity();
    try {
      if (severity < Diagnostic.WARNING) {
        marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_INFO);
      } else if (severity < Diagnostic.ERROR) {
        marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_WARNING);
      } else {
        marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
      }

      String message = diagnostic_p.getMessage();
      if (message != null) {
        marker.setAttribute(IMarker.MESSAGE, message);
      }

      String affectedObjectsURIs = ICommonConstants.EMPTY_STRING;
      String pathAttributes = ICommonConstants.EMPTY_STRING;
      for (Object data : diagnostic_p.getData()) {
        if (data instanceof EObject) {
          String resourceURI = ((EObject) data).eResource().getURI().toString();
          String objUri = ((EObject) data).eResource().getURIFragment((EObject) data).toString();

          affectedObjectsURIs += resourceURI + "#" + objUri + ICommonConstants.LINE_SEPARATOR; //$NON-NLS-1$
          pathAttributes += resourceURI + ICommonConstants.LINE_SEPARATOR;
        }
      }

      marker.setAttribute(EmbeddedMessage.AFFECTED_OBJECTS_URI, affectedObjectsURIs);
      marker.setAttribute(MarkerViewUtil.PATH_ATTRIBUTE, pathAttributes);
      // expose the diagnostic itself
      marker.setAttribute(IValidationConstants.TAG_DIAGNOSTIC, diagnostic_p);
      // also store the rule id directly on the marker
      marker.setAttribute(IValidationConstants.TAG_RULE_ID, getRuleId(diagnostic_p));
      // also store the emf resource directly on the marker
      marker.setAttribute(IValidationConstants.EMF_RESOURCE, emfResource_p);
    } catch (CoreException e) {
      e.printStackTrace();
    }

    _registry.add(marker);
    notifyRegistryChanged(null, marker);
    return marker;
  }

  public void createMarker(IResource fileResource_p, Diagnostic diagnostic_p, Resource emfResource_p, String preferenceFile) {
    IMarker marker = createMarker(fileResource_p, emfResource_p, diagnostic_p);
    // also store the rule id directly on the marker
    try {
      marker.setAttribute(IValidationConstants.TAG_PREFERENCE_EPF_FILE, preferenceFile);
    } catch (CoreException exception_p) {
      exception_p.printStackTrace();
    }
  }

  /**
   * Create a new marker and apply the given modification to it.
   * @param resource_p
   * @param type_p
   * @param modification
   */
  public void createMarker(IResource resource_p, String type_p, IMarkerModification modification) {
    LightMarker marker = new LightMarker(resource_p, type_p);
    modification.modify(marker);
    _registry.add(marker);
    notifyRegistryChanged(null, marker);
  }

  /**
   * Create and add a new marker to the registry. The marker can be modified via the modification_p argument before the registry fires notifications to its
   * observers. Observers will NOT be notified about changes to the marker state after this call completes. See also the comments on the class definition above.
   * @param resource_p
   * @param type_p
   * @return
   * @deprecated use createMarker(IResource resource_p, String type_p, IMarkerModification modification_p) instead
   */
  @Deprecated
  public IMarker createMarker(IResource resource_p, String type_p) {
    MarkerModificationState mod = new MarkerModificationState();
    createMarker(resource_p, type_p, mod);
    return mod.getMarker();
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

    @SuppressWarnings("unused")
    private Diagnostic diagnostic;

    LightMarker(IResource resource_p, String type_p) {
      attributes = new HashMap<String, Object>();
      resource = resource_p;
      type = type_p;
      id = System.nanoTime();
      creationTime = System.currentTimeMillis();
    }

    public LightMarker(IResource resource_p, Diagnostic diagnostic_p) {
      attributes = new HashMap<String, Object>();
      resource = resource_p;
      diagnostic = diagnostic_p;
      id = System.nanoTime();
      type = VALIDATION_TYPE;
      creationTime = System.currentTimeMillis();
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
      return attributes.get(attributeName_p);
    }

    /**
     * @see org.eclipse.core.resources.IMarker#getAttribute(java.lang.String, int)
     */
    public int getAttribute(String attributeName_p, int defaultValue_p) {
      if ((attributes != null) && attributes.containsKey(attributeName_p)) {
        return ((Integer) attributes.get(attributeName_p)).intValue();
      }
      return defaultValue_p;
    }

    /**
     * @see org.eclipse.core.resources.IMarker#getAttribute(java.lang.String, java.lang.String)
     */
    public String getAttribute(String attributeName_p, String defaultValue_p) {
      if ((attributes != null) && attributes.containsKey(attributeName_p)) {
        Object attribute = attributes.get(attributeName_p);
        return attribute != null ? attribute.toString() : null;
      }
      return defaultValue_p;
    }

    /**
     * @see org.eclipse.core.resources.IMarker#getAttribute(java.lang.String, boolean)
     */
    @SuppressWarnings("boxing")
    public boolean getAttribute(String attributeName_p, boolean defaultValue_p) {
      if ((attributes != null) && attributes.containsKey(attributeName_p)) {
        Object attribute = attributes.get(attributeName_p);
        return attribute != null ? ((Boolean) attribute).booleanValue() : null;
      }
      return defaultValue_p;
    }

    /**
     * @see org.eclipse.core.resources.IMarker#getAttributes()
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Map getAttributes() throws CoreException {
      return attributes;
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
     * Not implemented, returns the current instance of the marker
     * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
     */
    @SuppressWarnings("rawtypes")
    public Object getAdapter(Class adapter_p) {
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

  private String getRuleId(Diagnostic diag) {
    String result = null;
    if (diag instanceof ConstraintStatusDiagnostic) {
      result = ((ConstraintStatusDiagnostic) diag).getConstraintStatus().getConstraint().getDescriptor().getId();
    } else {
      result = diag.getSource() + "." + diag.getCode(); //$NON-NLS-1$
    }
    return result;
  }
}
