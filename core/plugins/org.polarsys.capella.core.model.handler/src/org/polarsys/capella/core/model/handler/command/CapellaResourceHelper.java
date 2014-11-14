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
package org.polarsys.capella.core.model.handler.command;

import java.util.Collection;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.helper.SiriusUtil;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.kitalpha.emde.model.Element;
import org.polarsys.capella.common.mdsofa.common.activator.SolFaCommonActivator;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;
import org.polarsys.capella.core.model.handler.helpers.ICapellaResourceHelper;

/**
 * Helper that deal with Capella resources.
 */
public class CapellaResourceHelper {
  /**
   * The Capella model file extension, value:<code>melodymodeller</code>
   */
  public static final String CAPELLA_MODEL_FILE_EXTENSION = "melodymodeller"; //$NON-NLS-1$
  /**
   * The Capella fragment file extension value:<code>melodyfragment</code>
   */
  public static final String CAPELLA_FRAGMENT_FILE_EXTENSION = "melodyfragment"; //$NON-NLS-1$
  /**
   * The Aird fragment file extension value:<code>airdfragment</code>
   */
  public static final String AIRD_FRAGMENT_FILE_EXTENSION = "airdfragment"; //$NON-NLS-1$
  /**
   * The Aird file extension value:<code>aird</code>
   */
  public static final String AIRD_FILE_EXTENSION = SiriusUtil.SESSION_RESOURCE_EXTENSION;
  /**
   * Fragments default folder name:<code>fragments</code>
   */
  public static final String FRAGMENTS_DEFAULT_FOLDER = "fragments"; //$NON-NLS-1$

  private static ICapellaResourceHelper __delegatedCapellaResourceHelper;

  /**
   * Ensure given resource is writable.<br>
   * If the resource is in read only, a dialog is prompted to ask the end-user to make the resource writable.
   * @param resource_p
   * @return <code>true</code> means writable; <code>false</code> means read only and the end-user turns down to make the resource writable.
   */
  public static boolean ensureResourceIsWritable(Resource resource_p) {
    boolean result = false;
    // Precondition.
    if (null == resource_p) {
      return result;
    }
    IFile file = EcoreUtil2.getFile(resource_p);
    // Is a read only resource ?
    if ((null != file) && file.isReadOnly()) {
      // Try making the file writable.
      result = SolFaCommonActivator.getDefault().getUserEnforcedHelper().makeFileWritable(file).isOK();
    } else {
      // Resource is writable.
      result = true;
    }
    return result;
  }

  /**
   * Whether or not specified resource has provided file extension.
   * @param resource_p
   * @param ignoreFragment_p
   * @param fragmentFileExtension_p
   * @param modelFileExtension_p
   * @return
   */
  private static boolean hasFileExtension(IResource resource_p, boolean ignoreFragment_p, String fragmentFileExtension_p, String modelFileExtension_p) {
    boolean result = false;
    // Precondition.
    if ((null == resource_p) || (IResource.FILE != resource_p.getType())) {
      return result;
    }
    String fileExtension = resource_p.getFileExtension();
    result = modelFileExtension_p.equals(fileExtension);
    if (!result && !ignoreFragment_p) {
      result = fragmentFileExtension_p.equals(fileExtension);
    }
    return result;
  }

  /**
   * Whether or not given resource is an AIRD one i.e a model or a fragment depending on ignoreAirdFragment_p.
   * @param resource_p
   * @param ignoreAirdFragment_p <code>true</code> means files with {@link #AIRD_FRAGMENT_FILE_EXTENSION} file extension are ignored.
   * @return <code>true</code> means given resource is a Capella one.
   * @see {@link #AIRD_FRAGMENT_FILE_EXTENSION}, {@link #AIRD_FILE_EXTENSION}
   */
  public static boolean isAirdResource(IResource resource_p, boolean ignoreAirdFragment_p) {
    return hasFileExtension(resource_p, ignoreAirdFragment_p, AIRD_FRAGMENT_FILE_EXTENSION, AIRD_FILE_EXTENSION);
  }

  /**
   * Whether or not given resource is a Capella fragment.
   * @param uri_p
   * @return <code>true</code> means given resource is a Capella fragment.
   * @see #CAPELLA_FRAGMENT_FILE_EXTENSION
   */
  public static boolean isCapellaFragment(URI uri_p) {
    return (null != uri_p) ? CAPELLA_FRAGMENT_FILE_EXTENSION.equals(uri_p.fileExtension()) : false;
  }

