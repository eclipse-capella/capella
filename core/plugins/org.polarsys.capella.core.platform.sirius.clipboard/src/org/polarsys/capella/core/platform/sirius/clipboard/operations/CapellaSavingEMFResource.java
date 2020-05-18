/*******************************************************************************
 * Copyright (c) 2018, 2019 THALES GLOBAL SERVICES.
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

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.XMLSave;
import org.eclipse.emf.ecore.xmi.impl.XMISaveImpl;
import org.eclipse.gmf.runtime.emf.clipboard.core.CopyObjects;
import org.eclipse.gmf.runtime.emf.clipboard.core.IClipboardSupport;
import org.eclipse.gmf.runtime.emf.clipboard.core.internal.SavingEMFResource;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.IdentifiedElement;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.polarsys.capella.common.utils.ReflectUtil;

@SuppressWarnings({ "restriction", "rawtypes" })
public class CapellaSavingEMFResource extends SavingEMFResource {

  private static final Logger logger = Logger.getLogger(CapellaSavingEMFResource.class.getName());

  private Collection excludedObjects;

  public CapellaSavingEMFResource(URI uri, String encoding, Map defaultSaveOptions, Map copy2ObjectMap,
      CopyObjects copyObjects, IClipboardSupport clipboardOperationHelper) {
    super(uri, encoding, defaultSaveOptions, copy2ObjectMap, copyObjects, clipboardOperationHelper);
    this.excludedObjects = clipboardOperationHelper.getExcludedCopyObjects(copyObjects.totalCopyObjects);
  }

  @Override
  public String getID(EObject eObject) {
    String id = super.getID(eObject);
    Resource eResource = eObject.eResource();
    if (id == null && eResource != null && !(eResource instanceof XMLResource)) {
      id = eResource.getURIFragment(eObject);
    }
    return id;
  }

  // Override the XMLSave in order to exclude saving of non-containment features for the copied objects.
  // When we paste under team, those objects will remain dangling and will cause save failed due to a
  // DanglingIntegrityException
  @Override
  protected XMLSave createXMLSave() {
    return new XMISaveImpl(createXMLHelper()) {

      @Override
      protected void saveElement(InternalEObject o, EStructuralFeature f) {
        // do not save cross-resource-contained objects as hrefs, because
        // the clipboard resource must actually duplicate all of the
        // original data
        saveElement((EObject) o, f);
      }

      @Override
      protected void saveElement(EObject o, EStructuralFeature f) {
        if (excludedObjects.contains(o)) {
          return;
        }
        super.saveElement(o, f);
      }

      @Override
      protected boolean shouldSaveFeature(EObject o, EStructuralFeature f) {
        if (shouldNotSaveFeature(o, f)) {
          return false;
        }
        return super.shouldSaveFeature(o, f);
      }

      /**
       * @see org.eclipse.emf.ecore.xmi.impl.XMLSaveImpl#sameDocMany(org.eclipse.emf.ecore.EObject,
       *      org.eclipse.emf.ecore.EStructuralFeature)
       */
      @Override
      protected int sameDocMany(EObject o, EStructuralFeature f) {
        InternalEList values = (InternalEList) helper.getValue(o, f);
        if (values.isEmpty()) {
          return SKIP;
        }

        for (Iterator i = values.basicIterator(); i.hasNext();) {
          InternalEObject value = (InternalEObject) i.next();
          if (value.eIsProxy() || (reflectIsInSavingResource(value) == false)) {
            return CROSS_DOC;
          }
        }

        return SAME_DOC;
      }

      /**
       * @see org.eclipse.emf.ecore.xmi.impl.XMLSaveImpl#sameDocSingle(org.eclipse.emf.ecore.EObject,
       *      org.eclipse.emf.ecore.EStructuralFeature)
       */
      @Override
      protected int sameDocSingle(EObject o, EStructuralFeature f) {
        InternalEObject value = (InternalEObject) helper.getValue(o, f);
        if (value == null) {
          return SKIP;
        } else if (value.eIsProxy()) {
          return CROSS_DOC;
        } else {
          return (reflectIsInSavingResource(value)) ? SAME_DOC : CROSS_DOC;
        }
      }
    };
  }

  protected boolean shouldNotSaveFeature(EObject o, EStructuralFeature f) {
    return isSiriusElementAndUidFeature(o, f) || ((o instanceof DSemanticDecorator) && (f instanceof EReference)
        && !(((EReference) f).isContainment()) && (((EReference) f).getEType() instanceof EClass)
        && (ViewpointPackage.eINSTANCE.getDSemanticDecorator().isSuperTypeOf((EClass) ((EReference) f).getEType())
            || DiagramPackage.eINSTANCE.getEdgeTarget().isSuperTypeOf((EClass) ((EReference) f).getEType())));
  }

  // Skip saving uid for Sirius elements in order not to have duplicated diagram elements with same uids while
  // copy/paste
  // (see https://bugs.polarsys.org/show_bug.cgi?id=2462)
  private boolean isSiriusElementAndUidFeature(EObject o, EStructuralFeature f) {
    return o instanceof IdentifiedElement && ViewpointPackage.eINSTANCE.getIdentifiedElement_Uid().equals(f);
  }

  boolean reflectIsInSavingResource(EObject eObject) {
    boolean result = false;
    try {
      result = (Boolean) ReflectUtil.invokeInvisibleMethod(this, "isInSavingResource", eObject);
    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
      logger.fatal(e.getMessage(), e);
    }
    return result;
  }
}