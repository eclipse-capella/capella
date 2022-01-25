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
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.model.Category;
import org.eclipse.emf.validation.service.IConstraintDescriptor;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.common.helpers.validation.ConstraintStatusDiagnostic;
import org.polarsys.capella.common.helpers.validation.IValidationConstants;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.extpoint.ReportLogViewExtPointUtil;

/**
 * Helpers previously contained in MarkerContentProvider.java
 */
public class MarkerViewHelper {

  public static final String ECORE_DIAGNOSTIC_SOURCE = "org.eclipse.emf.ecore"; //$NON-NLS-1$

  public static final Object OTHER_CATEGORY = new Object();
  public static final Object OTHER_RULES = new Object();

  private final IMarkerSource markerSource;
  private final String viewId;

  public MarkerViewHelper(IMarkerSource markerSource, String viewId) {
    this.markerSource = markerSource;
    this.viewId = viewId;
  }

  /**
   * Find markers for specified marker type. We search the LightMarkerRegistry and the given resource.
   * @param markerType
   * @param resultingCollection A not <code>null</code> collection that should host matching markers.
   * @param resource the resource to check for additional markers (apart from the LightMarkerRegistry)
   */
  private void findMarkersFor(String markerType, Collection<IMarker> resultingCollection, IResource resource) {
    try {
      // search 'classic' workspace markers
      if (resource != null) {
        IMarker[] markers = resource.findMarkers(markerType, false, IResource.DEPTH_INFINITE);
        if (null != markers) {
          for (IMarker marker : markers) {
            resultingCollection.add(marker);
          }
        }
      }
      // search light markers
      for (IMarker marker : markerSource.getMarkers()) {
        if (markerType.equals(marker.getType())) {
          resultingCollection.add(marker);
        }
      }
    } catch (CoreException e) {
      Platform.getLog(MarkerViewPlugin.class).log(new Status(IStatus.ERROR, FrameworkUtil.getBundle(MarkerViewPlugin.class).getSymbolicName(), e.getLocalizedMessage(), e));
    }
  }

  /**
   * Find the marker types to show in the given view, as defined via extension point.
   * @param viewId
   * @return
   */
  String[] getMarkersToMatchId(String viewId) {
    // filters declared via extension point
    String[] markerIDs = ReportLogViewExtPointUtil.getMarkersID(viewId);
    if (null == markerIDs) {
      // default
      markerIDs = new String[] { MarkerView.MARKER_ID };
    }
    return markerIDs;
  }

  public Collection<IMarker> findMarkers(IResource resource) {
    return findMarkers(resource, null);
  }

  /**
   * Returns a collection that contains all markers found in the light marker registry and the argument resource. The result is sorted with the help of the
   * comparator passed as the second argument. ATTENTION: If you pass a comparator, make sure it returns only 0 for elements that are equal in the sense of
   * object.equals(). This is because the backing collection is a TreeSet.
   * @see TreeSet
   * @param resource - may be null
   * @param comparator - may be null
   * @return
   */
  public Collection<IMarker> findMarkers(IResource resource, Comparator<IMarker> comparator) {
    Collection<IMarker> result = null;
    if (comparator == null) {
      result = new HashSet<IMarker>();
    } else {
      result = new TreeSet<IMarker>(comparator);
    }
    for (String markerType : getMarkersToMatchId(viewId)) {
      findMarkersFor(markerType, result, resource);
    }
    return result;
  }

  /**
   * Adapt the marker into a Diagnostic and return the diagnostic's source attribute.
   * @see org.eclipse.emf.common.util.Diagnostic
   * @param marker
   * @return the diagnostic source if the marker can be adapted to a Diagnostic
   */
  public static String getSource(IMarker marker) {
    String result = null;
    Diagnostic d = (Diagnostic) marker.getAdapter(Diagnostic.class);
    if (d != null){
      result = d.getSource();
    }
    return result;
  }
  
