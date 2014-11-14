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
package org.polarsys.capella.common.flexibility.wizards.loader;

import org.polarsys.capella.common.flexibility.wizards.schema.IGroupRenderer;
import org.polarsys.capella.common.flexibility.wizards.schema.IPropertyRenderer;
import org.polarsys.capella.common.flexibility.wizards.schema.IRenderers;

/**
 *
 */
public interface ILoadableRenderers {

  public void addParent(IRenderers parent_p);

  public void addPropertyRenderer(String property_p, IPropertyRenderer renderer_p);

  public void addGroupRenderer(String property_p, IGroupRenderer renderer_p);

}
