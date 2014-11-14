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
package org.polarsys.capella.common.xmisupport.internal;

import org.eclipse.emf.ecore.EObject;

/**
 * <p>
 * Interface to support conservation of XMI Ids beyond XMI Resources.
 * </p>
 * <p>
 * In the first place, XMI IDs should not be used to externally reference model elements e.g. in Software specification documents because these IDs are bound to
 * the resource that the element is stored with. Once the XMI ID escapes into such external documents we need a way to conserve the ID beyond XMI resources,
 * e.g. when a model is ported to CDO/Database storage to avoid dangling external references.
 * </p>
 * This service transparently provides XMI IDs for EObjects regardless of the resource they are stored in. If an element is already stored in an XMI Resource we
 * just use the XMI Id. For other resources, we try to find an XMISupport.XMIResourceAdapter via the platform adapter registry for the element's resource. This
 * adapter is then responsible to provide an ID.
 * <p>
 * Of course this severely affects import/export of elements to/from XMI Resources into other types of resources. It is the import/export implementation's
 * responsibility to query this service to conserve existing IDs.
 * </p>
 * <p>
 * <b>Warning: </b>As mentioned above, you should not let XMI IDs escape into external documents in the first place. To underline this, this interface is marked
 * as internal.
 * </p>
 */
public interface XMISupport {

  /**
   * The default XMI Support instance.
   */
  public static final XMISupport INSTANCE = new BasicXMISupport();

  /**
   * Finds an XMI Id for the given EObject.
   * @param element_p the element for which to retrieve the ID.
   * @return an ID or null if the element is not directly or indirectly contained in a Resource, or if no XMIResourceAdapter is found for the elements Resource.
   */
  public String getID(EObject element_p);

}
