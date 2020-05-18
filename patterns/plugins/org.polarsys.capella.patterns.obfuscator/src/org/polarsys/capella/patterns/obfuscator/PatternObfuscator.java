/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.patterns.obfuscator;

import java.io.IOException;
import java.util.HashMap;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.diffmerge.patterns.core.gen.corepatterns.AbstractDescribedElement;
import org.eclipse.emf.diffmerge.patterns.core.gen.corepatterns.AbstractNamedElement;
import org.eclipse.emf.diffmerge.patterns.templates.gen.templatepatterns.TemplatePattern;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.polarsys.capella.core.model.obfuscator.IResourceObfuscator;
import org.polarsys.capella.core.model.obfuscator.ObfuscatorHelper;

public class PatternObfuscator implements IResourceObfuscator {

	public boolean isApplicableOn(Resource ressource_p) {
		if (ressource_p.getURI().fileExtension() != null)
			return ressource_p.getURI().fileExtension().equals("patterns"); //$NON-NLS-1$
		return false;
	}

	public void obfuscate(Resource ressource_p) {
		TreeIterator<EObject> it = ressource_p.getAllContents();
		while (it.hasNext()) {
			EObject obj = it.next();
			ObfuscatorHelper.obfuscateCapellaElement(obj);
			obfuscatePatternElement(obj);
		}
		try {
			ressource_p.save(new HashMap<Object, Object>());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void obfuscatePatternElement(EObject obj) {
		if (obj instanceof TemplatePattern) {
			TemplatePattern tp = (TemplatePattern) obj;
			tp.setImage(null);			
		}
		if (obj instanceof AbstractDescribedElement) {
			AbstractDescribedElement ade = (AbstractDescribedElement) obj;
			ade.setDescription(ObfuscatorHelper.generateUnreadableString(ade.getDescription()));
		}
		if (obj instanceof AbstractNamedElement) {
			AbstractNamedElement ane = (AbstractNamedElement) obj;
			ane.setName(ObfuscatorHelper.generateUnreadableString(ane.getName()));
		}
	}
}
