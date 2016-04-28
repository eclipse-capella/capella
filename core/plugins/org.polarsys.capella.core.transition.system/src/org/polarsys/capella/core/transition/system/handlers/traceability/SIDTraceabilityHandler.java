/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.transition.system.handlers.traceability;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.transition.common.handlers.session.SessionHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.traceability.LinkTraceabilityHandler;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class SIDTraceabilityHandler extends LinkTraceabilityHandler {

  /**
   * The name of the property which holds the transformation UID
   */
  public static final EAttribute PROPERTY_SID = ModellingcorePackage.Literals.MODEL_ELEMENT__SID;

  public EAttribute getAttribute(IContext context) {
    return PROPERTY_SID;
  }

  public SIDTraceabilityHandler(String identifier) {
    super(identifier);
  }

  @Override
  protected List<EObject> getSourceAttachments(EObject targetElement, IContext context) {
    List<EObject> elements = new ArrayList<EObject>();

    EObject result = null;
    if (targetElement instanceof CapellaElement) {
      for (String id : getSourceIds(targetElement, context)) {
        result = SessionHandlerHelper.getInstance(context).getEObjectFromId(id, context);
        if (result != null) {
          elements.add(result);
        }
      }
    }

    return elements;
  }

  protected List<String> getSourceIds(EObject targetElement, IContext context) {
    List<String> ids = new ArrayList<String>();

    if (targetElement instanceof CapellaElement) {
      EAttribute attribute = getAttribute(context);
      String propertyValue = (String) targetElement.eGet(attribute);
      propertyValue = propertyValue == null ? ICommonConstants.EMPTY_STRING : propertyValue;
      String values[] = propertyValue.split(";");
      for (String value : values) {
        if ((value != null) && (value.length() > 0)) {
          ids.add(value);
        }
      }
    }
    return ids;
  }

  @Override
  public void attachTraceability(EObject sourceElement, EObject targetElement, IContext context) {

    if (targetElement != null) { // we allow transformation one to nothing
      createAttachment(sourceElement, targetElement, context);
    }
  }

  protected void createAttachment(EObject sourceElement, EObject targetElement, IContext context) {
    EAttribute attribute = getAttribute(context);

    String propertyValue = (String) targetElement.eGet(attribute);
    List<String> values = new ArrayList<String>();
    if ((propertyValue != null) && (propertyValue.length() > 0)) {
      values.addAll(Arrays.asList(propertyValue.split(";")));
    }

    //Retrieve SID from sourceElement or ID if none
    List<String> ids = getSourceIds(sourceElement, context);
    if (ids.size() == 0) {
      String id = SessionHandlerHelper.getInstance(context).getId(sourceElement, context);
      ids.add(id);
      if (sourceElement.eGet(attribute) == null) {
        sourceElement.eSet(attribute, id);
      }
    }

    addMappings(sourceElement, targetElement, context);

    for (String id : ids) {
      if (!values.contains(id)) {
        values.add(id);
      } else {
        //return already attached !
        return;
      }
    }

    String result = ICommonConstants.EMPTY_STRING;
    int i = 0;
    for (String value : values) {
      if ((value != null) && (value.length() > 0)) {
        result += value;
        i++;
        if (i < values.size()) {
          result += ";";
        }
      }
    }

    targetElement.eSet(attribute, result);

  }

}
