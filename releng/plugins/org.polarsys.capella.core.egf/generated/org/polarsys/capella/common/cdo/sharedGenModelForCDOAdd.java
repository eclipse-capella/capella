//Generated on Tue Jun 24 15:10:02 CEST 2014 with EGF 1.2.0.v20140623-0645
package org.polarsys.capella.common.cdo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.egf.model.pattern.Node;
import org.eclipse.egf.model.pattern.PatternContext;
import org.eclipse.egf.pattern.execution.InternalPatternContext;
import org.eclipse.egf.pattern.execution.OutputManager;
import org.eclipse.egf.pattern.execution.SuperOrchestrationContext;
import org.eclipse.egf.pattern.query.IQuery;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;

public class sharedGenModelForCDOAdd extends
		org.eclipse.egf.emf.pattern.model.cdo.genModelForCDOAdd {

	public sharedGenModelForCDOAdd() {
		//Here is the constructor
		// add initialisation of the pattern variables (declaration has been already done).
	}

	public void generate(Object argument) throws Exception {
		InternalPatternContext ctx = (InternalPatternContext) argument;
		IQuery.ParameterDescription paramDesc = null;
		Map<String, String> queryCtx = null;
		Node.Container currentNode = ctx.getNode();
		List<Object> genModelList = null;
		//this pattern can only be called by another (i.e. it's not an entry point in execution)

		for (Object genModelParameter : genModelList) {

			this.genModel = (org.eclipse.emf.codegen.ecore.genmodel.GenModel) genModelParameter;

			if (preCondition(ctx)) {
				ctx.setNode(new Node.Container(currentNode, getClass()));
				orchestration((PatternContext) argument);

			}
		}
		if (ctx.useReporter()) {
			ctx.getReporter().executionFinished(
					OutputManager.computeExecutionOutput(ctx), ctx);
		}
	}

	public String orchestration(PatternContext ctx) throws Exception {
		InternalPatternContext ictx = (InternalPatternContext) ctx;
		Node.Container currentNode = ictx.getNode();
		super.orchestration(new SuperOrchestrationContext(ictx));
		ictx.setNode(currentNode);
		if (ictx.useReporter()) {
			Map<String, Object> parameterValues = new HashMap<String, Object>();
			parameterValues.put("genModel", this.genModel);
			String outputWithCallBack = OutputManager.computeLoopOutput(ictx);
			String loop = OutputManager.computeLoopOutputWithoutCallback(ictx);
			ictx.getReporter().loopFinished(loop, outputWithCallBack, ictx,
					parameterValues);
		}
		return null;
	}

	protected void method_checkGenModel(final StringBuffer out,
			final PatternContext ctx) throws Exception {
		for (GenPackage genPackage : newGenModel.getUsedGenPackages()) {
			boolean useCDO = false;
			for (String pluginVariable : genPackage.getGenModel()
					.getModelPluginVariables()) {
				useCDO = useCDO || pluginVariable.startsWith("CDO");
			}
			if (!useCDO
					&& !"http://www.eclipse.org/emf/2002/Ecore"
							.equals(genPackage.getNSURI()))
				throw new IllegalStateException("genModel "
						+ EcoreUtil.getURI(genPackage.getGenModel())
								.trimFragment() + " is not generated for cdo");
		}

		InternalPatternContext ictx = (InternalPatternContext) ctx;
		new Node.DataLeaf(ictx.getNode(), getClass(), "checkGenModel",
				out.toString());
	}

	public Map<String, Object> getParameters() {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("genModel", this.genModel);
		return parameters;
	}

}
