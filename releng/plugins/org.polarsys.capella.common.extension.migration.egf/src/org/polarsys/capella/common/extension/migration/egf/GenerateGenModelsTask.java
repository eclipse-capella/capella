/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.extension.migration.egf;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.egf.core.producer.InvocationException;
import org.eclipse.egf.ftask.producer.context.ITaskProductionContext;
import org.eclipse.egf.ftask.producer.invocation.ITaskProduction;
import org.eclipse.emf.codegen.ecore.genmodel.GenJDKLevel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenResourceKind;
import org.eclipse.emf.common.util.BasicMonitor;

/**
 * 
 */
public class GenerateGenModelsTask implements ITaskProduction {

	public void doExecute(ITaskProductionContext productionContext,
			IProgressMonitor monitor) throws InvocationException {
		List<GenModel> genModels = new ArrayList<GenModel>();
		
		generateGenModel(genModels, "/org.polarsys.capella.common.data.def/model/ModellingCore.ecore", "org.polarsys.capella.common.data.core.gen", "org.polarsys.capella.common.data");
		generateGenModel(genModels, "/org.polarsys.capella.common.data.def/model/Behavior.ecore", "org.polarsys.capella.common.data.behavior.gen", "org.polarsys.capella.common.data");
		generateGenModel(genModels, "/org.polarsys.capella.common.data.def/model/Activity.ecore", "org.polarsys.capella.common.data.activity.gen", "org.polarsys.capella.common.data");
		generateGenModel(genModels, "/org.polarsys.capella.core.data.def/model/CapellaModeller.ecore", "org.polarsys.capella.core.data.gen", "org.polarsys.capella.core.data");
		generateGenModel(genModels, "/org.polarsys.capella.common.re.gen/model/re.ecore", "org.polarsys.capella.common.re.gen", "org.polarsys.capella.common");
		generateGenModel(genModels, "/org.polarsys.capella.common.libraries.gen/model/libraries.ecore", "org.polarsys.capella.common.libraries.gen", "org.polarsys.capella.common");
		
	}

	private void generateGenModel(final List<GenModel> genModels_p, String ecorePath_p, String pluginId_p, String basePackage_p) {
		EcoreGenModelGenerator genModelGenerator = new EcoreGenModelGenerator() {
			@Override
			protected void addExternalGenModels(List<GenModel> genModelsP) {
				super.addExternalGenModels(genModelsP);
				genModelsP.addAll(genModels_p);
			}
			
			public void setGenModelParameters(GenModel genModel_p) {
				super.setGenModelParameters(genModel_p);
				genModel_p.setCopyrightText(" Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.\n All rights reserved. This program and the accompanying materials\n are made available under the terms of the Eclipse Public License v1.0\n which accompanies this distribution, and is available at\n http://www.eclipse.org/legal/epl-v10.html\n\n Contributors:\n    Thales - initial API and implementation");
				
				//Add a customization for set melody extension (to be discussed)
				if ("CapellaModeller".equals(genModel_p.getModelName())) {
					for (GenPackage pack:genModel_p.getGenPackages()) {
						if ("Capellamodeller".equals(pack.getPrefix())) {
							pack.setFileExtensions("melodymodeller");
						}
					}
				}
			}
		};
		
		genModelGenerator.setInputPath(new Path(ecorePath_p));
		genModelGenerator.setPluginId(pluginId_p);
		genModelGenerator.setBasePackagePrefix(basePackage_p);
		genModelGenerator.setModelDirectory("generated");
	     
		genModelGenerator.setJdkComplianceLevel(GenJDKLevel.JDK50_LITERAL);
		genModelGenerator.setResourceType(GenResourceKind.XMI_LITERAL);
		
		genModels_p.add(genModelGenerator.execute(new BasicMonitor()));
	}

	public void postExecute(ITaskProductionContext productionContext,
			IProgressMonitor monitor) throws InvocationException {
	}

	public void preExecute(ITaskProductionContext productionContext,
			IProgressMonitor monitor) throws InvocationException {
	}
}
