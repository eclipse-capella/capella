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
package org.polarsys.capella.core.platform.sirius.clipboard.operations;

import java.io.ByteArrayOutputStream;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.gmf.runtime.emf.clipboard.core.CopyObjects;
import org.eclipse.gmf.runtime.emf.clipboard.core.CopyOperation;
import org.eclipse.gmf.runtime.emf.clipboard.core.OverrideCopyOperation;
import org.eclipse.gmf.runtime.emf.clipboard.core.internal.ResourceInfoProcessor;
import org.eclipse.gmf.runtime.emf.clipboard.core.internal.SerializationEMFResource;

@SuppressWarnings("restriction")
public class CapellaOverrideCopyOperation extends OverrideCopyOperation {

  private static final int KILOBYTE = 1024;

  private static final int BUFFER_SIZE = 128 * KILOBYTE;
  
  private static final URI COPY_URI = URI.createFileURI(""); //$NON-NLS-1$
  
  public CapellaOverrideCopyOperation(CopyOperation overriddenCopyOperation) {
    super(overriddenCopyOperation);
  }
  
  @Override
  public String copy() throws Exception {
    return doCopy();
  }


  @Override
  protected String doCopy() throws Exception {
    CopyObjects copyObjects = getAuxiliaryObjects();
    if (isCancelled()) {
      throwCancelEx();
    }

    return saveCopyObjects(copyObjects, COPY_URI, "UTF-8", //$NON-NLS-1$
        SerializationEMFResource.SAVE_OPTIONS, getEObjectsHintMap());
  }
  
  private String saveCopyObjects(CopyObjects copyObjects, URI uri,
      String encoding, Map saveOptions, Map hints) throws Exception {
      CapellaSavingEMFResource res = null;
      try {
        try {
          res = new CapellaSavingEMFResource(uri, encoding, saveOptions,
            getCopyParentsCopier().getCopy2ObjectMap(), copyObjects,
            getClipboardOperationHelper());
        } catch (Exception ex) {
          //if the ctor of the saving resource threw an exception, then       
          //something is wrong with elements we have, usually a problem with a
          // detached view-element that renderes it un-copy-able.
          throwUncopyableEx();
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream(BUFFER_SIZE);
        if(res != null) {
          res.save(out, null);          
        }
        String dataStr = out.toString(encoding);
        String infoStr = ResourceInfoProcessor.getResourceInfo(dataStr
          .length(), encoding, res, hints, copyObjects);
        return dataStr + infoStr;
      } finally {
        if (res != null) {
          res.unload();
        }
      }
    }
  
  private void throwCancelEx() {
    throwCancelException(COPY);
  }
  
  private void throwUncopyableEx() {
    throwException(COPY,
      new IllegalArgumentException("Uncopyable Object")); //$NON-NLS-1$
  }
}
