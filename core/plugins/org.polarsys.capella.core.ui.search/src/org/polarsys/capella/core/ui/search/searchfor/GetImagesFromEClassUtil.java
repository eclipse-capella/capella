/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.search.searchfor;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.swt.graphics.Image;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.core.model.handler.helpers.HoldingResourceHelper;

/**
 * Utility class for images
 */
public final class GetImagesFromEClassUtil {
  private static ExecutionManager exeManager;
  private static TransactionalEditingDomain editingDomain;
  private static Resource res;
  private static Map<EClass, Image> mapImages;

  private static GetImagesFromEClassUtil INSTANCE = null;

  private GetImagesFromEClassUtil() {
    exeManager = ExecutionManagerRegistry.getInstance().addNewManager();
    editingDomain = exeManager.getEditingDomain();
    res = HoldingResourceHelper.getHoldingResource(editingDomain);
    mapImages = new HashMap<EClass, Image>();
  }

  public static GetImagesFromEClassUtil getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new GetImagesFromEClassUtil();
    }
    return INSTANCE;
  }

  public Image getImageForEClass(EClass eCls, ComposedAdapterFactory compAdapterFactory) {
    if (mapImages.containsKey(eCls)) {
      return mapImages.get(eCls);
    }

    if (! eCls.isAbstract()) {
      EPackage pkq = eCls.getEPackage();
      AdapterFactory factoryForType = compAdapterFactory.getFactoryForType(pkq);
      EObject eObj = createEObject(eCls);
      if (eObj != null) {
        IItemLabelProvider itemProvider = (IItemLabelProvider) factoryForType.adapt(eObj, IItemLabelProvider.class);
        if (null != itemProvider) {
          Image img = ExtendedImageRegistry.getInstance().getImage(itemProvider.getImage(eObj));
          mapImages.put(eCls, img);
          return img;
        }
      }
    }
    return null;
  }

  private EObject createEObject(EClass eClass) {
    EFactory fact = eClass.getEPackage().getEFactoryInstance();
    try {
      EObject obj = fact.create(eClass);
      if (obj != null) {
        editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
          @Override
          protected void doExecute() {
            res.getContents().add(obj);
          }
        });
      }
      return obj;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
