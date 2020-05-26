//Generated with EGF 1.4.2.v20161010-1511
package org.polarsys.capella.core.releng;

import org.eclipse.egf.common.helper.*;
import java.util.*;
import org.eclipse.emf.ecore.*;
import org.eclipse.egf.model.pattern.*;
import org.eclipse.egf.pattern.execution.*;
import org.eclipse.egf.pattern.query.*;
import org.eclipse.egf.portfolio.eclipse.build.*;
import org.eclipse.egf.portfolio.eclipse.build.buildcore.*;

public class buildxml_stepContainer
    extends org.eclipse.egf.portfolio.eclipse.build.buckminster.additions.buildxmlstepContainer_steps {
  protected static String nl;

  public static synchronized buildxml_stepContainer create(String lineSeparator) {
    nl = lineSeparator;
    buildxml_stepContainer result = new buildxml_stepContainer();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t<target name=\"";
  protected final String TEXT_2 = "\"";
  protected final String TEXT_3 = NL;
  protected final String TEXT_4 = NL;
  protected final String TEXT_5 = NL;

  public buildxml_stepContainer() {
    //Here is the constructor
    StringBuffer stringBuffer = new StringBuffer();

    // add initialisation of the pattern variables (declaration has been already done).

  }

  public String generate(Object argument) throws Exception {
    final StringBuffer stringBuffer = new StringBuffer();

    InternalPatternContext ctx = (InternalPatternContext) argument;
    Map<String, String> queryCtx = null;
    IQuery.ParameterDescription paramDesc = null;
    Node.Container currentNode = ctx.getNode();

    paramDesc = new IQuery.ParameterDescription("stepContainer",
        "http://www.eclipse.org/egf/1.0.1/buildcore#//StepContainer");
    queryCtx = new HashMap<String, String>();
    List<Object> stepContainerList = QueryHelper.load(ctx, "org.eclipse.egf.pattern.query.EObjectInjectedContextQuery")
        .execute(paramDesc, queryCtx, ctx);

    for (Object stepContainerParameter : stepContainerList) {

      this.stepContainer = (org.eclipse.egf.portfolio.eclipse.build.buildcore.StepContainer) stepContainerParameter;

      if (preCondition(ctx)) {
        ctx.setNode(new Node.Container(currentNode, getClass()));
        orchestration(ctx);
      }

    }
    ctx.setNode(currentNode);
    if (ctx.useReporter()) {
      ctx.getReporter().executionFinished(OutputManager.computeExecutionOutput(ctx), ctx);
    }

    stringBuffer.append(TEXT_4);
    stringBuffer.append(TEXT_5);
    return stringBuffer.toString();
  }

  public String orchestration(PatternContext ctx) throws Exception {
    InternalPatternContext ictx = (InternalPatternContext) ctx;

    super.orchestration(new SuperOrchestrationContext(ictx));

    if (ictx.useReporter()) {
      Map<String, Object> parameterValues = new HashMap<String, Object>();
      parameterValues.put("stepContainer", this.stepContainer);
      String outputWithCallBack = OutputManager.computeLoopOutput(ictx);
      String loop = OutputManager.computeLoopOutputWithoutCallback(ictx);
      ictx.getReporter().loopFinished(loop, outputWithCallBack, ictx, parameterValues);
    }
    return null;
  }

  public Map<String, Object> getParameters() {
    final Map<String, Object> parameters = new HashMap<String, Object>();
    parameters.put("stepContainer", this.stepContainer);
    return parameters;
  }

  protected void method_body(final StringBuffer stringBuffer, final PatternContext ctx) throws Exception {

    String desc = stepContainer.getDescription();
    StringBuilder steps = new StringBuilder();

    if (desc != null && desc.startsWith("unless_")) {
      steps.append(" unless=\"" + desc.replace("unless_", "") + "\"");

      steps.append(">\n");

      for (Step step : stepContainer.getSteps()) {
        steps.append("\t\t<antcall target=\"" + new GenerationHelper().getNameOrGeneratedIdString(step)
            + "\" inheritAll=\"true\" />\n");
      }
      steps.append("\t</target>");
    } else {
      String sep = "";
      steps.append(" depends=\"");
      for (Step step : stepContainer.getSteps()) {
        steps.append(sep);
        steps.append(new GenerationHelper().getNameOrGeneratedIdString(step));
        sep = ",";
      }
      steps.append("\" />");
    }

    stringBuffer.append(TEXT_1);
    stringBuffer.append(new GenerationHelper().getNameOrGeneratedIdString(stepContainer));
    stringBuffer.append(TEXT_2);
    stringBuffer.append(steps.toString());
    stringBuffer.append(TEXT_3);
    InternalPatternContext ictx = (InternalPatternContext) ctx;
    new Node.DataLeaf(ictx.getNode(), getClass(), "body", stringBuffer.toString());
  }
}