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
package org.polarsys.capella.core.data.helpers.capellacore;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.helpers.capellacore.delegates.AbstractPropertyValueHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.GeneralizableElementHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.InvolvedElementHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.InvolvementHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.InvolverElementHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.CapellaElementHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.NamedElementHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.NamespaceHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.PropertyValueGroupHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.RelationshipHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.StructureHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.TraceHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.TypeHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.TypedElementHelper;
import org.polarsys.capella.core.data.capellacore.AbstractPropertyValue;
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyLiteral;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyType;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.capellacore.InvolverElement;
import org.polarsys.capella.core.data.capellacore.KeyValue;
import org.polarsys.capella.core.data.capellacore.Namespace;
import org.polarsys.capella.core.data.capellacore.NamingRule;
import org.polarsys.capella.core.data.capellacore.PropertyValueGroup;
import org.polarsys.capella.core.data.capellacore.PropertyValuePkg;
import org.polarsys.capella.core.data.capellacore.Relationship;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.capellacore.Trace;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.data.capellacore.TypedElement;
import org.polarsys.capella.common.tig.model.HelperNotFoundException;
import org.polarsys.capella.common.tig.model.IHelper;

public class CapellaCoreHelper implements IHelper {

	/**
	 * @see org.polarsys.capella.common.tig.model.IHelper#getValue(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EStructuralFeature, org.eclipse.emf.ecore.EAnnotation)
	 */
  public Object getValue(EObject object_p, EStructuralFeature feature_p, EAnnotation annotation_p) {
		Object ret = null;

		if (object_p instanceof TypedElement) {
			ret = TypedElementHelper.getInstance().doSwitch((TypedElement) object_p, feature_p);
		}
		else if (object_p instanceof Type) {
			ret = TypeHelper.getInstance().doSwitch((Type) object_p, feature_p);
		}
		else if (object_p instanceof GeneralizableElement) {
			ret = GeneralizableElementHelper.getInstance().doSwitch((GeneralizableElement) object_p, feature_p);
		}
		else if (object_p instanceof KeyValue) {
			ret = CapellaElementHelper.getInstance().doSwitch((KeyValue) object_p, feature_p);
		}
		else if (object_p instanceof Involvement) {
			ret = InvolvementHelper.getInstance().doSwitch((Involvement) object_p, feature_p);
		}
		else if (object_p instanceof InvolverElement) {
			ret = InvolverElementHelper.getInstance().doSwitch((InvolverElement) object_p, feature_p);
		}
		else if (object_p instanceof InvolvedElement) {
			ret = InvolvedElementHelper.getInstance().doSwitch((InvolvedElement) object_p, feature_p);
		}
		else if (object_p instanceof Relationship) {
			ret = RelationshipHelper.getInstance().doSwitch((Relationship) object_p, feature_p);
		}
		else if (object_p instanceof Structure) {
			ret = StructureHelper.getInstance().doSwitch((Structure) object_p, feature_p);
		}
		else if (object_p instanceof Trace) {
			ret = TraceHelper.getInstance().doSwitch((Trace) object_p, feature_p);
		}
    else if (object_p instanceof NamingRule) {
      ret = CapellaElementHelper.getInstance().doSwitch((NamingRule) object_p, feature_p);
    }
    else if (object_p instanceof AbstractPropertyValue) {
      ret = AbstractPropertyValueHelper.getInstance().doSwitch((AbstractPropertyValue) object_p, feature_p);
    }
    else if (object_p instanceof EnumerationPropertyType) {
      ret = NamedElementHelper.getInstance().doSwitch((EnumerationPropertyType) object_p, feature_p);
    }
    else if (object_p instanceof EnumerationPropertyLiteral) {
      ret = NamedElementHelper.getInstance().doSwitch((EnumerationPropertyLiteral) object_p, feature_p);
    }
    else if (object_p instanceof PropertyValueGroup) {
      ret = PropertyValueGroupHelper.getInstance().doSwitch((PropertyValueGroup) object_p, feature_p);
    }
    else if (object_p instanceof PropertyValuePkg) {
      ret = StructureHelper.getInstance().doSwitch((PropertyValuePkg) object_p, feature_p);
    }
    else if (object_p instanceof Constraint) {
      ret = CapellaElementHelper.getInstance().doSwitch((Constraint) object_p, feature_p);
    }
    else if (object_p instanceof Namespace) {
      ret = NamespaceHelper.getInstance().doSwitch((Namespace) object_p, feature_p);
    }

		if (null != ret || feature_p.getUpperBound() == 1)
			return ret;

		throw new HelperNotFoundException();
	}

}
