/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.re.ui.quickfix.resolver;

import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractDeleteCommandResolver;

/**
 * Delete an Element, with confirmation also delete the marker (ok:yes, cancel:no)
 */
public class DeleteGivenElement extends AbstractDeleteCommandResolver {

  @Override
  public Object getElementToDelete(Object obj) {
    return obj;
  }

}
