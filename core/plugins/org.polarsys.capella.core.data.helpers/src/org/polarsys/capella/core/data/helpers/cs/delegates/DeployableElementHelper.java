/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.helpers.cs.delegates;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.polarsys.capella.core.data.cs.AbstractDeploymentLink;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.DeployableElement;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.NamedElementHelper;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;
import org.polarsys.capella.common.platform.sirius.tig.ef.SemanticEditingDomainFactory.SemanticEditingDomain;

public class DeployableElementHelper {
  private static DeployableElementHelper instance;
  /**
   * Cross referencing re-entrance collection for deploying links.
   */
  private List<DeployableElement> _isCrossReferencingDeployingLinks;

  private DeployableElementHelper() {
    _isCrossReferencingDeployingLinks = new ArrayList<DeployableElement>(0);
  }

  public static DeployableElementHelper getInstance() {
    if (instance == null) {
      instance = new DeployableElementHelper();
    }
    return instance;
  }

  public Object doSwitch(DeployableElement element_p, EStructuralFeature feature_p) {

    Object ret = null;

    if (feature_p.equals(CsPackage.Literals.DEPLOYABLE_ELEMENT__DEPLOYING_LINKS)) {
      ret = getDeployingLinks(element_p);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = NamedElementHelper.getInstance().doSwitch(element_p, feature_p);
    }

    return ret;
  }

  protected boolean isCrossReferencing(DeployableElement element_p) {
    return _isCrossReferencingDeployingLinks.contains(element_p);
  }

  protected void markAsCrossReferenced(DeployableElement element_p) {
    _isCrossReferencingDeployingLinks.add(element_p);
  }

  protected void unmarkAsCrossReferenced(DeployableElement element_p) {
    _isCrossReferencingDeployingLinks.remove(element_p);
  }

  protected List<AbstractDeploymentLink> getDeployingLinks(DeployableElement element_p) {
    List<AbstractDeploymentLink> ret = new ArrayList<AbstractDeploymentLink>();

    if (!isCrossReferencing(element_p)) {
      try {
        markAsCrossReferenced(element_p);

        TransactionalEditingDomain editingDomain = MDEAdapterFactory.getEditingDomain(element_p);
        if ((editingDomain != null) && (editingDomain instanceof SemanticEditingDomain)) {
          Collection<Setting> references = ((SemanticEditingDomain) editingDomain).getDerivedCrossReferencer().getInverseReferences(element_p, true);

          for (EStructuralFeature.Setting setting : references) {
            if (CsPackage.Literals.ABSTRACT_DEPLOYMENT_LINK__DEPLOYED_ELEMENT.equals(setting.getEStructuralFeature())) {
              ret.add((AbstractDeploymentLink) setting.getEObject());
            }
          }
        }

      } finally {
        unmarkAsCrossReferenced(element_p);
      }
    }
    return ret;
  }
}
