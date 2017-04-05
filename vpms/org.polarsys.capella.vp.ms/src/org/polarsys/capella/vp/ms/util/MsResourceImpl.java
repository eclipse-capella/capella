/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.vp.ms.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLLoad;
import org.eclipse.emf.ecore.xmi.XMLParserPool;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.XMLSave;
import org.eclipse.emf.ecore.xmi.XMLSave.XMLTypeInfo;
import org.eclipse.emf.ecore.xmi.impl.URIHandlerImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLParserPoolImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.polarsys.kitalpha.emde.xmi.XMIExtensionHelperImpl;
import org.polarsys.kitalpha.emde.xmi.XMIExtensionLoadImpl;
import org.polarsys.kitalpha.emde.xmi.XMIExtensionSaveImpl;

/**
 * <!-- begin-user-doc --> The <b>Resource </b> associated with the package. <!-- end-user-doc -->
 * 
 * @see org.polarsys.capella.vp.ms.ms.util.MsResourceFactoryImpl
 * @generated
 */
public class MsResourceImpl extends XMIResourceImpl {
  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private List<Object> lookupTable = new ArrayList<Object>();

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private XMLParserPool parserPool = new XMLParserPoolImpl();

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private Map<Object, Object> nameToFeatureMap = new HashMap<Object, Object>();

  /**
   * Creates an instance of the resource. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @param uri
   *          the URI of the new resource.
   * @generated
   */
  public MsResourceImpl(URI uri) {
    super(uri);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  protected XMLSave createXMLSave() {
    return new XMIExtensionSaveImpl(createXMLHelper());
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  protected XMLHelper createXMLHelper() {
    return new XMIExtensionHelperImpl(this);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  protected XMLLoad createXMLLoad() {
    return new XMIExtensionLoadImpl((XMIExtensionHelperImpl) createXMLHelper());
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  protected boolean useIDAttributes() {
    return false;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  protected void init() {
    super.init();
    setEncoding("UTF-8"); //$NON-NLS-1$
    setXMIVersion("2.1"); //$NON-NLS-1$
    // Save Options
    getDefaultSaveOptions().put(XMLResource.OPTION_CONFIGURATION_CACHE, Boolean.TRUE);
    getDefaultSaveOptions().put(XMLResource.OPTION_USE_CACHED_LOOKUP_TABLE, lookupTable);
    getDefaultSaveOptions().put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.TRUE);
    getDefaultSaveOptions().put(Resource.OPTION_SAVE_ONLY_IF_CHANGED,
        Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);
    getDefaultSaveOptions().put(XMLResource.OPTION_SAVE_TYPE_INFORMATION, new XMLTypeInfo() {
      public boolean shouldSaveType(EClass objectType, EClassifier featureType, EStructuralFeature feature) {
        return objectType != featureType && objectType != XMLTypePackage.Literals.ANY_TYPE;
      }

      public boolean shouldSaveType(EClass objectType, EClass featureType, EStructuralFeature feature) {
        return objectType != featureType && objectType != XMLTypePackage.Literals.ANY_TYPE;
      }
    });
    getDefaultSaveOptions().put(XMLResource.OPTION_LINE_WIDTH, new Integer(80));
    getDefaultSaveOptions().put(XMLResource.OPTION_URI_HANDLER, new URIHandlerImpl.PlatformSchemeAware());
    getDefaultSaveOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);
    getDefaultSaveOptions().put(XMIResource.OPTION_USE_XMI_TYPE, Boolean.TRUE);
    // Load Options
    getDefaultLoadOptions().put(XMLResource.OPTION_DEFER_ATTACHMENT, Boolean.FALSE);
    getDefaultLoadOptions().put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION, Boolean.FALSE);
    getDefaultLoadOptions().put(XMLResource.OPTION_USE_DEPRECATED_METHODS, Boolean.TRUE);
    getDefaultLoadOptions().put(XMLResource.OPTION_USE_PARSER_POOL, parserPool);
    getDefaultLoadOptions().put(XMLResource.OPTION_USE_XML_NAME_TO_FEATURE_MAP, nameToFeatureMap);
    getDefaultLoadOptions().put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.TRUE);
    getDefaultLoadOptions().put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
    getDefaultLoadOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);
  }

  // override to integrate the bug fix
  // https://bugs.eclipse.org/bugs/show_bug.cgi?id=412753. The bug has been
  // resolved in EMF 2.9.1 and 2.10

  @Override

  protected void detachedHelper(EObject eObject) {
    if (useIDs() && unloadingContents == null) {
      if (useUUIDs()) {
        DETACHED_EOBJECT_TO_ID_MAP.put(eObject, getID(eObject));
      }

      if (idToEObjectMap != null && eObjectToIDMap != null) {
        setID(eObject, null);
      }
    }
    resourceImplDetachedHelper(eObject);
  }

  // Duplicated from
  // org.eclipse.emf.ecore.resource.impl.ResourceImpl.detachedHelper(EObject)
  private void resourceImplDetachedHelper(EObject eObject) {
    Map<String, EObject> map = getIntrinsicIDToEObjectMap();
    if (map != null) {
      String id = EcoreUtil.getID(eObject);
      if (id != null) {
        map.remove(id);
      }
    }

    if (isTrackingModification()) {
      eObject.eAdapters().remove(modificationTrackingAdapter);
    }
  }

} // MsResourceImpl