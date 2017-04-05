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
package org.polarsys.capella.vp.ms.ui.contextual;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;
import org.polarsys.capella.vp.ms.CSConfiguration;
import org.polarsys.capella.vp.ms.MsPackage;

public class MsContextualQueries {

  public static class Elements implements IQuery {

    @Override
    public List<Object> compute(Object configuration) {
      return new ArrayList<Object>(((CSConfiguration) configuration).getElements());
    }
  }

  public static class ElementsInverse implements IQuery {
    @Override
    public List<Object> compute(Object modelElement) {
      List<Object> result = new ArrayList<Object>();
      SemanticEditingDomain domain = (SemanticEditingDomain) TransactionUtil.getEditingDomain(modelElement);
      if (domain != null) {
        for (Setting s : domain.getCrossReferencer().getInverseReferences((EObject) modelElement,
            MsPackage.Literals.CS_CONFIGURATION__ELEMENTS, true)) {
          result.add(s.getEObject());
        }
      }
      return result;
    }
  }

  public static class Modes implements IQuery {

    @Override
    public List<Object> compute(Object configuration) {
      return new ArrayList<Object>(((CSConfiguration) configuration).getSupportedModes());
    }
  }

  public static class ModesInverse implements IQuery {
    @Override
    public List<Object> compute(Object abstractState) {
      List<Object> result = new ArrayList<Object>();
      SemanticEditingDomain domain = (SemanticEditingDomain) TransactionUtil.getEditingDomain(abstractState);
      if (domain != null) {
        for (Setting s : domain.getCrossReferencer().getInverseReferences((EObject) abstractState,
            MsPackage.Literals.CS_CONFIGURATION__SUPPORTED_MODES, true)) {
          result.add(s.getEObject());
        }
      }
      return result;
    }
  }

  public static class Context implements IQuery {

    @Override
    public List<Object> compute(Object configuration) {
      return new ArrayList<Object>(((CSConfiguration) configuration).getContext());
    }
  }

  public static class ContextInverse implements IQuery {
    public List<Object> compute(Object situation) {
      List<Object> result = new ArrayList<Object>();
      SemanticEditingDomain domain = (SemanticEditingDomain) TransactionUtil.getEditingDomain(situation);
      if (domain != null) {
        for (Setting s : domain.getCrossReferencer().getInverseReferences((EObject) situation,
            MsPackage.Literals.CS_CONFIGURATION__CONTEXT, true)) {
          result.add(s.getEObject());
        }
      }
      return result;
    }
  }

}
