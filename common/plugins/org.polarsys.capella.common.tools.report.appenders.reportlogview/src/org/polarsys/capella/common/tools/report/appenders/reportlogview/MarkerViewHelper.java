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
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.validation.model.Category;
import org.eclipse.emf.validation.service.IConstraintDescriptor;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.common.helpers.validation.ConstraintStatusDiagnostic;
import org.polarsys.capella.common.helpers.validation.IValidationConstants;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.extpoint.ReportLogViewExtPointUtil;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.capella.shared.id.handler.ResourceSetScope;

/**
 * Helpers previously contained in MarkerContentProvider.java
 */
public class MarkerViewHelper {

  public static final String ECORE_DIAGNOSTIC_SOURCE = "org.eclipse.emf.ecore"; //$NON-NLS-1$

  public static final Object OTHER_CATEGORY = new Object();
  public static final Object OTHER_RULES = new Object();

  private final IMarkerSource markerSource;
  private final String viewId;

  public MarkerViewHelper(IMarkerSource markerSource_p, String viewId_p) {
    markerSource = markerSource_p;
    viewId = viewId_p;
  }

  /**
   * Find markers for specified marker type. We search the LightMarkerRegistry and the given resource.
   * @param markerType_p
   * @param resultingCollection_p A not <code>null</code> collection that should host matching markers.
   * @param filterElements_p Should elements be filtered ? <code>true</code> if so (see {@link #keepMarker(IMarker)} then), <code>false</code> otherwise.
   * @param resource_p the resource to check for additional markers (apart from the LightMarkerRegistry)
   */
  private void findMarkersFor(String markerType_p, Collection<IMarker> resultingCollection_p, IResource resource_p) {
    try {
      // search 'classic' workspace markers
      if (resource_p != null) {
        IMarker[] markers = resource_p.findMarkers(markerType_p, false, IResource.DEPTH_INFINITE);
        if (null != markers) {
          for (IMarker marker : markers) {
            resultingCollection_p.add(marker);
          }
        }
      }
      // search light markers
      for (IMarker marker : markerSource.getMarkers()) {
        if (markerType_p.equals(marker.getType())) {
          resultingCollection_p.add(marker);
        }
      }
    } catch (CoreException e) {
      MarkerViewPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, MarkerViewPlugin.PLUGIN_ID, e.getLocalizedMessage(), e));
    }
  }

  /**
   * Find the marker types to show in the given view, as defined via extension point.
   * @param viewId_p
   * @return
   */
  String[] getMarkersToMatchId(String viewId_p) {
    // filters declared via extension point
    String[] markerIDs = ReportLogViewExtPointUtil.getMarkersID(viewId_p);
    if (null == markerIDs) {
      // default
      markerIDs = new String[] { MarkerView.MARKER_ID };
    }
    return markerIDs;
  }

  public Collection<IMarker> findMarkers(IResource resource_p) {
    return findMarkers(resource_p, null);
  }

  /**
   * Returns a collection that contains all markers found in the light marker registry and the argument resource. The result is sorted with the help of the
   * comparator passed as the second argument. ATTENTION: If you pass a comparator, make sure it returns only 0 for elements that are equal in the sense of
   * object.equals(). This is because the backing collection is a TreeSet.
   * @see TreeSet
   * @param resource_p - may be null
   * @param comparator_p - may be null
   * @return
   */
  public Collection<IMarker> findMarkers(IResource resource_p, Comparator<IMarker> comparator_p) {
    Comparator<IMarker> comparator = comparator_p;
    Collection<IMarker> result = null;
    if (comparator == null) {
      result = new HashSet<IMarker>();
    } else {
      result = new TreeSet<IMarker>(comparator);
    }
    for (String markerType : getMarkersToMatchId(viewId)) {
      findMarkersFor(markerType, result, resource_p);
    }
    return result;
  }

  /**
   * Get the TAG_RULE_ID Attribute from a marker.
   * @param marker
   * @return the value stored in the TAG_RULE_ID attribute of the argument. May be null.
   */
  public static String getRuleId(IMarker marker) {
    return marker.getAttribute(IValidationConstants.TAG_RULE_ID, null);
  }

  /**
   * Get the TAG_RULE_ID Attribute from a marker.
   * @param marker
   * @return the value stored in the TAG_RULE_ID attribute of the argument. May be null.
   */
  public static String getPreferenceFileName(IMarker marker) {
    return marker.getAttribute(IValidationConstants.TAG_PREFERENCE_EPF_FILE, null);
  }

  /**
   * Get an unqualified ID from a qualified one. It just strips averything from ruleId_p up to the last '.'
   * @param ruleId_p
   * @return the unqualified rule id
   */
  public String getUnqualifiedRuleId(String ruleId_p) {
    // show the unqualified name
    String result = ruleId_p;
    int lastDot = result.lastIndexOf('.');
    if ((lastDot >= 0) && (lastDot < (result.length() - 1))) {
      result = result.substring(lastDot + 1, result.length());
    }
    return result;
  }

  /**
   * @param marker_p
   * @return
   */
  public static IConstraintDescriptor getConstraintDescriptor(IMarker marker_p) {
    IConstraintDescriptor descriptor = null;
    try {
      Diagnostic d = (Diagnostic) marker_p.getAttribute(IValidationConstants.TAG_DIAGNOSTIC);
      if (d instanceof ConstraintStatusDiagnostic) {
        descriptor = ((ConstraintStatusDiagnostic) d).getConstraintStatus().getConstraint().getDescriptor();
      }
    } catch (CoreException e) {
      MarkerViewPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, MarkerViewPlugin.PLUGIN_ID, e.getLocalizedMessage(), e));
    }
    return descriptor;
  }

  /**
   * @param marker_p
   * @return the marker's diagnostic, if it has one. null otherwise.
   */
  public static Diagnostic getDiagnostic(IMarker marker_p) {
    try {
      return (Diagnostic) marker_p.getAttribute(IValidationConstants.TAG_DIAGNOSTIC);
    } catch (CoreException e) {
      MarkerViewPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, MarkerViewPlugin.PLUGIN_ID, e.getLocalizedMessage(), e));
    }
    return null;
  }

  public static Category getCategory(IMarker marker_p) {
    Category result = null;
    Diagnostic diag = getDiagnostic(marker_p);
    if ((diag != null) && (diag instanceof ConstraintStatusDiagnostic)) {
      Set<Category> cats = ((ConstraintStatusDiagnostic) diag).getConstraintStatus().getConstraint().getDescriptor().getCategories();
      if (!cats.isEmpty()) {
        result = cats.iterator().next();
      }
    }
    return result;
  }

  private static EObject find(IMarker marker_p, URI uri_p) {
    EObject eobject = null;
    if (uri_p == null) {
      return eobject;
    }

    String fragment = uri_p.fragment();
    try {
      //If there is a resource in the marker (validation marker), try to retrieve the object
      Resource resource = (Resource) marker_p.getAttribute(IValidationConstants.EMF_RESOURCE);
      if ((fragment != null) && (resource != null)) {
        eobject = resource.getEObject(fragment);
      }

      if (eobject == null) {
        //try to retrieve the object from the given resourceSet
        eobject = resource.getResourceSet().getEObject(uri_p, false);
      }

    } catch (Exception e) {
      //Nothing here
    }

    if (eobject == null) {
      //otherwise, we try to retrieve it from editing domains
      for (TransactionalEditingDomain domain : ExecutionManagerRegistry.getInstance().getAllEditingDomains()) {
        try {
          eobject = domain.getResourceSet().getEObject(uri_p, false);
        } catch (Exception e) {
          //Nothing here
        }

        if ((eobject == null) && (fragment != null)) {
          try {
            eobject = IdManager.getInstance().getEObject(fragment, new ResourceSetScope(domain.getResourceSet()));
          } catch (Exception e) {
            //Nothing here
          }
        }

        if (eobject != null) {
          break;
        }
      }
    }
    return eobject;
  }

  /**
   * Finds EObjects that are attached to this marker. The resulting list will not contain duplicates.
   * @param marker_p the marker to inspect
   * @return A List of EObjects that are attached to the marker. Never null. May be empty.
   */
  public static List<EObject> getModelElementsFromMarker(IMarker marker_p) {
    Set<EObject> result = new LinkedHashSet<EObject>(); // preserve order
    String uris = marker_p.getAttribute(EmbeddedMessage.AFFECTED_OBJECTS_URI, null);
    if (uris != null) {
      for (String uriValue : uris.split(ICommonConstants.LINE_SEPARATOR)) {
        if (uriValue.length() > 0) {
          EObject obj = find(marker_p, URI.createURI(uriValue));
          if (obj != null) {
            result.add(obj);
          }
        }
      }
    }
    // API specified a list..
    return new ArrayList<EObject>(result);
  }

  /**
   * Checks whether a marker is associated to an ecore diagnostic
   * @param marker_p
   * @return
   */
  public static boolean isEcore(IMarker marker_p) {
    boolean result = false;
    Diagnostic diag = getDiagnostic(marker_p);
    if ((diag != null) && diag.getSource().equals(ECORE_DIAGNOSTIC_SOURCE)) {
      result = true;
    }
    return result;
  }

}
