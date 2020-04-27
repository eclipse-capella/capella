/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.postgeneration.egf;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * Utility class.
 * 
 */
public final class UpdateIconsUtil {
  
  private UpdateIconsUtil() {
    // Forbids instantiation
  }
  

  /* Building of resource sets */
  
  public static Resource fileToResource(ResourceSet rs_p, IFile file_p) {
    URI fileUri = URI.createPlatformResourceURI(file_p.getFullPath().toString(), true);
    Resource result = rs_p.getResource(fileUri, true);
    return result;
  }
    
  public static Resource getResourceFrom(IFile origin_p, boolean loadUsedResources) {
    ResourceSet rs = new ResourceSetImpl();
    Resource result = fileToResource(rs, origin_p);
    if (loadUsedResources) EcoreUtil.resolveAll(rs);
    return result;
  }
  
  public static Resource getResourceFrom(IFile file_p, ResourceSet rs_p, boolean verbose_p) {
    Resource result = null;
    if (file_p != null) {
      try {
        result = UpdateIconsUtil.fileToResource(rs_p, file_p);
      } catch(RuntimeException e) {
        if (verbose_p) e.printStackTrace();
      }
    }
    return result;
  }

  
  
  /* Low-level utils */
  
  public static String buildString(Object... objects_p) {
    StringBuilder builder = new StringBuilder();
    for(Object object : objects_p) {
      if (null != object) builder.append(object);
    }
    return builder.toString();
  }

  
}
