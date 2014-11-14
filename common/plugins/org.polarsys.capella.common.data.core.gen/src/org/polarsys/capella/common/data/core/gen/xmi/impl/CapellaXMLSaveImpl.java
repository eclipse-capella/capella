/*******************************************************************************
 *  Copyright (c) 2007, 2009 LCELB
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 * 
 *  Contributors:
 *      LCELB - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.data.core.gen.xmi.impl;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLSave;
import org.polarsys.capella.common.bundle.FeatureHelper;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.kitalpha.emde.xmi.XMIExtensionSaveImpl;

/**
 * {@link XMLSave} implementation that serializes the Capella Release at the beginning of the resource.<br>
 * The Capella release is serialized as a XML comment.
 */
public class CapellaXMLSaveImpl extends XMIExtensionSaveImpl {
  /**
   * Capella version prefix serialized as XML comment at the beginning of Capella EMF resources.
   */
  public static final String CAPELLA_VERSION_PREFIX = "Capella_Version_"; //$NON-NLS-1$
  /**
   * Melody legacy version prefix (still needed to migrate old models).
   */
  public static final String MELODY_VERSION_PREFIX = "MelodyAdvance_Version_"; //$NON-NLS-1$
  /**
   * Keep a mark at the beginning of the document just below the XML declaration.
   */
  private Object _capellaReleaseMark;

  /**
   * Constructor.
   * @param options_p
   * @param helper_p
   * @param encoding_p
   * @param xmlVersion_p
   */
  public CapellaXMLSaveImpl(Map<?, ?> options_p, XMLHelper helper_p, String encoding_p, String xmlVersion_p) {
    super(options_p, helper_p, encoding_p, xmlVersion_p);
  }

  /**
   * Constructor.
   * @param helper_p
   */
  public CapellaXMLSaveImpl(XMLHelper helper_p) {
    super(helper_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected boolean shouldSaveFeature(EObject o_p, EStructuralFeature f_p) {
    // Always save the ID feature.
    if (ModellingcorePackage.Literals.MODEL_ELEMENT__ID.equals(f_p)) {
      return true;
    }
    return super.shouldSaveFeature(o_p, f_p);
  }

  /**
   * @see org.eclipse.emf.ecore.xmi.impl.XMLSaveImpl#addDoctypeInformation()
   */
  @Override
  protected void addDoctypeInformation() {
    // Add Capella Version as comment.
    doc.resetToMark(_capellaReleaseMark);
    doc.addComment(CAPELLA_VERSION_PREFIX + FeatureHelper.getCapellaVersion(false));
    doc.addLine();
    super.addDoctypeInformation();
  }

  /**
   * @see org.eclipse.emf.ecore.xmi.impl.XMISaveImpl#writeTopObjects(java.util.List)
   */
  @Override
  public Object writeTopObjects(List<? extends EObject> contents_p) {
    _capellaReleaseMark = doc.mark();
    return super.writeTopObjects(contents_p);
  }

  /**
   * @see org.eclipse.emf.ecore.xmi.impl.XMLSaveImpl#writeTopObject(org.eclipse.emf.ecore.EObject)
   */
  @Override
  protected Object writeTopObject(EObject top) {
    _capellaReleaseMark = doc.mark();
    return super.writeTopObject(top);
  }
  
  /**
   * 
   * @see org.eclipse.emf.ecore.xmi.impl.XMLSaveImpl#saveElementID(org.eclipse.emf.ecore.EObject)
   */
  @Override
  protected void saveElementID(EObject o_p) {
    saveFeatures(o_p);
    return;
  }
  
}
