/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.mdsofa.common.misc;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

public class ExtensionClassDescriptor {

  private static final String ATTR_CLASS = "class"; //$NON-NLS-1$
  
  private String className;
  private String contributorPluginId;
  
  /*
   * Constructor.
   */
  public ExtensionClassDescriptor(IConfigurationElement configurationElement){
    Assert.isNotNull(configurationElement);

    contributorPluginId = configurationElement.getContributor().getName();
    Assert.isNotNull(contributorPluginId);

    className = configurationElement.getAttribute(ATTR_CLASS);
    Assert.isNotNull(className);
  }
  
  public String getContributorPluginId() {
    return contributorPluginId;
  }
  
  public String getClassName() {
    return className;
  }
  
  public Class<?> loadClass() throws Exception {
    Bundle bundle = Platform.getBundle(contributorPluginId);
    if (bundle == null) {
      throw new IllegalStateException("Cannot locate contributor plug-in '" + contributorPluginId + "'"); //$NON-NLS-1$ //$NON-NLS-2$
    }
    return bundle.loadClass(className);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((className == null) ? 0 : className.hashCode());
    result = prime * result + ((contributorPluginId == null) ? 0 : contributorPluginId.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ExtensionClassDescriptor other = (ExtensionClassDescriptor) obj;
    if (className == null) {
      if (other.className != null)
        return false;
    } else if (!className.equals(other.className))
      return false;
    if (contributorPluginId == null) {
      if (other.contributorPluginId != null)
        return false;
    } else if (!contributorPluginId.equals(other.contributorPluginId))
      return false;
    return true;
  }
}
