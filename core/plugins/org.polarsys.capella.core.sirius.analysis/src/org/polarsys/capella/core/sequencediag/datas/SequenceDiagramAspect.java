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
package org.polarsys.capella.core.sequencediag.datas;

import org.eclipse.sirius.business.api.featureextensions.AbstractFeatureExtensionServices;
import org.eclipse.sirius.viewpoint.DFeatureExtension;
import org.eclipse.sirius.viewpoint.description.FeatureExtensionDescription;

public class SequenceDiagramAspect extends AbstractFeatureExtensionServices {
	private Class<? extends DFeatureExtension> _featureExtension = SequenceLabelFeatureExtension.class;
	private Class<? extends FeatureExtensionDescription> _featureExtensionDescription = SequenceLabelFeatureExtensionDescription.class;

	@Override
	protected Class<? extends FeatureExtensionDescription> getFeatureExtensionDescriptionClass() {
		return _featureExtensionDescription;
	}

	@Override
	protected Class<? extends DFeatureExtension> getFeatureExtensionClass() {
		return _featureExtension;
	}

}
