//Generated with EGF 5.0.0.v20161010-1511
package org.polarsys.capella.core.releng;

import org.eclipse.egf.common.helper.*;
import java.util.*;
import org.eclipse.emf.ecore.*;
import org.eclipse.egf.model.pattern.*;
import org.eclipse.egf.pattern.execution.*;
import org.eclipse.egf.pattern.query.*;

public class buildproperties extends org.eclipse.egf.portfolio.eclipse.build.buckminster.buildproperties {
  protected static String nl;

  public static synchronized buildproperties create(String lineSeparator) {
    nl = lineSeparator;
    buildproperties result = new buildproperties();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + "#site servers" + NL + "eclipse.download.prefix=http://download.eclipse.org" + NL
      + "cloudsmith.download.prefix=http://download.cloudsmith.com" + NL
      + "polarion.download.prefix=http://community.polarion.com" + NL + "" + NL + "#director" + NL
      + "director.url=${eclipse.download.prefix}/tools/buckminster/products/director_latest.zip" + NL + "" + NL
      + "#buckminster installation" + NL + "buckminster.release=4.2" + NL
      + "bm.headless.site=${eclipse.download.prefix}/tools/buckminster/headless-${buckminster.release}" + NL
      + "bm.external.site=${cloudsmith.download.prefix}/buckminster/external-${buckminster.release}" + NL
      + "polarion.site=${polarion.download.prefix}/projects/subversive/download/eclipse/2.0/update-site/" + NL + "" + NL
      + "#egf installation" + NL + "egf.release=helios" + NL
      + "egf.site=${eclipse.download.prefix}/egf/updates/${egf.release}/official/" + NL
      + "egf.eclipse.site=${eclipse.download.prefix}/releases/${egf.release}" + NL + "" + NL + "#buckminster build" + NL
      + "buckminster.loglevel=INFO" + NL + "buckminster.output.root=${result}/output" + NL
      + "buckminster.temp.root=${result}/temp" + NL + "" + NL + "#buckminster qualifier" + NL
      + "qualifier.replacement.*=generator:buildTimestamp" + NL + "generator.buildTimestamp.format='v'yyyyMMdd-HHmm"
      + NL + "" + NL + "#buckminster signing" + NL + "signing.type=eclipse.local" + NL
      + "eclipse.staging.area=${result}/signing" + NL + "site.pack200=true" + NL + "site.retain.unpacked=true" + NL + ""
      + NL + "#buckminster : do not generate version range in manifest.mf and content.jar" + NL
      + "pde.bundle.range.generation=false" + NL;
  protected final String TEXT_2 = NL;
  protected final String TEXT_3 = NL;

  public buildproperties() {
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

    List<Object> jobList = null;
    //this pattern can only be called by another (i.e. it's not an entry point in execution)

    for (Object jobParameter : jobList) {

      this.job = (org.eclipse.egf.portfolio.eclipse.build.buildcore.Job) jobParameter;

      if (preCondition(ctx)) {
        ctx.setNode(new Node.Container(currentNode, getClass()));
        orchestration(ctx);
      }

    }
    ctx.setNode(currentNode);
    if (ctx.useReporter()) {
      ctx.getReporter().executionFinished(OutputManager.computeExecutionOutput(ctx), ctx);
    }

    stringBuffer.append(TEXT_2);
    stringBuffer.append(TEXT_3);
    return stringBuffer.toString();
  }

  public String orchestration(PatternContext ctx) throws Exception {
    InternalPatternContext ictx = (InternalPatternContext) ctx;

    super.orchestration(new SuperOrchestrationContext(ictx));

    if (ictx.useReporter()) {
      Map<String, Object> parameterValues = new HashMap<String, Object>();
      parameterValues.put("job", this.job);
      String outputWithCallBack = OutputManager.computeLoopOutput(ictx);
      String loop = OutputManager.computeLoopOutputWithoutCallback(ictx);
      ictx.getReporter().loopFinished(loop, outputWithCallBack, ictx, parameterValues);
    }
    return null;
  }

  public Map<String, Object> getParameters() {
    final Map<String, Object> parameters = new HashMap<String, Object>();
    parameters.put("job", this.job);
    return parameters;
  }

  protected void method_body(final StringBuffer stringBuffer, final PatternContext ctx) throws Exception {

    {
      //<%@ egf:patternCall
      //	patternId="platform:/plugin/org.eclipse.egf.portfolio.eclipse.build/egf/Build.fcore#LogicalName=org.eclipse.egf.portfolio.eclipse.build.TextHeader"
      //%>

      InternalPatternContext ictx = (InternalPatternContext) ctx;
      new Node.DataLeaf(ictx.getNode(), getClass(), null, stringBuffer.toString());
      stringBuffer.setLength(0);

      final Map<String, Object> callParameters = new HashMap<String, Object>();
      CallHelper.executeWithParameterInjection(
          "platform:/plugin/org.eclipse.egf.portfolio.eclipse.build/egf/Build.fcore#_xJwAgJ5OEd-3wvN5SnesGA",
          new ExecutionContext((InternalPatternContext) ctx), callParameters);
      stringBuffer.setLength(0);
    }

    stringBuffer.append(TEXT_1);
    InternalPatternContext ictx = (InternalPatternContext) ctx;
    new Node.DataLeaf(ictx.getNode(), getClass(), "body", stringBuffer.toString());
  }
}