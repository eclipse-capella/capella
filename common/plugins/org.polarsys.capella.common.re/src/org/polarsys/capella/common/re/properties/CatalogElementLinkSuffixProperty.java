/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.re.properties;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.re.Activator;
import org.polarsys.capella.common.re.CatalogElementKind;
import org.polarsys.capella.common.re.CatalogElementLink;

/**
 * This property is used in the CatalogElementLink property view
 */
public class CatalogElementLinkSuffixProperty
    extends org.polarsys.capella.common.flexibility.properties.property.EAdaptableFeatureProperty {

  @Override
  public boolean isEnabled(IPropertyContext context) {
    Object source = context.getSource();
    
    if (source instanceof CatalogElementLink) { 
      CatalogElementLink link = (CatalogElementLink)source;
      CatalogElementKind kind = link.getSource().getKind();
      
      if (kind == CatalogElementKind.RPL || kind == CatalogElementKind.GROUPING) {
        return false;
      }
    }

    return true;
  }
  
  @Override
  public IStatus validate(Object newValue, IPropertyContext context) {
    Object source = context.getSource();
    
    if (source instanceof CatalogElementLink) {
      CatalogElementLink link = (CatalogElementLink)source;
      CatalogElementKind kind = link.getSource().getKind();
      
      if (kind == CatalogElementKind.RPL) {
        return new Status(IStatus.INFO, Activator.PLUGIN_ID, "This attribute should be changed in the REC link");
        
      } else if (kind == CatalogElementKind.GROUPING) {
        return new Status(IStatus.INFO, Activator.PLUGIN_ID, "This attribute is not enabled on Grouping element");
        
      }
    }
    return Status.OK_STATUS;
  }

}