  /**
   * Returns the validation rule id for the given marker or null if the argument is not a validation marker
   * @param marker the marker
   * @param qualified whether the rule should be qualified or not
   * @return
   */
  public static String getRuleID(IMarker marker, boolean qualified) {
    String result = null;
    Diagnostic diag = (Diagnostic) marker.getAdapter(Diagnostic.class);
    if (diag instanceof ConstraintStatusDiagnostic) {
      result = ((ConstraintStatusDiagnostic) diag).getConstraintStatus().getConstraint().getDescriptor().getId();
      if (!qualified) {

        int lastDot = result.lastIndexOf('.');
        if ((lastDot >= 0) && (lastDot < (result.length() - 1))) {
          result = result.substring(lastDot + 1);
        }
      }
    } else if (diag instanceof BasicDiagnostic && diag.getSource() != null) {
      if(diag.getSource().equals(ECORE_DIAGNOSTIC_SOURCE))
        return diag.getSource() + "." + diag.getCode();
      return diag.getSource();
    }
    return result;
  }

  /**
   * Get the TAG_PREFERENCE_EPF_FILE Attribute from a marker.
   * @param marker
   * @return the value stored in the TAG_PREFERENCE_EPF_FILE attribute of the argument. May be null.
   */
  public static String getPreferenceFileName(IMarker marker) {
    return marker.getAttribute(IValidationConstants.TAG_PREFERENCE_EPF_FILE, null);
  }

  /**
   * Get an unqualified ID from a qualified one. It just strips averything from ruleId up to the last '.'
   * @param ruleId
   * @return the unqualified rule id
   * @deprecated this method will be removed soon
   */
  @Deprecated
  public String getUnqualifiedRuleId(String ruleId) {
    // show the unqualified name
    String result = ruleId;
    int lastDot = result.lastIndexOf('.');
    if ((lastDot >= 0) && (lastDot < (result.length() - 1))) {
      result = result.substring(lastDot + 1, result.length());
    }
    return result;
  }
  
  /**
   * @param marker
   * @return
   */
  public static IConstraintDescriptor getConstraintDescriptor(IMarker marker) {
    IConstraintDescriptor descriptor = null;
    Diagnostic d = (Diagnostic) marker.getAdapter(Diagnostic.class);
    if (d instanceof ConstraintStatusDiagnostic) {
      descriptor = ((ConstraintStatusDiagnostic) d).getConstraintStatus().getConstraint().getDescriptor();
    }
    return descriptor;
  }

  /**
   * @param marker
   * @return the marker's diagnostic, if it has one. null otherwise.
   */
  public static Diagnostic getDiagnostic(IMarker marker) {
    return (Diagnostic) marker.getAdapter(Diagnostic.class);
  }

  public static Category getCategory(IMarker marker) {
    Category result = null;
    Diagnostic diag = getDiagnostic(marker);
    if ((diag != null) && (diag instanceof ConstraintStatusDiagnostic)) {
      Set<Category> cats = ((ConstraintStatusDiagnostic) diag).getConstraintStatus().getConstraint().getDescriptor().getCategories();
      if (!cats.isEmpty()) {
        result = cats.iterator().next();
      }
    }
    return result;
  }

  /**
   * Finds EObjects that are attached to this marker. The resulting list will not contain duplicates.
   * @param marker the marker to inspect
   * @return A List of EObjects that are attached to the marker. Never null. May be empty.
   */
  public static List<EObject> getModelElementsFromMarker(IMarker marker) {
    Diagnostic diag = getDiagnostic(marker);
    if (diag != null && diag.getData() != null) {
      if (diag.getData().size() == 1) {
        Object o = diag.getData().get(0);
        if (o instanceof EObject) {
          return Collections.singletonList((EObject) o);
        }
      }
      
      Set<EObject> result = new LinkedHashSet<EObject>(); // preserve order
      for (Object o : diag.getData()){
        if (o instanceof EObject){
          result.add((EObject) o);
        }
      }
      return new ArrayList<EObject>(result);
    }
    return Collections.emptyList();
  }

  /**
   * Checks whether the given marker violates any of the basic EObject constraints
   * defined in {@link org.eclipse.emf.ecore.util.EObjectValidator}
   * @param marker
   * @return
   */
  public static boolean isEcore(IMarker marker) {
    boolean result = false;
    Diagnostic diag = getDiagnostic(marker);
    if ((diag != null) && (diag.getSource() != null) && diag.getSource().equals(ECORE_DIAGNOSTIC_SOURCE)) {
      result = true;
    }
    return result;
  }

}