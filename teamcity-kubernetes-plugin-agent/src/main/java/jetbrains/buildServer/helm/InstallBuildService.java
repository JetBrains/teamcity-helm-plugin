package jetbrains.buildServer.helm;

import jetbrains.buildServer.RunBuildException;
import jetbrains.buildServer.agent.runner.BuildServiceAdapter;
import jetbrains.buildServer.agent.runner.ProgramCommandLine;
import jetbrains.buildServer.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static jetbrains.buildServer.helm.HelmConstants.*;

/**
 * Created by Evgeniy Koshkin (evgeniy.koshkin@jetbrains.com) on 18.10.17.
 */
public class InstallBuildService extends BuildServiceAdapter {
    @NotNull
    @Override
    public ProgramCommandLine makeProgramCommandLine() throws RunBuildException {
        return new ProgramCommandLine() {
            @NotNull
            @Override
            public String getExecutablePath() throws RunBuildException {
                return HELM_EXEC_NAME;
            }

            @NotNull
            @Override
            public String getWorkingDirectory() throws RunBuildException {
                return getRunnerContext().getBuild().getCheckoutDirectory().getAbsolutePath();
            }

            @NotNull
            @Override
            public List<String> getArguments() throws RunBuildException {
                Map<String, String> runnerParameters = getRunnerParameters();
                List<String> result = new LinkedList<String>();
                result.add(HELM_INSTALL_COMMAND);
                result.add(StringUtil.emptyIfNull(runnerParameters.get(CHART)));
                result.add(StringUtil.emptyIfNull(runnerParameters.get(ADDITIONAL_FLAGS)));
                return result;
            }

            @NotNull
            @Override
            public Map<String, String> getEnvironment() throws RunBuildException {
                return getRunnerContext().getBuildParameters().getEnvironmentVariables();
            }
        };
    }
}