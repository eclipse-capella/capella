/**
 *
 *  Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 * 
 *  Contributors:
 *     Thales - initial API and implementation
 */

package org.polarsys.capella.core.data.cs;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.fa.AbstractFunctionalStructure;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component Pkg</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.cs.ComponentPkg#getOwnedParts <em>Owned Parts</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.cs.CsPackage#getComponentPkg()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='a package containing parts' constraints='none' comment/notes='none'"
 * @generated
 */

public interface ComponentPkg extends AbstractFunctionalStructure {





	/**
	 * Returns the value of the '<em><b>Owned Parts</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.cs.Part}.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Parts</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Parts</em>' reference list.
	 * @see org.polarsys.capella.core.data.cs.CsPackage#getComponentPkg_OwnedParts()
	 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the Parts included in this package' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
	 * @generated
	 */

	EList<Part> getOwnedParts();





} // ComponentPkg
