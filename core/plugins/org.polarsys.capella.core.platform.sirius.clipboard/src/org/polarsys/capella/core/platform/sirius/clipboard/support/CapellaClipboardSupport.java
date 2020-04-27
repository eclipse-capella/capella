/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.clipboard.support;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.gmf.runtime.emf.clipboard.core.AbstractClipboardSupport;
import org.eclipse.gmf.runtime.emf.clipboard.core.CopyOperation;
import org.eclipse.gmf.runtime.emf.clipboard.core.IClipboardSupport;
import org.eclipse.gmf.runtime.emf.clipboard.core.OverrideCopyOperation;
import org.eclipse.gmf.runtime.emf.clipboard.core.PasteTarget;
import org.eclipse.gmf.runtime.emf.clipboard.core.internal.IClipboardSupport2;
import org.eclipse.gmf.runtime.emf.clipboard.core.internal.PasteIntoParentOperation;
import org.eclipse.gmf.runtime.emf.clipboard.core.internal.PasteOperation;
import org.polarsys.capella.core.platform.sirius.clipboard.operations.CapellaOverrideCopyOperation;
import org.polarsys.capella.core.platform.sirius.clipboard.operations.CapellaPasteIntoParentOperation;

@SuppressWarnings("restriction")
public class CapellaClipboardSupport extends AbstractClipboardSupport implements IClipboardSupport2{

  private static final IClipboardSupport instance = new CapellaClipboardSupport();
  
  private CapellaClipboardSupport() {
    super();
  }
  
  /**
   * Obtains the singleton instance of this class.
   * 
   * @return my instance
   */
  public static IClipboardSupport getInstance() {
    return instance;
  }

  @Override
  public boolean shouldOverridePasteIntoParentOperation(PasteTarget pasteTarget, Map hintsMap) {
    return true;
  }

  
  @Override
  public PasteIntoParentOperation getPasteIntoParentOperation(PasteOperation pasteOperation, PasteTarget pasteTarget,
      Map hintsMap) throws Exception {
    return new CapellaPasteIntoParentOperation(pasteOperation, pasteTarget, hintsMap);
  }
  
  @Override
  public XMLResource getResource(EObject eObject) {
    Resource eResource = eObject.eResource();
    if(eResource instanceof XMLResource) {
      return (XMLResource) eResource;      
    }
    return new ResourceWrapper(eResource);
  }
  
  @Override
  public boolean shouldOverrideCopyOperation(Collection eObjects, Map hintMap) {
    return true;
  }
  
  @Override
  public OverrideCopyOperation getOverrideCopyOperation(CopyOperation overriddenCopyOperation) {
    return new CapellaOverrideCopyOperation(overriddenCopyOperation);
  }
}