  /**
   * Whether or not given resource is a Capella one i.e a model or a fragment.
   * @param resource_p
   * @return <code>true</code> means given resource is a Capella one.
   * @see {@link #CAPELLA_FRAGMENT_FILE_EXTENSION}, {@link #CAPELLA_MODEL_FILE_EXTENSION}
   */
  public static boolean isCapellaResource(IResource resource_p) {
    return isCapellaResource(resource_p, false);
  }

  /**
   * Whether or not given resource is a Capella one i.e a model or a fragment depending on ignoreCapellaFragment_p.
   * @param resource_p
   * @param ignoreCapellaFragment_p <code>true</code> means files with {@link #CAPELLA_FRAGMENT_FILE_EXTENSION} file extension are ignored.
   * @return <code>true</code> means given resource is a Capella one.
   * @see {@link #CAPELLA_FRAGMENT_FILE_EXTENSION}, {@link #CAPELLA_MODEL_FILE_EXTENSION}
   */
  public static boolean isCapellaResource(IResource resource_p, boolean ignoreCapellaFragment_p) {
    return hasFileExtension(resource_p, ignoreCapellaFragment_p, CAPELLA_FRAGMENT_FILE_EXTENSION, CAPELLA_MODEL_FILE_EXTENSION);
  }

  /**
   * Whether or not given object is a Capella resource i.e a model or a fragment.
   * @param object_p
   * @return
   */
  public static boolean isCapellaResource(Object object_p) {
    // Must be a EMF resource and a capella one.
    // DO NOT TEST CapellaResourceImpl for CDO compatibility.
    return (object_p instanceof Resource) && isCapellaResource(((Resource) object_p));
  }

  /**
   * Whether or not given resource is a Capella one i.e a model or a fragment.
   * @param resource_p
   * @return <code>true</code> means given resource is a Capella one.
   * @see {@link #CAPELLA_FRAGMENT_FILE_EXTENSION}, {@link #CAPELLA_MODEL_FILE_EXTENSION}
   */
  public static boolean isCapellaResource(Resource resource_p) {
    return (null != resource_p) ? isCapellaResource(resource_p.getURI()) : false;
  }

  /**
   * Whether or not given resource is a Capella one i.e a model or a fragment.
   * @param uri_p
   * @return <code>true</code> means given resource is a Capella one.
   * @see {@link #CAPELLA_FRAGMENT_FILE_EXTENSION}, {@link #CAPELLA_MODEL_FILE_EXTENSION}
   */
  public static boolean isCapellaResource(URI uri_p) {
    // Precondition.
    if (null == uri_p) {
      return false;
    }
    if (null == __delegatedCapellaResourceHelper) {
      __delegatedCapellaResourceHelper = loadDelegatedCapellaResourceHelper();
    }
    // Call the delegation if any.
    boolean isCapellaResource = (null != __delegatedCapellaResourceHelper) ? __delegatedCapellaResourceHelper.isCapellaResource(uri_p) : false;
    return isCapellaResource || CAPELLA_MODEL_FILE_EXTENSION.equals(uri_p.fileExtension()) || isCapellaFragment(uri_p)
           || /* Needed for the Capella Phantom resource */uri_p.toString().toLowerCase().endsWith(CAPELLA_MODEL_FILE_EXTENSION);
  }

  /**
   * Returns whether an object is a semantic element. Such element benefit of all basic tooling provided by capella
   */
  public static boolean isSemanticElement(Object object_p) {
    return object_p instanceof Element;
  }

  /**
   * Returns whether objects are semantic elements. Such element benefit of all basic tooling provided by capella
   */
  public static boolean isSemanticElements(Collection<?> objects_p) {
    for (Object o : objects_p) {
      if (!isSemanticElement(o)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Load the unique delegated Capella Resource Helper.
   */
  private static ICapellaResourceHelper loadDelegatedCapellaResourceHelper() {
    ICapellaResourceHelper delegatedHelper = null;
    // Load SemanticEditingDomain providers if any.
    IConfigurationElement[] configurationElements =
        ExtensionPointHelper.getConfigurationElements("org.polarsys.capella.core.model.handler", "delegatedCapellaResourceHelper"); //$NON-NLS-1$ //$NON-NLS-2$
    // Loop over contributed SemanticEditingDomain providers, must be only one.
    if (configurationElements.length > 0) {
      delegatedHelper = (ICapellaResourceHelper) ExtensionPointHelper.createInstance(configurationElements[0], ExtensionPointHelper.ATT_CLASS);
    }
    return delegatedHelper;
  }
}
