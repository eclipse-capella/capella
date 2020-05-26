//Generated with EGF 1.4.2.v20161010-1511
package org.polarsys.capella.core.releng;

import org.eclipse.egf.common.helper.*;
import java.util.*;
import org.eclipse.emf.ecore.*;
import org.eclipse.egf.model.pattern.*;
import org.eclipse.egf.pattern.execution.*;
import org.eclipse.egf.pattern.query.*;
import org.eclipse.egf.portfolio.eclipse.build.GenerationHelper;

public class rmaplocaladdorgpolarsyscapella
    extends org.eclipse.egf.portfolio.eclipse.build.buckminster.additions.buildrmaplocal {
  protected static String nl;

  public static synchronized rmaplocaladdorgpolarsyscapella create(String lineSeparator) {
    nl = lineSeparator;
    rmaplocaladdorgpolarsyscapella result = new rmaplocaladdorgpolarsyscapella();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t<locator searchPathRef=\"";
  protected final String TEXT_2 = "\" ";
  protected final String TEXT_3 = " failOnError=\"false\" />" + NL + "\t<searchPath name=\"";
  protected final String TEXT_4 = "\">" + NL
      + "\t\t<provider componentTypes=\"eclipse.feature,osgi.bundle\" readerType=\"url.catalog\" mutable=\"true\" source=\"true\">"
      + NL + "\t\t\t<uri format=\"file:/";
  protected final String TEXT_5 = "/{0}";
  protected final String TEXT_6 = "\">" + NL + "\t\t\t\t<bc:propertyRef key=\"buckminster.component\" />" + NL
      + "\t\t\t</uri>" + NL + "\t\t</provider>" + NL + "" + NL
      + "\t\t<provider componentTypes=\"eclipse.feature,osgi.bundle\" readerType=\"url.catalog\" mutable=\"true\" source=\"true\">"
      + NL + "\t\t\t<uri format=\"file:/";
  protected final String TEXT_7 = "/{0}";
  protected final String TEXT_8 = "\">" + NL + "\t\t\t\t<bc:replace>" + NL
      + "\t\t\t\t\t<bc:propertyRef key=\"buckminster.component\" />" + NL
      + "\t\t\t\t\t<bc:match pattern=\"^org\\.polarsys\\.capella\\.(.*)$\" replacement=\"$1\" />" + NL
      + "\t\t\t\t</bc:replace>" + NL + "\t\t\t</uri>" + NL + "\t\t</provider>" + NL + "\t</searchPath>" + NL + "\t";
  protected final String TEXT_9 = NL;
  protected final String TEXT_10 = NL;

  public rmaplocaladdorgpolarsyscapella() {
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

    paramDesc = new IQuery.ParameterDescription("localBuildLocation",
        "http://www.eclipse.org/egf/1.0.2/buildstep#//LocalBuildLocation");
    queryCtx = new HashMap<String, String>();
    List<Object> localBuildLocationList = QueryHelper
        .load(ctx, "org.eclipse.egf.pattern.query.EObjectInjectedContextQuery").execute(paramDesc, queryCtx, ctx);

    for (Object localBuildLocationParameter : localBuildLocationList) {

      this.localBuildLocation = (org.eclipse.egf.portfolio.eclipse.build.buildstep.LocalBuildLocation) localBuildLocationParameter;

      if (preCondition(ctx)) {
        ctx.setNode(new Node.Container(currentNode, getClass()));
        orchestration(ctx);
      }

    }
    ctx.setNode(currentNode);
    if (ctx.useReporter()) {
      ctx.getReporter().executionFinished(OutputManager.computeExecutionOutput(ctx), ctx);
    }

    stringBuffer.append(TEXT_9);
    stringBuffer.append(TEXT_10);
    return stringBuffer.toString();
  }

  public String orchestration(PatternContext ctx) throws Exception {
    InternalPatternContext ictx = (InternalPatternContext) ctx;

    super.orchestration(new SuperOrchestrationContext(ictx));

    if (ictx.useReporter()) {
      Map<String, Object> parameterValues = new HashMap<String, Object>();
      parameterValues.put("localBuildLocation", this.localBuildLocation);
      String outputWithCallBack = OutputManager.computeLoopOutput(ictx);
      String loop = OutputManager.computeLoopOutputWithoutCallback(ictx);
      ictx.getReporter().loopFinished(loop, outputWithCallBack, ictx, parameterValues);
    }
    return null;
  }

  public Map<String, Object> getParameters() {
    final Map<String, Object> parameters = new HashMap<String, Object>();
    parameters.put("localBuildLocation", this.localBuildLocation);
    return parameters;
  }

  protected void method_body(final StringBuffer stringBuffer, final PatternContext ctx) throws Exception {

    String id = new GenerationHelper().getNameOrGeneratedIdString(localBuildLocation);
    stringBuffer.append(TEXT_1);
    stringBuffer.append(id);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(new GenerationHelper().getPatternString(localBuildLocation));
    stringBuffer.append(TEXT_3);
    stringBuffer.append(id);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(localBuildLocation.getPath());
    stringBuffer.append(TEXT_5);
    stringBuffer.append(new GenerationHelper().getStringIfNotNull(localBuildLocation.getSuffix()));
    stringBuffer.append(TEXT_6);
    stringBuffer.append(localBuildLocation.getPath());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(new GenerationHelper().getStringIfNotNull(localBuildLocation.getSuffix()));
    stringBuffer.append(TEXT_8);
    InternalPatternContext ictx = (InternalPatternContext) ctx;
    new Node.DataLeaf(ictx.getNode(), getClass(), "body", stringBuffer.toString());
  }
}