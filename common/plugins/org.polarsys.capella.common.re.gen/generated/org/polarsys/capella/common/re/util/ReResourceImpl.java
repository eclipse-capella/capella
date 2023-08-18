/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.re.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLLoad;
import org.eclipse.emf.ecore.xmi.XMLParserPool;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.XMLSave;
import org.eclipse.emf.ecore.xmi.impl.URIHandlerImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLParserPoolImpl;
import org.polarsys.capella.common.data.core.gen.xmi.impl.CapellaXMLSaveImpl;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.eclipse.emf.ecore.xmi.impl.XMLSaveImpl;
import org.polarsys.kitalpha.emde.xmi.XMIExtensionHelperImpl;
import org.polarsys.kitalpha.emde.xmi.XMIExtensionLoadImpl;

/**
 * <!-- begin-user-doc -->
 * The <b>Resource </b> associated with the package.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.common.re.util.ReResourceFactoryImpl
 * @generated
 */
public class ReResourceImpl extends XMIResourceImpl {
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private List<Object> lookupTable = new ArrayList<Object>();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private XMLParserPool parserPool = new XMLParserPoolImpl();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private Map<Object, Object> nameToFeatureMap = new HashMap<Object, Object>();

  /**
   * Creates an instance of the resource.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param uri the URI of the new resource.
   * @generated
   */
  public ReResourceImpl(URI uri) {
    super(uri);
  }

  /**
     * {@inheritDoc}
     * @generated NOT
     */
  @Override
  protected void attachedHelper(EObject eObject_p) {
    super.attachedHelper(eObject_p);
  }

  /**
  * <!-- begin-user-doc -->
  * <!-- end-user-doc -->
  * @generated NOT
  */
  @Override
  protected XMLSave createXMLSave() {
    return new XMLSaveImpl(createXMLHelper());
  }

  /**
   * <!-- begin-user-doc -->
  * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected XMLHelper createXMLHelper() {
    return new XMIExtensionHelperImpl(this);	
  }

  /**
   * <!-- begin-user-doc -->
  * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected XMLLoad createXMLLoad() {
    return new XMIExtensionLoadImpl((XMIExtensionHelperImpl) createXMLHelper());	
  }

  /**
   * <!-- begin-user-doc -->
  * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected boolean useIDAttributes() {
    return false;
  }

  /**
   * <!-- begin-user-doc -->
  * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected void init() {
    super.init();

    // Save Options
    getDefaultSaveOptions().put(XMLResource.OPTION_ENCODING, "UTF-8");  //$NON-NLS-1$
    getDefaultSaveOptions().put(XMLResource.OPTION_CONFIGURATION_CACHE, Boolean.TRUE);
    getDefaultSaveOptions().put(XMLResource.OPTION_USE_CACHED_LOOKUP_TABLE, lookupTable);    
    getDefaultSaveOptions().put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.TRUE);    
    getDefaultSaveOptions().put(XMLResource.OPTION_SAVE_TYPE_INFORMATION, Boolean.TRUE);    
    getDefaultSaveOptions().put(XMLResource.OPTION_LINE_WIDTH, new Integer(80));
    getDefaultSaveOptions().put(XMLResource.OPTION_URI_HANDLER, new URIHandlerImpl.PlatformSchemeAware());		        
    getDefaultSaveOptions().put(XMLResource.OPTION_FLUSH_THRESHOLD, Integer.valueOf(0x01000000));
    getDefaultSaveOptions().put(XMLResource.OPTION_USE_FILE_BUFFER, Boolean.TRUE);

    // Load Options
    getDefaultLoadOptions().put(XMLResource.OPTION_DEFER_ATTACHMENT, Boolean.TRUE);
    getDefaultLoadOptions().put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION, Boolean.TRUE);
    getDefaultLoadOptions().put(XMLResource.OPTION_USE_DEPRECATED_METHODS, Boolean.TRUE);
    getDefaultLoadOptions().put(XMLResource.OPTION_USE_PARSER_POOL, parserPool);
    getDefaultLoadOptions().put(XMLResource.OPTION_USE_XML_NAME_TO_FEATURE_MAP, nameToFeatureMap);
    getDefaultLoadOptions().put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.TRUE);
    getDefaultLoadOptions().put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
  }

  //begin-capella-code
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * 
   * @see org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl#getEObjectByID(java.lang.String)
   * @generated
   */
  @Override
  protected EObject getEObjectByID(String id) {
    if (idToEObjectMap == null)
      return super.getEObjectByID(id);
    return getIDToEObjectMap().get(id);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * 
   * @see org.eclipse.emf.ecore.resource.impl.ResourceImpl#getIntrinsicIDToEObjectMap()
   * @generated
   */
  @Override
  @SuppressWarnings("unchecked")
  public Map getIntrinsicIDToEObjectMap() {
    return getIDToEObjectMap();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * 
   * @see org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl#doUnload()
   * @generated
   */
  @Override
  protected void doUnload() {
    super.doUnload();
    this.idToEObjectMap = null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * 
   * @see org.eclipse.emf.ecore.resource.impl.ResourceImpl#setLoaded()
   * @generated
   */
  @Override
  protected Notification setLoaded(boolean isLoaded) {
    return super.setLoaded(isLoaded);
  }
  //end-capella-code

} //ReResourceImpl