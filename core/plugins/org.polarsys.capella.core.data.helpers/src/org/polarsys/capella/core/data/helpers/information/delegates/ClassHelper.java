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
package org.polarsys.capella.core.data.helpers.information.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.helpers.capellacore.delegates.GeneralClassHelper;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.InformationRealization;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

public class ClassHelper {

	private static ClassHelper instance;

  /**
   *
   */
	private ClassHelper() {
    // do nothing
	}

  /**
   *
   */
	public static ClassHelper getInstance(){
		if(instance == null)
			instance = new ClassHelper();
		return instance;
	}

  /**
   *
   */
	public Object doSwitch(Class element_p, EStructuralFeature feature_p) {
		Object ret = null;

		if (feature_p.equals(InformationPackage.Literals.CLASS__REALIZED_CLASSES)) {
      ret = getRealizedClasses(element_p);
    } else if (feature_p.equals(InformationPackage.Literals.CLASS__REALIZING_CLASSES)) {
      ret = getRealizingClasses(element_p);
    }

		// no helper found... searching in super classes...
		if(null == ret) {
			ret = GeneralClassHelper.getInstance().doSwitch(element_p, feature_p);
		}

		return ret;
	}

  /**
   *
   */
  protected List<Class> getRealizedClasses(Class element_p) {
    List <Class> ret = new ArrayList <Class>();
    for (AbstractTrace trace : element_p.getOutgoingTraces()) {
      if (trace instanceof InformationRealization) {
        TraceableElement elt = trace.getTargetElement();
        if (elt instanceof Class) {
          ret.add((Class) elt);
        }
      }
    }
    return ret;
  }

  /**
   *
   */
  protected List<Class> getRealizingClasses(Class element_p) {
    List <Class> ret = new ArrayList <Class>();
    for (AbstractTrace trace : element_p.getIncomingTraces()) {
      if (trace instanceof InformationRealization) {
        TraceableElement elt = trace.getSourceElement();
        if (elt instanceof Class) {
          ret.add((Class) elt);
        }
      }
    }
    return ret;
  }
}
