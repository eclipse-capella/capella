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
package org.polarsys.capella.core.data.helpers.capellacommon.delegates;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.NamedElementHelper;
import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.capellacommon.AbstractStateRealization;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.Region;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.platform.sirius.tig.ef.SemanticEditingDomainFactory.SemanticEditingDomain;

public class AbstractStateHelper {
	private static AbstractStateHelper instance;
  /**
   * Cross referencing re-entrance collection for involver.
   */
  private List<AbstractState> _isCrossReferencingInvolvers;

	private AbstractStateHelper() {
    _isCrossReferencingInvolvers = new ArrayList<AbstractState>(0);
	}

	public static AbstractStateHelper getInstance(){
		if(instance == null)
			instance = new AbstractStateHelper();
		return instance;
	}

	public Object doSwitch(AbstractState element_p, EStructuralFeature feature_p){
		Object ret = null;

    if (feature_p.equals(CapellacommonPackage.Literals.ABSTRACT_STATE__REALIZED_ABSTRACT_STATES)) {
      ret = getRealizedAbstractStates(element_p);
    }
    else if (feature_p.equals(CapellacommonPackage.Literals.ABSTRACT_STATE__REALIZING_ABSTRACT_STATES)) {
      ret = getRealizingAbstractStates(element_p);
    }
    else if (feature_p.equals(CapellacommonPackage.Literals.ABSTRACT_STATE__INVOLVER_REGIONS)) {
      ret = getInvolverRegions(element_p);
    }

		// no helper found... searching in super classes...
		if (null == ret) {
		  ret = NamedElementHelper.getInstance().doSwitch(element_p, feature_p);
		}

		return ret;
	}

  protected List<AbstractState> getRealizedAbstractStates(AbstractState element_p) {
    List<AbstractState> result = new ArrayList<AbstractState>();
    for (AbstractTrace trace : element_p.getOutgoingTraces()) {
      if (trace instanceof AbstractStateRealization) {
        AbstractState state = ((AbstractStateRealization) trace).getRealizedAbstractState();
        if (null != state) {
          result.add(state);
        }
      }
    }
    return result;
  }

  protected List<AbstractState> getRealizingAbstractStates(AbstractState element_p) {
    List<AbstractState> result = new ArrayList<AbstractState>();
    for (AbstractTrace trace : element_p.getIncomingTraces()) {
      if (trace instanceof AbstractStateRealization) {
        AbstractState state = ((AbstractStateRealization) trace).getRealizingAbstractState();
        if (null != state) {
          result.add(state);
        }
      }
    }
    return result;
  }

  protected boolean isCrossReferencing(AbstractState element_p) {
    return _isCrossReferencingInvolvers.contains(element_p);
  }

  protected void markAsCrossReferenced(AbstractState element_p) {
    _isCrossReferencingInvolvers.add(element_p);
  }

  protected void unmarkAsCrossReferenced(AbstractState element_p) {
    _isCrossReferencingInvolvers.remove(element_p);
  }

  protected List<Region> getInvolverRegions(AbstractState element_p) {
    List<Region> ret = new ArrayList<Region>();

    if (!isCrossReferencing(element_p)) {
      try {
        markAsCrossReferenced(element_p);
        SemanticEditingDomain editingDomain = (SemanticEditingDomain) AdapterFactoryEditingDomain.getEditingDomainFor(element_p);
        if (editingDomain != null) {
          Collection<Setting> references = editingDomain.getDerivedCrossReferencer().getInverseReferences(element_p, true);
          for (EStructuralFeature.Setting setting : references) {
            if (CapellacommonPackage.Literals.REGION__INVOLVED_STATES.equals(setting.getEStructuralFeature())) {
              ret.add((Region) setting.getEObject());
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
